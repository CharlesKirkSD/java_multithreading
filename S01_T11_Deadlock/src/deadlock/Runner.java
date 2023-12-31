package deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private Random random = new Random(); 
	private final int loopIterations = 10000;
	
	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
		
		while (true) {
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			// Acquire Locks
			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}
			finally {
				// If both locks have been acquired return, otherwise unlock locks that have been acquired to allow the process to try again.
				if (gotFirstLock && gotSecondLock) {
					return;
				}
				if (gotFirstLock) {
					firstLock.unlock();
				}
				if (gotSecondLock) {
					secondLock.unlock();
				}
			}
			
			// Lock not acquired. Sleep for a short period and try again.
			Thread.sleep(1); 
		}
	}
	
	public void firstThread() throws InterruptedException {
		
		for (int i = 0; i < loopIterations; i++) {
			acquireLocks(lock1, lock2);
			
			try {
				Account.transfer(acc1, acc2, random.nextInt(100));				
			}
			finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
		
	}
	
	public void secondThread() throws InterruptedException {
		
		for (int i = 0; i < loopIterations; i++) {
			acquireLocks(lock2, lock1);
			
			try {
				Account.transfer(acc2, acc1, random.nextInt(100));				
			}
			finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	
	public void finished() {
		System.out.println("Account 1 balance:" + acc1.getBalance());
		System.out.println("Account 2 balance:" + acc2.getBalance());
		System.out.println("Total balance:" + (acc1.getBalance() + acc2.getBalance()));
	}
	
}
