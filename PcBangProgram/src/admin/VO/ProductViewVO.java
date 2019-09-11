package admin.VO;

public class ProductViewVO {

	private String ProductCode,ProductName,
				ProductImg,ProductExplain,ProductInputDate,ProductCategory;
	private int ProductPrice;
	public ProductViewVO(String productCode, String productName, String productImg, String productExplain,
			String productInputDate, String productCategory, int productPrice) {
		super();
		ProductCode = productCode;
		ProductName = productName;
		ProductImg = productImg;
		ProductExplain = productExplain;
		ProductInputDate = productInputDate;
		ProductCategory = productCategory;
		ProductPrice = productPrice;
	}
	public String getProductCode() {
		return ProductCode;
	}
	public String getProductName() {
		return ProductName;
	}
	public String getProductImg() {
		return ProductImg;
	}
	public String getProductExplain() {
		return ProductExplain;
	}
	public String getProductInputDate() {
		return ProductInputDate;
	}
	public String getProductCategory() {
		return ProductCategory;
	}
	public int getProductPrice() {
		return ProductPrice;
	}
	
}
