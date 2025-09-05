package com.ual.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor 
@AllArgsConstructor
public class Booking {
	@Id
	private String PNR;
	
	
	private Integer noOfPassengers;
	private Double totalFare;
	
	@ManyToOne
	@JoinColumn(name="flight_id")
	private Flight flight;
	
	
	

	
	
}