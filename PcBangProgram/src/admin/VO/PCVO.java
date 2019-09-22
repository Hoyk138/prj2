package admin.VO;

public class PCVO {
    private String ip_address, input_date;
    private int pc_num;
	
    public PCVO(String ip_address, String input_date, int pc_num) {
		this.ip_address = ip_address;
		this.input_date = input_date;
		this.pc_num = pc_num;
	}

	public String getIp_address() {
		return ip_address;
	}

	public String getInput_date() {
		return input_date;
	}

	public int getPc_num() {
		return pc_num;
	}
    
    
}//class
