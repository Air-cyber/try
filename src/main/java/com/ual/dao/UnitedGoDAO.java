package com.ual.dao;

import java.time.LocalDate;
import java.util.List;

import com.ual.entity.Flight;
import com.ual.exception.URSException;

public interface UnitedGoDAO {
	
	public List<Flight> getAllFlights() throws URSException;
	public List<Flight> searchFlights(String source, String destination,LocalDate date) throws URSException;
	public String bookFlight(String flightId, Integer noOfPassengers) throws URSException;
	public String generatePNR(String flightId, String source, String destination, LocalDate date, Integer noOfPassengers);
   //Add the remaining methods if required as per the application requirement
}
