package asu.turinggeeks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model){		
		return "login";
	}
	
	
	
	@RequestMapping(value="/loginSuccess", method=RequestMethod.GET)
	public String register(Model model){		
		return "success";
	}
	
	@RequestMapping(value="/loginfailed", method=RequestMethod.GET)
	public String loginFailure(Model model){
		model.addAttribute("error", "Invalid User Name or Password");
		return "login";
	}
}
