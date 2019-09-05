package package_entities;

import java.time.LocalDate;

import javax.persistence.Column;
//import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import package_search.SearchMainSpec;
import package_specificationsEnums.ProductStatus;

@Table(name="MainSpec")
@IdClass(SearchMainSpec.class)
@Entity
public class MainSpec {
	@Id
	@Column()
	private String uniqueTitleInside;
	@Id
	private String productTitle;
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	private double realCostPrice;
	private double realWorkingHours;
	//@Embedded
	private LocalDate createDate;
	//@Embedded
	private LocalDate changeDate;
	
	public MainSpec() {
		super();
	}

	public MainSpec(String uniqueTitleInside, String productTitle, ProductStatus status, double realCostPrice,
			double realWorkingHours, LocalDate createDate, LocalDate changeDate) {
		super();
		this.uniqueTitleInside = uniqueTitleInside;
		this.productTitle = productTitle;
		this.status = status;
		this.realCostPrice = realCostPrice;
		this.realWorkingHours = realWorkingHours;
		this.createDate = createDate;
		this.changeDate = changeDate;
	}
	
	

	public String getUniqueTitleInside() {
		return uniqueTitleInside;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

	public double getRealCostPrice() {
		return realCostPrice;
	}

	public void setRealCostPrice(double realCostPrice) {
		this.realCostPrice = realCostPrice;
	}

	public double getRealWorkingHours() {
		return realWorkingHours;
	}

	public void setRealWorkingHours(double realWorkingHours) {
		this.realWorkingHours = realWorkingHours;
	}

	public LocalDate getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDate changeDate) {
		this.changeDate = changeDate;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productTitle == null) ? 0 : productTitle.hashCode());
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
		MainSpec other = (MainSpec) obj;
		if (productTitle == null) {
			if (other.productTitle != null)
				return false;
		} else if (!productTitle.equals(other.productTitle))
			return false;
		if (uniqueTitleInside == null) {
			if (other.uniqueTitleInside != null)
				return false;
		} else if (!uniqueTitleInside.equals(other.uniqueTitleInside))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MainSpec [uniqueTitleInside=" + uniqueTitleInside + ", productTitle=" + productTitle + ", status=" + status
				+ ", realCostPrice=" + realCostPrice + ", realWorkingHours=" + realWorkingHours + ", createDate="
				+ createDate + ", changeDate=" + changeDate + "]";
	}	
}
