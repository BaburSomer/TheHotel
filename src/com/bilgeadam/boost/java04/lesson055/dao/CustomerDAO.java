package com.bilgeadam.boost.java04.lesson055.dao;

import java.util.ArrayList;

import org.hibernate.Session;

import com.bilgeadam.boost.java04.lesson055.model.Customer;
import com.bilgeadam.boost.java04.lesson055.util.HibernateUtil;

import jakarta.persistence.TypedQuery;

public class CustomerDAO implements DatabaseCRUDable<Customer>{
	private final String entityName = Customer.class.getTypeName();
	
	@Override
	public ArrayList<Customer> retrieve() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "SELECT xxx FROM " + entityName + " AS xxx";  
		TypedQuery<Customer> typedQuery = session.createQuery(hql, Customer.class);
		
		ArrayList<Customer> data = (ArrayList<Customer>)typedQuery.getResultList();
		return data;
	}

	@Override
	public Customer find(long oid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		String hql = "SELECT xxx FROM " + entityName + " AS xxx WHERE xxx.oid =: key";  
		TypedQuery<Customer> typedQuery = session.createQuery(hql, Customer.class);
		typedQuery.setParameter("key", oid);
		
		Customer data = (Customer)typedQuery.getSingleResult();
		return data;
	}
}
