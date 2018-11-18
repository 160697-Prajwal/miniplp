package com.cg.hbms.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.cg.hbms.bean.Hotel;
import com.cg.hbms.bean.User;
import com.cg.hbms.exceptions.HMSExceptions;
import com.cg.hbms.service.HotelServiceImpl;
import com.cg.hbms.service.IHotelService;

public class MainUI {
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		PropertyConfigurator.configure("resources/log4j.properties");
		IHotelService service = new HotelServiceImpl();
		int choice = 0;
		int loginAttempts = 0;

		while (true) {
			next: while (loginAttempts < 3) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + "\n" + "~~~ Welcome to CheapStays.com ~~~"
						+ "\n" + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("Select your option");
				System.out.println("[1]. Register" + "\n" + "[2]. Login" + "\n" + "[3]. Exit");

				try {
					choice = scanner.nextInt();
				} catch (InputMismatchException e) {
					System.err.println("Enter digits only(1-2)");
					break;
				}
				switch (choice) {
				case 1:
					try {
						System.out.println("Are you " + "\n" + "[1].Customer?" + "\n" + "[2].Employee?");
						int registration = scanner.nextInt();
						if (registration == 1) {
							CustomerConsole console = new CustomerConsole();
							User user = console.registerCustomer();
							boolean registerValidation = service.validateRegister(user);

							if (registerValidation) {
								try {
									String userId = service.getRegistered(user);
									System.out.println("Succesfully Registered with id: " + userId);
								} catch (HMSExceptions e) {
									System.err.println(e.getMessage());
								}
							} else {
								System.exit(0);
							}
						} else if (registration == 2) {
							System.out.println("Enter the Id of Employer Hotel?");
							String hotelId = scanner.next();
							try {
								Hotel hotel = service.getHotelDetails(hotelId);
								if (!hotel.equals(null)) {
									EmployeeConsole console = new EmployeeConsole();
									User user = console.registerCustomer();
									String userId = service.getRegistered(user);
									System.out.println("Succesfully Registered with id: " + userId);
								} else {
									System.err.println("Hotel Id wrong!! Enter Again");
								}
							} catch (NullPointerException e) {

							} catch (HMSExceptions e) {
								System.err.println(e.getMessage());
							}
						} else {
							System.err.println("Enter digitis (1-2)");
							continue next;
						}
					} catch (InputMismatchException e) {
						System.err.println("Enter digits only(1-2)");
						break;
					}
					break;

				case 2:
					System.out.print("UserName? ");
					String userName = scanner.next();
					System.out.print("Password? ");
					String password = scanner.next();
					loginAttempts++;
					try {
						String role = service.getRole(userName, password);
						String userId = service.getUserId(userName, password);
						System.err.println(role);
						if (role.equalsIgnoreCase("Admin")) {
							AdminConsole console = new AdminConsole();
							console.start(userName);
						} else if (role.equalsIgnoreCase("Customer")) {
							CustomerConsole console = new CustomerConsole();
							console.start(userName, userId);
						} else if (role.equalsIgnoreCase("Employee")) {
							EmployeeConsole console = new EmployeeConsole();
							console.start(userName, userId);
						} else {
							break;
						}
					} catch (HMSExceptions e) {
						System.err.println(e.getMessage());
					}
					break;
				case 3:
					System.out.println("Thank You Visit Us Again");
					System.exit(0);
					break;
				default:
					System.err.println("Wrong input!! Enter the options [1-3] only!");
					break;
				}
			}
			System.err.println("More than 3 attempts" + "\n" + "Exitting");
			System.exit(0);
			scanner.close();
		}
	}
}
