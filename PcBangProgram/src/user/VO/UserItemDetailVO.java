package user.VO;

public class UserItemDetailVO {
	String cateName, itemName, itemImg, itemStrongPoint;
	int ItemPrice;
	
	public UserItemDetailVO(String cateName, String itemName, String itemImg, String itemStrongPoint, int itemPrice) {
		super();
		this.cateName = cateName;
		this.itemName = itemName;
		this.itemImg = itemImg;
		this.itemStrongPoint = itemStrongPoint;
		ItemPrice = itemPrice;
	}

	public String getCateName() {
		return cateName;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemImg() {
		return itemImg;
	}

	public String getItemStrongPoint() {
		return itemStrongPoint;
	}

	public int getItemPrice() {
		return ItemPrice;
	}
	
	

}
