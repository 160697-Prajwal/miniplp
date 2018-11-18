package com.cg.hbms.presentation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.cg.hbms.bean.BookingDetails;
import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.Room;
import com.cg.hbms.bean.User;
import com.cg.hbms.exceptions.HMSExceptionList;
import com.cg.hbms.exceptions.HMSExceptions;
import com.cg.hbms.service.HotelServiceImpl;
import com.cg.hbms.service.IHotelService;

public class CustomerConsole {
	Scanner scanner = new Scanner(System.in); 
	IHotelService service = new HotelServiceImpl();
	static Logger log = Logger.getLogger(CustomerConsole.class);
	public void start(String userName, String userId) throws HMSExceptions {
		int choice = 0;
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" + "~~~~~~~~ Welcome " + userName + " ~~~~~~~\n"
				+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		again:while (true) {
			System.out.println("Choose Following Options");
			System.out.println("[1]. Search For Hotels");
			System.out.println("[2]. Book For Hotels");
			System.out.println("[3]. Check Booking Status");
			System.out.println("[4]. Exit");
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Enter digits only(1-4)");
			}

			switch (choice) {
			case 1:
				log.info("In Hotel List by City Case");
				System.out.println("Enter the city to get Hotels List");
				String city = scanner.next();
				comeHere:try{
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println("~~~~~~~~Hotel List~~~~~~~~~");
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					List<Hotel> list = service.getAllHotelsbyCity(city);
					if (!list.isEmpty()) {
						for (Hotel hotel : list) {
							System.out.println("Hotel ID: " + hotel.getHotelId());
							System.out.println("Hotel Name: " + hotel.getHotelName());
							System.out.println("Hotel City: " + hotel.getHotelCity());
							System.out.println("Hotel Address: " + hotel.getAddress());
							System.out.println("Hotel Description: " + hotel.getHotelDescription());
							System.out.println("Hotel Rating: " + hotel.getRating());
							System.out.println("Hotel Rate: " + hotel.getRate());
							System.out.println("Hotel Phone Number: " + hotel.getPhoneNumber1());
							System.out.println("Hotel Email: " + hotel.geteMail());
							System.out.println();
							System.out.println();
						}
					}else {
						System.err.println("\n"+"Cannot find any hotels in this City!! " + "\n"
								+ "Try Again!!!"+"\n"+"\n");
						continue again;
					}
				}catch (NullPointerException e) {
					break comeHere;
				}
				continue again;
				
			case 2:
				log.info("In Room List ");
				System.out.println("Enter the Hotel Id for information: ");
				String hotelId = scanner.next();
				boolean roomFound = false;
				try {
					List<Room> roomDetails = service.getRoomDetailsById(hotelId);
					if (!roomDetails.isEmpty()) {
						for (Room room : roomDetails) {
							System.out.println(room.toString());
						}
						System.out.println("Enter the room Id for booking");
						String roomId = scanner.next();
						for (Room room : roomDetails) {
							if (room.getRoomId().equals(roomId)) {
								roomFound = true;
								BookingDetails bookingdetails = addBookingDetails(userId, roomId, hotelId);
								boolean addBookingFlag = service.validateBooking(bookingdetails);
								if(addBookingFlag){
									String bookingId = service.addBookingDetails(bookingdetails);
									System.out.println("Congratulations " + bookingdetails.getUserName()
											+ " you have done booking and your reference id is:  " + bookingId
											+ " status is " + bookingdetails.getStatus() + "!!!");
								}else{
									System.exit(0);
								}
								
							}
						}
					} else {
						System.err.println("\n"+"Hotel Id entered is wrong!! Unable to find Hotel from this Id!!" + "\n"
								+ "Try Again!!!"+"\n"+"\n");
						break ;
					}
					if (!roomFound) {
						System.err.println("Room Id not found");
					}
				} catch (NullPointerException e) {
					break again;
				}
				break;
			case 3:
				log.info("View Status of Booking ");
				System.out.println("Enter booking Id to view Status: ");
				String bookingId = scanner.next();
				String status = service.getBookingStatus(bookingId);
				if (status.equalsIgnoreCase("PENDING")) {
					System.out.println("Sorry your booking is still in Pending. Check after some time!!!");
				} else if(status.equalsIgnoreCase("Approved")) {
					System.out.println("Congrats Your booking Status is " + status);
				}else if(status.equalsIgnoreCase("Rejected")) {
					System.out.println("Sorry!! Your booking Status is " + status);
				}else{
					System.err.println("Wrong Booking ID!!! Try Again!!!");
				}

				break;
			case 4: {
				System.out.println("Thank You Visit Again!!!");
				System.exit(0);

			}
			default:

				break;
			}
		}
	}

	private BookingDetails addBookingDetails(String userId, String roomId2, String hotelId) throws HMSExceptions {

		System.out.println("Enter Details for Booking: ");
		System.out.println("Enter the date to book Room?  (In The format (dd/MM/yyyy)(01/01/2010)");
		String date1 = scanner.next();
		Date bookedFrom = new Date();
		Date bookedTo = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			bookedFrom = format.parse(date1);
			System.out.println("Enter the end date to book Room?  (In The format (dd/MM/yyyy)(01/01/2010)");
			String date2 = scanner.next();
			bookedTo = format.parse(date2);
		} catch (ParseException e) {
			throw new HMSExceptions(HMSExceptionList.Error8);
		}
		System.out.println("Enter Name");
		String userName = scanner.nextLine();
		System.out.println("Enter number of adults: ");
		String noOfAdults = scanner.next();
		System.out.println("Enter number of children: ");
		String noOfChildren = scanner.next();
		System.out.println("Enter advance booking amount: ");
		double amount = scanner.nextDouble();
		String status = "PENDING";
		BookingDetails bookingDetails = new BookingDetails(userName, roomId2, userId, bookedFrom, bookedTo, noOfAdults,
				noOfChildren, amount, status, hotelId);
		return bookingDetails;

	}

	public User registerCustomer() {
		System.out.println("Ente following Details to register");
		System.out.println("Enter User Name");
		String userName = scanner.nextLine();
		System.out.println("Enter Password");
		String password = scanner.next();
		System.out.println("Enter Mobile Number");
		String mobileNumber = scanner.next();
		System.out.println("Enter Phone Number");
		String phoneNumber = scanner.next();
		System.out.println("Enter Address");
		String address = scanner.next();
		System.out.println("Enter Email");
		String eMail = scanner.next();
		String role = "Customer";
		User user = new User(password, role, userName, mobileNumber, phoneNumber, address, eMail);
		return user;
	}

}
