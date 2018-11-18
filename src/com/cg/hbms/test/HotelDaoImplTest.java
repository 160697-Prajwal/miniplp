package com.cg.hbms.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.hbms.bean.BookingDetails;
import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.bean.User;
import com.cg.hbms.dao.HotelDaoImpl;
import com.cg.hbms.dao.IHotelDao;
import com.cg.hbms.exceptions.HMSExceptions;

public class HotelDaoImplTest {

	IHotelDao dao;

	@Before
	public void setUp() throws Exception {
		dao = new HotelDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void testGetRegistered() throws HMSExceptions {
		User user = new User("123", "Praj@123", "Admin", "Prajwal", "9060078949", "7019881359", "Bangalore",
				"prajwaloct@gmail.com");
		String userId = dao.getRegistered(user);
		assertEquals("41", userId);
	}

	@Test
	public void testGetRole() throws HMSExceptions {
		String userName = "Prajwal";
		String password = "Praj@123";
		String role = dao.getRole(userName, password);
		assertEquals("Admin", role);
	}

	@Test
	public void testGetUserId() throws HMSExceptions {
		String userName = "Prajwal";
		String password = "Praj@123";
		String userId = dao.getUserId(userName, password);
		assertEquals("43", userId);
	}

	@Test
	public void testAddHotelDetails() throws HMSExceptions {
		Hotel hotel = new Hotel("1234","Dubai", "Continental", "dubai", "Comfort at Peak", 12000.0, "9060078949",
				"9060078949", "5", "dubai@continental.com", "12345678");
		String hotelId = dao.addHotelDetails(hotel);
		assertEquals("1254", hotelId);
	}

	@Test
	public void testGetHotelDetails() throws HMSExceptions {
		Hotel hotel = dao.getHotelDetails("1254");
		assertEquals("Dubai", hotel.getHotelCity());

	}

	@Test
	public void testDeleteById() throws HMSExceptions {
		int deleteFlag = dao.deleteById("1255");
		assertEquals(0, deleteFlag);
	}

	@Test
	public void testModifyById() throws HMSExceptions {
		int modifyFlag = dao.modifyById("1256", "Luxury at its Peak");
		assertEquals(1, modifyFlag);
	}

	@Test
	public void testAddRoomDetails() throws HMSExceptions {
		Room room = new Room("1256", "123", "101", "AC", 2800.0, "YES");
		String roomId = dao.addRoomDetails(room);
		assertEquals("123", roomId);
	}

	@Test
	public void testGetRoomDetialsStringString() throws HMSExceptions {
		Room room = dao.getRoomDetials("1256", "123");
		assertEquals("YES", room.getAvailability());
	}

	@Test
	public void testDeleteRoomById() throws HMSExceptions {
		int deleteFlag = dao.deleteRoomById("121");
		assertEquals(0, deleteFlag);
	}

	@Test
	public void testGetAllHotels() throws HMSExceptions {
		List<Hotel> list = dao.getAllHotels();
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetRoomDetialsString() throws HMSExceptions {
		List<Room> list = dao.getRoomDetials("1256");
		assertFalse(list.isEmpty());
	}

	@Test
	public void testAddBookingDetails() throws HMSExceptions {
		@SuppressWarnings("deprecation")
		BookingDetails bookingdetails = new BookingDetails("Prajwal", "123", "123", "65", new Date(22, 11, 2018),
				new Date(30, 11, 2018), " 2", "2", 800.0, "Pending", "1259");
		String bookingId = dao.addBookingDetails(bookingdetails);
		assertEquals("123", bookingId);
	}

	@Test
	public void testGetAllHotelsbyCity() throws HMSExceptions {
		List<Hotel> list = dao.getAllHotelsbyCity("Dubai");
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetBookingStatus() throws HMSExceptions {
		String status = dao.getBookingStatus("1874");
		assertEquals("Approved", status);
	}

	@Test
	public void testGetBookingDetails() throws HMSExceptions {
		List<BookingDetails> list = dao.getBookingDetails("1259");
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetGuestList() throws HMSExceptions {
		List<String> list = dao.getGuestList("1259");
		assertFalse(list.isEmpty());
	}

	@Test
	public void testGetBookingList() throws HMSExceptions {
		@SuppressWarnings("deprecation")
		List<BookingDetails> list = dao.getBookingList(new Date(22, 11, 2018));
		assertFalse(list.isEmpty());
	}

	@Test
	public void testChangeStatus() throws HMSExceptions {
		String status = dao.changeStatus("1874", "Approved");
		assertEquals("65", status);
	}

	@Test
	public void testChangeRoomAvailability() throws HMSExceptions {
		String roomId = dao.changeRoomAvailability("1259", "1874");
		assertEquals("123", roomId);
	}

}
