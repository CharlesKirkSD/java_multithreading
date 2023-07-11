package callableAndFuture;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
Future, Callable and the return type of the @Override call() method need to be specified as Future and Callable are parameterised objects.
To use an instance of Future without a result specify wildcard as the type for the Futures<?> and the type of Callable and return type of @Override call() method as Void.
 */

public class App {
	
	public static void main(String args[]) {
		
		System.out.println("S01_T13 Callable and Future");
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<Integer> future = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				
				Random random = new Random();
				int duration = random.nextInt(1000);
				if (duration > 500) {
					throw new IOException("Sleeping for too long");
				}
				
				System.out.println("Starting ...");
				
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Finished.");
				
				return duration;
			}
		});
		
		executor.shutdown();
		
		Integer result = 0;
		try {
			result = future.get(); // Blocks until the thread has terminated.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println(e);
			System.out.println(e.getMessage());
			
			// Alternatively get the original exception back
			IOException eIO = (IOException) e.getCause();
			System.out.println(eIO.getMessage());
		}
		
		System.out.println("Thread sleep duration: " + result);
	}
}