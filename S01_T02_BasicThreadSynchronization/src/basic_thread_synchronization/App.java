package basic_thread_synchronization;

import java.util.Scanner;

class Processor extends Thread {
	private volatile boolean running = true; // Prevents caching of an instance data member by Java.
	private int threadNumber = 0;
	
	public Processor() {
		running = true;
	}
	
	public Processor(int threadNumber) {
		this.threadNumber = threadNumber;
	}
	
	public void run() {
		while (running) {
			System.out.println("Hello from thread " + threadNumber);
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running = false;
	}
}

public class App {
	public static void main(String args []) {
		System.out.println("Hellow World from S04_T02_BasicThreadSynchronization");
	
		Processor processor1 = new Processor(1);
		processor1.start();
		
		Processor processor2 = new Processor(2);
		processor2.start();
		
		System.out.println("Press Return to stop");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine(); // Execution pauses here until a carriage return is detected by the scanner.
		
		processor1.shutdown();
		processor2.shutdown();
		
		scanner.close();
	}
}
