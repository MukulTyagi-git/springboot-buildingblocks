package com.stacksimplify.restservices.springbootbuildingblocks.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="orders")

public class Order {
	
	@Id
	@GeneratedValue
	@JsonView(Views.Internal.class)
	private long orderid;
	@JsonView(Views.External.class)
	private String orderdesc;
	
	
	@ManyToOne(fetch= FetchType.LAZY)
	@JsonIgnore
	private User user;


	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	public long getOrderid() {
		return orderid;
	}


	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}


	public String getOrderdesc() {
		return orderdesc;
	}


	public void setOrderdesc(String orderdesc) {
		this.orderdesc = orderdesc;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	
}
