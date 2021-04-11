package com.booking.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.booking.dto.BookingDto;
import com.booking.entity.Hotel;
import com.booking.entity.User;
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
	public Hotel bookHotelByUerId(Long userId, @Valid BookingDto booking) {

		User user = userService.getUser(userId);

		Hotel hotel = hotelService.getHotelById(booking.getHotelId());

		if (user.getBonusPoint() >= hotel.getPrice()) {
			user.setBonusPoint(user.getBonusPoint() - hotel.getPrice());
			hotel.setStatus("BOOKED");
			hotel.setUser(user);
		} else {
			hotel.setStatus("PENDING APPROVAL");
			hotel.setUser(user);
		}

		userRepository.save(user);

		return hotelRepository.save(hotel);
	}

}
