package com.stacksimplify.restservices.springbootbuildingblocks.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.restservices.springbootbuildingblocks.Entity.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exception.UserExistsException;
import com.stacksimplify.restservices.springbootbuildingblocks.exception.UserNameNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.exception.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

//Controller

@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {
	
	// Auto wired UserService
	
	@Autowired
	private UserService userService;
	
// ------------Get All users Method--------------------------------------------
      //By Request Mapping
	
	//@RequestMapping(method = RequestMethod.GET, path = "/users")
	
	  //By Get Mapping
	@GetMapping()
	public List<User> getAllUsers(){
	
		return userService.getAllUsers();
	}

//----------Create User Method-------------------------------------------
	
	
	@PostMapping("/createuser")
	
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user , UriComponentsBuilder builder)
	{
		try {
		userService.createUser(user);
		HttpHeaders header = new HttpHeader();
		header.setLocation(builder.path("/serachusersbyid/{id}").buildAndExpand(user.getId()).toUri());	
		return new ResponseEntity<Void>(header ,HttpStatus.CREATED);
	
		}
		catch (UserExistsException ex){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
//---------Find User By Id Method -------------------
	@GetMapping("/serachusersbyid/{id}")
	
	public Optional <User> getUserById(@PathVariable("id") @Min(1) long id) throws UserNotFoundException {
		
		try {
		Optional<User> user = userService.findUserById(id);
		
		return user;	
		}
		
		catch (UserNotFoundException ex){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
//--------Update User By Id Method-----------------
	
	@PutMapping("/updateuserbyid/{id}")
	public User updateUserById(@PathVariable("id") long id , @RequestBody User user)
	{
		try {
	return userService.updateUserByid(id, user);
		}
		
		catch (UserNotFoundException ex){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
		
	}
	
//------Delete by Id Method-------------------
	
	@DeleteMapping("/deleteuser/{id}")
	
	public void deleteUserByid(@PathVariable("id") long id) throws UserNotFoundException {
		Optional<User> user = userService.findUserById(id);
		
		 if(!user.isPresent())
		 {
			 throw new UserNotFoundException("UserId : '" +id + "' not found in record");
		 }
		 userService.deleteUserByid(id);
	}
	
//--------------Find by user name method ---------------------
	
	@GetMapping("/searchbyusername/{username}")
	public User findByUsername(@PathVariable("username") String username) throws UserNameNotFoundException {
		
		User user = userService.findByUsername(username);
		 if(user == null)
		 {
			 throw new UserNameNotFoundException("Username: '" +username + "' not found in record");
		 }
		return user;
	}
	
	}