package com.bilgeadam.boost.java04.lesson055.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.boost.java04.lesson055.util.CommonData;
import com.bilgeadam.boost.java04.lesson055.util.HibernateUtil;


public interface DatabaseCRUDable<T> {

	default void insert(T entity) { // create yerine
		try {
			Session session = databaseConnection();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			CommonData.getInstance().info("Veri eklendi: " + entity.toString());
		} catch (Exception e) {
			CommonData.getInstance().error("Veri eklerken hata oluştu: " + e.getMessage());
			e.printStackTrace();
		}
	}

	List<T> retrieve();

	default void update(T entity) {
		try {
			Session session = databaseConnection();
			session.getTransaction().begin();
			session.merge(entity);
			session.getTransaction().commit();
			CommonData.getInstance().info("Veri güncellendi: " + entity.toString());
		} catch (Exception e) {
			CommonData.getInstance().error("Veri güncellerken hata oluştu: " + e.getMessage());
		}

	}

	default void delete(T entity) {
		try {
			Session session = databaseConnection();
			session.getTransaction().begin();
			session.remove(entity);
			session.getTransaction().commit();
			CommonData.getInstance().info("Veri silindi: " + entity.toString());
		} catch (Exception e) {
			CommonData.getInstance().error("Veri silerken hata oluştu: " + e.getMessage());
		}

	}

	T find(long oid);

	default Session databaseConnection() {
		return HibernateUtil.getSessionFactory().openSession();
	}
}
