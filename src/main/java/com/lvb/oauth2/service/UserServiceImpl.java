package com.lvb.oauth2.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lvb.oauth2.dao.UsersDAO;
import com.lvb.oauth2.entity.Users;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsersDAO userDao;
   @Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
	System.out.println("this bean is called....!!!");
	   Users user = userDao.findUsersByUsername(userId);
		System.out.println("Working...#####################");
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), getAuthority());
	}

	private List getAuthority() {
		
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	
}