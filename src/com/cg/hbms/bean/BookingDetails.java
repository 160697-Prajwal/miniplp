package com.cg.hbms.bean;

import java.util.Date;

public class BookingDetails {
	private String userName;
	private String bookingId;
	private String roomId;
	private String userId;
	private Date bookedFrom;
	private Date bookedTo;
	private String noOfAdults;
	private String noOfChildren;
	private double amount;
	private String status;
	private String hotelId;

	public BookingDetails() {
	}

	public BookingDetails(String userName, String bookingId, String roomId,
			String userId, Date bookedFrom, Date bookedTo, String noOfAdults,
			String noOfChildren, double amount, String status, String hotelId) {
		super();
		this.userName = userName;
		this.bookingId = bookingId;
		this.roomId = roomId;
		this.userId = userId;
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.noOfAdults = noOfAdults;
		this.noOfChildren = noOfChildren;
		this.amount = amount;
		this.status = status;
		this.hotelId = hotelId;
	}

	public BookingDetails(String userName, String roomId, String userId,
			Date bookedFrom, Date bookedTo, String noOfAdults,
			String noOfChildren, double amount, String status, String hotelId) {
		super();
		this.userName = userName;
		this.roomId = roomId;
		this.userId = userId;
		this.bookedFrom = bookedFrom;
		this.bookedTo = bookedTo;
		this.noOfAdults = noOfAdults;
		this.noOfChildren = noOfChildren;
		this.amount = amount;
		this.status = status;
		this.hotelId = hotelId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getBookedFrom() {
		return bookedFrom;
	}

	public void setBookedFrom(Date bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	public Date getBookedTo() {
		return bookedTo;
	}

	public void setBookedTo(Date bookedTo) {
		this.bookedTo = bookedTo;
	}

	public String getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(String noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public String getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(String noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	@Override
	public String toString() {
		return "BookingDetails [userName=" + userName + ", bookingId="
				+ bookingId + ", roomId=" + roomId + ", userId=" + userId
				+ ", bookedFrom=" + bookedFrom + ", bookedTo=" + bookedTo
				+ ", noOfAdults=" + noOfAdults + ", noOfChildren="
				+ noOfChildren + ", amount=" + amount + ", status=" + status
				+ ", hotelId=" + hotelId + "]";
	}

}
