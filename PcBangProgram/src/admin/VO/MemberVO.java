package admin.VO;

public class MemberVO {
	//id, name, phone, join_date, pc_use_time, item_pay_sum
	private String id, name, phone, join_date;
	private int pc_use_time, item_pay_sum;

	public MemberVO(String id, String name, String phone, String join_date, int pc_use_time, int item_pay_sum) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.join_date = join_date;
		this.pc_use_time = pc_use_time;
		this.item_pay_sum = item_pay_sum;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getJoin_date() {
		return join_date;
	}

	public int getPc_use_time() {
		return pc_use_time;
	}

	public int getItem_pay_sum() {
		return item_pay_sum;
	}

}//class
