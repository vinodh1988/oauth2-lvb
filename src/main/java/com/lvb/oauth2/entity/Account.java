package com.lvb.oauth2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
@Id
   private String username;
@Column
   private String firstname;
@Column
   private String email;
@Column
   private String mobile;
@Column  
   private String profilepic;
   
  

public Account() {
	super();
	// TODO Auto-generated constructor stub
}

public Account(String username, String firstname, String email, String mobile, String profilepic) {
	super();
	this.username = username;
	this.firstname = firstname;
	this.email = email;
	this.mobile = mobile;
	this.profilepic = profilepic;
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

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public String getProfilepic() {
	return profilepic;
}

public void setProfilepic(String profilepic) {
	this.profilepic = profilepic;
}
   
   
   
}
