package admin.VO;

public class OrderViewVO {
	private String orderCode,orderDate,id,name,payment_time;
	private int pcNum,quantity,totalPrice;
	
	public OrderViewVO(String orderCode, String orderDate, String id, String name, String payment_time, int pcNum,
			int quantity, int totalPrice) {
		super();
		this.orderCode = orderCode;
		this.orderDate = orderDate;
		this.id = id;
		this.name = name;
		this.payment_time = payment_time;
		this.pcNum = pcNum;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPayment_time() {
		return payment_time;
	}

	public int getPcNum() {
		return pcNum;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	
	
	
}
