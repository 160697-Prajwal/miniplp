package com.cg.hbms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.cg.hbms.bean.BookingDetails;
import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.bean.User;
import com.cg.hbms.exceptions.HMSExceptionList;
import com.cg.hbms.exceptions.HMSExceptions;
import com.cg.hbms.utility.JdbcUtility;

public class HotelDaoImpl implements IHotelDao {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	static Logger log = Logger.getLogger(HotelDaoImpl.class);
	
	@Override
	public String getRegistered(User user) throws HMSExceptions {
		log.info("Inside getRegistered Method");
		String userId = null;
		int insert = 0;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query18);
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getRole());
			preparedStatement.setString(3, user.getUserName());
			preparedStatement.setString(4, user.getMobileNumber());
			preparedStatement.setString(5, user.getPhoneNumber());
			preparedStatement.setString(6, user.getAddress());
			preparedStatement.setString(7, user.geteMail());
			insert = preparedStatement.executeUpdate();
			if (insert >= 0) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(IQueryConstants.Query19);
				preparedStatement1.setString(1, user.getUserName());
				ResultSet resultSet = preparedStatement1.executeQuery();
				if (resultSet.next()) {
					userId = resultSet.getString(1);
				}
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return userId;
	}

	@Override
	public String getRole(String userName, String password) throws HMSExceptions {
		log.info("Inside getRole Method");
		String role = null;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query1);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				role = resultSet.getString(1);
			} else {
				role = "Invalid Credintials or Username Does not exists";
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return role;
	}

	@Override
	public String getUserId(String userName, String password) throws HMSExceptions {
		log.info("Inside getUserId Method");
		connection = JdbcUtility.getConnection();
		String getUserId = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query24);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				getUserId = resultSet.getString(1);
			} else {
				getUserId = "Invalid Credintials or Username Does not exists";
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return getUserId;
	}

	@Override
	public String addHotelDetails(Hotel hotel) throws HMSExceptions {
		log.info("Inside addHotelDetails Method");
		String hotelId = null;
		int insert = 0;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query2);
			preparedStatement.setString(1, hotel.getHotelCity());
			preparedStatement.setString(2, hotel.getHotelName());
			preparedStatement.setString(3, hotel.getAddress());
			preparedStatement.setString(4, hotel.getHotelDescription());
			preparedStatement.setDouble(5, hotel.getRate());
			preparedStatement.setString(6, hotel.getPhoneNumber1());
			preparedStatement.setString(7, hotel.getPhoneNumber2());
			preparedStatement.setString(8, hotel.getRating());
			preparedStatement.setString(9, hotel.geteMail());
			preparedStatement.setString(10, hotel.getFax());
			insert = preparedStatement.executeUpdate();
			if (insert >= 0) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(IQueryConstants.Query3);
				preparedStatement1.setString(1, hotel.getHotelName());
				ResultSet resultSet = preparedStatement1.executeQuery();
				if (resultSet.next()) {
					hotelId = resultSet.getString(1);
				}
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return hotelId;
	}

	@Override
	public Hotel getHotelDetails(String hotelId) throws HMSExceptions {
		log.info("Inside getHotelDetails Method");
		connection = JdbcUtility.getConnection();
		Hotel hotel = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query4);
			preparedStatement.setString(1, hotelId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String hotelCity = resultSet.getString(2);
				String hotelName = resultSet.getString(3);
				String address = resultSet.getString(4);
				String hotelDescription = resultSet.getString(5);
				Double rate = resultSet.getDouble(6);
				String phoneNumber1 = resultSet.getString(7);
				String phoneNumber2 = resultSet.getString(8);
				String rating = resultSet.getString(9);
				String eMail = resultSet.getString(10);
				String fax = resultSet.getString(11);
				hotel = new Hotel(hotelCity, hotelName, address, hotelDescription, rate, phoneNumber1, phoneNumber2,
						rating, eMail, fax);
			} else {
				System.err.println(HMSExceptionList.Error9);
			}
		} catch (NullPointerException e) {
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return hotel;
	}

	@Override
	public int deleteById(String hotelId) throws HMSExceptions {
		log.info("Inside deleteById Method");
		connection = JdbcUtility.getConnection();
		int flag = 0;
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query5);
			preparedStatement.setString(1, hotelId);
			flag = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return flag; 
	}

	@Override
	public int modifyById(String hotelId1, String hotelDescription) throws HMSExceptions {
		log.info("Inside modifyById Method");
		connection = JdbcUtility.getConnection();
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query6);
			preparedStatement.setString(1, hotelDescription);
			preparedStatement.setString(2, hotelId1);

			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}

		return result;
	}

	@Override
	public String addRoomDetails(Room room) throws HMSExceptions {
		log.info("Inside addRoomDetails Method");
		String roomId = "";
		int insert = 0;
		connection = JdbcUtility.getConnection();
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query7);
			preparedStatement.setString(1, room.getHotelId());
			preparedStatement.setString(2, room.getRoomNumber());
			preparedStatement.setString(3, room.getRoomType());
			preparedStatement.setDouble(4, room.getPerNightRate());
			preparedStatement.setString(5, room.getAvailability());
			insert = preparedStatement.executeUpdate();
			if (insert >= 0) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(IQueryConstants.Query8);
				preparedStatement1.setString(1, room.getHotelId());
				ResultSet resultSet = preparedStatement1.executeQuery();
				if (resultSet.next()) {
					roomId = resultSet.getString(1);
				}
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return roomId;
	}

	@Override
	public Room getRoomDetials(String hotelId, String roomId) throws HMSExceptions {
		log.info("Inside getRoomDetails Method");
		connection = JdbcUtility.getConnection();
		Room room = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query9);
			preparedStatement.setString(1, hotelId);
			preparedStatement.setString(2, roomId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String hotelId1 = resultSet.getString(1);
				String roomId1 = resultSet.getString(2);
				String roomNumber = resultSet.getString(3);
				String roomType = resultSet.getString(4);
				double perDayRate = resultSet.getDouble(5);
				String availability = resultSet.getString(6);
				room = new Room(hotelId1, roomId1, roomNumber, roomType, perDayRate, availability);
			} else {
				System.err.println("Room Does not exist!!");
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return room;
	}

	@Override
	public int deleteRoomById(String roomId) throws HMSExceptions {
		log.info("Inside deleteRoomById Method");
		connection = JdbcUtility.getConnection();
		int delete;
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query10);
			preparedStatement.setString(1, roomId);
			delete = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return delete;
	}

	@Override
	public List<Hotel> getAllHotels() throws HMSExceptions {
		log.info("Inside getAllHotels Method");
		connection = JdbcUtility.getConnection();
		List<Hotel> list = new ArrayList<Hotel>();
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query11);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String hotelID = resultSet.getString(1);
				String hotelCity = resultSet.getString(2);
				String hotelName = resultSet.getString(3);
				String address = resultSet.getString(4);
				String hotelDescription = resultSet.getString(5);
				Double rate = resultSet.getDouble(6);
				String phoneNumber1 = resultSet.getString(7);
				String phoneNumber2 = resultSet.getString(8);
				String rating = resultSet.getString(9);
				String eMail = resultSet.getString(10);
				String fax = resultSet.getString(11);
				Hotel hotel = new Hotel(hotelID, hotelCity, hotelName, address, hotelDescription, rate, phoneNumber1,
						phoneNumber2, rating, eMail, fax);
				list.add(hotel);
			}

		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return list;
	}

	@Override
	public List<Room> getRoomDetials(String hotelId) throws HMSExceptions {
		log.info("Inside getRoomDetails Method");
		connection = JdbcUtility.getConnection();
		List<Room> list = new ArrayList<Room>();
		Room room = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query12);
			preparedStatement.setString(1, hotelId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String hotelId1 = resultSet.getString(1);
				String roomId1 = resultSet.getString(2);
				String roomNumber = resultSet.getString(3);
				String roomType = resultSet.getString(4);
				double perDayRate = resultSet.getDouble(5);
				String availability = resultSet.getString(6);
				room = new Room(hotelId1, roomId1, roomNumber, roomType, perDayRate, availability);
				list.add(room);
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return list;
	}

	@Override
	public String addBookingDetails(BookingDetails bookingdetails) throws HMSExceptions {
		log.info("Inside addBookingDetails Method");
		connection = JdbcUtility.getConnection();
		int insert = 0;
		String bookingId = null;
		try {
			Date date = bookingdetails.getBookedFrom();
			long ms = date.getTime();
			java.sql.Date dateBookedFrom = new java.sql.Date(ms);

			Date date1 = bookingdetails.getBookedTo();
			long ms1 = date1.getTime();
			java.sql.Date dateBookedTo = new java.sql.Date(ms1);

			preparedStatement = connection.prepareStatement(IQueryConstants.Query13);
			preparedStatement.setString(1, bookingdetails.getHotelId());
			preparedStatement.setString(2, bookingdetails.getRoomId());
			preparedStatement.setString(3, bookingdetails.getUserName());
			preparedStatement.setString(4, bookingdetails.getUserId());
			preparedStatement.setDate(5, dateBookedFrom);
			preparedStatement.setDate(6, dateBookedTo);
			preparedStatement.setString(7, bookingdetails.getNoOfAdults());
			preparedStatement.setString(8, bookingdetails.getNoOfChildren());
			preparedStatement.setDouble(9, bookingdetails.getAmount());
			preparedStatement.setString(10, bookingdetails.getStatus());
			insert = preparedStatement.executeUpdate();
			if (insert >= 0) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(IQueryConstants.Query14);
				preparedStatement1.setString(1, bookingdetails.getUserId());
				ResultSet resultSet = preparedStatement1.executeQuery();
				if (resultSet.next()) {
					bookingId = resultSet.getString(1);
				}
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}

		return bookingId;
	}

	@Override
	public List<Hotel> getAllHotelsbyCity(String city) throws HMSExceptions {
		log.info("Inside getAllHotelsByCity Method");
		connection = JdbcUtility.getConnection();
		List<Hotel> list = new ArrayList<Hotel>();
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query15);
			preparedStatement.setString(1, city);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String hotelID = resultSet.getString(1);
				String hotelCity = resultSet.getString(2);
				String hotelName = resultSet.getString(3);
				String address = resultSet.getString(4);
				String hotelDescription = resultSet.getString(5);
				Double rate = resultSet.getDouble(6);
				String phoneNumber1 = resultSet.getString(7);
				String phoneNumber2 = resultSet.getString(8);
				String rating = resultSet.getString(9);
				String eMail = resultSet.getString(10);
				String fax = resultSet.getString(11);
				Hotel hotel = new Hotel(hotelID, hotelCity, hotelName, address, hotelDescription, rate, phoneNumber1,
						phoneNumber2, rating, eMail, fax);
				list.add(hotel);
			}
			return list;
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
	}

	@Override
	public String getBookingStatus(String bookingId) throws HMSExceptions {
		log.info("Inside getBookingStatus Method");
		connection = JdbcUtility.getConnection();
		String status = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query16);
			preparedStatement.setString(1, bookingId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				status = resultSet.getString(1);
			} else {
				status = "Wrong Booking Id";
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return status;
	}

	@Override
	public List<BookingDetails> getBookingDetails(String hotelId) throws HMSExceptions {
		log.info("Inside getBookingDetails Method");
		connection = JdbcUtility.getConnection();
		List<BookingDetails> list = new ArrayList<BookingDetails>();
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query17);
			preparedStatement.setString(1, hotelId);
			ResultSet resultSet = preparedStatement.executeQuery(); 
			while (resultSet.next()) {
				String hotelId1 = resultSet.getString(1);
				String bookingId = resultSet.getString(2);
				String userName = resultSet.getString(3);
				String roomId = resultSet.getString(4);
				String userId = resultSet.getString(5);
				Date bookedFrom = resultSet.getDate(6);
				Date bookedTo = resultSet.getDate(7);
				String noOfAdults = resultSet.getString(8);
				String noOfChildren = resultSet.getString(9);
				double amount = resultSet.getDouble(10);
				String status = resultSet.getString(11);
				BookingDetails bookingDetails = new BookingDetails(roomId, bookingId, userName, userId, bookedFrom,
						bookedTo, noOfAdults, noOfChildren, amount, status, hotelId1);
				list.add(bookingDetails);
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return list;
	}

	@Override
	public List<String> getGuestList(String hotelId1) throws HMSExceptions {
		log.info("Inside getGuestList Method");
		connection = JdbcUtility.getConnection();
		List<String> list = new ArrayList<String>();
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query20);
			preparedStatement.setString(1, hotelId1);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String guestName = resultSet.getString(1);
				list.add(guestName);

			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return list;
	}

	@Override
	public List<BookingDetails> getBookingList(Date bookingsDate) throws HMSExceptions {
		log.info("Inside getBookingList Method");
		connection = JdbcUtility.getConnection();
		List<BookingDetails> list = new ArrayList<BookingDetails>();
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query21);
			Date date = bookingsDate;
			long ms = date.getTime();
			java.sql.Date dateBooked = new java.sql.Date(ms);
			preparedStatement.setDate(1, dateBooked);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String hotelId1 = resultSet.getString(1);
				String bookingId = resultSet.getString(2);
				String userName = resultSet.getString(3);
				String roomId = resultSet.getString(4);
				String userId = resultSet.getString(5);
				Date bookedFrom = resultSet.getDate(6);
				Date bookedTo = resultSet.getDate(7);
				String noOfAdults = resultSet.getString(8);
				String noOfChildren = resultSet.getString(9);
				double amount = resultSet.getDouble(10);
				String status = resultSet.getString(11);
				BookingDetails bookingDetails = new BookingDetails(userName, bookingId, roomId, userId, bookedFrom,
						bookedTo, noOfAdults, noOfChildren, amount, status, hotelId1);
				list.add(bookingDetails);
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return list;
	}

	@Override
	public String changeStatus(String bookingId, String newStatus) throws HMSExceptions {
		log.info("Inside changeStatus Method");
		connection = JdbcUtility.getConnection();
		int result = 0;
		String userId = null;
		try {
			preparedStatement = connection.prepareStatement(IQueryConstants.Query22);
			preparedStatement.setString(1, newStatus);
			preparedStatement.setString(2, bookingId);
			result = preparedStatement.executeUpdate();
			if (result > 0) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(IQueryConstants.Query23);
				preparedStatement1.setString(1, bookingId);
				ResultSet resultSet = preparedStatement1.executeQuery();
				if (resultSet.next()) {
					userId = resultSet.getString(1);
				} else {
					userId = "Unavailable";
					throw new HMSExceptions(HMSExceptionList.Error9);
				}
			}
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
		return userId;
	}

	@Override
	public String changeRoomAvailability(String hotelId1, String bookingId) throws HMSExceptions {
		log.info("Inside changeRoomAvailability Method");
		connection = JdbcUtility.getConnection();
		String roomId = null;
		try {

			preparedStatement = connection.prepareStatement(IQueryConstants.Query26);
			preparedStatement.setString(1, bookingId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				roomId = resultSet.getString(1);
			}
			PreparedStatement preparedStatement1 = connection.prepareStatement(IQueryConstants.Query25);
			preparedStatement1.setString(1, hotelId1);
			preparedStatement1.setString(2, roomId);
			preparedStatement1.executeUpdate();
		} catch (SQLException e) {
			throw new HMSExceptions(HMSExceptionList.Error6);
		}
return roomId; 
	}

}