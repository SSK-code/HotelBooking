package com.booking.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.booking.dto.BookingDto;
import com.booking.dto.UserDto;
import com.booking.entity.Hotel;
import com.booking.entity.User;
import com.booking.exception.InputDataValidationException;
import com.booking.repository.HotelRepository;
import com.booking.repository.UserRepository;

@Service
@Validated
public class BookingService {

	@Autowired
	private UserService userService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Transactional
	public Hotel bookHotelByUerId(@NotNull(message = "userId can not be null") Long userId, @Valid BookingDto booking) {

		User user = userService.getUser(userId);

		Hotel hotel = hotelService.getHotelById(booking.getHotelId());

		if (hotel.getUser() == null) {
			if (user.getBonusPoint() >= hotel.getPrice()) {
				user.setBonusPoint(user.getBonusPoint() - hotel.getPrice());
				hotel.setStatus("BOOKED");
				hotel.setUser(user);
			} else {
				hotel.setStatus("PENDING APPROVAL");
				hotel.setUser(user);
			}
		} else {
			if (user.getBonusPoint() >= hotel.getPrice()) {
				user.setBonusPoint(user.getBonusPoint() - hotel.getPrice());
				hotel.setStatus("BOOKED");
				hotel.setUser(user);
			}
		}

		userRepository.save(user);

		return hotelRepository.save(hotel);
	}

	@Transactional
	public Hotel updateBooking(@NotNull(message = "userId can not be null") Long userId, UserDto userInput) {

		if (new UserDto().equals(userInput)) {

			throw new InputDataValidationException("Please provide bonus points to update.");
		}

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User is not found with userId " + userId));

		if (userInput.getBonusPoint() != null) {
			user.setBonusPoint(userInput.getBonusPoint());
		}

		Hotel hotel = hotelRepository.findByUserUserId(userId)
				.orElseThrow(() -> new InputDataValidationException("Hotel is not found with userId " + userId));

		if ("PENDING APPROVAL".equals(hotel.getStatus()) && user.getBonusPoint() >= hotel.getPrice()) {
			hotel.setStatus("BOOKED");
			user.setBonusPoint(user.getBonusPoint() - hotel.getPrice());
		}

		userRepository.save(user);

		return hotelRepository.save(hotel);
	}

}
