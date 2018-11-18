package com.cg.hbms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.hbms.bean.BookingDetails;
import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.bean.User;
import com.cg.hbms.dao.HotelDaoImpl;
import com.cg.hbms.dao.IHotelDao;
import com.cg.hbms.exceptions.HMSExceptions;

public class HotelServiceImpl implements IHotelService {
	static Logger log = Logger.getLogger(HotelServiceImpl.class);
	IHotelDao dao = new HotelDaoImpl();

	@Override
	public String getRole(String userName, String password)
			throws HMSExceptions {
		return dao.getRole(userName, password);
	}

	@Override
	public String addHotelDetails(Hotel hotel) throws HMSExceptions {
		return dao.addHotelDetails(hotel);
	}

	/*
	 * Validation of hotel Details
	 */
	public boolean hotelValidation(Hotel hotel) {
		log.info("Inside hotelValidation Method");
		boolean validationFlag = true;
		List<String> validation = validation(hotel);
		if (!validation.isEmpty()) {
			for (String string : validation) {
				System.err.println(string);
				log.error("Validation of  Hotel is Unsuccessfull");
			}
			log.debug("Validation of  Hotel is Successfull");
			validationFlag = false;
		}
		return validationFlag;
	}

	private List<String> validation(Hotel hotel) {
		List<String> validationErrors = new ArrayList<String>();
		String nameRegex = "[A-Z]{1}[a-z]{2,}[a-z]";
		Pattern namePattern = Pattern.compile(nameRegex);
		Matcher nameMatcher = namePattern.matcher(hotel.getHotelName());
		if (!nameMatcher.find()) {
			validationErrors
					.add("\n Hotel Name is in wrong Format"
							+ "\n"
							+ "It should start from capital and minimum of 5 characters");
		}

		String phoneRegex = "[7-9]{1}[0-9]{9}";
		Pattern phonePattern = Pattern.compile(phoneRegex);
		Matcher phoneMatcher = phonePattern.matcher(hotel.getPhoneNumber1());
		if (!phoneMatcher.find()) {
			validationErrors.add("\n Phone number 1 is in wrong Format" + "\n"
					+ "It should be 10 digits");
		}

		String phoneRegex1 = "[7-9]{1}[0-9]{9}";
		Pattern phonePattern1 = Pattern.compile(phoneRegex1);
		Matcher phoneMatcher1 = phonePattern1.matcher(hotel.getPhoneNumber2());
		if (!phoneMatcher1.find()) {
			validationErrors.add("\n Phone number 2  is in wrong Format" + "\n"
					+ "It should be 10 digits");
		}

		int rating = Integer.parseInt(hotel.getRating());
		if (!(rating > 0 && rating < 6)) {
			validationErrors.add("\n Rating should be between 1-5");
		}

		String faxRegex = "[1-9]{1}[0-9]{7}";
		Pattern faxPattern = Pattern.compile(faxRegex);
		Matcher faxMatcher = faxPattern.matcher(hotel.getFax());
		if (!faxMatcher.find()) {
			validationErrors.add("Fax number is in wrong Format" + "\n"
					+ "It should be 8 digits");
		}

		if (!(hotel.getRate() > 499)) {
			validationErrors.add("\n Rate should be greater than 500");
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher emailMatcher = emailPattern.matcher(hotel.geteMail());
		if (!emailMatcher.find()) {
			validationErrors.add("\n E-mail id is in wrong format");
		}

		return validationErrors;
	}

	@Override
	public Hotel getHotelDetails(String hotelId) throws HMSExceptions {
		return dao.getHotelDetails(hotelId);
	}

	@Override
	public int deleteById(String hotelId) throws HMSExceptions {
		return dao.deleteById(hotelId);
	}

	@Override
	public int modifyById(String hotelId1, String hotelDescription)
			throws HMSExceptions {
		return dao.modifyById(hotelId1, hotelDescription);
	}

	@Override
	public String addRoomDetails(Room room) throws HMSExceptions {
		return dao.addRoomDetails(room);
	}

	@Override
	public boolean roomValidation(Room room) {
		log.info("Inside roomValidation Method");
		boolean validationFlag = true;
		List<String> validation = validationRoom(room);
		if (!validation.isEmpty()) {
			for (String string : validation) {
				log.error("Validation of  Room is Unsuccessfull");
				System.err.println(string);
			}
			validationFlag = false;
		}
		log.debug("Validation of  Room is Successfull");
		return validationFlag;
	}

	private List<String> validationRoom(Room room) {
		List<String> validationErrors = new ArrayList<String>();
		String roomNoRegex = "[0-9]{3}";
		Pattern roomPattern = Pattern.compile(roomNoRegex);
		Matcher roomMatcher = roomPattern.matcher(room.getRoomNumber());
		if (!roomMatcher.find()) {
			validationErrors.add("\n Room number  is in wrong Format" + "\n"
					+ "It should be 3 digits");
		}

		if (!(room.getPerNightRate() > 499)) {
			validationErrors.add("\n Rate should be greater than 500");
		}

		if (!(room.getAvailability().equalsIgnoreCase("YES") || room
				.getAvailability().equalsIgnoreCase("NO"))) {
			validationErrors.add("\n Availablity should be YES or NO only");
		}

		return validationErrors;
	}

	@Override
	public Room getRoomDetails(String hotelId, String roomId)
			throws HMSExceptions {
		return dao.getRoomDetials(hotelId, roomId);
	}

	@Override
	public int deleteRoomById(String roomId) throws HMSExceptions {
		return dao.deleteRoomById(roomId);
	}

	@Override
	public List<Hotel> getAllHotels() throws HMSExceptions {
		return dao.getAllHotels();
	}

	@Override
	public List<Room> getRoomDetailsById(String hotelId) throws HMSExceptions {
		return dao.getRoomDetials(hotelId);
	}

	@Override
	public boolean validateBooking(BookingDetails bookingdetails) {
		log.info("Inside validateBooking Method");
		boolean validationFlag = true;
		List<String> validation = validationBooking(bookingdetails);
		if (!validation.isEmpty()) {
			for (String string : validation) {
				System.err.println(string);
				log.error("Validation of  Booking Details is Unsuccessfull");
			}
			validationFlag = false;
			log.debug("Validation of  Booking Details is Successfull");
		}
		return validationFlag;

	}

	private List<String> validationBooking(BookingDetails bookingdetails) {
		List<String> validationErrors = new ArrayList<String>();
		String roomNoRegex = "[0-9]{3}";
		Pattern roomPattern = Pattern.compile(roomNoRegex);
		Matcher roomMatcher = roomPattern.matcher(bookingdetails.getRoomId());
		if (!roomMatcher.find()) {
			validationErrors.add("\n Room number  is in wrong Format" + "\n"
					+ "It should be 3 digits");
		}

		if (!(bookingdetails.getAmount() > 499)) {
			validationErrors.add("\n Rate should be greater than 200");
		}

		int adults = Integer.parseInt(bookingdetails.getNoOfAdults());
		int children = Integer.parseInt(bookingdetails.getNoOfChildren());
		if (!(adults > 0 || children > 0)) {
			validationErrors
					.add("\n Adults or children count must be greater than 0");
		}

		return validationErrors;
	}

	@Override
	public String addBookingDetails(BookingDetails bookingdetails)
			throws HMSExceptions {
		return dao.addBookingDetails(bookingdetails);
	}

	@Override
	public List<Hotel> getAllHotelsbyCity(String city) throws HMSExceptions {
		return dao.getAllHotelsbyCity(city);
	}

	@Override
	public String getBookingStatus(String bookingId) throws HMSExceptions {
		return dao.getBookingStatus(bookingId);
	}

	@Override
	public List<BookingDetails> getBookingDetails(String hotelId)
			throws HMSExceptions {
		return dao.getBookingDetails(hotelId);
	}

	@Override
	public String getRegistered(User user) throws HMSExceptions {
		return dao.getRegistered(user);
	}

	@Override
	public boolean validateRegister(User user) {
		log.info("Inside validateRegister Method");
		boolean validationFlag = true;
		List<String> validation = validationRegister(user);
		if (!validation.isEmpty()) {
			for (String string : validation) {
				System.err.println(string);
				log.error("Validation of  Registration Details is Unsuccessfull");
			}
			log.debug("Validation of  Registration Details is Successfull");
			validationFlag = false;
		}
		return validationFlag;
	}

	private List<String> validationRegister(User user) {
		List<String> validationErrors = new ArrayList<String>();
		String nameRegex = "[A-Z]{1}[a-z]{2,}[a-z]";
		Pattern namePattern = Pattern.compile(nameRegex);
		Matcher nameMatcher = namePattern.matcher(user.getUserName());
		if (!nameMatcher.find()) {
			validationErrors
					.add("\n User Name is in wrong Format"
							+ "\n"
							+ "It should start from capital and minimum of 5 characters");
		}

		String phoneRegex1 = "[7-9]{1}[0-9]{9}";
		Pattern phonePattern1 = Pattern.compile(phoneRegex1);
		Matcher phoneMatcher1 = phonePattern1.matcher(user.getMobileNumber());
		if (!phoneMatcher1.find()) {
			validationErrors.add("\n Phone number  is in wrong Format" + "\n"
					+ "It should be 10 digits");
		}

		String phoneRegex = "[7-9]{1}[0-9]{9}";
		Pattern phonePattern = Pattern.compile(phoneRegex);
		Matcher phoneMatcher = phonePattern.matcher(user.getPhoneNumber());
		if (!phoneMatcher.find()) {
			validationErrors.add("\n Phone number  is in wrong Format" + "\n"
					+ "It should be 10 digits");
		}

		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher emailMatcher = emailPattern.matcher(user.geteMail());
		if (!emailMatcher.find()) {
			validationErrors.add("\n E-mail id is in wrong format");
		}

		return validationErrors;
	}

	@Override
	public List<String> getGuestList(String hotelId1) throws HMSExceptions {
		return dao.getGuestList(hotelId1);
	}

	@Override
	public List<BookingDetails> getBookingList(Date bookingsDate)
			throws HMSExceptions {
		return dao.getBookingList(bookingsDate);
	}

	@Override
	public String changeStatus(String bookingId, String newStatus)
			throws HMSExceptions {
		return dao.changeStatus(bookingId, newStatus);
	}

	@Override
	public String getUserId(String userName, String password)
			throws HMSExceptions {
		return dao.getUserId(userName, password);
	}

	@Override
	public String changeRoomAvailability(String hotelId1, String bookingId) throws HMSExceptions {
		return dao.changeRoomAvailability(hotelId1,bookingId);
	}
}
