package com.bilgeadam.boost.java04.lesson055.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;

import com.bilgeadam.boost.java04.lesson055.util.CommonData;

public class Menu {
	public void showMenu() {
		while (true) {
			System.out.println("1 - Hotel yarat");
			System.out.println("2 - Oda ekle");
			System.out.println("3 - Misafir tanımla ");
			System.out.println("4 - Rezervasyon yap ");
			System.out.println("5 - Check-in ");
			System.out.println("6 - Check-out ");
			System.out.println("7 - Oda durumu ");
			System.out.println("0 - Programı sonlandır ");
			System.out.println("\nSeçiminiz: ");
			try {
				int selection = CommonData.getInstance().getScanner().nextInt();
				CommonData.getInstance().getScanner().nextLine();
				if (processSelection(selection)) {
					break;
				}
			} catch (Exception ex) {
				System.out.println("Doğru değer giriniz!");
				continue;
			}
		}
		CommonData.getInstance().getScanner().close();
	}

	private boolean processSelection(int selection) {
		if (selection == 0)
			return true;

		switch (selection) {
		case 1:
			CommonData.getInstance().getHotelController().createHotel();
			CommonData.getInstance().getHotelController().showHotels();
			break;
		case 2:
			CommonData.getInstance().getRoomController().addRoom();
			break;
		case 3:
			CommonData.getInstance().getCustomerController().addGuest();
			break;
		case 4:
			long selectedRoom = CommonData.getInstance().getRoomController().selectRoom();
			long selectedCust = CommonData.getInstance().getCustomerController().selectGuest();
			CommonData.getInstance().getRoomController().showRoomAvailablity(selectedRoom);
			System.out.println("Giriş tarihi (YYYY-MM-dd): ");
			Scanner sc = CommonData.getInstance().getScanner();
			String fromStr = sc.nextLine();
			LocalDate localDate = LocalDate.parse(fromStr);
			ZonedDateTime from = ZonedDateTime.of(localDate, LocalTime.of(12, 0, 0, 0), ZoneId.systemDefault());
			System.out.println("Çıkış tarihi (YYYY-MM-dd): ");
			String toStr = sc.nextLine();
			localDate = LocalDate.parse(toStr);
			ZonedDateTime to = ZonedDateTime.of(localDate, LocalTime.of(11, 30, 0, 0), ZoneId.systemDefault());
			CommonData.getInstance().getRoomController().reserveRoom(selectedRoom, selectedCust, from, to);
			break;
		case 7:
			CommonData.getInstance().getRoomController().showRoomAvailablity();
			break;
		default:
			break;
		}

		return false;
	}
}
