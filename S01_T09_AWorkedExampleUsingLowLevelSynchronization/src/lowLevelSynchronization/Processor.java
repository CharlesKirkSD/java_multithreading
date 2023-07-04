package lowLevelSynchronization;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
The produce method adds items to the end of the list, the consume method removes items from the start of the list.
A lock is created by creating an instance of the Object class.
wait() and notify() need to be called on the object that the lock is declared on, in this case lock.
*/

public class Processor {
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object(); // Declare an object to lock on
	
	/**
	Adds items to the end of the LinkedList list until the list is full.
	*/
	public void produce() throws InterruptedException {
		
		int value = 0;
		
		while (true) {
			
			synchronized(lock) {
				// Only add items to the list if the list is not full.
				while (list.size() == LIMIT) {
					lock.wait();
				}
				
				list.add(value++);
				lock.notify();
			}
			Thread.sleep(500);
		}
	}
	
	/**
	Removes items from the end of the LinkedList list until the list is empty.
	*/
	public void consume() throws InterruptedException {
		
		Random random = new Random();
		
		while (true) {
			
			synchronized(lock) {
				while (list.size() == 0) {
					lock.wait();
				}
				
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();
			}
			
			Thread.sleep(random.nextInt(1000));
		}
	}

}
