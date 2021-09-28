package com.group.FakeMyspace.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.group.FakeMyspace.models.User;



public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	List<User> findAll();
	
	
	
}

