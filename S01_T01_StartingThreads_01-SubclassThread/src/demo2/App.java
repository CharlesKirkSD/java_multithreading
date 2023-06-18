// There are two main ways of creating threads.
// 1. Create a class that extends the class Thread, demonstrated in package demo1;
// 2. Create a class that implements the interface Runnable, demonstrated in package demo2.

package demo2;

class Runner implements Runnable {
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
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}


public class App {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runner(1));
		t1.start();
		
		Thread t2 = new Thread(new Runner(2));
		t2.start();
	}
}