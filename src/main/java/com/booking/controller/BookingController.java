package com.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.booking.dto.BookingDto;
import com.booking.entity.Hotel;
import com.booking.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author Shilanand
 * 
 *         BookingController books the hotel for user
 *
 */

@RestController
@Tag(name = "Booking", description = "Book hotel for user")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping(path = "/hotels/book/{userId}")
	@Operation(summary = "Book a hotel for user by userId", description = "Returns booked hotel")
	public ResponseEntity<Hotel> bookHotelByUerId(@PathVariable Long userId, @RequestBody BookingDto booking) {

		Hotel hotelBooked = bookingService.bookHotelByUerId(userId, booking);

		return new ResponseEntity<>(hotelBooked, HttpStatus.CREATED);
	}

//	@GetMapping(path = "/hotels/book/{userId}")
//	@Operation(summary = "Get Booked hotel for user by userId", description = "Returns booked hotel")
//	public Hotel getBookingUerId(@PathVariable Long userId) {
//
//		return  bookingService.bookHotelByUerId(userId);
//	}
}
