package com.booking.repository;

import org.springframework.data.repository.CrudRepository;

import com.booking.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
