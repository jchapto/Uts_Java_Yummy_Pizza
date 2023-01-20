package Gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Gui.CustomerDetailsReceiver;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);		
		// The two lines below show the image added
		JLabel lblNewLabel_1 = new JLabel("New label");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/descarga (3).png"));
		lblNewLabel_1.setIcon(img);
		lblNewLabel_1.setBounds(153, 6, 223, 178);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Yummy Pizza System");
		lblNewLabel.setBounds(6, 54, 135, 82);
		frame.getContentPane().add(lblNewLabel);
		// The event handlers below allow the user to go to different screens when clicking the buttons.
		JButton btnNewButton = new JButton("New order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerDetailsReceiver NewOrderCreator = new CustomerDetailsReceiver();
				NewOrderCreator.newCustomerDetailsScreen();
			}
		});
		btnNewButton.setBounds(0, 219, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("All Time Orders");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllTimeOrdersViewer ViewAllTimeOrders = new AllTimeOrdersViewer();
				ViewAllTimeOrders.NewAllTimeOrdersScreen();
				
			}
		});
		btnNewButton_1.setBounds(108, 219, 125, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("All Time Customers");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AllTimeCustomersViewer ViewAllTimeCustomers = new AllTimeCustomersViewer();
				ViewAllTimeCustomers.NewAllTimeCustomersScreen();
			}
		});
		btnNewButton_2.setBounds(225, 219, 151, 29);
		frame.getContentPane().add(btnNewButton_2);
	}
}
