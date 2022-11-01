package com.bilgeadam.boost.java04.lesson055.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * Booking tablosunun eşsiz anahtarı (primary key / primary index) room ve customer
 * tablolarının oid'lerinde oluşan tümleşik bir anahtar. Burada onu yaratmaya 
 * çalışıyoruz. 
 *
 */
@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
//@EqualsAndHashCode ==> kullanılabilir
public class BookingPrimaryKey implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long customerOID;
	private Long roomOID;
	
	@Override
	public int hashCode() {
		final int prime = 17;
		int retVal = 1;
		
		retVal = prime * retVal + ((customerOID == null) ? 0 : customerOID.hashCode());
		retVal = prime * retVal + ((roomOID == null) ? 0 : roomOID.hashCode());
		
		return retVal;
	}
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) return false;
		
		if (!(obj instanceof BookingPrimaryKey)) return false;
		
		BookingPrimaryKey other = (BookingPrimaryKey)obj;
		
		return Objects.equals(this.getCustomerOID(), 	other.getCustomerOID()) &&
			   Objects.equals(this.getRoomOID(), 		other.getRoomOID());
	}
	
	
}
