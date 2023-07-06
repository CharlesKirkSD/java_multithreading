package semaphores;

import java.util.concurrent.Semaphore;

/**
 Connection is a Singleton class.
 Call getInstance to get an instance of the Connection class.
 Call connect to make a connection.
 
 @param instance - Instance of the class.
 @param sem - The instance of the Semaphore class controlling the number of connections.
 */
public class Connection {
	
	private static Connection instance = new Connection();
	private int currentConnectionCount = 0;
	private int totalConnectionsMade = 0;
	private Semaphore sem = new Semaphore(10);
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			doConnect();
		}
		finally {
			sem.release();
		}

	}
	
	public void doConnect() {
		
		// Open a connection
		synchronized(this) {
			++currentConnectionCount;
			++totalConnectionsMade;
			System.out.println("The current number of connections is: " + currentConnectionCount + "   Total connections made: " + totalConnectionsMade);
		}
		
		// Simulate doing the work!
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Close the open connection
		synchronized(this) {
			--currentConnectionCount;
		}

	}
	
}
