package multithreadingInSwingWithSwingWorker;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/**
SwingWorker<T, V>
A new SwingWorker needs to be created each time it is run.
 */
public class MainFrame extends JFrame {
	
	private JLabel countLabel_01 = new JLabel("0");
	private JLabel statusLabel = new JLabel("Task not completed.");
	private JButton startButtonEx1 = new JButton("Start Example 1");
	private JButton startButtonEx2 = new JButton("Start Example 2");
	private JButton startButtonEx3 = new JButton("Start Example 3");
	
	public MainFrame(String title) {
		super(title);
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0;
		gc.weighty = 0;
		add(countLabel_01, gc);

		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		add(statusLabel, gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 1;
		add(startButtonEx1, gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weightx = 1;
		gc.weighty = 1;
		add(startButtonEx2, gc);
		
		gc.gridx = 0;
		gc.gridy = 5;
		gc.weightx = 1;
		gc.weighty = 1;
		add(startButtonEx3, gc);
		
		startButtonEx1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startExample1();
			}
		});
		
		startButtonEx2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startExample2();
			}
		});
		
		startButtonEx3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startExample3();
			}
		});
		
		setSize(400,800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	Example 1 : Do some work in the background without returning a value or updating the ui.
	 */
	private void startExample1() {
		System.out.println("Start");
		
		SwingWorker<Void, Void> worker = new SwingWorker<>() {

			@Override
			protected Void doInBackground() throws Exception {
				for (int i = 0; i < 30; i++) {
					Thread.sleep(100);
					System.out.println("Hello " + i);
				}
				
				return null;
			}
		};
		
		// Executes the thread, equivalent to Thread.start()
		worker.execute();
	}

	/**
	Example 2 : Do some work in the background return a Boolean and update the ui to when the thread has finished.
	 */
	private void startExample2() {
		System.out.println("Start");
		
		SwingWorker<Boolean, Void> worker = new SwingWorker<>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				for (int i = 0; i < 10; i++) {
					Thread.sleep(100);
					String s = String.format("sin(%d) = %f", i, Math.sin(i));
					System.out.println(s);
					
				}
				
				return true;
			}
			

			@Override
			protected void done() {
				System.out.println("Done");
				try {
					Boolean status = get();
					statusLabel.setText("Completed with status: " + status);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		// Executes the thread, equivalent to Thread.start()
		worker.execute();
	}
	
	/**
	Example 3 : Do some work in the background return a Boolean and update the ui with the results as they are produced using the publish and process methods.
	 */
	private void startExample3() {
		System.out.println("Start");
		
		SwingWorker<Boolean, Double> worker = new SwingWorker<>() {
			
			@Override
			protected Boolean doInBackground() throws Exception {
				for (int i = 0; i < 10; i++) {
					Thread.sleep(100);
					double result = Math.sin(i);
					String s = String.format("sin(%d) = %f", i, result);
					System.out.println(s);
					
					publish(result);
					
				}
				
				return true;
				
				
			}
			
			
			@Override
			protected void process(List<Double> chunks) {
				double value = chunks.get(chunks.size() - 1);
				
				countLabel_01.setText("Current value: " + value);
			}


			@Override
			protected void done() {
				System.out.println("Done");
				try {
					Boolean status = get();
					statusLabel.setText("Completed with status: " + status);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		
		// Executes the thread, equivalent to Thread.start()
		worker.execute();
	}

}
