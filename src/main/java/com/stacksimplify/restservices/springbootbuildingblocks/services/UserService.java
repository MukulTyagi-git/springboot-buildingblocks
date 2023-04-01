package com.stacksimplify.restservices.springbootbuildingblocks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.springbootbuildingblocks.Entity.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exception.UserExistsException;
import com.stacksimplify.restservices.springbootbuildingblocks.exception.UserNotFoundException;
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
	
	public User createUser(User user) throws UserExistsException{
		
		User ExistsUser = userRepository.findByUsername(user.getUsername());
		
		if (ExistsUser != null)
		{
			throw new UserExistsException("User with same username already in Record");
		}
		
		else
		{
			return userRepository.save(user);
		}
		
	}
	
	// Find User by Id Method
	
	public Optional<User> findUserById(long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		
		if (!user.isPresent()) {
			
			throw new UserNotFoundException("User Not Found in Record"); 
		}
		return user;
	}

	// Update User by id
	
	public User updateUserByid(long id, User user) throws UserNotFoundException {
        Optional<User> user2 = userRepository.findById(id);
		
		if (!user2.isPresent()) {
			
			throw new UserNotFoundException("User Not Found in Record Provide correct id"); 
		}
		
		user.setId(id);
		return userRepository.save(user);
		
	}
	
	// Delete User by id
	
	public void deleteUserByid(long id) throws UserNotFoundException {
		
		 Optional<User> user3 = userRepository.findById(id);
			
			if (!user3.isPresent()) {
				
				throw new UserNotFoundException("User Not Found in Record to delete, Provide correct id"); 
			}
			
	
			userRepository.deleteById(id);
	
	}
	
	// Find User by user name 
	
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}
	
	
}
	


