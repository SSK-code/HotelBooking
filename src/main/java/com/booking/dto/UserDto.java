package com.booking.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {

	@NotBlank(message = "firstName is required input")
	private String firstName;
	
	private String lastName;
	
	private Long bonusPoint = 0L;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(Long bonusPoint) {
		this.bonusPoint = bonusPoint;
	}
	
	
}
