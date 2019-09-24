package user.VO;

public class UserItemDetailVO {
	String categoryName, itemName, itemImg, itemDescription;
	int ItemPrice;
	
	public UserItemDetailVO(String categoryName, String itemName, String itemImg, String itemDescription,
			int itemPrice) {
		super();
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.itemImg = itemImg;
		this.itemDescription = itemDescription;
		ItemPrice = itemPrice;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemImg() {
		return itemImg;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public int getItemPrice() {
		return ItemPrice;
	}

	@Override
	public String toString() {
		return "UserItemDetailVO [categoryName=" + categoryName + ", itemName=" + itemName + ", itemImg=" + itemImg
				+ ", itemDescription=" + itemDescription + ", ItemPrice=" + ItemPrice + "]";
	}

}//class
