package com.stacksimplify.restservices.springbootbuildingblocks.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.stacksimplify.restservices.springbootbuildingblocks.Entity.User;
import com.stacksimplify.restservices.springbootbuildingblocks.dtos.UserMmDto;
import com.stacksimplify.restservices.springbootbuildingblocks.exception.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/modelmapper/users")


public class UserModelMapperController {
	
	// Auto wired UserService
	
		@Autowired
		private UserService userService;
		
		@Autowired
		private ModelMapper modelMapper;
		
		
		
		//---------Find User By Id Method -------------------
		@GetMapping("/serachusersbyid/{id}")
		
		public UserMmDto getUserById(@PathVariable("id") @Min(1) long id) throws UserNotFoundException {
			
			Optional<User> optionalUser = userService.findUserById(id);
			
			if(!optionalUser.isPresent())
			{
				throw new UserNotFoundException("User Not Found");
			}
			
			User user = optionalUser.get();
			UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
			
			return userMmDto;
			
		}

}
