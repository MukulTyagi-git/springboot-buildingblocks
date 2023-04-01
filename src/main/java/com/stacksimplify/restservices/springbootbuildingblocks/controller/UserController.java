package com.stacksimplify.restservices.springbootbuildingblocks.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.springbootbuildingblocks.Entity.User;
import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;

//Controller

@RestController
public class UserController {
	
	// Auto wired UserService
	
	@Autowired
	private UserService userService;
	
// ------------Get All users Method--------------------------------------------
      //By Request Mapping
	
	//@RequestMapping(method = RequestMethod.GET, path = "/users")
	
	  //By Get Mapping
	@GetMapping("/users")
	public List<User> getAllUsers(){
	
		return userService.getAllUsers();
	}

//----------Create User Method-------------------------------------------
	
	
	@PostMapping("/createuser")
	public User createUser(@RequestBody User user)
	{
		return userService.createUser(user);
	}
	
//---------Find User By Id Method -------------------
	@GetMapping("/serachusersbyid/{id}")
	public Optional <User> getUserById(@PathVariable("id") long id) {
		Optional<User> user = userService.findUserById(id);
		return user;	
	}
	
//--------Update User By Id Method-----------------
	
	@PutMapping("/updateuserbyid/{id}")
	public User updateUserById(@PathVariable("id") long id , @RequestBody User user)
	{
	return userService.updateUserByid(id, user);
	}
	
//------Delete by Id Method-------------------
	
	@DeleteMapping("/deleteuser/{id}")
	
	public void deleteUserByid(@PathVariable("id") long id) {
		userService.deleteUserByid(id);
	}
	
//--------------Find by user name method ---------------------
	
	@GetMapping("/searchbyusername/{username}")
	public User findByUsername(@PathVariable("username") String username) {
		return userService.findByUsername(username);
		
	}
	
	}

