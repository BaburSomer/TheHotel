package com.bilgeadam.boost.java04.lesson055.controller;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import com.bilgeadam.boost.java04.lesson055.dao.RoomDAO;
import com.bilgeadam.boost.java04.lesson055.model.Booking;
import com.bilgeadam.boost.java04.lesson055.model.Customer;
import com.bilgeadam.boost.java04.lesson055.model.Hotel;
import com.bilgeadam.boost.java04.lesson055.model.Room;
import com.bilgeadam.boost.java04.lesson055.util.Cacheable;
import com.bilgeadam.boost.java04.lesson055.util.CommonData;

public class RoomController implements Cacheable<Room>{
	private RoomDAO dao;
	private Scanner sc;
	private Map<Long, Room> map;
	private ArrayList<Room> lst;

	public RoomController() {
		super();
		this.dao = new RoomDAO();
		this.sc = CommonData.getInstance().getScanner();
	}

	public void addRoom() {
		while (true) {
			System.out.println("\nHangi otele oda eklemek istersiniz (0 ile sonlandır)? ");
			CommonData.getInstance().getHotelController().showHotels();
			int sel = this.sc.nextInt();
			if (sel == 0) break;
			Hotel hotel = CommonData.getInstance().getHotelController().getEntry(sel);
			System.out.println("Oda numarası: ");
			int roomNumber = sc.nextInt();
			System.out.println("Yatak sayısı: ");
			int numOfBeds = sc.nextInt();
			Room room = new Room(roomNumber, numOfBeds, hotel);
			hotel.addRoom(room);
			dao.insert(room);
			sc.nextLine();
		}
	}

	public void showRooms() {
		System.out.println("\nHangi otelin odalarını görmek istersiniz? ");
		CommonData.getInstance().getHotelController().showHotels();
		int sel = this.sc.nextInt();
		Hotel hotel = CommonData.getInstance().getHotelController().getEntry(sel);
		System.out.println(hotel.getName() + "\n");
		List<Room> rooms = this.list();
		for (Room room : rooms) {
			System.out.println("\t" + room.getOid() + " - " + room.getRoomNumber());
		}
	}

	@Override
	public Room getEntry(long key) {
		if (this.map == null) {
			initCache();
		}
		return this.map.get(key);
	}

	@Override
	public List<Room> list() {
		if (this.lst == null) {
			initCache();
		}
		return this.lst;
	}

	@Override
	public void invalidate() {
		this.lst = null;
		this.map = null;
		CommonData.getInstance().info("Room cache invalidated");
	}

	@Override
	public void initCache() {
		CommonData.getInstance().info("Room cache is empty. Initializing");
		this.map = new HashMap<>();
		this.lst = new ArrayList<>();
		ArrayList<Room> entities =  dao.retrieve();
		for (Room entity : entities) {
			this.lst.add(entity);
			this.map.put(entity.getOid(), entity);
		}
		CommonData.getInstance().info("Room cache initialized");
	}

	public void showRoomAvailablity() {
		long selectedRoom = this.selectRoom();		
		Room room = CommonData.getInstance().getRoomController().getEntry(selectedRoom);
		Stream<Booking> bookings = room.getBookings().stream();
		bookings.forEach(b -> System.out.println(b));
	}
	
	public void showRoomAvailablity(long selectedRoom) {
		Room room = CommonData.getInstance().getRoomController().getEntry(selectedRoom);
		Stream<Booking> bookings = room.getBookings().stream();
		bookings.forEach(b -> System.out.println(b));
	}
	
	public long selectRoom() {
		this.showRooms();
		System.out.println("\nHangi oda ile devam etmek istersiniz? ");
		return this.sc.nextInt();
	}

	public void reserveRoom(long selectedRoom, long selectedCust, ZonedDateTime from, ZonedDateTime to) {
		Room room = this.getEntry(selectedRoom);
		Customer cust = CommonData.getInstance().getCustomerController().getEntry(selectedCust);
		Booking booking = new Booking(from, to, 123.5, false);
		booking.setRoom(room);
		booking.setCustomer(cust);
		room.addBooking(booking);
		cust.addBooking(booking);
		dao.update(room);
	}
}
