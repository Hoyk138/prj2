package admin.VO;

public class CalcItemVO {
//번호, 제품명, 개수, 일시, 가격, 총 판매 수량, 총 매출
	private String itemName, id, pcCode, itemCode;
	private int num, quantity, price;
	public CalcItemVO(String itemName, String id, String pcCode, String itemCode, int num, int quantity, int price) {
		
		this.itemName = itemName;
		this.id = id;
		this.pcCode = pcCode;
		this.itemCode = itemCode;
		this.num = num;
		this.quantity = quantity;
		this.price = price;
	} // CalcItemVO
	
	public String getItemName() {
		return itemName;
	}
	public String getId() {
		return id;
	}
	public String getPcCode() {
		return pcCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public int getNum() {
		return num;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getPrice() {
		return price;
	}
	



} // class

