package com.bilgeadam.boost.java04.lesson055.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;

public class DatabaseListener extends Thread {
	private PGConnection pgConn;
	private Connection conn;

	public DatabaseListener() {
		super();
		try {
			this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TheHotelDB?user=postgres&password=root");
			this.pgConn = (PGConnection) conn;
			Statement stmt = conn.createStatement();
			stmt.execute("LISTEN table_changed");
			stmt.close();

		} catch (SQLException e) {
			CommonData.getInstance().error(e.getMessage());
		}

	}

	public void run() {
		try {
			while (true) {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT 1"); // bekleyen tüm notificationları okuyabiliyoruz
				rs.close();
				stmt.close();
				
				PGNotification[] notifications = pgConn.getNotifications();
				if (notifications != null) {
					for (PGNotification notification : notifications) {
						String tableName = notification.getParameter();
						if (tableName.equalsIgnoreCase("customers")) {
							CommonData.getInstance().getCustomerController().invalidate();
						}
						else if(tableName.equalsIgnoreCase("rooms")) {
							CommonData.getInstance().getRoomController().invalidate();
						}
						CommonData.getInstance().info("PostGreSql'den mesaj var: " + notification.getName() + " -- " + tableName);
					}
				}
				else {
					CommonData.getInstance().info("No news good news");
				}
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
