package user.VO;

public class UserLoginVO {
	private String id, pass;
	
	public UserLoginVO(String id, String pass) {
		this.id=id;
		this.pass=pass;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

}
