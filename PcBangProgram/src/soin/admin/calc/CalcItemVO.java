package soin.admin.calc;

public class CalcItemVO {
//번호, 제품명, 개수, 일시, 가격, 총 판매 수량, 총 매출
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

