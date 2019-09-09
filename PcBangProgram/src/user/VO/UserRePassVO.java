package user.VO;

public class UserRePassVO {
	
	private String pass, passConfirm;
	
	public UserRePassVO(String pass, String passConfirm) {
		this.pass=pass;
		this.passConfirm=passConfirm;
	}

	public String getPass() {
		return pass;
	}

	public String getPassConfirm() {
		return passConfirm;
	}

	
	
}
