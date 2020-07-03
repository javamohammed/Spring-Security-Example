package com.security.example.controller;

import javax.validation.Valid;
	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.security.example.entity.User;   
import com.security.example.service.UserServiceImpl;

@Controller
public class AuthController {

	@Autowired
	private UserServiceImpl  userServiceImpl; 
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	  
	@GetMapping("/signup")
	public String signup(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}   
	
	@PostMapping("/signup")
	public String signupSave(@RequestBody String str, @Valid @ModelAttribute("user")  User user,  BindingResult result,Model model) {
		
		System.out.println("str::::"+str);
		if(!CheckPassword(str, user.getPassword())) {
			result.rejectValue("password", "error.user", "Confrim password should be equals password.");

		}
		
		if (result.hasErrors()) {
			user.setPassword("");
	         return "signup";
	      }
		//System.out.println(user);   
		userServiceImpl.save(user);
		return "redirect:/login";
	}
	
	private boolean CheckPassword(String str, String password) {
		String confirm_password ="";
		String[] params = str.split("&");
		for (String param : params) {
			String[] sub = param.split("=");
			if(sub[0].equalsIgnoreCase("confirm_password")) {
				confirm_password = sub[1];
			}
		}
		return confirm_password.contentEquals(password);
	}
}
