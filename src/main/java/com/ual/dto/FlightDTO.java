package com.ual.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FlightDTO {
	@NotBlank(message = "{Validation.NOT_BLANK_FLIGHTID}")
	@Pattern(regexp = "^[A-Z]{2}-[0-9]{3}$", message = "{Validation.Invalid_FlightID}")
	private String flightId;
	
	@NotBlank(message = "{Validation.NOT_BLANK_FLIGHTNAME}")
    @Pattern(regexp = "^[A-Z][a-z]+$", message = "{Validation.Invalid_FlightName}")
	private String airline;
	
	@NotBlank(message = "{Validation.NOT_BLANK_SOURCE}")
	@Pattern(regexp = "^[A-Z][a-z]+$", message = "{Validation.Invalid_Source}")
	private String source;
	
	@NotBlank(message = "{Validation.NOT_BLANK_DESTINATION}")
	@Pattern(regexp = "^[A-Z][a-z]+$", message = "{Validation.Invalid_Destination}")
	private String destination;
	
	@NotBlank(message = "{Validation.NOT_BLANK_DEPARTURE}")
	@Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "{Validation.Invalid_Departure}")
	private String departureTime;
	
	
	@NotBlank(message = "{Validation.NOT_BLANK_ARRIVAL}")
	@Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "{Validation.Invalid_Arrival}")
	private String arrivalTime;
	
	@NotNull(message = "{Validation.NOT_BLANK_SEATS}")
	@Min(value = 1, message = "{Validation.Invalid_Seats}")   
	private Integer availableSeats;
	
	@NotNull(message = "{Validation.NOT_BLANK_DATE}")
    @FutureOrPresent(message = "{Validation.Invalid_Date}")
	private LocalDate date;
	@NotNull(message = "{Validation.NOT_BLANK_FARE}")
    @Positive(message = "{Validation.Invaid_Fare}")
	private Double fare;
}
