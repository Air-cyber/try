//package com.ual.dao;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import com.ual.exception.URSException;
//import com.ual.dao.UnitedGoDAO;
//
//import com.ual.model.Flight;
//
//public class UnitedGoDAOImpl implements UnitedGoDAO {
//	private static List<Flight> flightList;
//	public UnitedGoDAOImpl() {
//		// Constructor to initialize the flight list if needed
//		flightList = new ArrayList<>();
//		Flight flight1 = new Flight("AI-732","AirIndia","Banglore","Mumbai","12:23","13:45",3,LocalDate.of(2025,12,22),2345.0);
//		Flight flight2 = new Flight("IE-263","Indigo","Delhi", "Chennai", "13:30", "16:45", 39,LocalDate.of(2025, 11, 13),5345.0);
//		Flight flight3 = new Flight("MG-435","SpiceJet","Kolkata","Hyderabad","14:00","16:30",83,LocalDate.of(2025, 10, 25),3456.0);
//		Flight flight4 = new Flight("UK-789","Vistarat","Mumbai","Pune","8:45","10:15",90,LocalDate.of(2025, 9, 30),4342.35);
//		Flight flight5 = new Flight("AI-123","AirIndia","Delhi","Banglore","10:00","12:30",54,LocalDate.of(2025, 12, 1),5000.0);
//		flightList.addAll(Arrays.asList(flight1, flight2, flight3, flight4, flight5));
//		
//	}
//	@Override
//	public List<Flight> getAllFlights() throws URSException {
//		
//		// This method should interact with the database to retrieve all flights.
//		
//		
//		if (flightList != null && !flightList.isEmpty()) {
//			return flightList;
//		}
//		
//		return flightList;
//	}
//	
//	@Override
//	public List<Flight> searchFlights(String source, String destination, LocalDate date) throws URSException {
//		// This method should interact with the database to search flights based on source, destination, and date.
//	//	getAllFlights();	
//		List<Flight> result = new ArrayList<Flight>();
//	//	System.err.println( !flightList.isEmpty() );
//	
//		if (flightList != null && !flightList.isEmpty()) {
//			for (Flight flight : flightList) {
//				if (flight.getSource().equalsIgnoreCase(source) && //Mumbai == Mumbai
//					flight.getDestination().equalsIgnoreCase(destination) && 
//					flight.getAvailableDate().equals(date)) {
//					result.add(flight);
//				}
//			}
//		}
//		
//		return result;
//	}
//	
//
//	@Override
//	public String bookFlight(String flightId, Integer noOfPassengers) throws URSException {
//		if (flightList != null && !flightList.isEmpty()) {
//			for (Flight flight : flightList) {
//				if (flight.getFlightId().equalsIgnoreCase(flightId)) {
//					if (flight.getAvailableSeats() >= noOfPassengers) {
//						flight.setAvailableSeats(flight.getAvailableSeats() - noOfPassengers);
//						return generatePNR(flightId, flight.getSource(), flight.getDestination(), flight.getAvailableDate(), noOfPassengers);
//					} else {
//					throw new URSException("BOOKING_FAILED");
//					}
//				}
//			}
//		}
//		throw new URSException("FLIGHT_NOT_FOUND");
//	}
//	
//	@Override
//	public String generatePNR(String flightId, String source, String destination, LocalDate date, Integer noOfPassengers) {
//		// Generate a random PNR in the format AAA123
//		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		StringBuilder pnrBuilder = new StringBuilder();
//		for (int i = 0; i < 3; i++) {
//			int idx = (int) (Math.random() * letters.length());
//			pnrBuilder.append(letters.charAt(idx));
//		}
//		for (int i = 0; i < 3; i++) {
//			int digit = (int) (Math.random() * 10);
//			pnrBuilder.append(digit);
//		}
//		return pnrBuilder.toString();
//	}
//	
//	
//
//	
//	
//
//}