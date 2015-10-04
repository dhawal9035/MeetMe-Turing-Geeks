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
	
	/*@RequestMapping(value="/meetme/login", method=RequestMethod.GET)
	public String login1(Model model){	
	System.out.println("IN login controller");
		return "login";
	}
	
	@RequestMapping(value="meetme/login", method=RequestMethod.GET)
	public String login2(Model model){	
	System.out.println("IN login2 controller");
		return "login";
	}*/
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model){		
		return "register";
	}
}
