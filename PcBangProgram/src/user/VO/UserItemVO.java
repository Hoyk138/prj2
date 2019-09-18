package user.VO;

public class UserItemVO {
	String itemCode, itemName, itemImg, categoryName;
	int price;
	
	public UserItemVO(String itemCode, String itemName, String itemImg, String categoryName, int price) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.itemImg = itemImg;
		this.categoryName = categoryName;
		this.price = price;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemImg() {
		return itemImg;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "UserItemVO [itemCode=" + itemCode + ", itemName=" + itemName + ", itemImg=" + itemImg
				+ ", categoryName=" + categoryName + ", price=" + price + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
