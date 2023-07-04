package reentrantLock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
Re-entrant Locks are an alternative to using the Synchronised Keyword
 */

public class Runner {
	
	private int count;
	private final int loopSize = 10000;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment() {
		for (int i = 0; i < loopSize; i++) {
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException {
		lock.lock();
		System.out.println("Waiting");
		cond.await();
		System.out.println("Woken up!");
		
		try {
			increment();			
		}
		finally {
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException {
		Thread.sleep(1000);
		lock.lock();
		
		System.out.println("Press the return key!");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		System.out.println("Got return key");
		
		cond.signal(); // Wake up first thread
		
		try {
			increment();			
		}
		finally {
			lock.unlock();
			scanner.close();
		}
	}
	
	public void finished() {
		System.out.println("Count is: " + count);
	}
}
