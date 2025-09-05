package com.ual.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
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
public class BookingDTO {
	@NotBlank(message = "{Validation.NOT_BLANK_PNR}")
    @Pattern(regexp = "^URS\\d{3}$", message = "{Validation.Invalid_PNR}")	
	private String PNR;
	@NotNull(message = "{Validation.NOT_BLANK_PASSENGER}")
    @Min(value = 1, message = "{Validation.Invalid_NoOfPassenger}")
	@Max(value = 4, message = "{Validation.Invalid_NoOfPassenger}")
	private Integer noOfPassengers;
	@NotNull(message = "{Validation.NOT_BLANK_FARE}")
    @Positive(message = "{Validation.Invaid_Fare}")
	private Double totalFare;
	@NotNull(message = "{Validation.NOT_BLANK_FLIGHT}")
    @Valid
	private FlightDTO flight; // Assuming FlightDTO is another DTO class that contains flight details

}
