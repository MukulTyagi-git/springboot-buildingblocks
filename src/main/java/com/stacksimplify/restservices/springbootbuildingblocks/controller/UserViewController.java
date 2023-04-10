package com.stacksimplify.restservices.springbootbuildingblocks.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;
import com.stacksimplify.restservices.springbootbuildingblocks.Entity.User;
import com.stacksimplify.restservices.springbootbuildingblocks.Entity.Views;
import com.stacksimplify.restservices.springbootbuildingblocks.exception.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;

import jakarta.validation.constraints.Min;


@RestController
@Validated
@RequestMapping(value = "/jsonview/users")

public class UserViewController {
	
	
	// Auto wired UserService
	
		@Autowired
		private UserService userService;
		
		
		//---------Find User By Id Method -------------------
		
		   //------ExternalView-----------------
		
		@JsonView(Views.External.class)
		@GetMapping("/serachusersbyidExt/{id}")
		public Optional <User> getUserById(@PathVariable("id") @Min(1) long id) throws UserNotFoundException {
			
			try {
			Optional<User> user = userService.findUserById(id);
			
			return user;	
			}
			
			catch (UserNotFoundException ex){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
		}
		
		
		
		//------InternalView-----------------
		
		
				@GetMapping("/serachusersbyidInt/{id}")
				@JsonView(Views.Internal.class)
				public Optional <User> getUserById2(@PathVariable("id") @Min(1) long id) throws UserNotFoundException {
					
					try {
					Optional<User> user = userService.findUserById(id);
					
					return user;	
					}
					
					catch (UserNotFoundException ex){
						throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
					}
				}

}
