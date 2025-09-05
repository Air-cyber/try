package com.ual.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ual.dto.FlightDTO;
import com.ual.entity.Flight;
import com.ual.exception.URSException;
import com.ual.repository.FlightRepository;

@Service
public class UnitedGoServiceImpl implements UnitedGoService {
	@Autowired
	private FlightRepository flightRepo;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public List<FlightDTO> getAllFlights() throws URSException {


		List<Flight> flightList = flightRepo.findAll();
		if (flightList == null || flightList.isEmpty()) {
	        throw new URSException("FLIGHT_NOT_FOUND");
	    }

	    return flightList.stream()
	            .map(flight -> mapper.map(flight, FlightDTO.class))
	            .toList();
		
	}
	
	public List<FlightDTO> searchFlights(String source, String destination, LocalDate date) throws URSException {

		List<Flight> flightList = flightRepo.searchFlights(source, destination, date);
		if (flightList == null || flightList.isEmpty()) {
			throw new URSException("FLIGHT_NOT_FOUND_SOURCE");
		}
		return flightList.stream()
				.map(flight -> mapper.map(flight, FlightDTO.class))
				.toList();
	}
	

}





















































































// This method should interact with the DAO to search flights based on source, destination, and date.
//UnitedGoDAO unitedGoDAO = new UnitedGoDAOImpl();

//System.err.println(flightList+"service ");
//if(!Validator.isValidSource(source)) {
//	throw new URSException("Validation.Invalid_Source");
//}
//if(!Validator.isValidDestination(destination)) {
//	throw new URSException("Validation.Invalid_Destination");
//}
//if(!Validator.isValidDate(date)) {
//	throw new URSException("Validation.Invalid_Date");
//}
//Validator validator = new Validator();
//validator.validate(source, destination, date);
//if (flightList != null && !flightList.isEmpty()) {
//	return flightList;
//}
//
//
//throw new URSException("FLIGHT_NOT_FOUND");


// This method should interact with the DAO to fetch all flights.
//UnitedGoDAO unitedGoDAO = new UnitedGoDAOImpl();
//List<Flight> flightList = unitedGoDAO.getAllFlights();
//if (flightList != null && !flightList.isEmpty()) {
//	return flightList;
//}
//
//
//throw new URSException("FLIGHT_NOT_FOUND")



//public String bookFlight(String flightId, Integer noOfPassengers) throws URSException {
//	UnitedGoDAO unitedGoDAO = new UnitedGoDAOImpl();
//	Validator validator = new Validator();
//	validator.validat(flightId, noOfPassengers);
//	if (!Validator.isValidFlightId(flightId)) {
//		throw new URSException("Validation.Invalid_FlightId");
//	}
//	if (!Validator.isValidPassengerCount(noOfPassengers)) {
//		throw new URSException("Validation.Invalid_Number_Of_Passengers");
//	}
//	String pnr = unitedGoDAO.bookFlight(flightId, noOfPassengers);
//
//	if (!Validator.isValidPNR(pnr)) {
//		throw new URSException("BOOKING_FAILED");
//	}
//	
//	return pnr;
//}
//
//@Override
//public String generatePNR(String flightId, String source, String destination, LocalDate date, Integer noOfPassengers) {
//	// This method should interact with the DAO to generate a PNR for the booked flight.
//	UnitedGoDAO unitedGoDAO = new UnitedGoDAOImpl();
//	String pnr = unitedGoDAO.generatePNR(flightId, source, destination, date, noOfPassengers);
//	if (pnr != null && !pnr.isEmpty()) {
//		return pnr;
//	}
//	
//	return null;
//}
