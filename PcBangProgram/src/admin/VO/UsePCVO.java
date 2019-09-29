package admin.VO;

public class UsePCVO {
    private String userID;
    private int pcNum;
	
    public UsePCVO(String userID, int pcNum) {
		super();
		this.userID = userID;
		this.pcNum = pcNum;
	}

	public String getUserID() {
		return userID;
	}

	public int getPcNum() {
		return pcNum;
	}
    
}//class
