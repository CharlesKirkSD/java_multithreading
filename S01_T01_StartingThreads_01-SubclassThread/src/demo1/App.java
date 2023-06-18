// There are two main ways of creating threads.
// 1. Create a class that extends the class Thread, demonstrated in package demo1;
// 2. Create a class that implements the interface Runnable, demonstrated in package demo2.

package demo1;

class Runner extends Thread {
	private int threadNumber = 0;
	
	public Runner() {}
	
	public Runner(int threadNumber) {
		this.threadNumber = threadNumber;
	}

	@Override
	public void run() {
		int iterations = 10;
		for (int i = 0; i < iterations; i++) {
			System.out.println("Iteration " + i + " in Thread" + threadNumber);
			
			try {
				sleep(100); // The static method Thread.sleep() could be used instead calling sleep on this.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		super.run();
	}
	
}



public class App {

	public static void main(String[] args) {
		// To run the thread call start. Calling run would result in the method being executed in the main thread.
		Runner runner1 = new Runner(1);
		runner1.start();
		
		Runner runner2 = new Runner(2);
		runner2.start();
	}
}
