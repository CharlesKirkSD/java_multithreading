package waitAndNotify;

import java.util.Scanner;

public class Processor {
	// The produce and consume methods must share the same lock.
	
	public void produce() throws InterruptedException {
		synchronized(this) {
			System.out.println("Producer running ... ");
			wait(); // Inherited from the Object class. Hands over the lock that the synchronized block owns.
			System.out.println("Resumed .");
		}
	}
	
	public void consume() throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized(this) {
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify(); // Notify does not relinquish control of the lock, control of the lock is relinquished when the process exits the synchronized block.
		}
		
		scanner.close();
	}
}
