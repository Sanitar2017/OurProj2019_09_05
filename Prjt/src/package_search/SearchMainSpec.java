package package_search;

import java.io.Serializable;

public class SearchMainSpec implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;//appears a warning without this constant
	protected String uniqueTitleInside;
	protected String productTitle;
	
	public SearchMainSpec() {
		super();
	}

	public SearchMainSpec(String uniqueTitleInside, String productTitle) {
		super();
		this.uniqueTitleInside = uniqueTitleInside;
		this.productTitle = productTitle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUniqueTitleInside() {
		return uniqueTitleInside;
	}

	public String getTitle() {
		return productTitle;
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
		SearchMainSpec other = (SearchMainSpec) obj;
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
		return "SearchProduct [uniqueTitleInside=" + uniqueTitleInside + ", title=" + productTitle + "]";
	}	
}
