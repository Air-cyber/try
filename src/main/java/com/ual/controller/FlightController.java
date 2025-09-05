package com.ual.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ual.dto.BookingRequestDTO;
import com.ual.dto.FlightDTO;
import com.ual.exception.URSException;
import com.ual.service.BookingService;
import com.ual.service.UnitedGoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/flights")
public class FlightController {
	@Autowired
	private UnitedGoService unitedGoService;
	@Autowired
	private BookingService bookingService;
	
	@GetMapping(value = "/{source}/{destination}/{date}")
	public ResponseEntity<List<FlightDTO>> searchFlights(@Valid @RequestBody
			@PathVariable String source, 
			@Valid @RequestBody @PathVariable String destination,@Valid @RequestBody @PathVariable LocalDate date) throws URSException {
		
			List<FlightDTO> flights = unitedGoService.searchFlights(source, destination, date);
			return ResponseEntity.ok(flights);
		
	}
//	@PostMapping(value ="/book", consumes = "application/json")
//	public ResponseEntity<String> bookFlight(@Valid @RequestBody String flightId, Integer noOfPassengers) {
//		try {
//			String pnr = bookingService.bookFlight(flightId, noOfPassengers);
//			return ResponseEntity.ok(pnr);
//		} catch (URSException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//		
//	}
	
	@PostMapping(value ="/book")
	public ResponseEntity<String> bookFlight(@Valid @RequestBody BookingRequestDTO bookingRequest) throws URSException {
		
			String pnr = bookingService.bookFlight(bookingRequest.getFlightId(), bookingRequest.getNoOfPassengers());
			return ResponseEntity.ok(pnr);

		
	}
	
	
	
	
}







//http://localhost:8080/flights/Delhi/Banglore/2025-12-01
//http://localhost:8080/flights/book