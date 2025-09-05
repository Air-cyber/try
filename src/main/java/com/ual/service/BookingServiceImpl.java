package com.ual.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ual.dto.BookingDTO;
import com.ual.entity.Booking;
import com.ual.entity.Flight;
import com.ual.exception.URSException;
import com.ual.repository.BookingRepository;
import com.ual.repository.FlightRepository;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	private FlightRepository flightRepo;
	@Autowired
	Environment env;
	private ModelMapper mapper = new ModelMapper();
	
	public String generatePNR(String flightId, String source, String destination, LocalDate date, Integer noOfPassengers) {
	    StringBuilder pnrBuilder = new StringBuilder("URS");
	    for (int i = 0; i < 3; i++) {
	        int digit = (int) (Math.random() * 10);
	        pnrBuilder.append(digit);
	    }
	    return pnrBuilder.toString();
	}

	
	@Override
	public String bookFlight(String flightId, Integer noOfPassengers) throws URSException {
	    
	    Flight flight = flightRepo.findById(flightId)
	            .orElseThrow(() -> new URSException("FLIGHT_NOT_FOUND"));

	    
	    if (flight.getAvailableSeats() < noOfPassengers) {
	        throw new URSException("INSUFFICIENT_SEATS");
	    }

	    
	    flight.setAvailableSeats(flight.getAvailableSeats() - noOfPassengers);
	    flightRepo.save(flight);

	    
	    Booking booking = new Booking();
	    booking.setFlight(flight);
	    booking.setNoOfPassengers(noOfPassengers);

	    
	    String pnr = generatePNR(
	            flightId,
	            flight.getSource(),
	            flight.getDestination(),
	            flight.getDate(),
	            noOfPassengers
	    );
	    booking.setPNR(pnr);
	    Double TotalFare = flight.getFare() * noOfPassengers;
	    booking.setTotalFare(TotalFare);

	    
	    bookingRepo.save(booking);

	    return pnr;
	}

	@Override
	public  BookingDTO viewBooking(String pnr) throws URSException {
		
		Booking booking = bookingRepo.findByPNR(pnr);
				

		
		if (booking == null) {
			throw new URSException("PNR_NOT_FOUND");
		}

		BookingDTO bookingDTO = mapper.map(booking, BookingDTO.class);
		
		return bookingDTO;
	}


	@Override
	public String cancelBooking(String pnr) throws URSException {
	    Booking booking = bookingRepo.findByPNR(pnr);
	    if (booking == null) {
	        throw new URSException("BOOKING_NOT_FOUND");
	    }

	    Flight flight = booking.getFlight();

	    
	    LocalDateTime departureDateTime = LocalDateTime.of(
	            flight.getDate(),
	            LocalTime.parse(flight.getDepartureTime()) // Assuming HH:mm format
	    );

	    LocalDateTime now = LocalDateTime.now();

	    
	    if (now.isAfter(departureDateTime)) {
	        throw new URSException("CANNOT_CANCEL_AFTER_DEPARTURE");
	    }

	    String refundMessage;
	    
	    if (Duration.between(now, departureDateTime).toHours() < 6) {
	        refundMessage = "Booking cancelled. No refund will be provided.";
	    } 
	 	    else {
	        refundMessage = "Booking cancelled. Full refund will be provided.";
	    }

	    
	    flight.setAvailableSeats(flight.getAvailableSeats() + booking.getNoOfPassengers());
	    flightRepo.save(flight);

	    
	    bookingRepo.delete(booking);

	    return "Booking with PNR " + pnr + " has been cancelled successfully. " + refundMessage;
	}




	
}


