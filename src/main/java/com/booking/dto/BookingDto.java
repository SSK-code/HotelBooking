package com.booking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookingDto {

	@NotNull(message = "hotelId is required input")
	private Long hotelId;
	
	@NotBlank(message = "roomNumber is required input")
	private String roomNumber;

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

}
