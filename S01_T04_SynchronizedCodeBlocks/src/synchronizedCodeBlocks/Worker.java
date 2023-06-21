package synchronizedCodeBlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	
	private int iterations = 1000;
	Random random = new Random();
	
	// Create a lock for each thread that will be created. Object contains an intrinsic lock, so creating an instance of the Object class will create a lock.
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	
	public void StageOne() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Synchronized code block
		synchronized(lock1) {			
			list1.add(random.nextInt(100));
		}
	}

	public void StageTwo() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Synchronized code block
		synchronized(lock2) {	
			list2.add(random.nextInt(100));
		}
	}
	
	public void process() {
		for (int i = 0; i < iterations; i++) {
			StageOne();
			StageTwo();
		}
	}
	
	void main() {
		System.out.println("Starting ...");
		
		long startTime = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				process();
			}
		});
		
		// Start the threads.
		// Both will try to run both stages in sequence, because one will be locked the first thread will run StageOne and the second thread will run StageTow
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		long duration = endTime - startTime;
		
		System.out.println("Time taken " + duration);
		System.out.println("List1 length: " + list1.size());
		System.out.println("List2 length: " + list2.size());
	}
}
