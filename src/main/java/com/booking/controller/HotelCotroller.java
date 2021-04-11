package com.booking.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.booking.dto.HotelDto;
import com.booking.entity.Hotel;
import com.booking.service.HotelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * 
 * @author Shilanand
 * 
 *         HotelCotroller class hold the API to manage the hotel details. Support
 *         Create, Update, Delete, Get Hotel
 *
 */

@RestController
@Tag(name = "Hotel", description = "Hold the API to manage the hotel details."
		+ " Support Create, Update, Delete, Get Hotel.")
public class HotelCotroller {

	
	@Autowired
	private HotelService hotelService;

	@PostMapping(path = "/hotels")
	@Operation(summary = "Create a new hotel", description = "Returns created hotel")
	public ResponseEntity<Hotel> createHotel(@Valid @RequestBody HotelDto hotel) {

		Hotel hotelSaved = hotelService.createHotel(hotel);

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{hotelId}")
				.buildAndExpand(hotelSaved.getHotelId()).toUri();

		headers.setLocation(location);

		return new ResponseEntity<>(hotelSaved, headers, HttpStatus.CREATED);
	}

	@GetMapping(path = "/hotels")
	@Operation(summary = "Get all hotels", description = "Returns hotel list")
	public List<Hotel> getAllHotels() {

		return hotelService.getAllHotels();
	}

	@GetMapping(path = "/hotels/{hotelId}")
	@Operation(summary = "Get a specific hotel by hotelId", description = "Returns a Hotel")
	public Hotel getHotelById(@PathVariable Long hotelId) {

		return hotelService.getHotelById(hotelId);
	}

	@PatchMapping(path = "/hotels/{hotelId}")
	@Operation(summary = "Update a specific hotel by hotelId", description = "Returns a updated hotel")
	public Hotel updateHotel(@PathVariable Long hotelId, @RequestBody HotelDto hotel) {

		return hotelService.updateHotel(hotelId, hotel);
	}
	
	@GetMapping(path = "/hotels/rooms/{roomNumber}")
	@Operation(summary = "Get a specific hotel by roomNumber", description = "Returns a Hotel")
	public Hotel getHotelByRoomNumber(@PathVariable String roomNumber) {

		return hotelService.getHotelByRoomNumber(roomNumber);
	}
}
