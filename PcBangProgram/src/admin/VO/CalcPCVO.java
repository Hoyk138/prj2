package admin.VO;

public class CalcPCVO {
//번호, 이용 시간, 이용 금액, 총 계, 총 매출
	//pc_num, pc_code, id, use_time, use_fee
	private String pcCode, id ;
	private int pcNum, useFee, useTime ;
	
	public CalcPCVO(String pcCode, String id, int useTime, int pcNum, int useFee) {
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

	public int getUseTime() {
		return useTime;
	}

	public int getPcNum() {
		return pcNum;
	}

	public int getUseFee() {
		return useFee;
	}

	@Override
	public String toString() {
		return pcCode + "\t" +   id + "\t" +  pcNum  + "\t\t" + useFee + "원\t\t" + useTime + "분\n" ;
	}
	
	

} // class

