package com.stacksimplify.restservices.springbootbuildingblocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.restservices.springbootbuildingblocks.Entity.User;

@Repository

public interface UserRepository extends JpaRepository<User,Long> {

	List<User> findAll();
	
	User findByUsername(String username);




	

}
