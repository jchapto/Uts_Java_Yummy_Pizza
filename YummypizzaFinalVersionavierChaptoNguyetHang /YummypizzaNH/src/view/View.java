package view;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import ConnectSQL.App2;
import Main1.*;
import java.awt.Color;
import java.awt.event.ActionEvent;


public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private App2 connectsql = new App2();
	private ArrayList<Menuitem> listpizza;
	private final JPanel panel = new JPanel();
	private Map<Integer, JTextField> idToJT = new HashMap<>();
	private JTextField textField_9;
	private Map<Integer,Integer> choose;
	private Float total;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		listpizza = connectsql.getData();
		panel.setBackground(new Color(250, 128, 114));
		panel.setBounds(0, 0,168, 37);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 228, 225));
		panel_1.setBounds(166, 0, 124, 37);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("MENU");
		panel_1.add(lblNewLabel);
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arima Madurai", Font.BOLD, 23));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(250, 128, 114));
		panel_2.setBounds(282, 0, 168, 37);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("Quantity");
		lblNewLabel_2.setBounds(340, 48 , 141, 23);
		contentPane.add(lblNewLabel_2);
		
		JLabel calculateprice = new JLabel("");
		calculateprice.setBounds(340, 323, 61, 16);
		contentPane.add(calculateprice);
		
		JButton totalpricez = new JButton("Total price");
		totalpricez.setBounds(177, 304, 117, 29);
		contentPane.add(totalpricez);
		
		totalpricez.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				System.out.println(choose);
				int totalPrice = 0;
				for(Integer ch: choose.keySet()) {
					totalPrice += (int) listpizza.get(ch).getPrice() * Integer.parseInt(idToJT.get(ch).getText());
					System.out.println(ch + " ---> " +  (int) listpizza.get(ch).getPrice());
				}
				textField_9.setText(totalPrice + "");
			}
		});
		
		JLabel calprice = new JLabel("");
		calprice.setBounds(340, 309, 61, 16);
		contentPane.add(calprice);
		
		textField_9 = new JTextField();
		textField_9.setBounds(340, 304, 70, 26);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		textField_9.setEditable(false);
		
		choose = new HashMap<Integer,Integer>();
		for (int i = 0 ; i < listpizza.size(); i ++) {
			
			JTextField textField = new JTextField();
			textField.setBounds(330, 80+30*i , 80, 20);
			textField.setName(Integer.toString(i));
			contentPane.add(textField);
			textField.setColumns(10);
			// textField.setText("ABC");
			
			idToJT.put(i, textField);
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton(listpizza.get(i).getMenuname());
			rdbtnNewRadioButton.setBounds(18, 77 + 30*i, 141, 23);
			rdbtnNewRadioButton.setName(Integer.toString(i));
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Integer curr = Integer.parseInt(rdbtnNewRadioButton.getName());
					if(choose.containsKey(curr)) {
						choose.remove(curr);
					} else {
						choose.put(curr, 1);
					}
					System.out.println(choose);
				}
			});
			rdbtnNewRadioButton.setFont(new Font("Noteworthy", Font.PLAIN, 14));
			contentPane.add(rdbtnNewRadioButton);

			
		}
		total = (float) 0;
		JButton btnNewButton = new JButton("Check out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for( int i =0 ; i < choose.size(); i++) {
					total = total +  1;
				}
			}
		});
		btnNewButton.setForeground(new Color(210, 105, 30));
		btnNewButton.setBounds(177, 426, 117, 29);
		contentPane.add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				int orderNum = connectsql.getOrderNum() + 1;
				connectsql.saveOrder(orderNum, choose, idToJT, listpizza);
			}
		});
		
		System.out.print(choose);
	}
}
	
