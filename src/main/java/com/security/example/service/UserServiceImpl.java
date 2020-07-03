package com.security.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.example.entity.User;
import com.security.example.repository.UserRepository;


@Service
public class UserServiceImpl {

	@Autowired
    private UserRepository userRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("save::"+user);
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
   }
    
    public boolean checkIfEmailExist(String email) {
    	User user = userRepository.findByEmail(email);
    	if(user != null) {
    		return true;
    	}
    	return false;
    }
}
