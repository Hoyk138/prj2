package soin.admin.calc;

public class CalcItemVO {
//��ȣ, ��ǰ��, ����, �Ͻ�, ����, �� �Ǹ� ����, �� ����
	private String itemName, sellingDate;
	private int num, cnt, price, totalCnt, totalPrice;
	

	public CalcItemVO(String itemName, String sellingDate, int num, int cnt, int price, int totalCnt, int totalPrice) {
		this.itemName = itemName;
		this.sellingDate = sellingDate;
		this.num = num;
		this.cnt = cnt;
		this.price = price;
		this.totalCnt = totalCnt;
		this.totalPrice = totalPrice;
	} // CalcItemVO


	public String getItemName() {
		return itemName;
	}


	public String getSellingDate() {
		return sellingDate;
	}


	public int getNum() {
		return num;
	}


	public int getCnt() {
		return cnt;
	}


	public int getPrice() {
		return price;
	}


	public int getTotalCnt() {
		return totalCnt;
	}


	public int getTotalPrice() {
		return totalPrice;
	}
	
	


} // class

