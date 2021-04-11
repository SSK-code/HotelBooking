package com.booking.service;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.booking.dto.HotelDto;
import com.booking.entity.Hotel;
import com.booking.repository.HotelRepository;

@Service
@Validated
public class HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Transactional
	public Hotel createHotel(HotelDto hotelInput) {

		Hotel hotel = new Hotel();
		hotel.setRoomNumber(hotelInput.getRoomNumber());
		hotel.setHotelName(hotelInput.getHotelName());
		hotel.setPrice(hotelInput.getPrice());

		return hotelRepository.save(hotel);
	}

	public List<Hotel> getAllHotels() {

		return (List<Hotel>) hotelRepository.findAll();
	}

	public Hotel getHotelById(@NotNull(message = "hotelId can not be null") Long hotelId) {

		return hotelRepository.findById(hotelId)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel is not found with hotelId " + hotelId));
	}

	@Transactional
	public Hotel updateHotel(@NotNull(message = "hotelId can not be null")Long hotelId, HotelDto hotelInput) {

		Hotel hotel = getHotelById(hotelId);

		if (null != hotelInput.getRoomNumber()) {
			hotel.setRoomNumber(hotelInput.getRoomNumber());
		}

		if (null != hotelInput.getHotelName()) {
			hotel.setHotelName(hotelInput.getHotelName());
		}

		if (null != hotelInput.getPrice()) {
			hotel.setPrice(hotelInput.getPrice());
		}

		return hotelRepository.save(hotel);
	}

	public Hotel getHotelByRoomNumber(@NotBlank(message = "roomNumber can not be blank") String roomNumber) {

		return hotelRepository.findByRoomNumber(roomNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel is not found with roomNumber " + roomNumber));
	}

}
