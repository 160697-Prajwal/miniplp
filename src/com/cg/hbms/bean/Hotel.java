package com.cg.hbms.bean;

public class Hotel {
	private String hotelId;
	private String hotelCity;
	private String hotelName;
	private String address;
	private String hotelDescription;
	private Double rate;
	private String phoneNumber1;
	private String phoneNumber2;
	private String rating;
	private String eMail;
	private String fax;

	public Hotel() {
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelCity() {
		return hotelCity;
	}

	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHotelDescription() {
		return hotelDescription;
	}

	public void setHotelDescription(String hotelDescription) {
		this.hotelDescription = hotelDescription;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Hotel(String hotelId, String hotelCity, String hotelName,
			String address, String hotelDescription, Double rate,
			String phoneNumber1, String phoneNumber2, String rating,
			String eMail, String fax) {
		super();
		this.hotelId = hotelId;
		this.hotelCity = hotelCity;
		this.hotelName = hotelName;
		this.address = address;
		this.hotelDescription = hotelDescription;
		this.rate = rate;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.rating = rating;
		this.eMail = eMail;
		this.fax = fax;
	}

	public Hotel(String hotelCity, String hotelName, String address,
			String hotelDescription, Double rate, String phoneNumber1,
			String phoneNumber2, String rating, String eMail, String fax) {
		super();
		this.hotelCity = hotelCity;
		this.hotelName = hotelName;
		this.address = address;
		this.hotelDescription = hotelDescription;
		this.rate = rate;
		this.phoneNumber1 = phoneNumber1;
		this.phoneNumber2 = phoneNumber2;
		this.rating = rating;
		this.eMail = eMail;
		this.fax = fax;
	}

	@Override
	public String toString() {
		return "Hotel [hotelCity=" + hotelCity + ", hotelName=" + hotelName
				+ ", address=" + address + ", hotelDescription="
				+ hotelDescription + ", rate=" + rate + ", phoneNumber1="
				+ phoneNumber1 + ", phoneNumber2=" + phoneNumber2 + ", rating="
				+ rating + ", eMail=" + eMail + ", fax=" + fax + "]";
	}

}
