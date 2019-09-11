package user.VO;

public class PcHistoryVO {
	int pcNum, useTime, useFee;
	String userId, startTime;
	
	public PcHistoryVO(int pcNum, int useTime, int useFee, String userId, String startTime) {
		super();
		this.pcNum = pcNum;
		this.useTime = useTime;
		this.useFee = useFee;
		this.userId = userId;
		this.startTime = startTime;
	}
	//getter
	public int getPcNum() {
		return pcNum;
	}
	public int getUseTime() {
		return useTime;
	}
	public int getUseFee() {
		return useFee;
	}
	public String getUserId() {
		return userId;
	}
	public String getStartTime() {
		return startTime;
	}
	
}//class
