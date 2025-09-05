package com.ual;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ual.dto.BookingDTO;
import com.ual.dto.FlightDTO;
import com.ual.exception.URSException;
import com.ual.service.BookingService;
import com.ual.service.UnitedGoServiceImpl;

@SpringBootApplication(scanBasePackages = "com.ual")
public class Main implements CommandLineRunner{
	@Autowired
	private UnitedGoServiceImpl unitedGoService;
	@Autowired
	private BookingService bookingService;
	
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
		//getflight();		
		//search();
		//bookFlight("MG-435", 3);
		//viewBooking("LVA379");
		//cancel();
		
		
		
	}
	private void getflight() throws URSException
	{
		List<FlightDTO> flights = unitedGoService.getAllFlights();
		for(FlightDTO flight : flights) {
			System.out.println(flight);
		}
	}
	
	private void search() throws URSException
	{
		List<FlightDTO> flights = unitedGoService.searchFlights("Delhi", "Banglore", LocalDate.of(2025, 12, 1));
		for(FlightDTO flight : flights) {
			System.out.println(flight);
		}
	}
	private void bookFlight(String flightId, Integer noOfPassengers) throws URSException {
		String pnr = bookingService.bookFlight(flightId, noOfPassengers);
		System.out.println("Booking successful! Your PNR is: " + pnr);
	}
	private void viewBooking(String pnr) throws URSException {
		 BookingDTO booking = bookingService.viewBooking(pnr);
		 System.out.println(booking);
	}
	private void cancel() throws URSException
	{
		String msg= bookingService.cancelBooking("URS619");
		System.out.println(msg);
	}
}






















































































//package com.ual;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.apache.commons.configuration2.PropertiesConfiguration;
//import org.apache.commons.configuration2.builder.fluent.Configurations;
//import org.apache.commons.configuration2.ex.ConfigurationException;
//
//import com.ual.entity.Flight;
//import com.ual.exception.*;
//import com.ual.service.UnitedGoServiceImpl;
//
//import lombok.extern.log4j.Log4j2;
//
//@Log4j2
//public class Main {
//
//	public static void main(String[] args) throws ConfigurationException {
//		// Initialize the application and data of all flights present with validator of Flight not found
//		getAllFlights();	
//		
//		//Search of flights based on source, destination and date and validator present to check if flights are available or not through database 
//		//using ursexeception
//		searchFlights("Delhi", "Banglore", LocalDate.of(2025, 12, 1));
//		
//		// Book a flight with flight ID and number of passengers and validator present to check if flight is available or not through database using URSException
//		bookFlight("MG-435", 3);
//		// Add the remaining methods if required as per the application requirement
//
//	}
//	
//	// Display function to log the flight details in a formatted manner
//	private static void logFlights(List<Flight> flights) {
//	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	
//	     String header = String.format("%-10s %-15s %-15s %-15s %-15s %-15s %-10s %-15s %-10s",
//	             "FlightID", "Airline", "Source", "Destination", "ArrivalTime", "DepartureTime", "Seats", "Date", "Fare");
//	     log.info(header);
//	     log.info("=".repeat(header.length()));
//	
//	     for (Flight flight : flights) {
//	         String formattedDate = flight.getAvailableDate() != null ? flight.getAvailableDate().format(formatter) : "N/A";
//	         String row = String.format("%-10s %-15s %-15s %-15s %-15s %-15s %-10d %-15s $%-9.2f",
//	                 flight.getFlightId(),
//	                 flight.getAirline(),
//	                 flight.getSource(),
//	                 flight.getDestination(),
//	                 flight.getArrivalTime(),
//	                 flight.getDepartureTime(),
//	                 flight.getAvailableSeats(),
//	                 formattedDate,
//	                 flight.getFare());
//	         log.info(row);
//	     }
//	 }
//
//	public static void getAllFlights() throws ConfigurationException {
//		Configurations configurations = new Configurations();
//		PropertiesConfiguration config = configurations.properties("configuration.properties");
//
//		try {
//			
//			UnitedGoServiceImpl service = new UnitedGoServiceImpl();
//			List<Flight> flights = service.getAllFlights();
//			logFlights(flights);
//
//		} catch (Exception exception) {
//			log.error(config.getProperty(exception.getMessage()));
//		}
//	}
//	
//	public static void searchFlights(String source,String destination,LocalDate date) throws ConfigurationException {
//		
//		Configurations configurations = new Configurations();
//		PropertiesConfiguration config = configurations.properties("configuration.properties");
//		UnitedGoServiceImpl service = new UnitedGoServiceImpl();
//		
//		try {
//			List<Flight> flights = service.searchFlights(source, destination, date);
//			if (flights.isEmpty()) {
//				throw new URSException("FLIGHT_NOT_FOUND");
//			} else {
//				logFlights(flights);
//
//				}
//			
//			
//		} catch (URSException exception) {
//			log.error(config.getProperty(exception.getMessage()));
//			
//		} catch (Exception exception) {
//			log.error(exception.getMessage());
//					}
//		
//	}
//	
//	public static void bookFlight(String flightId, Integer noOfPassengers) throws ConfigurationException {
//		Configurations configurations = new Configurations();
//		PropertiesConfiguration config = configurations.properties("configuration.properties");
//		
//		try {
//			UnitedGoServiceImpl service = new UnitedGoServiceImpl();
//			String pnr = service.bookFlight(flightId, noOfPassengers);
//			System.out.println("Booking successful! Your PNR is: " + pnr);
//			
//		} catch (URSException exception) {
//			log.error(config.getProperty(exception.getMessage()));
//		} catch (Exception exception) {
//			log.error(exception.getMessage());
//		}
//		
//	}
//		
//
//}