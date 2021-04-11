package com.booking.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HotelDto {

	@NotBlank(message = "roomNumber is reuired input")
	private String roomNumber;

	@NotBlank(message = "hotelName is reuired input")
	private String hotelName;

	@NotNull(message = "price is reuired input")
	private Long price;

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

}
