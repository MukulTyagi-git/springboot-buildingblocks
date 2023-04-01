package com.stacksimplify.restservices.springbootbuildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.springbootbuildingblocks.Entity.User;
import com.stacksimplify.restservices.springbootbuildingblocks.repository.UserRepository;

// Service Creation
@Service
public class UserService {

	@Autowired
	
	//Auto wired User Repository
	private UserRepository userRepository;
	
	//Get All User Method
	
	
	public List<User> getAllUsers()
	{
		
		return userRepository.findAll();
	}
	
	// Create User Method
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	// Find User by Id Method
	
	public Optional<User> findUserById(long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}

	// Update User by id
	
	public User updateUserByid(long id, User user) {
		user.setId(id);
		return userRepository.save(user);
		
	}
	
	// Delete User by id
	
	public void deleteUserByid(long id) {
		
		if (userRepository.findById(id).isPresent())
		{
			userRepository.deleteById(id);
		}
	}
	
	// Find User by user name 
	
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	
}
	


