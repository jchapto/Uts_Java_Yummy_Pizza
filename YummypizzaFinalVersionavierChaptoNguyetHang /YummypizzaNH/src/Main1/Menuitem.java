package Main1;

public class Menuitem {

	private int id;
	private String menuname;
	private float price;
	private String size;


	private int quantity;
	
	public Menuitem(int id,String menuname, String size, float price, int quantity) {
		this.id = id;
		this.menuname = menuname;
		this.price = price;
		
		this.size = size;
	
		
	}
	public Menuitem() {}
	
	public int getId() {	
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}

	public String getMenuname() {
		return menuname;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

