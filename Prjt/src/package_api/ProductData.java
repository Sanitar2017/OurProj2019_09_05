package package_api;

import package_specificationsEnums.ProductStatus;

public class ProductData {
	private String uniqueTitleInside;
	private ProductStatus status;
	private double realCostPrice;
	private double realWorkingHours;
	private String createDate;
	private String changeDate;
	private String groups;
	private int quantityInStock;
	private int quantityInOrder;
	private double targetCostPrice;
	private double targetWorkingHours;
	private String userName;
	private String userPass;
	public ProductData() {
		super();
	}

	public ProductData(String uniqueTitleInside, ProductStatus status, double realCostPrice, double realWorkingHours,
			String createDate, String changeDate, String groups, int quantityInStock, int quantityInOrder,
			double targetCostPrice, double targetWorkingHours, String userName, String userPass) {
		super();
		this.uniqueTitleInside = uniqueTitleInside;
		this.status = status;
		this.realCostPrice = realCostPrice;
		this.realWorkingHours = realWorkingHours;
		this.createDate = createDate;
		this.changeDate = changeDate;
		this.groups = groups;
		this.quantityInStock = quantityInStock;
		this.quantityInOrder = quantityInOrder;
		this.targetCostPrice = targetCostPrice;
		this.targetWorkingHours = targetWorkingHours;
		this.userName = userName;
		
	}

	public String getUniqueTitleInside() {
		return uniqueTitleInside;
	}

	public void setUniqueTitleInside(String uniqueTitleInside) {
		this.uniqueTitleInside = uniqueTitleInside;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public double getTargetCostPrice() {
		return targetCostPrice;
	}

	public void setTargetCostPrice(double targetCostPrice) {
		this.targetCostPrice = targetCostPrice;
	}

	public double getTargetWorkingHours() {
		return targetWorkingHours;
	}

	public void setTargetWorkingHours(double targetWorkingHours) {
		this.targetWorkingHours = targetWorkingHours;
	}

	public double getRealCostPrice() {
		return realCostPrice;
	}

	public double getRealWorkingHours() {
		return realWorkingHours;
	}

	public String getCreateDate() {
		return createDate;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public int getQuantityInOrder() {
		return quantityInOrder;
	}

	public String getUserName() {
		return userName;
	}
	
	public String getUserPass() {
		return userPass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uniqueTitleInside == null) ? 0 : uniqueTitleInside.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductData other = (ProductData) obj;
		if (uniqueTitleInside == null) {
			if (other.uniqueTitleInside != null)
				return false;
		} else if (!uniqueTitleInside.equals(other.uniqueTitleInside))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [uniqueTitleInside=" + uniqueTitleInside + ", status=" + status + ", realCostPrice="
				+ realCostPrice + ", realWorkingHours=" + realWorkingHours + ", createDate=" + createDate
				+ ", changeDate=" + changeDate + ", groups=" + groups + ", quantityInStock=" + quantityInStock
				+ ", quantityInOrder=" + quantityInOrder + ", targetCostPrice=" + targetCostPrice
				+ ", targetWorkingHours=" + targetWorkingHours + "]";
	}
	
}
