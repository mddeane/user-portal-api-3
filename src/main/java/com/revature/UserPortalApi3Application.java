package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
public class UserPortalApi3Application {

	public static void main(String[] args) {
		SpringApplication.run(UserPortalApi3Application.class, args);
	}
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");					// index.html rendered at http://localhost:5000/api 
		return modelAndView;								// we will see index.html
	}

}
