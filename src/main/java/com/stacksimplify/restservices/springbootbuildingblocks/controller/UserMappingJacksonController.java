package com.stacksimplify.restservices.springbootbuildingblocks.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.restservices.springbootbuildingblocks.Entity.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exception.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/jacksonfilter/user")
@Validated
public class UserMappingJacksonController {
	

	// Auto wired UserService
	
		@Autowired
		private UserService userService;
		
//-----------Get User MethodBy Id-------------------------------------------------//


		@GetMapping("/serachusersbyid/{id}")
		
		public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) long id) throws UserNotFoundException {
			
			try {
				
			Optional<User> optionalUser = userService.findUserById(id);
			User user = optionalUser.get();
			
			
			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			
			
			fields.add("orderid");
			
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields)) ;
			
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			
			mapper.setFilters(filterProvider);
			
			return mapper;
			
			}
			
			catch (UserNotFoundException ex){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
		} 

	
		//-----------Get User MethodBy Id : Request Param Filter-------------------------------------------------//
		
@GetMapping("/serachusersbyidparam/{id}")
		
		public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) long id,
				@RequestParam Set<String> fields) throws UserNotFoundException {
			
			try {
				
			Optional<User> optionalUser = userService.findUserById(id);
			User user = optionalUser.get();
		
			FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields)) ;
			
			MappingJacksonValue mapper = new MappingJacksonValue(user);
			
			mapper.setFilters(filterProvider);
			
			return mapper;
			
			}
			
			catch (UserNotFoundException ex){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
		} 
}
