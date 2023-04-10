package com.stacksimplify.restservices.springbootbuildingblocks.Hello;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController


//Controller


public class HelloWorldController {
	
	//method
	@RequestMapping(method = RequestMethod.GET , path = "/HelloWorld")
	
	public String HelloWorld(){
		return "Hello World" ;
	}
	
	@RequestMapping(method = RequestMethod.GET , path = "/HelloWorldBean")
	
	public UserDetail HelloWorldBean() {
		return new UserDetail("Mukul", "Tyagi" , "SRE");
	}
	
	

}
