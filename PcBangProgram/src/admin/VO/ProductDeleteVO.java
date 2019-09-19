package admin.VO;

public class ProductDeleteVO {
	
	String itemCode;
	
	public ProductDeleteVO(String itemCode){
		this.itemCode=itemCode;
	}//기본생성자



	public String getItemCode() {
		return itemCode;
	}

	
}
