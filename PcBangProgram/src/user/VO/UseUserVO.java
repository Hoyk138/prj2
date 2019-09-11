package user.VO;

public class UseUserVO {
	int pcNum;
	String userId;
	
	public UseUserVO(int pcNum, String userId) {
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
	
}
