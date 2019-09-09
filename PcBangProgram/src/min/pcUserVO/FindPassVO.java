package min.pcUserVO;

public class FindPassVO {
	
	private String id, name, question, answer;
	private int phone;
	
	public FindPassVO (String id, String name, int phone, String question, String answer) {
		this.id=id;
		this.name=name;
		this.phone=phone;
		this.question=question;
		this.answer=answer;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public int getPhone() {
		return phone;
	}
	
	
	
}
