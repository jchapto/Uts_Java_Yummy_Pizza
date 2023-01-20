package ConnectSQL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JTextField;

import Main1.Menuitem;
 
public class App2 {
    private static String DB_URL = "jdbc:mysql://localhost:3306/pizza";
    private static String USER_NAME = "root";
    private static String PASSWORD = "1909000";
    private static Connection conn = null;
    private static Statement st = null;
    private static  ResultSet rs;

    public App2() {
    	
   
    }
    
    public int getOrderNum() {
    	int cnt = 0;
    	try {
        	conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
    	
    			String sql = "select DISTINCT order_id from orders ";
    	
    			st = conn.createStatement();
    	
    			
    			rs = st.executeQuery(sql);
    			while (rs.next()) {
    				cnt += 1;
    			}
        	}
        	catch (Exception e) {
        		  e.printStackTrace();
        		}
    	return cnt;
    }
    
    public ArrayList<Menuitem> getData(){
    	ArrayList<Menuitem> menuitemList = new ArrayList<Menuitem>();
    	try {
    	conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
	
			String sql = "select * from pizzamenu ";
	
			st = conn.createStatement();
	
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Menuitem menuitem = new Menuitem();
				menuitem.setId(rs.getInt("id"));
				
				menuitem.setMenuname(rs.getString("menuname"));
				
				menuitem.setQuantity(rs.getInt("quantity"));
				
				menuitem.setPrice(rs.getFloat("price"));
				
				menuitemList.add(menuitem);
			}
    	}
    	catch (Exception e) {
    		  e.printStackTrace();
    		}
    	return menuitemList;
    }
    public void hehe() {
    	System.out.print("hehe");
    }

	public void saveOrder(int orderNum, Map<Integer, Integer> choose, Map<Integer, JTextField> idToJT,
			ArrayList<Menuitem> listpizza) {

		for(Integer ch: choose.keySet()) {
			int sellPrice = (int) listpizza.get(ch).getPrice();
			int  quantity = Integer.parseInt(idToJT.get(ch).getText());
			String sql = String.format("INSERT INTO orders(order_id, product_id, quantity, sell_price) VALUES(%d, %d, %d, %d)", orderNum, ch, quantity, sellPrice);
			try {
		    	conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
					st = conn.createStatement();
			
					st.executeUpdate(sql);
		    	}
		    	catch (Exception e) {
		    		  e.printStackTrace();
		    		}
		}
		
	}
}

