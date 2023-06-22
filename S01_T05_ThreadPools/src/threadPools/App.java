package threadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
	private int id;
	
	public Processor(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		System.out.println("Staring :" + id);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed :" + id);
	}
	
}

public class App {
	
	public static void main(String args[]) {
		
		System.out.println("S01_T05_ThreadPools");
		
		// Create a Thread Pool, named executor, from the ExecutorServices class
		ExecutorService executor = Executors.newFixedThreadPool(4);
		
		int numberOfTasks = 8;
		
		// Submit jobs to the Thread Pool.
		for (int i = 0; i < numberOfTasks; i++) {
			executor.submit(new Processor(i));
		}
		
		// Initiate an orderly shutdown where all submitted tasks are executed, but no new tasks will be accepted
		executor.shutdown();
		
		System.out.println("All tasks submitted.");
		
		// Wait until the tasks have completed, with a timeout in case they take too long.
		try {
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed");
	}
}