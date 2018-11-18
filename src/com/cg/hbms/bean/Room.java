package com.cg.hbms.bean;

public class Room {
	private String hotelId;
	private String roomId;
	private String roomNumber;
	private String roomType;
	private double perNightRate;
	private String availability;

	public Room() {
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getPerNightRate() {
		return perNightRate;
	}

	public void setPerNightRate(double perNightRate) {
		this.perNightRate = perNightRate;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public Room(String hotelId, String roomId, String roomNumber, String roomType, double perNightRate,
			String availability) {
		super();
		this.hotelId = hotelId;
		this.roomId = roomId;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.perNightRate = perNightRate;
		this.availability = availability;
	}

	public Room(String hotelId, String roomNumber, String roomType, double perNightRate, String availability) {
		super();
		this.hotelId = hotelId;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.perNightRate = perNightRate;
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Room [hotelId=" + hotelId + ", roomId=" + roomId + ", roomNumber=" + roomNumber + ", roomType="
				+ roomType + ", perNightRate=" + perNightRate + ", availability=" + availability + "]";
	}

}
