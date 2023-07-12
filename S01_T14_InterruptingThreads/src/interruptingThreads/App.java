package interruptingThreads;

import java.util.Random;
import java.util.Timer;

/**
The interrupt method sets an internal flag, so it only works if the thread explicitly checks isInterrupted.
Thread.currentThread() gets the current thread object which may then be used to call isInterrupted().
Thread pools have cancel methods.
 */


public class App {
	
	public static void main(String [] args) throws InterruptedException {
		
		System.out.println("S01_T14 Interrupting Threads");
		
		System.out.println("Thread started...");
		
		long startTime = 0L;
		long finishTime = 0L;
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				// First example
				// Add comment block to stop this section of the code from running
				Random random = new Random();
				for (int i = 0; i < 2E8; i++) {
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted");
						break;
					}
					Math.sin(random.nextDouble());
				}
				
				// Second example
				/* // Remove comment block to run this section of the code
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println("Interrupted!");
				}
				*/
			}
		});
		
		t1.start();
		startTime = System.currentTimeMillis();
		
		Thread.sleep(500);
		// t1.interrupt();
		
		
		t1.join();
		finishTime = System.currentTimeMillis();
		
		System.out.println("Thread finished.");
		System.out.println("Thread completed in: " + (finishTime - startTime) + " ms");
		
	}
}