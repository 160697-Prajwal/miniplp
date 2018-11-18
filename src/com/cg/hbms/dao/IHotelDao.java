package com.cg.hbms.dao;

import java.util.Date;
import java.util.List;

import com.cg.hbms.bean.BookingDetails;
import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.bean.User;
import com.cg.hbms.exceptions.HMSExceptions;

public interface IHotelDao {

	String getRole(String userName, String password) throws HMSExceptions;

	String addHotelDetails(Hotel hotel)throws HMSExceptions;

	Hotel getHotelDetails(String hotelId)throws HMSExceptions;

	int deleteById(String hotelId)throws HMSExceptions;

	int modifyById(String hotelId1, String hotelDescription)throws HMSExceptions;

	String addRoomDetails(Room room) throws HMSExceptions;

	Room getRoomDetials(String hotelId, String roomId)throws HMSExceptions;

	int deleteRoomById(String roomId)throws HMSExceptions;

	List<Hotel> getAllHotels()throws HMSExceptions;

	List<Room> getRoomDetials(String hotelId)throws HMSExceptions;

	String addBookingDetails(BookingDetails bookingdetails)throws HMSExceptions;

	List<Hotel> getAllHotelsbyCity(String city) throws HMSExceptions;

	String getBookingStatus(String bookingId) throws HMSExceptions;

	List<BookingDetails> getBookingDetails(String hotelId) throws HMSExceptions;

	String getRegistered(User user) throws HMSExceptions;

	List<String> getGuestList(String hotelId1) throws HMSExceptions;

	List<BookingDetails> getBookingList(Date bookingsDate) throws HMSExceptions;

	String changeStatus(String bookingId, String newStatus) throws HMSExceptions;

	String getUserId(String userName, String password) throws HMSExceptions;

	String changeRoomAvailability(String hotelId1, String bookingId)  throws HMSExceptions;

}
