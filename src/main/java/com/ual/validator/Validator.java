package com.ual.validator;

import java.time.LocalDate;

import com.ual.exception.URSException;
import com.ual.service.UnitedGoServiceImpl; 

public class Validator {
	public static UnitedGoServiceImpl service= new UnitedGoServiceImpl();
	
	public  static void validate(String source, String destination, LocalDate date) throws URSException {
		if (!isValidSource(source)) {
			throw new URSException("Validation.Invalid_Source");
		}
		if (!isValidDestination(destination)) {
			throw new URSException("Validation.Invalid_Destination");
		}
		if (!isValidDate(date)) {
			throw new URSException("Validation.Invalid_Date");
		}
		
	}
	public static void validat(String flighId, int noOfPassengers) throws URSException {
		if (!isValidFlightId(flighId)) {
			throw new URSException("Validation.Invalid_FlightId");
		}
		if (!isValidPassengerCount(noOfPassengers)) {
			throw new URSException("Validation.Invalid_Number_Of_Passengers");
		}
		
	}
	
	
	
	
	
	public static boolean isValidPNR(String pnr) {
		if (pnr == null || pnr.isEmpty()) {
			return false;
		}
		return pnr.matches("^[A-Z]{3}[0-9]{3}$");
	}
	public static boolean isValidFlightId(String flightId) throws URSException {
		if (flightId == null || flightId.isEmpty()) {
			return false;
		}
		for(String id: service.getAllFlights().stream().map(flight -> flight.getFlightId()).toList()) {
			if (flightId.equalsIgnoreCase(id)) {
				return true;
			}
		}
		//return flightId.matches("^[A-Z]{2}-[0-9]{3}$");
		return false;
	}
	public static boolean isValidDate(LocalDate date) {
		if (date == null) {
			return false;
		} 
		
		LocalDate today = LocalDate.now();
		//System.out.println("Date: " + today);
		return !date.isBefore(today);	
		
	}
	public static boolean isValidSource(String source) throws URSException {
		if (source == null || source.isEmpty()) {
			return false;
		}
		for(String sc :service.getAllFlights().stream().map(flight -> flight.getSource()).toList())
		{
			if(source.equals(sc)) {
				return true;
			}
		}
		return false;
		//return source.matches("^[A-Z][a-z]+$");
	}
	public static boolean isValidDestination(String destination) throws URSException {
		if (destination == null || destination.isEmpty()) {
			return false;
		}
		for(String sc :service.getAllFlights().stream().map(flight -> flight.getDestination()).toList())
		{
			if(destination.equals(sc)) {
				return true;
			}
		}
		return false;
	//	return destination.matches("^[A-Z][a-z]+$");
	}
	public static boolean isValidPassengerCount(int count) {
		return count > 0 && count < 5;
	}
	public static boolean isValidFare(double fare) {
		return fare > 0;
	}
	
	

}