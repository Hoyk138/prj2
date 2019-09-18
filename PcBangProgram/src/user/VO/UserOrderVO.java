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

	public String getItemCode() {
		return itemCode;
	}

	public String getPcUserCode() {
		return pcUserCode;
	}

	public int getQuantity() {
		return quantity;
	}
	
	
	

	
	


}
