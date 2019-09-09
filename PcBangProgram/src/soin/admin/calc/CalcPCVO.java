package soin.admin.calc;

public class CalcPCVO {
//번호, 이용 시간, 이용 금액, 총 계, 총 매출
	private String useTime;
	private int num, totalCnt, price, totalPrice;
	
	public CalcPCVO(String useTime, int num, int totalCnt, int price, int totalPrice) {
		this.useTime = useTime;
		this.num = num;
		this.totalCnt = totalCnt;
		this.price = price;
		this.totalPrice = totalPrice;
	} // CalcPCVO

	public String getUseTime() {
		return useTime;
	}

	public int getNum() {
		return num;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public int getPrice() {
		return price;
	}

	public int getTotalPrice() {
		return totalPrice;
	}


	

} // class

