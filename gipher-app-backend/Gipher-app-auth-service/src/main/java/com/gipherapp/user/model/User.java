package com.gipherapp.user.model;

import javax.persistence.Entity;



import javax.persistence.Id;




@Entity
public class User {
	
	@Id
	private String email;
	private String password;
	private String address;
	private String mobilenNumber;
	private String name;
	public User() {
		super();
	}

	public User(String email, String password, String address, String mobilenNumber, String name) {
		super();
		this.email = email;
		this.password = password;
		this.address = address;
		this.mobilenNumber = mobilenNumber;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobilenNumber() {
		return mobilenNumber;
	}
	public void setMobilenNumber(String mobilenNumber) {
		this.mobilenNumber = mobilenNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", address=" + address + ", mobilenNumber="
				+ mobilenNumber + ", name=" + name + "]";
	}
	
	

}
