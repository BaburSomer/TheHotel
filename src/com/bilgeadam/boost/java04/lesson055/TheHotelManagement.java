package com.bilgeadam.boost.java04.lesson055;

import com.bilgeadam.boost.java04.lesson055.dao.HotelDAO;
import com.bilgeadam.boost.java04.lesson055.model.Hotel;
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
		hotelDAO.insert(new Hotel(0L, "Hotel Bilge", "Türkiye", 5));
		CommonData.getInstance().info("Güle güle..");
	}

}
