package package_entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import package_search.SearchUser;
import package_specificationsEnums.UsersRoles;

@Table(name="BaseUsers")
@IdClass(SearchUser.class)
@Entity
public class BaseUsers {
	@Id
	@Column()
	private String userName;
	@Id
	private String pass;
	private int accessRights;
	@Enumerated(EnumType.STRING)
	private UsersRoles role;
	private LocalDate createTime;
	private LocalDate changeTime;
	public BaseUsers() {
		super();
	}
	public BaseUsers(String userName, String pass, int accessRights, UsersRoles role, LocalDate createTime, LocalDate changeTime) {
		super();
		this.userName = userName;
		this.pass = pass;
		this.accessRights = accessRights;
		this.role = role;
		this.createTime = createTime;
		this.changeTime = changeTime; 
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
	
	public boolean getAccessRights(int noAccessRights){
		if ((this.accessRights & (1<<noAccessRights)) == 0){
			return false;
		}
		return true;
	}

	public int getAllAccessRights(){
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
	public LocalDate getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(LocalDate changeTime) {
		this.changeTime = changeTime;
	}
	public LocalDate getCreateTime() {
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
		BaseUsers other = (BaseUsers) obj;
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
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BaseUsers [userName=" + userName + ", pass=" + pass + ", accessRights=" + accessRights + ", role="
				+ role + ", createTime=" + createTime + ", changeTime=" + changeTime + "]";
	}	
}
