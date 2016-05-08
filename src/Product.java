
public class Product {
	public int ID;
	public String productName;
	public String lowestPrice;
	public String highestPrice;
	public String productLocalityString;
	public String warrantyString;
	public String monthlySellingString;
	public String localProductString;
	
	public Product(int ID,String productName) {
		super();
		this.ID = ID;
		this.productName = productName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(String lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public String getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(String highestPrice) {
		this.highestPrice = highestPrice;
	}
	public String getProductLocalityString() {
		return productLocalityString;
	}
	public void setProductLocalityString(String productLocalityString) {
		this.productLocalityString = productLocalityString;
	}
	public String getWarrantyString() {
		return warrantyString;
	}
	public void setWarrantyString(String warrantyString) {
		this.warrantyString = warrantyString;
	}
	public String getMonthlySellingString() {
		return monthlySellingString;
	}
	public void setMonthlySellingString(String monthlySellingString) {
		this.monthlySellingString = monthlySellingString;
	}
	public String getLocalProductString() {
		return localProductString;
	}
	public void setLocalProductString(String localProductString) {
		this.localProductString = localProductString;
	}
	
	public Product toClone() {
		return new Product(ID, productName);
	}
	
	@Override
	public String toString() {
		return productName+"\t| BDT ("+lowestPrice+"-"+highestPrice+")\t| "+productLocalityString+"\t| "+warrantyString+"\t| "+monthlySellingString+"\t| "+localProductString;
	}
	
	
}
