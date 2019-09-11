package user.VO;

public class UserOrderVO {
	String ipAddr,itemCode, id, orderDate;
	int orderNum, quantity;
	
	public UserOrderVO(String ipAddr, String itemCode, String id, String orderDate, int orderNum, int quantity) {
		super();
		this.ipAddr = ipAddr;
		this.itemCode = itemCode;
		this.id = id;
		this.orderDate = orderDate;
		this.orderNum = orderNum;
		this.quantity = quantity;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getId() {
		return id;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public int getQuantity() {
		return quantity;
	}
	

}
