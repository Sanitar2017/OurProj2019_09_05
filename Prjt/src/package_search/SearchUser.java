package package_search;

import java.io.Serializable;


public class SearchUser implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userName;
	private String pass;
	public SearchUser() {
		super();
	}
	public SearchUser(String userName, String pass) {
		super();
		this.userName = userName;
		this.pass = pass;
	}
	public String getUserName() {
		return userName;
	}
	public String getPass() {
		return pass;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		SearchUser other = (SearchUser) obj;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SearchUser [userName=" + userName + ", pass=" + pass + "]";
	}
}
