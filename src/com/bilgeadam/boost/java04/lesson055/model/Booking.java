package com.bilgeadam.boost.java04.lesson055.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString (exclude = {"customer", "room"})
@Entity
@Table(name = "bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oid;
	
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_oid")
    private Customer customer;
	
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "room_oid")
    private Room room;
    	
	@Column(name = "from_date", nullable = false)
	private long fromDateAsLong;
	@Transient
	private ZonedDateTime from;

	@Column(name = "to_date", nullable = false)
	private long toDateAsLong;
	@Transient
	private ZonedDateTime to;

	@Column(name = "price", nullable = false)
	private double price;
	@Column(name = "checked_in", nullable = false, columnDefinition = "boolean default false")
	private boolean checkedIn;

	public Booking(ZonedDateTime from, ZonedDateTime to, double price, boolean checkedIn) {
		super();
		this.from = from;
		this.fromDateAsLong = this.from.toInstant().toEpochMilli();
		this.to = to;
		this.toDateAsLong = this.to.toInstant().toEpochMilli();
		this.price = price;
		this.checkedIn = checkedIn;
	}

	public ZonedDateTime getFrom() {
		this.from = ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.fromDateAsLong), ZoneId.systemDefault());
		return this.from;
	}

	public ZonedDateTime getTo() {
		this.to = ZonedDateTime.ofInstant(Instant.ofEpochMilli(this.toDateAsLong), ZoneId.systemDefault());
		return this.to;
	}

	public void setFrom(ZonedDateTime from) {
		this.from = from;
		this.fromDateAsLong = this.from.toInstant().toEpochMilli();
	}

	public void setTo(ZonedDateTime to) {
		this.to = to;
		this.toDateAsLong = this.to.toInstant().toEpochMilli();
	}
}
