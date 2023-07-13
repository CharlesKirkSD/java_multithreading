package multithreadingInSwingWithSwingWorker;

import javax.swing.SwingUtilities;

/**
The User Interface should be updated from the Swing thread.
The SwingWorker class was introduced to handle updating the User Interface.
The User Interface should not be updated from threads you create yourself.
 */
public class App {
	
	public static void main(String args []) {
		
		System.out.println("S01_T15 Multithreading in Swing with Swing Worker");
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainFrame("SwingWorker Demo");
			}
			
		});
	}
}