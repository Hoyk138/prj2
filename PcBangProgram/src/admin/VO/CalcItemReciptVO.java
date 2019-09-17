package admin.VO;

public class CalcItemReciptVO {
	//item.name item_name, ordering.quantity quantity, (item.price*ordering.quantity) price, ordering.order_date order_date
//	private String itemName ;
//	private int quantity, price;
//	
//	public CalcItemReciptVO(String itemName, int quantity, int price) {
//		this.itemName = itemName;
//		this.quantity = quantity;
//		this.price = price;
//	} // CalcItemReciptVO
//
//	public String getItemName() {
//		return itemName;
//	}
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public int getPrice() {
//		return price;
//	}
	
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
	
	public int getorderedPrice() {
		return orderedPrice;
	}

	public int getPrice() {
		return price;
	}
	
} // class

