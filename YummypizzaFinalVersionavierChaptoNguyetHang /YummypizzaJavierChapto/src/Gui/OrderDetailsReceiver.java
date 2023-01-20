package Gui;

import java.awt.EventQueue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import Gui.CustomerDetailsReceiver;

public class OrderDetailsReceiver {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField textOrderId;
	private JTextField textCustomerId;
	private JComboBox comboBoxItemName_1;
	private JLabel lblItemQuantity;
	private JTextField textItemQuantity;
	private JButton btnNewButton;
	private JTextField textCalculatedPrice;

	/**
	 * Launch the application.
	 */
	
	// The newScreen() replaces main(String args[])for the buttons in the home page to work

	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetailsReceiver window = new OrderDetailsReceiver();
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
	public OrderDetailsReceiver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		// make form with center position
		frame.setLocationRelativeTo(null);
		// HIDE_ON_CLOSE allows the user to be able to close a screen without shutting down the application
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		// the following event handler sends the order to the database
		
		JButton btnNext = new JButton("Confirm Order");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveToDatabase ();
			}
		});
		btnNext.setBounds(284, 237, 123, 29);
		frame.getContentPane().add(btnNext);
		
		JLabel lblSelectTheKind = new JLabel("Fill in the order details:");
		lblSelectTheKind.setBounds(23, 17, 169, 16);
		frame.getContentPane().add(lblSelectTheKind);
		
		lblItemQuantity = new JLabel("Item Quantity");
		lblItemQuantity.setBounds(23, 196, 109, 16);
		frame.getContentPane().add(lblItemQuantity);
		
		comboBoxItemName_1 = new JComboBox();
		comboBoxItemName_1.setModel(new DefaultComboBoxModel(new String[] {"Small Chicken Pizza", "Large Chicken Pizza", "Small Supreme Pizza", "Large Supreme Pizza", "Small Veggie Pizza", "Large Veggie Pizza"}));
		comboBoxItemName_1.setBounds(115, 144, 271, 27);
		frame.getContentPane().add(comboBoxItemName_1);
		
		JLabel lblOrderId = new JLabel("Order Id");
		lblOrderId.setBounds(23, 61, 69, 16);
		frame.getContentPane().add(lblOrderId);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setBounds(23, 148, 69, 16);
		frame.getContentPane().add(lblItemName);
		
		textOrderId = new JTextField();
		textOrderId.setBounds(115, 58, 73, 29);
		frame.getContentPane().add(textOrderId);
		textOrderId.setColumns(10);
		
		JLabel lblCustomerId = new JLabel("Customer Id");
		lblCustomerId.setBounds(23, 103, 90, 16);
		frame.getContentPane().add(lblCustomerId);
		
		textCustomerId = new JTextField();
		textCustomerId.setColumns(10);
		textCustomerId.setBounds(115, 97, 73, 29);
		frame.getContentPane().add(textCustomerId);
		
		textItemQuantity = new JTextField();
		textItemQuantity.setColumns(10);
		textItemQuantity.setBounds(115, 191, 73, 29);
		frame.getContentPane().add(textItemQuantity);
		
		btnNewButton = new JButton("Calculate Price ($)");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// The code below calculates the total price of the order using the customer's selection
				
				String chosenValue=comboBoxItemName_1.getSelectedItem().toString();
				int smallChickenPrice = 5;
				int smallSupremePrice = 6;
				int smallVeggiePrice = 7;
				int largeChickenPrice = 10;
				int largeSupremePrice = 11;
				int largeVeggiePrice = 12;
				int orderQuantity = Integer.parseInt(textItemQuantity.getText());
				int finalPrice = 0;
				
				 switch (chosenValue) {
		            case "Small Chicken Pizza":
		            	finalPrice = (finalPrice + smallChickenPrice)*orderQuantity;
		                break ;
		            case "Large Chicken Pizza":
		            	finalPrice = (finalPrice + largeChickenPrice)*orderQuantity;
		                break ;
		            case "Small Supreme Pizza":
		            	finalPrice = (finalPrice + smallSupremePrice)*orderQuantity;
		                break ;
		            case "Large Supreme Pizza":
		            	finalPrice = (finalPrice + largeSupremePrice)*orderQuantity;
		            	break ;
		            case "Small Veggie Pizza":
		            	finalPrice = (finalPrice + smallVeggiePrice)*orderQuantity;
		            	break ;
		            case "Large Veggie Pizza":
		            	finalPrice = (finalPrice + largeVeggiePrice)*orderQuantity;
		            	break ;
		   
		        }
				textCalculatedPrice.setText(Integer.toString(finalPrice) );
				

				
				

						

			}
		});
		btnNewButton.setBounds(6, 237, 143, 29);
		frame.getContentPane().add(btnNewButton);
		
		textCalculatedPrice = new JTextField();
		textCalculatedPrice.setBounds(162, 237, 98, 26);
		frame.getContentPane().add(textCalculatedPrice);
		textCalculatedPrice.setColumns(10);
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
	
	private void SaveToDatabase () {
		Connection con = con();
		try {
			
			// The getText method gets information entered in the J Text filled by the user as a string

			// The getSelectedItem method gets information chosen in the combo box by the user.
			
			// The toString method turns the information chosen in the combo box to a string.


			String query = "insert into orders values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, textOrderId.getText());
			ps.setString(2, textCustomerId.getText());
			String value=comboBoxItemName_1.getSelectedItem().toString();
			ps.setString(3, value);
			ps.setString(4, textItemQuantity.getText());
			ps.execute();
			
			// This line tells the user the order has been sent

			
			JOptionPane.showMessageDialog(null, "Your Order has been sent!");

	} catch (Exception e) {
		System.out.println("error: " + e);}
		
	}
	
	
	
}



