package user.VO;

public class UserJoinVO {

	private String id, pass, name, phone, question, answer ;

	public UserJoinVO(String id, String pass, String name, String phone, String question, String answer) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
		this.question = question;
		this.answer = answer;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	
	
}
