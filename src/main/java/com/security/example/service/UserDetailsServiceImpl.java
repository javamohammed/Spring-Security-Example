package com.security.example.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.example.entity.User;
import com.security.example.repository.UserRepository;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		if (email.trim().isEmpty()) {
			throw new UsernameNotFoundException("username is empty");
		}
 
		User user = userRepository.findByEmail(email);
	        if (user == null) throw new UsernameNotFoundException(email);
	        System.out.println("===="+user);
	        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	        return new UserDetailsImpl(user);
			//return user.m(UserDetailsImpl::new).get();
	}
	/*
	public User getUser(){
		return this.user;
	}*/

}
