package com.ual.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ual.dto.BookingDTO;
import com.ual.exception.URSException;
import com.ual.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@GetMapping(value = "/{pnr}")
	public ResponseEntity<BookingDTO> viewBooking(@Valid @PathVariable String pnr) throws URSException {
		
		
			BookingDTO booking = bookingService.viewBooking(pnr);
			return ResponseEntity.ok(booking);
		
	}
	
	@DeleteMapping(value = "/cancel/{pnr}")
	public ResponseEntity<String> cancelBooking(@Valid @PathVariable String pnr) throws URSException {
		
			String message = bookingService.cancelBooking(pnr);
			return ResponseEntity.ok(message);
		
	}
	
	
	
}
//http://localhost:8080/bookings/cancel/KLB596
