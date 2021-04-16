package com.booking.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.booking.entity.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

	Optional<Hotel> findByRoomNumber(String roomNumber);

	Optional<Hotel> findByUserUserId(Long userId);

}
