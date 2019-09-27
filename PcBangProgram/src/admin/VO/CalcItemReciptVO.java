package admin.VO;

public class CalcItemReciptVO {

	private String itemName, sellingDate;
	private int quantity, orderedPrice, price;
	
	public CalcItemReciptVO(String itemName, String sellingDate, int quantity, int orderedPrice, int price) {
		this.itemName = itemName;
		this.sellingDate = sellingDate;
		this.quantity = quantity;
		this.orderedPrice = orderedPrice ;
		this.price = price;
	} // CalcItemReciptVO

	public String getItemName() {
		return itemName;
	}

	public String getSellingDate() {
		return sellingDate;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public int getOrderedPrice() {
		return orderedPrice;
	}

	public int getPrice() {
		return price;
	}
	
} // class

