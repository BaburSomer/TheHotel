package com.bilgeadam.boost.java04.lesson055;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import com.bilgeadam.boost.java04.lesson055.dao.BookingDAO;
import com.bilgeadam.boost.java04.lesson055.dao.CustomerDAO;
import com.bilgeadam.boost.java04.lesson055.dao.HotelDAO;
import com.bilgeadam.boost.java04.lesson055.dao.RoomDAO;
import com.bilgeadam.boost.java04.lesson055.model.Booking;
import com.bilgeadam.boost.java04.lesson055.model.Customer;
import com.bilgeadam.boost.java04.lesson055.model.Hotel;
import com.bilgeadam.boost.java04.lesson055.model.Room;
import com.bilgeadam.boost.java04.lesson055.util.CommonData;

public class TheHotelManagement {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Wrong number of arguments. Expected <<<1>>> actual <<<" + args.length + ">>>");
			System.exit(100);
		}

		CommonData.getInstance().setPropertiesFile(args[0]);

		CommonData.getInstance().info("Otelimize hoş geldiniz");
		
		HotelDAO hotelDAO = new HotelDAO();
		final Hotel hotel = new Hotel("Boost Hotel", "Türkiye", 5);
		hotelDAO.insert(hotel);
		

		CustomerDAO customerDAO = new CustomerDAO();
		final Customer entity = new Customer("Hilal", "Korkmaz", 1234567);
//		entity.addBooking(booking);
		customerDAO.insert(entity);
		
		customerDAO.insert(new Customer("Tuna", "Dağ", 345678));
		
		
		ArrayList<Customer> customers =  customerDAO.retrieve();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		
		RoomDAO roomDAO = new RoomDAO();
		Room room = new Room(101, 2, hotel);
		roomDAO.insert(room);

		room = new Room(102, 2, hotel);
		roomDAO.insert(room);

		room = new Room(103, 2, hotel);
		roomDAO.insert(room);

		room = new Room(105, 2, hotel);
		roomDAO.insert(room);
		
		Booking booking = new Booking();
		booking.setPrice(123.56);
		booking.setFrom(ZonedDateTime.of(2022, 11, 5, 12, 30, 0, 0, ZoneId.systemDefault()));
		booking.setTo(ZonedDateTime.of(2022, 11, 12, 14, 00, 0, 0, ZoneId.systemDefault()));
		booking.setRoom(room);
		booking.setCustomer(entity);
		entity.addBooking(booking);
		room.addBooking(booking);
//		customerDAO.update(entity);
		
		booking = new Booking();
		booking.setPrice(425.0);
		booking.setFrom(ZonedDateTime.of(2022, 11, 15, 12, 30, 0, 0, ZoneId.systemDefault()));
		booking.setTo(ZonedDateTime.of(2022, 11, 25, 14, 00, 0, 0, ZoneId.systemDefault()));
		booking.setRoom(room);
		booking.setCustomer(entity);
		entity.addBooking(booking);
		room.addBooking(booking);
		
//		roomDAO.update(room);
		customerDAO.update(entity);

		ArrayList<Hotel> hotels =  hotelDAO.retrieve();
		for (Hotel hotl : hotels) {
			System.out.println(hotl);
		}

		CommonData.getInstance().info("Güle güle..");
	}
}
