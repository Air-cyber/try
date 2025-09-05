package com.ual.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingRequestDTO {
	@NotBlank(message = "{Validation.NOT_BLANK_FLIGHTID}")
	@Pattern(regexp = "^[A-Z]{2}-[0-9]{3}$", message = "{Validation.Invalid_FlightID}")	
	private String flightId;
	@NotNull(message = "{Validation.NOT_BLANK_PASSENGER}")
    @Min(value = 1, message = "{Validation.Invalid_NoOfPassenger}")
	@Max(value = 4, message = "{Validation.Invalid_NoOfPassenger}")
	private Integer noOfPassengers;
}
