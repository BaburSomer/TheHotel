package com.bilgeadam.boost.java04.lesson055.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.java04.lesson055.model.Room;
import com.bilgeadam.boost.java04.lesson055.util.HibernateUtil;

import jakarta.persistence.TypedQuery;

public class RoomDAO implements DatabaseCRUDable<Room>{
	@Override
	public ArrayList<Room> retrieve() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String entityName = this.getClass().getTypeName();
		String hql = "SELECT xxx FROM Room AS xxx";  
		TypedQuery<Room> typedQuery = session.createQuery(hql, Room.class);
		
		ArrayList<Room> data = (ArrayList<Room>)typedQuery.getResultList();
		return data;
	}

	@Override
	public Room find(long oid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "SELECT xxx FROM Room AS xxx WHERE xxx.oid =: key";  
		TypedQuery<Room> typedQuery = session.createQuery(hql, Room.class);
		typedQuery.setParameter("key", oid);
		
		Room data = (Room)typedQuery.getSingleResult();
		return data;
	}
}
