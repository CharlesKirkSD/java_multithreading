package waitAndNotify;

//	Processor class with produce and consume methods that share the intrinsic lock of the Processor class.
// The wait() and notify() objects inherited from JObject are used to synchronize the produce() and consume() methods.

public class App {

	public static void main(String[] args) {
		System.out.println("S01_T08 Wait and Notify");
		
		final Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Program finished.");
	}
}
