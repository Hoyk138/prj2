package admin.VO;

public class OrderedItemAndQuantityVO {

	String category, name;
	int quantity;
	
	public OrderedItemAndQuantityVO(String category, String name, int quantity) {
		this.category = category;
		this.name = name;
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}
	
	
}//OrderedItemAndQuantity
