package com.bilgeadam.boost.java04.lesson055.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bilgeadam.boost.java04.lesson055.dao.HotelDAO;
import com.bilgeadam.boost.java04.lesson055.model.Hotel;
import com.bilgeadam.boost.java04.lesson055.util.Cacheable;
import com.bilgeadam.boost.java04.lesson055.util.CommonData;

public class HotelController implements Cacheable<Hotel>{
	private Map<Long, Hotel> map;
	private ArrayList<Hotel> lst;
	private HotelDAO 		 dao;
	
	public HotelController() {
		super();
		this.dao = new HotelDAO();
	}

	public void createHotel() {
		Scanner sc = CommonData.getInstance().getScanner();
		System.out.println("Otelin ismi : ");
		String name = sc.nextLine();
		
		System.out.println("Ülkesi      : ");
		String country = sc.nextLine();
		
		System.out.println("Yıldız adedi: ");
		int stars = sc.nextInt();
		sc.nextLine();
		
		final Hotel hotel = new Hotel(name, country, stars);
		dao.insert(hotel);
		CommonData.getInstance().info("Hotel created :" + hotel);
	}

	public void showHotels() {
		List<Hotel> hotels = this.list();
		for (Hotel hotel : hotels) {
			System.out.println(hotel.getOid() + " - " + hotel);
		}
	}

	@Override
	public Hotel getEntry(long key) {
		if (this.map == null) {
			initCache();
		}
		return this.map.get(key);
	}

	@Override
	public ArrayList<Hotel> list() {
		if (this.lst == null) {
			initCache();
		}
		return this.lst;
	}

	@Override
	public void initCache() {
		CommonData.getInstance().info("Hotel cache is empty. Initializing");
		this.map = new HashMap<>();
		this.lst = new ArrayList<>();
		ArrayList<Hotel> hotels =  dao.retrieve();
		for (Hotel hotel : hotels) {
			this.lst.add(hotel);
			this.map.put(hotel.getOid(), hotel);
		}
		CommonData.getInstance().info("Hotel cache initialized");
	}

	@Override
	public void invalidate() {
		this.lst = null;
		this.map = null;
		CommonData.getInstance().info("Hotel cache invalidated");
	}

	public void updateHotel(Hotel hotel) {
		this.dao.update(hotel);
	}
}
