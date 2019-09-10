package admin.VO;

public class CalcPCReciptVO {
//번호, 이용 시간, 이용 금액, 총 계, 총 매출
	private String useTime;
	private int pcNum, usePrice ;
	
	public CalcPCReciptVO(String useTime, int pcNum, int usePrice) {
		this.useTime = useTime;
		this.pcNum = pcNum;
		this.usePrice = usePrice;
	} // CalcPCReciptVO

	public String getUseTime() {
		return useTime;
	}

	public int getpcNum() {
		return pcNum;
	}

	public int getUsePrice() {
		return usePrice;
	}


} // class

