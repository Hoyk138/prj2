package admin.VO;

public class CalcItemVO {
//��ȣ, ��ǰ��, ����, �Ͻ�, ����, �� �Ǹ� ����, �� ����
	private String itemName, id, pcCode ;
	private int num, quantity, price;
	public CalcItemVO(String id, String pcCode, String itemName, int num, int quantity, int price) {
		this.itemName = itemName;
		this.id = id;
		this.pcCode = pcCode;
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

