package admin.VO;

public class CalcItemVO {
//번호, 제품명, 개수, 일시, 가격, 총 판매 수량, 총 매출
	private String orderCode, id, itemName;
	private int PCnum, quantity, price;
	
	public CalcItemVO(String orderCode, String id, String itemName, int pCnum, int quantity, int price) {
		super();
		this.orderCode = orderCode;
		this.id = id;
		this.itemName = itemName;
		this.PCnum = pCnum;
		this.quantity = quantity;
		this.price = price;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public String getId() {
		return id;
	}

	public String getItemName() {
		return itemName;
	}

	public int getPCnum() {
		return PCnum;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return itemName + "\t" + quantity  + "개\t\t" + price + "원\n" ;
	}
	
	
} // class

