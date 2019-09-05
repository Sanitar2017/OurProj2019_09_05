package package_search;

import java.io.Serializable;

public class SearchProduct implements Serializable{
	private static final long serialVersionUID = 1L;
	private String uniqueTitleInside;
	public SearchProduct() {
		super();
	}
	public SearchProduct(String uniqueTitleInside) {
		super();
		this.uniqueTitleInside = uniqueTitleInside;
	}
	
	public String getUniqueTitleInside() {
		return uniqueTitleInside;
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
		SearchProduct other = (SearchProduct) obj;
		if (uniqueTitleInside == null) {
			if (other.uniqueTitleInside != null)
				return false;
		} else if (!uniqueTitleInside.equals(other.uniqueTitleInside))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "SearchProduct [uniqueTitleInside=" + uniqueTitleInside + "]";
	}
	
}
