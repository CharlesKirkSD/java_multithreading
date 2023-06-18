// A shortcut to creating a single thread is to create a thread and to pass an anonymous class that implements the Runnable interface in its constructor.

package demo3;

public class App {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				int iterations = 10;
				for (int i = 0; i < iterations; i++) {
					System.out.println("Iteration " + i + " in Thread");
					
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		});
		
		t1.start();
	}
}
