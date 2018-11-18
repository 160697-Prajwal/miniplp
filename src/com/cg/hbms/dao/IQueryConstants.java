package com.cg.hbms.dao;

public interface IQueryConstants {

	String Query1 = "SELECT role FROM users WHERE user_name=? and password=?";
	String Query2 = "INSERT INTO hotel values(hotel_id.nextval,?,?,?,?,?,?,?,?,?,?)";
	String Query3 = "SELECT HOTEL_ID FROM hotel WHERE HOTEL_NAME=?";
	String Query4 = "SELECT * FROM hotel WHERE HOTEL_ID=?";
	String Query5 = "DELETE FROM hotel WHERE HOTEL_ID=?";
	String Query6 = "UPDATE hotel SET DESCRIPTION=? WHERE HOTEL_ID=?";
	String Query7 = "INSERT INTO room_table values(?,room_id.nextval,?,?,?,?)";
	String Query8 = "SELECT ROOM_ID FROM room_table WHERE HOTEL_ID=?";
	String Query9 = "SELECT * FROM room_table WHERE HOTEL_ID=? and ROOM_ID=?";
	String Query10 = "DELETE FROM room_table WHERE ROOM_ID=?";
	String Query11 = "SELECT * FROM hotel";
	String Query12 = "SELECT * from room_table WHERE HOTEL_ID=?";
	String Query13 = "INSERT INTO booking_details values(?,booking_id.nextval,?,?,?,?,?,?,?,?,?)";
	String Query14 = "SELECT booking_id FROM booking_details WHERE user_id=?";
	String Query15 = "SELECT * FROM hotel WHERE city=?";
	String Query16 = "SELECT status from booking_details WHERE booking_id=?";
	String Query17 = "SELECT * FROM booking_details WHERE hotel_id=?";
	String Query18 = "INSERT INTO users values(user_id.nextval,?,?,?,?,?,?,?)";
	String Query19 = "SELECT user_id From users WHERE user_name=?";
	String Query20 = "SELECT user_name from booking_details WHERE hotel_id=?";
	String Query21 = "SELECT * FROM booking_details WHERE booked_from=? ";
	String Query22 = "UPDATE booking_details SET STATUS=? WHERE BOOKING_ID=?";
	String Query23 = "SELECT user_id FROM booking_details WHERE BOOKING_ID=?";
	String Query24 = "SELECT user_id FROM users WHERE user_name=? and password=?";
	String Query25 = "UPDATE room_table SET AVAILABILITY='NO' WHERE HOTEL_ID=? and ROOM_ID=?";
	String Query26 = "SELECT room_id FROM booking_details WHERE booking_id =?";

}
