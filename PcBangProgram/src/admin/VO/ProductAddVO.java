package admin.VO;

public class ProductAddVO {
	
	private String img,category,name,explain;
	private int price;
	
	public ProductAddVO(String img, String category, String name, String explain, int price) {
		super();
		this.img = img;
		this.category = category;
		this.name = name;
		this.explain = explain;
		this.price = price;
	}

	public String getImg() {
		return img;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public String getExplain() {
		return explain;
	}

	public int getPrice() {
		return price;
	}
	
}
