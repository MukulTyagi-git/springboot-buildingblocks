package com.stacksimplify.restservices.springbootbuildingblocks;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.stacksimplify.restservices.springbootbuildingblocks.services.UserService;



@SpringBootApplication
@EntityScan("com.stacksimplify.restservices.springbootbuildingblocks.Entity")
@EnableJpaRepositories("com.stacksimplify.restservices.springbootbuildingblocks.repository")
@ComponentScan("com.stacksimplify.restservices.springbootbuildingblocks.controller")

public class SpringbootBuildingblocksApplication {
//Second
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBuildingblocksApplication.class, args);
	}
		@Bean 
		public ModelMapper modelMapper(){
			return new ModelMapper();
			}
		@Bean
		public UserService userService () {
			
			return new UserService();
		}
		
	
}
