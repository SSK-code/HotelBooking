package com.booking.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.booking.dto.UserDto;
import com.booking.entity.User;
import com.booking.repository.UserRepository;

@Service
@Validated
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User createUser(@Valid UserDto userInput) {

		User user = new User();
		user.setFirstName(userInput.getFirstName());
		user.setLastName(userInput.getLastName());
		user.setBonusPoint(userInput.getBonusPoint());

		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public User getUser(@NotNull(message = "userId can not be null") Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User is not found with userId " + userId));
	}

	@Transactional
	public User updateUser(Long userId, UserDto userInput) {

		User user = getUser(userId);

		if (null != userInput.getFirstName()) {
			user.setFirstName(userInput.getFirstName());
		}
		if (null != userInput.getLastName()) {
			user.setLastName(userInput.getLastName());
		}
		if (null != userInput.getLastName()) {
			user.setBonusPoint(userInput.getBonusPoint());
		}

		return userRepository.save(user);
	}
}
