package com.stacksimplify.restservices.springbootbuildingblocks.Hello;

public class UserDetail {
	
	private String firstname;
	private String lastname;
	private String city;
	
	
	
	public UserDetail(String firstname, String lastname, String city) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "UserDetail [firstname=" + firstname + ", lastname=" + lastname + ", city=" + city + "]";
	}
	
	

}
