package com.ual.service;

import java.time.LocalDate;
import java.util.List;

import com.ual.dto.FlightDTO;
import com.ual.exception.URSException;

public interface UnitedGoService {

	public List<FlightDTO> getAllFlights() throws URSException;
	public List<FlightDTO> searchFlights(String source, String destination,LocalDate date) throws URSException;
//	public  String bookFlight(String flightId, Integer noOfPassengers) throws URSException;
//	public String generatePNR(String flightId, String source, String destination, LocalDate date, Integer noOfPassengers);
	// Add the remaining methods if required as per the application requirement
	

}
