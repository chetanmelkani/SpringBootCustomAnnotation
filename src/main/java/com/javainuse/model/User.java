package com.javainuse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private long id;

	private String userName;
	private String password;
	private Role role;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}


}
