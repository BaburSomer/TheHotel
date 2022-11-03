package com.bilgeadam.boost.java04.lesson055.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bilgeadam.boost.java04.lesson055.dao.CustomerDAO;
import com.bilgeadam.boost.java04.lesson055.model.Customer;
import com.bilgeadam.boost.java04.lesson055.util.Cacheable;
import com.bilgeadam.boost.java04.lesson055.util.CommonData;

public class CustomerController implements Cacheable<Customer>{
	private Map<Long, Customer> map;
	private ArrayList<Customer> lst;
	private CustomerDAO 		dao;
	
	public CustomerController() {
		super();
		this.dao = new CustomerDAO();
	}

	public void addGuest() {
		Customer entry = new Customer("Hilal", "Korkmaz", 1234567);
		dao.insert(entry);
		CommonData.getInstance().info("Guest created :" + entry);
		entry = new Customer("Tuna", "Dağ", 345678);
		dao.insert(entry);
		CommonData.getInstance().info("Guest created :" + entry);
		entry = new Customer("Yiğit Aral", "Yücepur", 5435433);
		dao.insert(entry);
		CommonData.getInstance().info("Guest created :" + entry);
		entry = new Customer("Barış", "Yücetürk", 64364363);
		dao.insert(entry);
		CommonData.getInstance().info("Guest created :" + entry);
		entry = new Customer("Selçuk", "Durukan", 63636364);
		dao.insert(entry);
		CommonData.getInstance().info("Guest created :" + entry);
		entry = new Customer("Mert", "Cömertoğlu", 96594);
		dao.insert(entry);
		CommonData.getInstance().info("Guest created :" + entry);
	}

	@Override
	public Customer getEntry(long key) {
		if (this.map == null) {
			initCache();
		}
		return this.map.get(key);
	}

	@Override
	public ArrayList<Customer> list() {
		if (this.lst == null) {
			initCache();
		}
		return this.lst;
	}

	@Override
	public void initCache() {
		CommonData.getInstance().info("Customer cache is empty. Initializing");
		this.map = new HashMap<>();
		this.lst = new ArrayList<>();
		ArrayList<Customer> entries =  dao.retrieve();
		for (Customer entry : entries) {
			this.lst.add(entry);
			this.map.put(entry.getOid(), entry);
		}
		CommonData.getInstance().info("Customer cache initialized");
	}

	@Override
	public void invalidate() {
		this.lst = null;
		this.map = null;
		CommonData.getInstance().info("Customer cache invalidated");
	}

	public void updateGuest(Customer entry) {
		this.dao.update(entry);
	}

	public long selectGuest() {
		this.listGuests();
		System.out.println("\nHangi müşteri için rezervaston yapmak istersiniz? ");
		long sel = CommonData.getInstance().getScanner().nextInt();
		CommonData.getInstance().getScanner().nextLine();
		return sel;
	}

	private void listGuests() {
		ArrayList<Customer> customers = this.list();
		for (Customer customer : customers) {
			System.out.println(customer.getOid() + " - " + customer.getFullName());
		}
	}
}
