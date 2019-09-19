package admin.VO;

public class ProductModifyVO {
	private String imgPath,name,explain,itemCode;
	private int price;

	public ProductModifyVO(String imgPath, String name, String explain, int price,String itemCode) {
		super();
		this.itemCode=itemCode;
		this.imgPath = imgPath;
		this.name = name;
		this.explain = explain;
		this.price = price;
	}
	
	public String getImgPath() {
		return imgPath;
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
	public String getItemCode() {
		return itemCode;
	}
	
}//class
