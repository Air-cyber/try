package com.ual.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ual.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, String>{
	@Query("SELECT f FROM Flight f WHERE f.source = :source AND f.destination = :destination AND f.date = :date ORDER BY f.fare ASC")
	List<Flight> searchFlights(String source, String destination, LocalDate date);
}
