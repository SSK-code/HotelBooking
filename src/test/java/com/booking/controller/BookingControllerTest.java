package com.booking.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.booking.dto.BookingDto;
import com.booking.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private BookingController bookingController;

	@Mock
	private BookingService bookingService;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
	}

	@Test
	void bookHotelByUerIdShouldBookHotelForUserAndReturnStatusIsOk() throws Exception {

		BookingDto booking = new BookingDto();
		booking.setHotelId(1L);
		booking.setRoomNumber("101");

		ObjectMapper objectMapper = new ObjectMapper();

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/hotels/book/1")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(booking));

		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isCreated());

		Mockito.verify(bookingService, Mockito.times(1)).bookHotelByUerId(Mockito.eq(1L), Mockito.any());
	}

}
