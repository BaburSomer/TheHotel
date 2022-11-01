package com.bilgeadam.boost.java04.lesson055.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.java04.lesson055.model.Booking;
import com.bilgeadam.boost.java04.lesson055.util.HibernateUtil;

import jakarta.persistence.TypedQuery;

public class BookingDAO implements DatabaseCRUDable<Booking>{
	@Override
	public ArrayList<Booking> retrieve() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "SELECT xxx FROM Booking AS xxx";  
		TypedQuery<Booking> typedQuery = session.createQuery(hql, Booking.class);
		
		ArrayList<Booking> data = (ArrayList<Booking>)typedQuery.getResultList();
		return data;
	}

	@Override
	public Booking find(long oid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "SELECT xxx FROM Booking AS xxx WHERE xxx.oid =: key";  
		TypedQuery<Booking> typedQuery = session.createQuery(hql, Booking.class);
		typedQuery.setParameter("key", oid);
		
		Booking data = (Booking)typedQuery.getSingleResult();
		return data;
	}
}
