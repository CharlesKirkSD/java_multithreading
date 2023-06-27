package processorConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class App {
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String [] args) {
		System.out.println("S01_T07 Producer-Consumer");
	
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
	}
	
	private static void producer() throws InterruptedException {
		Random random = new Random();
		
		while (true) {
			// Add random Integers to the queue
			queue.put(random.nextInt(100)); // put will wait if the queue is full.
		}
	}
	
	private static void consumer() throws InterruptedException {
		Random random = new Random();
		
		// Simulate a consumer process that randomly takes an item from the queue.
		// Randomly take a value from the queue, output it to the console and report the size of the queue.
		while (true) {
			Thread.sleep(100);
			
			if (random.nextInt(10) ==0) {
				Integer value = queue.take(); // take() will wait until something is available.
				
				System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
			}
		}
	}
}