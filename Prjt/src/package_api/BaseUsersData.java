package package_api;

import package_specificationsEnums.UsersRoles;

public class BaseUsersData {
	private String superUserName;
	private String userName;
	private String pass;
	private int accessRights;
	private UsersRoles role;
	private String createTime;
	private String changeTime;
	public BaseUsersData() {
		super();
	}
	
	public BaseUsersData(String superUserName, String userName, String pass, int accessRights, UsersRoles role, String createTime, 
			String changeTime) {
		super();
		this.superUserName = superUserName;
		this.userName = userName;
		this.pass = pass;
		this.accessRights = accessRights;
		this.role = role;
		this.createTime = createTime;
		this.changeTime = changeTime;
	}
	public String getSuperUserName() {
		return superUserName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public int getAccessRights(){
		return accessRights;
	}

	public void setAccessRights(int accessRights) {
		this.accessRights = accessRights;
	}
	public UsersRoles getRole() {
		return role;
	}
	public void setRole(UsersRoles role) {
		this.role = role;
	}
	public String getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accessRights;
		result = prime * result + ((changeTime == null) ? 0 : changeTime.hashCode());
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((superUserName == null) ? 0 : superUserName.hashCode());
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
		BaseUsersData other = (BaseUsersData) obj;
		if (accessRights != other.accessRights)
			return false;
		if (changeTime == null) {
			if (other.changeTime != null)
				return false;
		} else if (!changeTime.equals(other.changeTime))
			return false;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (role != other.role)
			return false;
		if (superUserName == null) {
			if (other.superUserName != null)
				return false;
		} else if (!superUserName.equals(other.superUserName))
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
		return "BaseUsersData [superUserName=" + superUserName + ", userName=" + userName + ", pass=" + pass
				+ ", accessRights=" + accessRights + ", role=" + role + ", createTime=" + createTime + ", changeTime="
				+ changeTime + "]";
	}
}
