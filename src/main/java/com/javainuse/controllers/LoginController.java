package com.javainuse.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javainuse.data.UserRepository;
import com.javainuse.model.User;

@Controller
public class LoginController {
 
	@Autowired
	private UserRepository userData;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String newLogin(HttpServletRequest request, User user) {
		System.out.println("Employee to be added is " + user);
		User inSystem = userData.findByUserName(user.getUserName());
		if(inSystem == null || !inSystem.getPassword().equals(user.getPassword())){
			return ("redirect:/invalidCredentials");
		}
		else
		{
			request.getSession().setAttribute("user", inSystem);
			return ("redirect:/addNewEmployee");
		}

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView newLogin() {

		User user = new User();
		return new ModelAndView("login", "form", user);

	}


}
