package com.bilgeadam.boost.java04.lesson055.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@ToString (exclude = {"hotel"})
@NoArgsConstructor
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oid;
	
	@Column(name = "room_number", nullable = false, unique = true)
	private int roomNumber;
	
	@Column(name = "number_of_beds", nullable = false)
	private int numOfBeds;

	@ManyToOne
	@JoinColumn(name = "hotel_oid", nullable = false)
	private Hotel hotel;
	
	@OneToMany(mappedBy = "room", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Booking> bookings;

	public Room(int roomNumber, int numOfBeds, Hotel hotel) {
		super();
		this.roomNumber = roomNumber;
		this.numOfBeds = numOfBeds;
		this.hotel = hotel;
	}
	
	public void addBooking(Booking booking) {
		if (this.bookings == null) {
			this.bookings = new HashSet<>();
		}
		this.bookings.add(booking);
	}
}
