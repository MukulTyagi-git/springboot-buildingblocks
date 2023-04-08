package com.stacksimplify.restservices.springbootbuildingblocks.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.restservices.springbootbuildingblocks.Entity.Order;
import com.stacksimplify.restservices.springbootbuildingblocks.Entity.User;
import com.stacksimplify.restservices.springbootbuildingblocks.exception.UserNotFoundException;
import com.stacksimplify.restservices.springbootbuildingblocks.repository.OrderRepository;
import com.stacksimplify.restservices.springbootbuildingblocks.repository.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepositiory;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrder(@PathVariable long userid ) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(userid) ;
		
		if (!optionalUser.isPresent())
		{
			throw new UserNotFoundException("User not found in Record");
		}
		
		return optionalUser.get().getOrders();
	}
	
	
	@PostMapping("/{userid}/createorder")
	public Order createOrder(@PathVariable long userid , @RequestBody Order order ) throws UserNotFoundException
	{
Optional<User> optionalUser = userRepository.findById(userid) ;
		
		if (!optionalUser.isPresent())
		{
			throw new UserNotFoundException("User not found in Record");
		}
		
		User user = optionalUser.get();
		order.setUser(user);
		return orderRepositiory.save(order);
	}

	@GetMapping("/{userid}/orders/{orderid}")
	
	public Optional<Order> getOrderById (@PathVariable long userid , @PathVariable long orderid ) throws UserNotFoundException {
		
		Optional<User> optionalUser = userRepository.findById(userid) ;
		 
		Optional <Order> optionalOrder = orderRepositiory.findById(orderid);
		
		if(optionalUser.isPresent())
		{
			
			if (optionalOrder.isPresent())
			{
				Optional<Order> order = orderRepositiory.findById(orderid);
				return order;
			}
			throw new UserNotFoundException("User not found in Record");
		}
		throw new UserNotFoundException("User not found in Record");
		
	}
}
