package the_synchronized_keyword;

public class App {
	private int count = 0;
	
	public static void main(String ards []) {
		
		System.out.println("Hello World: from S01_T03_TheSynchronizedKeyword");
		
		App app = new App();
		app.doWork();
	}
	
	public void doWork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				int maxIterations = 10000;
				for (int i = 0; i < maxIterations; i++) {
					increment();
				}	
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				int maxIterations = 10000;
				for (int i = 0; i < maxIterations; i++) {
					increment();
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
		
		// The result is usually < 20,000 if the increment method is not declared with the synchronized keyword, as increment is not an atomic operation, 
		// so one thread may start to update the value of count while the other is in the process of updating it resulting in count being increments once instead of twice.
		// The result is always 20,000 when the increment method has is declared with the synchronized keyword.
		System.out.println(count);
	}
	
	// Making the increment method synchronized results in the object's intrinsic lock being acquired prior to the method being executed.
	// The result is that removing the only one thread can modify instance variables of the object at any given time.
	public synchronized void increment() {
		count++;
	}
}