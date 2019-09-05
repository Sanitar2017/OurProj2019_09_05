package package_insideFunctions;

public class KeepAlive {
	private String userName;
	private String userPass;
	private boolean keepAlive;
	public KeepAlive() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KeepAlive(String userName, String userPass, boolean keepAlive) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.keepAlive = keepAlive;
	}
	public boolean isKeepAlive() {
		return keepAlive;
	}
	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
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
		result = prime * result + (keepAlive ? 1231 : 1237);
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPass == null) ? 0 : userPass.hashCode());
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
		KeepAlive other = (KeepAlive) obj;
		if (keepAlive != other.keepAlive)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPass == null) {
			if (other.userPass != null)
				return false;
		} else if (!userPass.equals(other.userPass))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "KeepAlive [userName=" + userName + ", userPass=" + userPass + ", keepAlive=" + keepAlive + "]";
	}
	
}
