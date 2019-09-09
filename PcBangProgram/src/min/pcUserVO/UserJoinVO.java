package min.pcUserVO;

public class UserJoinVO {

	private String id, pass, passConfirm, name, question, answer;
	private int phone;
	
	public UserJoinVO(String id, String pass, String passConfirm, String name, int phone, String question, String answer) {
		this.id=id;
		this.pass=pass;
		this.passConfirm=passConfirm;
		this.name=name;
		this.phone=phone;
		this.question=question;
		this.answer=answer;
	}
	
}
