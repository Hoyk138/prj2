package user.VO;

public class UserItemVO {
	String itemName, itemImg;
	int price;
	
	public UserItemVO(String itemName, String itemImg, int price) {
		super();
		this.itemName = itemName;
		this.itemImg = itemImg;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemImg() {
		return itemImg;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "UserItemVO [itemName=" + itemName + ", itemImg=" + itemImg + ", price=" + price + "]";
	}
	
	
	
	
	
	

}
