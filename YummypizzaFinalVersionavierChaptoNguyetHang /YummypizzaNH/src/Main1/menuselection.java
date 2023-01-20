package Main1;

public class menuselection {



	private String menuitem;
	private float price;
	private int quantity;

	public menuselection(String menuitem, float price, int quantity) {
		this.menuitem = menuitem;
		this.price = price;
		this.quantity = quantity;
	}

	public String getMenuitem() {
		return menuitem;
	}

	public void setMenuitem(String menuitem) {
		this.menuitem = menuitem;
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

