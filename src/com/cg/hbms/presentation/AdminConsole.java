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
import com.cg.hbms.exceptions.HMSExceptionList;
import com.cg.hbms.exceptions.HMSExceptions;
import com.cg.hbms.service.HotelServiceImpl;
import com.cg.hbms.service.IHotelService;

public class AdminConsole {
	Scanner scanner = new Scanner(System.in);
	IHotelService service = new HotelServiceImpl();
	static Logger log = Logger.getLogger(AdminConsole.class);
	public void start(String userName) throws HMSExceptions {
		log.info("Inside Admin Console");
		int choice = 0; 
		int choiceOption = 0;
		System.out.println("\n\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" + "~~~~~~~~~ Welcome " + userName
				+ " ~~~~~~~~~\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		mainMenu: while (true) {
			System.out.println("\n\n" + "Select your option");
			System.out.println("Choose Following Options" + "\n" + "[1]. Perform Hotel Management");
			System.out.println("[2]. Perform Room Management" + "\n" + "[3]. View List of Hotels");
			System.out
					.println("[4]. View Bookings of specific hotel" + "\n" + "[5]. View guest list of specific hotel");
			System.out.println("[6]. View bookings for specified date");
			System.out.println("[7]. Confirm Pending Booking Details" + "\n" + "[8]. Exit");
			try {
				choice = scanner.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Enter digits only(1-4)");
			}
			switch (choice) {
			case 1:
				log.info("Inside Hotel Management Menu");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" + "~~ Welcome Hotel Management Menu ~~"
						+ "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				while (true) {
					System.out.println("Select your option");
					System.out.println("Choose Following Options" + "\n" + "[1]. Add Hotel Details");
					System.out.println("" + "[2]. Delete Hotel Details" + "\n" + "[3]. Modify Hotel Offers" + "\n"
							+ "[4]. Go Back to Main Menu");
					// System.out.println("[4]. Exit from Hotel Management Menu");
					try {
						choiceOption = scanner.nextInt();
						scanner.nextLine();
					} catch (InputMismatchException e) {
						System.err.println("Enter digits only(1-3)");
					}
					switch (choiceOption) {
					case 1:
						log.info("In Add Hotel Details Case");
						Hotel hotel = addHotelDetails();
						try {
							boolean hotelValidation = service.hotelValidation(hotel);
							if(hotelValidation){
								String id = service.addHotelDetails(hotel);
								System.out.println(
										"Hotel " + hotel.getHotelName() + " is registered successfully with id: " + id);
							}
							else{
								System.exit(0);
							}
						} catch (HMSExceptions e) {
							System.err.println(e.getMessage());
						}
						break;

					case 2:
						log.info("In Delete Hotel Details Case");
						System.out.println("Enter the Id of hotel to delete");
						String hotelId = scanner.nextLine();
						try {
							Hotel hotelDetails = service.getHotelDetails(hotelId);
							System.out.println("Hotel Name: " + hotelDetails.getHotelName() + "\n" + "Hotel City: "
									+ hotelDetails.getHotelCity());
							System.out.println("Confirm Delete? (y/n)?");
							String confirm = scanner.nextLine();
							if (confirm.equalsIgnoreCase("y")) {
								int delete = service.deleteById(hotelId);
								if (delete == 1) {
									System.out.println("Details of Hotel " + hotelDetails.getHotelName()
											+ " deleted successfully");
								}
							} else {
								System.out.println("Delete Operation Cancelled by user");
							}
						} catch (NullPointerException e) {
						} catch (HMSExceptions e) {
							System.err.println(e.getMessage());
						}

						break;
					case 3:
						log.info("In Modify Hotel Details Case");
						System.out.println("Enter the Id of hotel to modify");
						String hotelId1 = scanner.nextLine();

						try {
							Hotel hotelDetails = service.getHotelDetails(hotelId1);
							if (!hotelDetails.getHotelName().isEmpty()) {
								System.out.println(
										"Enter the new Hotel Description for hotel: " + hotelDetails.getHotelName());
							} else {
								throw new HMSExceptions(HMSExceptionList.Error7);
							}
							String description = scanner.nextLine();
							int modify = service.modifyById(hotelId1, description);
							if (modify > 0) {
								System.out.println("Hotel Description Updated for Hotel with Id: " + hotelId1);
							} else {
								System.err.println("Wrong Hotel Id");
							}
						} catch (HMSExceptions | NullPointerException e) {
							System.err.println(e.getMessage());
						}

						break;
					case 4:
						continue mainMenu;
					default:
						break;
					}
				}

			case 2:
				log.info("In Room Management Menu");
				System.out.println("Enter Hotel Id to perform Room Management");
				String hotelId = scanner.next();
				Hotel hotelDetails = new Hotel();
				try {
					hotelDetails = service.getHotelDetails(hotelId);
					if(!hotelDetails.equals(null)) {
						System.out
						.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" + "~~ Welcome Room Management Menu ~~"
								+ "\n" + "~~~~~~~~HOTEL " + hotelDetails.getHotelName() + " ~~~~~~~~~~~~~" + "\n"
								+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					}
				} catch (NullPointerException e) {
					break;
				}catch (HMSExceptions e) {
					System.err.println(e.getMessage());
					break;
				}
				while (true) {
					{
						System.out.println("Select your option");
						System.out.println("Choose Following Options" + "\n" + "[1]. Add Room Details");
						System.out.println("" + "[2]. Delete Room Details" + "\n" + "[3]. Go back to Main Menu");
					}
					
					// System.out.println("[4]. Exit from Hotel Management Menu");
					try {
						choiceOption = scanner.nextInt();
						scanner.nextLine();
					} catch (InputMismatchException e) {
						System.err.println("Enter digits only(1-3)");
					}
					switch (choiceOption) {
					case 1:
						log.info("In View Room Details Case");
						try {
							System.out.println("Enter the room details for hotel: " + hotelDetails.getHotelName());
						} catch (NullPointerException e) {
						}

						if (!hotelDetails.getHotelName().isEmpty()) {
							Room room = addRoomDetails(hotelId);
							boolean roomFlag = service.roomValidation(room);
							try {
								System.out.println(room.toString());
								if(roomFlag){
									String id = service.addRoomDetails(room);
									System.out.println("The Room with id: " + room.getRoomId() + " for Hotel with  Id: "
											+ id + " added!!");
								}else{
									System.exit(0);
								}
							} catch (HMSExceptions e) {
								System.err.println(e.getMessage());
							}
						}
						break;

					case 2:
						log.info("In Delete Room Details Case");
						try {
							System.out.println("Enter the Room Id to delete? ");
							String roomId = scanner.next();
							Room roomDetails = service.getRoomDetails(hotelId, roomId);
							System.out.println("Hotel Name: " + hotelDetails.getHotelName() + "\n" + "Room Id: "
									+ roomId + "\n" + "Room Number: " + roomDetails.getRoomNumber());
							System.out.println("Confirm Delete? (y/n)?");
							String confirm = scanner.next();
							if (confirm.equals("y")) {
								int delete = service.deleteRoomById(roomId);
								if (delete == 1) {
									System.out.println(
											"Details of Room " + roomDetails.getRoomNumber() + " deleted successfully");
								}
							} else {
								System.out.println("Delete Operation Cancelled by user");
							}
						} catch (HMSExceptions e) {
							System.err.println(e.getMessage());
						}

					case 3:
						continue mainMenu;
					}

				}
			case 3: {
				log.info("In View Hotel List Case");
				/*
				 * 
				 * case 3 for main case View List of Hotels
				 */
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("~~~~~~~~Hotel List~~~~~~~~~");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				List<Hotel> hotels = service.getAllHotels();
				for (Hotel hotel : hotels) {
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
				continue mainMenu;
			}
			/*
			 * 
			 * Case 4 for main
			 */
			case 4: {
				log.info("In View Booking Details Case");
				System.out.println("Enter hotel ID to View Booking Details");
				String hotelId1 = scanner.next();
				try {
				List<BookingDetails> list = service.getBookingDetails(hotelId1);
				if(!list.isEmpty()){
					for (BookingDetails bookingDetails : list) {
						System.out.println(bookingDetails);
					}
				}else{
					System.err.println("No Bookings Done For this Hotel");
				}
					}catch (NullPointerException e) {
						break;
					}catch (HMSExceptions e) {
						System.err.println(e.getMessage());
						break;
					}
				
				
				continue mainMenu;
			}			
				
			case 5: {
				log.info("In View Guest List Case");
				System.out.println("Enter hotel ID to View Guest List");
				String hotelId1 = scanner.next();
				List<String> list = service.getGuestList(hotelId1);
				Hotel hotel = service.getHotelDetails(hotelId1);
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Guest List of Hotel: " + hotel.getHotelName());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				int count = 1;
				for (String string : list) {
					System.out.println(count + "." + string);
					count++;
				}
				continue mainMenu;
			}
			case 6: {
				log.info("In View Booking Details for Specific Date Case");
				/*
				 * 
				 * View bookings for specified date
				 */
				System.out.println("Enter date to View Bookings?  (In The format (dd/MM/yyyy)(01/01/2010)");
				Date bookingsDate = new Date();
				String date = scanner.next();
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				try {
					bookingsDate = format.parse(date);
				} catch (ParseException e) {
					throw new HMSExceptions(HMSExceptionList.Error8);
				}
				List<BookingDetails> list = service.getBookingList(bookingsDate);
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("~~~~~~~List of Booking Details~~~~~~~");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				for (BookingDetails bookingDetails : list) {
					System.out.println(bookingDetails);
				}
				continue mainMenu;
			}
			case 7: {
				log.info("In Confirm Booking Details Case");
				System.out.println("Enter Hotel Id to Confirm details");
				String hotelId1 = scanner.next();
				String newStatus = null;
				List<BookingDetails> list = service.getBookingDetails(hotelId1);
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("~~~~~~~List of Booking Details~~~~~~~");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				for (BookingDetails bookingDetails : list) {
					System.out.println(bookingDetails);
				}
				System.out.println("Enter Booking Id to change status");
				String bookingId = scanner.next();
				System.out.println("Change Status to ?" + "\n" + "[1].Approve?" + "\n" + "[2].Reject?");
				String statusChoice = scanner.next();
				if (statusChoice.equals("1")) {
					newStatus = "Approved";
				} else if (statusChoice.equals("2")) {
					newStatus = "Rejected";
				} else {
					System.err.println("Invalid status");
				}
				String userId = service.changeStatus(bookingId, newStatus);
				if(statusChoice.equals("1")){
					service.changeRoomAvailability(hotelId1,bookingId);
				}
				System.out.println("You Changed the Status of booking of User: "+userId);
				continue mainMenu;
			}
			case 8: {
				System.out.println("Thankyou!!!");
				System.exit(0);
			}
			default:
				break;
			}
		}
	}

	private Hotel addHotelDetails() {
		System.out.println("Enter the Hotel Details");
		System.out.println("Enter City");
		String hotelCity = scanner.nextLine();
		System.out.println("Enter Hotel Name");
		String hotelName = scanner.nextLine();
		System.out.println("Enter Address");
		String address = scanner.nextLine();
		System.out.println("Enter Description");
		String hotelDescription = scanner.nextLine();
		System.out.println("Enter Average Rate Per Night ");
		double rate = scanner.nextDouble();
		System.out.println("Enter Phone Number 1");
		String phoneNumber1 = scanner.next();
		System.out.println("Enter Phone Number 2");
		String phoneNumber2 = scanner.next();
		System.out.println("Enter Ratings");
		String rating = scanner.next();
		System.out.println("Enter Email");
		String eMail = scanner.next();
		System.out.println("Enter Fax");
		String fax = scanner.next();
		Hotel hotel = new Hotel(hotelCity, hotelName, address, hotelDescription, rate, phoneNumber1, phoneNumber2,
				rating, eMail, fax);
		return hotel;
	}

	private Room addRoomDetails(String hotelId2) {
		System.out.println("Enter the Room Details");
		System.out.println("Enter Room Number");
		String roomNumber = scanner.next();
		System.out.println("Enter Room Type"); 
		String roomType = scanner.nextLine();
		System.out.println("Enter Rate per Day");
		double perNightRate = scanner.nextDouble();
		System.out.println("Enter Availablity");
		String availability = scanner.next();

		Room room = new Room(hotelId2, roomNumber, roomType, perNightRate, availability);

		return room;
	}
}
