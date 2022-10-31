package com.bilgeadam.boost.java04.lesson055.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oid;
	@Column(name = "hotel_name", length = 50, nullable = false, unique = true)
	private String name;
	@Column(length = 50, nullable = false)
	private String country;
	@Column(nullable = false)
	private int stars;
}
