package com.bilgeadam.boost.java04.lesson055.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "hotels")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Hotel {
	public Hotel(String name, String country, int stars) {
		super();
		this.name = name;
		this.country = country;
		this.stars = stars;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oid;
	@Column(name = "hotel_name", length = 50, nullable = false, unique = true)
	private String name;
	@Column(length = 50, nullable = false)
	private String country;
	@Column(nullable = false)
	private int stars;
	
	@OneToMany(mappedBy = "hotel")  // Room sınıfındaki field'in adı
	private Set<Room> rooms;
	
	public void addRoom(Room room) {
		if (this.rooms == null) {
			this.rooms = new HashSet<>();
		}
		this.rooms.add(room);
	}
}
