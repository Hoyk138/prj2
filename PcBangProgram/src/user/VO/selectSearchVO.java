package user.VO;

public class selectSearchVO {
	
	private String itemCode,itemName;
	private int price;
	
	public selectSearchVO(String itemCode, String itemName, int price) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.price = price;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "selectSearchVO [itemCode=" + itemCode + ", itemName=" + itemName + ", price=" + price + "]";
	}
	
	
	
	
	
	

}
