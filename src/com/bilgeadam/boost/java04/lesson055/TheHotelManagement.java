package com.bilgeadam.boost.java04.lesson055;

import com.bilgeadam.boost.java04.lesson055.util.CommonData;
import com.bilgeadam.boost.java04.lesson055.util.DatabaseListener;
import com.bilgeadam.boost.java04.lesson055.view.Menu;

public class TheHotelManagement {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Wrong number of arguments. Expected <<<1>>> actual <<<" + args.length + ">>>");
			System.exit(100);
		}

		CommonData.getInstance().setPropertiesFile(args[0]);

		CommonData.getInstance().info("Otelimize hoş geldiniz");
		
		DatabaseListener dbListen = new DatabaseListener();
		dbListen.start();
		
		(new Menu()).showMenu();
		
		CommonData.getInstance().info("Güle güle..");
	}
}
