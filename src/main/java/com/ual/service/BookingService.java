package com.ual.service;

import com.ual.dto.BookingDTO;
import com.ual.exception.URSException;

public interface BookingService {
	public String bookFlight(String flightId, Integer noOfPassengers) throws URSException;
	public BookingDTO viewBooking(String pnr) throws URSException;
	public String cancelBooking(String pnr) throws URSException;
}
