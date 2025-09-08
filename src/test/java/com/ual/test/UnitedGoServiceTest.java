package com.ual.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ual.dto.BookingRequestDTO;
import com.ual.dto.FlightDTO;
import com.ual.entity.Flight;
import com.ual.exception.URSException;
import com.ual.repository.FlightRepository;
import com.ual.service.BookingService;
import com.ual.service.UnitedGoService;

import jakarta.transaction.Transactional;

 
@SpringBootTest
@Transactional
class UnitedGoServiceTest {
 
    @Autowired
    private BookingService bookingService;
 
    @Autowired
    private UnitedGoService flightService;
 
    @Autowired
    private FlightRepository flightRepo;
 
    @BeforeEach
    void setUp() {
        Flight flight = new Flight();
        flight.setFlightId("AI-123");
        flight.setAirline("Air India");
        flight.setSource("Delhi");
        flight.setDestination("Banglore");
        flight.setAvailableSeats(10);
        flight.setFare(1500.0);
        flight.setDate(LocalDate.now());
        flight.setDepartureTime("10:00");
        flight.setArrivalTime("12:30");
 
        flightRepo.save(flight);
    }
 
    @Test
    void testSearchFlights_Valid() throws URSException {
        List<FlightDTO> flights = flightService.searchFlights("Delhi", "Banglore", LocalDate.now());
        assertEquals(1, flights.size());
        assertEquals("AI-123", flights.get(0).getFlightId());
        System.out.println("Flight found: " + flights.get(0).getFlightId());
    }
 
    @Test
    void testSearchFlights_InvalidRoute() {
        URSException ex = assertThrows(URSException.class, () ->
            flightService.searchFlights("Invalid", "Nowhere", LocalDate.now())
        );
        assertEquals("FLIGHT_NOT_FOUND_SOURCE", ex.getMessage());
    }
    

    @Test
    void testDeleteBooking_InvalidPNR() {
        URSException ex = assertThrows(URSException.class, () ->
            bookingService.cancelBooking("URS999")
        );
        assertEquals("BOOKING_NOT_FOUND", ex.getMessage());
    }
 
//    @Test
//    void testGetBooking_ValidPNR() throws URSException {
//        
// 
//        String booking = "URS980";
//        BookingDTO fetched = bookingService.viewBooking(booking);
// 
//        assertEquals(booking, fetched.getPNR());
//       // assertEquals(1500.0, fetched.getTotalFare());
//    }
 
    @Test
    void testGetBooking_InvalidPNR() {
        URSException ex = assertThrows(URSException.class, () ->
            bookingService.viewBooking("URS000")
        );
        assertEquals("PNR_NOT_FOUND", ex.getMessage());
    }
    
    @Test
    void testBookFlight_Valid() throws URSException {
        
    	BookingRequestDTO request = new BookingRequestDTO();
        request.setFlightId("AI-123");
        request.setNoOfPassengers(2);
 
        String response = bookingService.bookFlight(request.getFlightId(), request.getNoOfPassengers());
 
        assertNotNull(response);
        
    }
 
    @Test
    void testBookFlight_InvalidFlightId() {
        BookingRequestDTO request = new BookingRequestDTO();
        request.setFlightId("INVALID");
        request.setNoOfPassengers(1);
 
        URSException ex = assertThrows(URSException.class, () ->
            bookingService.bookFlight(request.getFlightId(), request.getNoOfPassengers())
        );
        assertEquals("FLIGHT_NOT_FOUND", ex.getMessage());
    }

//    @Test
//    void testCancelFlight_Valid() throws URSException {
//        String pnr = "URS980";
//        String message = bookingService.cancelBooking(pnr);
//        assertNotNull(message);
//        //assertEquals("Flight cancelled successfully.", message);
//    }

    @Test
    void testBookFlight_InvalidPassengerCount() {
		BookingRequestDTO request = new BookingRequestDTO();
		request.setFlightId("AI-123");
		request.setNoOfPassengers(1000); // Invalid passenger count
 
		URSException ex = assertThrows(URSException.class, () ->
			bookingService.bookFlight(request.getFlightId(), request.getNoOfPassengers())
		);
		assertEquals("INSUFFICIENT_SEATS", ex.getMessage());
	}
    @Test
    void testCancelFlight_AfterDeparture() {
		String pnr = "URS619";
		URSException ex = assertThrows(URSException.class, () ->
			bookingService.cancelBooking(pnr)
		);
		System.out.println("Message: " + ex.getMessage());
		assertEquals("CANNOT_CANCEL_AFTER_DEPARTURE", ex.getMessage());
	}
    

}

























































//package com.ual.test;
//
//import static org.junit.Assert.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import com.ual.entity.Flight;
//import com.ual.exception.URSException;
//import com.ual.service.UnitedGoService;
//import com.ual.service.UnitedGoServiceImpl;
//
//import java.time.LocalDate;
//
//public class UnitedGoServiceTest {
//
//
//	@Test
//	void bookFlightValidTest() {
//		String flightId = "UA123"; // Use a valid flightId
//	    Integer noOfPassengers = 3;
//
//	    UnitedGoService service = new UnitedGoServiceImpl();
////	    System.out.println("Service created: " + service);
//	    
//	    try {
//	        String actualFlightId = service.bookFlight(flightId, noOfPassengers);
//	     
//	        assertNotNull(actualFlightId, "The flight ID should not be null");
//	        assertEquals(flightId, actualFlightId, "The booked flight ID should match the requested flight ID");
//	    } catch (Exception e) {
//	      //  System.out.println("Exception occurred: " + e.getMessage());
//	    }
//	}
//	 @Test
//	 void searchFlightsValidTest() throws URSException {
//	       // UnitedGoService service;
//		  	UnitedGoService service = new UnitedGoServiceImpl();
//			List<Flight> result = service.searchFlights("Mumbai", "Pune", LocalDate.of(2025, 9, 30));
//			assertNotNull(result, "The result should not be null");
//	        assertFalse(result.isEmpty(), "The result should not be empty");
//	        
//	}
//	 
//	 @Test
//	 void invalidSourceSearchFlightsTest() {
//			UnitedGoService service = new UnitedGoServiceImpl();
//			try {
//				List<Flight> result = service.searchFlights("InvalidSource", "Pune", LocalDate.of(2025, 9, 30));
//				assertFalse(result.isEmpty(), "The result should not be empty for invalid source");
//			} catch (Exception e) {
//				assertEquals("Validation.Invalid_Source", e.getMessage(), "Expected exception message for invalid source");
//			}
//		}
//	
//	@Test
//	void bookFlightInvalidFlightIdTest() {
//		String flightId = "INVALID123"; // Use an invalid flightId
//	    Integer noOfPassengers = 3;
//
//	    UnitedGoService service = new UnitedGoServiceImpl();
////	    System.out.println("Service created: " + service);
//	    
//	    try {
//	        String actualFlightId = service.bookFlight(flightId, noOfPassengers);
//	  
//	    } catch (Exception e) {
//	     //   System.out.println("Exception occurred: " + e.getMessage());
//	        assertEquals("Validation.Invalid_FlightId", e.getMessage(), "Expected exception message for invalid flight ID");
//	    }
//	}
//}