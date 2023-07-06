package semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
Semaphore : The count, called the number of permits, is passed in the constructor.
Semaphore.release() increments the number of permits.
Semaphore.acquire() increments the number of permits, it waits if no permits are available, so it acts as a lock.
 */
public class App {
	
	public static void main(String args []) throws InterruptedException{
		
		System.out.println("S01_T12 Semaphores");

		// Connection.getInstance().connect();
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		int maxThreadCount = 200;
		for (int i = 0; i < maxThreadCount; i++) {
			executor.submit(new Runnable() {
				public void run() {
					 Connection.getInstance().connect();
				}
			});
		}
		
		executor.shutdown();
		
		executor.awaitTermination(1, TimeUnit.DAYS);
		
	}
	
	public void semaphoreMethodsDemo() throws InterruptedException {
		
		Semaphore sem = new Semaphore(2);
		
		System.out.println("Semaphore initial number of available permits: " + sem.availablePermits());
		
		sem.release(2);
		sem.acquire();
		
		System.out.println("Semaphore current number of available permits: " + sem.availablePermits());

	}
}