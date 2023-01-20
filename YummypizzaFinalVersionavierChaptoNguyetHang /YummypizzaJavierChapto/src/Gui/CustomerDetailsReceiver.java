package Gui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Gui.Home;

public class CustomerDetailsReceiver {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtPhoneNumber;
	private JTextField txtAddress;
	private JTextField txtCustomerId;

	/**
	 * Launch the application.
	 */
	
	// The newCustomerDetailsScreen() replaces main(String args[])for the buttons in the home page to work
	public static void newCustomerDetailsScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerDetailsReceiver window = new CustomerDetailsReceiver();
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
	public CustomerDetailsReceiver() {
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
		
		JLabel lblNewLabel = new JLabel("Enter customer's details");
		lblNewLabel.setBounds(18, 23, 165, 16);
		frame.getContentPane().add(lblNewLabel);
		
		// The event handler below runs calls the method to send customer details to the database, and also takes the user to the next screen.
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDatabase ();
				OrderDetailsReceiver nw = new OrderDetailsReceiver();
				nw.NewScreen();
			}
		});
		btnNewButton.setBounds(6, 217, 117, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblToppings_2 = new JLabel("Name");
		lblToppings_2.setBounds(20, 106, 46, 16);
		frame.getContentPane().add(lblToppings_2);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(131, 101, 130, 26);
		frame.getContentPane().add(txtName);
		
		JLabel lblToppings_2_1_1 = new JLabel("Phone Number");
		lblToppings_2_1_1.setBounds(18, 144, 92, 16);
		frame.getContentPane().add(lblToppings_2_1_1);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(131, 139, 130, 26);
		frame.getContentPane().add(txtPhoneNumber);
		
		JLabel lblToppings_2_1_1_1 = new JLabel("Address");
		lblToppings_2_1_1_1.setBounds(18, 182, 92, 16);
		frame.getContentPane().add(lblToppings_2_1_1_1);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(131, 177, 130, 26);
		frame.getContentPane().add(txtAddress);
		
		JLabel lblToppings_2_1_1_1_1 = new JLabel("Customer Id");
		lblToppings_2_1_1_1_1.setBounds(18, 63, 92, 16);
		frame.getContentPane().add(lblToppings_2_1_1_1_1);
		
		txtCustomerId = new JTextField();
		txtCustomerId.setColumns(10);
		txtCustomerId.setBounds(131, 58, 130, 26);
		frame.getContentPane().add(txtCustomerId);
	}
	
	// The following method connects to the database

	
	static Connection con() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost/YummyPizza";
			Class.forName(driver);
			return DriverManager.getConnection(url, "root", "Chapijo12");
			
		} catch (Exception e) {
		    System.out.println("Connection failed! " + e);}
		return null;
	}
	
	// The method below saves the customer details introduced by the user to the database and informs the user that the customers details were sent
	
	private void SaveToDatabase () {
		Connection con = con();
		try {
			String query = "insert into customers values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			// The getText method gets information entered in the J Text filled by the user as a string
			
			ps.setString(1, txtCustomerId.getText());
			ps.setString(2, txtName.getText());
			ps.setString(3, txtAddress.getText());
			ps.setString(4, txtPhoneNumber.getText());
			
			ps.execute();
			JOptionPane.showMessageDialog(null, "Customer details sent! Now complete the order details!");

	} catch (Exception e) {
		System.out.println("error: " + e);}
	}
	
}


