package admin.VO;

public class OrderStateVO {
	private String orderCode,paymentTime;

	public OrderStateVO(String orderCode, String paymentTime) {
		super();
		this.orderCode = orderCode;
		this.paymentTime = paymentTime;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public String getPaymentTime() {
		return paymentTime;
	}
	
	
	
}
