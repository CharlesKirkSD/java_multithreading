package countDownLatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable {
	private CountDownLatch latch;
	private int threadID;

	Processor(CountDownLatch latch, int threadId) {
		this.latch = latch;
		this.threadID = threadId;
	}
	
	@Override
	public void run() {
		System.out.println("Started Processor" + " Task id: " + threadID);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown();
	}
}


public class App {
	public static void main(String [] args) {
		System.out.println("S01_T06_CountDownLatches");
		
		CountDownLatch latch = new CountDownLatch(3);
		
		int numThreads = 3;
		int numTasks = 6;
		
		ExecutorService executor = Executors.newFixedThreadPool(numThreads);
		
		for (int i = 0; i < numTasks; i++) {
			executor.submit(new Processor(latch, i));
		}
		
		try {
			latch.await(); // Wait until the CountDownLatch has counted down to zero.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed");
	}
}