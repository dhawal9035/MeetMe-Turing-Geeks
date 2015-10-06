package asu.turinggeeks.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asu.turinggeeks.model.UserInfo;
import asu.turinggeeks.service.RegistrationService;

@Controller
public class RegistrationController {
	
	@Autowired
	RegistrationService registrationService;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String viewRegistration(Model model){
		UserInfo userForm = new UserInfo();
		model.addAttribute("userForm", userForm);
		
		return "registration";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") UserInfo userForm, Model model){
		
		registrationService.registerUser(userForm);
		return "success";
	}
}
