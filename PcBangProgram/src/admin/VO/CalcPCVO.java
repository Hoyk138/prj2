package admin.VO;

public class CalcPCVO {
//번호, 이용 시간, 이용 금액, 총 계, 총 매출
	//pc_num, pc_code, id, use_time, use_fee
	private String pcCode, id, useTime ;
	private int pcNum, useFee;
	
	public CalcPCVO(String pcCode, String id, String useTime, int pcNum, int useFee) {
		this.pcCode = pcCode;
		this.id = id;
		this.useTime = useTime;
		this.pcNum = pcNum;
		this.useFee = useFee;
	}

	public String getPcCode() {
		return pcCode;
	}

	public String getId() {
		return id;
	}

	public String getUseTime() {
		return useTime;
	}

	public int getPcNum() {
		return pcNum;
	}

	public int getUseFee() {
		return useFee;
	}
	

	

} // class

