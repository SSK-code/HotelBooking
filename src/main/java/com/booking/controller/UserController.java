package com.booking.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.booking.dto.UserDto;
import com.booking.entity.User;
import com.booking.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author Shilanand
 * 
 * 
 *         UserController class hold the API to manage the user details. Support
 *         Create, Update, Delete, Get User
 *
 */

@RestController
@Tag(name = "User", description = "Hold the API to manage the user details."
		+ " Support Create, Update, Delete, Get User.")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = "/users")
	@Operation(summary = "Create a new user", description = "Returns created user")
	public ResponseEntity<User> createUser(@RequestBody UserDto user) {

		User userSaved = userService.createUser(user);

		HttpHeaders headers = new HttpHeaders();
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}")
				.buildAndExpand(userSaved.getUserId()).toUri();

		headers.setLocation(location);

		return new ResponseEntity<>(userSaved, headers, HttpStatus.CREATED);
	}

	@GetMapping(path = "/users")
	@Operation(summary = "Get all users", description = "Returns user list")
	public List<User> getAllUsers() {

		return userService.getAllUsers();
	}

	@GetMapping(path = "/users/{userId}")
	@Operation(summary = "Get a specific user by userId", description = "Returns a user")
	public User getUser(@PathVariable Long userId) {

		return userService.getUser(userId);
	}

	@PatchMapping(path = "/users/{userId}")
	@Operation(summary = "Update a specific user by userId", description = "Returns a updated user")
	public User updateUser(@PathVariable Long userId, @RequestBody UserDto user) {

		return userService.updateUser(userId, user);
	}

}
