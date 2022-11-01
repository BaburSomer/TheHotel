package com.bilgeadam.boost.java04.lesson055.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.java04.lesson055.model.Hotel;
import com.bilgeadam.boost.java04.lesson055.util.HibernateUtil;

import jakarta.persistence.TypedQuery;

public class HotelDAO implements DatabaseCRUDable<Hotel>{
	@Override
	public ArrayList<Hotel> retrieve() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "SELECT xxx FROM Hotel AS xxx";  // <=== SQL'in aksine tablo adı değil okumak istediğimiz sınıf ismini yazıyoruz. Java sınıf adı yazım kurallarına uygun olmalı
		TypedQuery<Hotel> typedQuery = session.createQuery(hql, Hotel.class);
		
		ArrayList<Hotel> data = (ArrayList<Hotel>)typedQuery.getResultList();
		return data;
	}

	@Override
	public Hotel find(long oid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "SELECT xxx FROM Hotel AS xxx WHERE xxx.oid =: key";  
		TypedQuery<Hotel> typedQuery = session.createQuery(hql, Hotel.class);
		typedQuery.setParameter("key", oid);
		
		Hotel data = (Hotel)typedQuery.getSingleResult();
		return data;
	}
}
