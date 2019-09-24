package user.VO;

public class PcHistoryVO {
	int pcNum;
	String userId;
	
	public PcHistoryVO(int pcNum, String userId) {
		super();
		this.pcNum = pcNum;
		this.userId = userId;
	}

	public int getPcNum() {
		return pcNum;
	}

	public String getUserId() {
		return userId;
	}

//	@Override
//	public String toString() {
//		return "PcHistoryVO [pcNum=" + pcNum + ", userId=" + userId + "]";
//	}

}//class
