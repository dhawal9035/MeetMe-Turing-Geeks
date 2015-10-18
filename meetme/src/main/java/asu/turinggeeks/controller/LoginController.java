package asu.turinggeeks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asu.turinggeeks.model.UserInfo;

@Controller
public class LoginController {

	/*@RequestMapping(value="/", method=RequestMethod.GET)
	public String login1(Model model){		
		return "login";
	}*/
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model){
		UserInfo userForm = new UserInfo();
		model.addAttribute("userForm", userForm);
		return "login";
	}
	
	@RequestMapping(value="/loginSuccess", method=RequestMethod.GET)
	public String register(Model model){
		UserInfo userForm = new UserInfo();
		model.addAttribute("userForm", userForm);
		return "success";
	}
	
	@RequestMapping(value="/loginFailed", method=RequestMethod.GET)
	public String loginFailure(Model model){
		UserInfo userForm = new UserInfo();
		model.addAttribute("userForm", userForm);
		model.addAttribute("error", "Invalid User Name or Password");
		return "login";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(Model model){
		UserInfo userForm = new UserInfo();
		model.addAttribute("userForm", userForm);
		return "logout";
	}
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String error403(Model model){
		return "403";
	}
}
