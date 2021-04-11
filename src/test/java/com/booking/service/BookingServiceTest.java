package com.booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.booking.dto.BookingDto;
import com.booking.entity.Hotel;
import com.booking.entity.User;
import com.booking.repository.HotelRepository;
import com.booking.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

	@InjectMocks
	private BookingService bookingService;

	@Mock
	private UserService userService;

	@Mock
	private HotelService hotelService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private HotelRepository hotelRepository;

	@Test
	void bookHotelByUerIdShouldBookHotelForUserAndSetStatusToBooked() {

		BookingDto booking = new BookingDto();
		booking.setHotelId(1L);
		booking.setRoomNumber("101");

		User user = new User();
		user.setUserId(1L);
		user.setBonusPoint(100L);

		Mockito.when(userService.getUser(Mockito.eq(1L))).thenReturn(user);

		Hotel hotel = new Hotel();
		hotel.setHotelId(1L);
		hotel.setRoomNumber("101");
		hotel.setPrice(90L);

		Mockito.when(hotelService.getHotelById(Mockito.eq(1L))).thenReturn(hotel);

		Hotel hotelBooked = new Hotel();
		hotelBooked.setHotelId(1L);
		hotelBooked.setRoomNumber("101");
		hotelBooked.setStatus("BOOKED");

		Mockito.when(hotelRepository.save(Mockito.any(Hotel.class))).thenReturn(hotelBooked);

		Hotel hotelSaved = bookingService.bookHotelByUerId(1L, booking);

		assertEquals("BOOKED", hotelSaved.getStatus());

		Mockito.verify(userService, Mockito.times(1)).getUser(Mockito.eq(1L));
		Mockito.verify(hotelService, Mockito.times(1)).getHotelById(Mockito.eq(1L));
		Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));

	}
	
	@Test
	void bookHotelByUerIdShouldBookHotelForUserAndSetStatusToPendingApproval() {

		BookingDto booking = new BookingDto();
		booking.setHotelId(1L);
		booking.setRoomNumber("101");

		User user = new User();
		user.setUserId(1L);
		user.setBonusPoint(50L);

		Mockito.when(userService.getUser(Mockito.eq(1L))).thenReturn(user);

		Hotel hotel = new Hotel();
		hotel.setHotelId(1L);
		hotel.setRoomNumber("101");
		hotel.setPrice(90L);

		Mockito.when(hotelService.getHotelById(Mockito.eq(1L))).thenReturn(hotel);

		Hotel hotelBooked = new Hotel();
		hotelBooked.setHotelId(1L);
		hotelBooked.setRoomNumber("101");
		hotelBooked.setStatus("PENDING APPROVAL");

		Mockito.when(hotelRepository.save(Mockito.any(Hotel.class))).thenReturn(hotelBooked);

		Hotel hotelSaved = bookingService.bookHotelByUerId(1L, booking);

		assertEquals("PENDING APPROVAL", hotelSaved.getStatus());

		Mockito.verify(userService, Mockito.times(1)).getUser(Mockito.eq(1L));
		Mockito.verify(hotelService, Mockito.times(1)).getHotelById(Mockito.eq(1L));
		Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));

	}
}
