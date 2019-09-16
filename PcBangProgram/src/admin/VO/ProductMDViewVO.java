package admin.VO;

public class ProductMDViewVO {
	
	private String explain,category,state,name,img;
	private int price;
	

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getExplain() {
		return explain;
	}

	public String getCategory() {
		return category;
	}

	public String getState() {
		return state;
	}

	public String getName() {
		return name;
	}

	public String getImg() {
		return img;
	}

	public int getPrice() {
		return price;
	}
	
}//class
