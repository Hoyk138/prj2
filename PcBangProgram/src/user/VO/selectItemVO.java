package user.VO;

public class selectItemVO {
	
	private String category;
	private int index;
	
	public selectItemVO(String category, int index) {
		super();
		this.category = category;
		this.index = index;
	}
	public String getCategory() {
		return category;
	}
	public int getIndex() {
		return index;
	}
	
	@Override
	public String toString() {
		return "selectItemVO [category=" + category + ", index=" + index + "]";
	}
	
	
	
	

}
