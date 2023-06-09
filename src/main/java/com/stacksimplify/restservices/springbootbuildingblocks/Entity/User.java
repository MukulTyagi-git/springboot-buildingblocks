package com.stacksimplify.restservices.springbootbuildingblocks.Entity;




import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

//import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity(name = "User")
@Table(name = "UserDetails")
//@JsonFilter(value = "userFilter") ----Used for Jackson Filtering
public class User {
	
	@Id
	@GeneratedValue
	@JsonView(Views.Internal.class)
	@Column(name = "ID")
	private long id;
	
	@JsonView(Views.Internal.class)
	@NotEmpty(message = "Username is mandatory Please enter username")
	@Column(name = "USER_NAME", length = 50, nullable = false , unique = true)
	private String username;
	
	@JsonView(Views.External.class)
	@Size(min =2 ,message = "FirstName should have atleast 2 characters")
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstname;
	
	@JsonView(Views.External.class)
	@Size(min =1 ,message = "LastName should have atleast 1 characters")
	@Column(name = "LAST_NAME")
	private String lastname;
	
	@JsonView(Views.Internal.class)
	@NotEmpty(message = "Email ID is mandatory Please enter Email Id")
	@Column(name = "EMAIL_ID", unique = true)
	private String email;
	
	@JsonView(Views.External.class)
	@Column(name = "ROLE")
	private String role;
	
	@JsonView(Views.Internal.class)
	@Column(name = "SSN", nullable = false , unique = true)
	private String ssn;
	
	@JsonView(Views.Internal.class)
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	// TODO Auto-generated No Argument constructor stub
	public User() {
		super();
	}
	// TODO Auto-generated Argument constructor stub
	
	public User(long id, String username, String firstname, String lastname, String email, String role, String ssn) {
		super();
		this.id = id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role = role;
		this.ssn = ssn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	// To String Optional - Use for bean logging 
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", email=" + email + ", role=" + role + ", ssn=" + ssn + "]";
	}
	
	
	
	
	
	
	

}

