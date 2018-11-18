package com.cg.hbms.bean;

public class User {
	private String userId;
	private String password;
	private String role;
	private String userName;
	private String mobileNumber;
	private String phoneNumber;
	private String address;
	private String eMail;

	public User() {
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", role=" + role + ", userName=" + userName
				+ ", mobileNumber=" + mobileNumber + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", eMail=" + eMail + "]";
	}

	public User(String password, String role, String userName, String mobileNumber, String phoneNumber, String address,
			String eMail) {
		super();
		this.password = password;
		this.role = role;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.eMail = eMail;
	}

	public User(String userId, String password, String role, String userName, String mobileNumber, String phoneNumber,
			String address, String eMail) {
		super();
		this.userId = userId;
		this.password = password;
		this.role = role;
		this.userName = userName;
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.eMail = eMail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}
