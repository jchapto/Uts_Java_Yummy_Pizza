package Gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTabbedPane;

public class AllTimeOrdersViewer {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	
	//  The NewAllTimeOrdersScreen() replaces main(String args[])for the buttons in the home page to work

	public static void NewAllTimeOrdersScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllTimeOrdersViewer window = new AllTimeOrdersViewer();
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
	public AllTimeOrdersViewer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ShowData();
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 52, 310, 196);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("All Time Orders");
		lblNewLabel.setBounds(27, 24, 219, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 70, 21, 21);
		frame.getContentPane().add(tabbedPane);
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
		return null; }
	
	// The following method retrieves data from the database
	private void ShowData() {
		Connection con = con();	
		DefaultTableModel model = new DefaultTableModel ();
		model.addColumn("Order ID");
		model.addColumn("Customer ID");
		model.addColumn("Item Name");
		model.addColumn("Item Quantity");
		try { 
		    String query = "select * from orders";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				model.addRow(new Object[] {
						rs.getString("order_id"),
						rs.getString("customerid"),
						rs.getString("item_name"),
						rs.getString("item_quantity"),
				});
			}
			rs.close();
			st.close();
			con.close();
			
			table.setModel(model);
			table.setAutoResizeMode(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(90);
			table.getColumnModel().getColumn(1).setPreferredWidth(90);
			table.getColumnModel().getColumn(2).setPreferredWidth(130);
			table.getColumnModel().getColumn(3).setPreferredWidth(130);

			
			
		} catch (Exception e) {
			System.out.println("error: " + e);
		}
								
						
						
			
				}	
}
