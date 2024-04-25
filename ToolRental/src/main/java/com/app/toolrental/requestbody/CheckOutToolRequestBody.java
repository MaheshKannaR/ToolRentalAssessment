package com.app.toolrental.requestbody;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
// Request body to read the inputs for checking out tool
public class CheckOutToolRequestBody {
	private final String toolCode;
	private final Integer rentalDays;
	private final Integer discountPercentage; 
	private final LocalDate checkoutDate;
}
