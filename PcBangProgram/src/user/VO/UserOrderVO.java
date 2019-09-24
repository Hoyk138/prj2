package user.VO;

public class UserOrderVO {
	String itemCode, pcUserCode;
	int quantity;
	
	public UserOrderVO(String itemCode, String pcUserCode, int quantity) {
		super();
		this.itemCode = itemCode;
		this.pcUserCode = pcUserCode;
		this.quantity = quantity;
	}

	//getter
	public String getItemCode() {
		return itemCode;
	}

	public String getPcUserCode() {
		return pcUserCode;
	}

	public int getQuantity() {
		return quantity;
	}

//	@Override
//	public String toString() {
//		return "UserOrderVO [itemCode=" + itemCode + ", pcUserCode=" + pcUserCode + ", quantity=" + quantity + "]";
//	}
	
}//class
