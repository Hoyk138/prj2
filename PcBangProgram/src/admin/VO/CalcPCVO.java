package admin.VO;

public class CalcPCVO {
//��ȣ, �̿� �ð�, �̿� �ݾ�, �� ��, �� ����
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

} // class

