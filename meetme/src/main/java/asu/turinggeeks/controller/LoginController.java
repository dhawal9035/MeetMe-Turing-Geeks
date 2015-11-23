package asu.turinggeeks.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asu.turinggeeks.model.Calendar;
import asu.turinggeeks.model.UserInfo;
import asu.turinggeeks.service.LoginService;
import asu.turinggeeks.service.MailService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MailService mailService;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String login1(Model model, @ModelAttribute("userForm") UserInfo userForm){		
		return "login";
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model){
		UserInfo userForm = new UserInfo();
		model.addAttribute("userForm", userForm);
		return "login";
	}
	
	@RequestMapping(value="/loginSuccess", method=RequestMethod.GET)
	public String register(Model model, HttpSession session){
		Calendar calendar = new Calendar();
		UserInfo userForm = new UserInfo();
		model.addAttribute("userForm", userForm);
		model.addAttribute("calendar", calendar);
		session.setAttribute("isGoogleUSer", false);
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
	
	@RequestMapping(value="/forgotPassword")
	public String forgotPassword(){
		return "forgotPassword";
	}
	
	@RequestMapping(value="/resetPassword")
	public String resetRequest(@ModelAttribute("userForm") UserInfo userForm, Model model){
		model.addAttribute("userForm", userForm);
		boolean userCheck = loginService.checkUser(userForm);
		if(userCheck){
			System.out.println(userForm.getEmail());
			System.out.println("User Found");
			mailService.sendMail(userForm.getEmail());
			model.addAttribute("checkMail", "Please check your email for password reset link");
			return "login";
		}
		else{
			System.out.println("User not Found");
			model.addAttribute("userNotFound", "You are not registered with us. Please click on Need Account to get started.");
			return "login";
		}
	}
		
	@RequestMapping(value="/newPassword/{email}", method=RequestMethod.GET)
	public String resetPassword(@PathVariable String email,@ModelAttribute("userForm") UserInfo userForm, Model model){
		model.addAttribute("userForm", userForm);
		model.addAttribute("email", email);
		return "/newPassword";
	}
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.POST)
	public String updatePassword(@ModelAttribute("userForm") UserInfo userForm, Model model){
		System.out.println(userForm.getEmail());
		model.addAttribute("userForm", userForm);
		model.addAttribute("email",userForm.getEmail());
		boolean userCheck = loginService.checkUser(userForm);
		if(userCheck){
			loginService.updatePass(userForm);
			model.addAttribute("updated", "Your password has been updated. Please wait for a while before logging in");
			return "login";
		}
		else{
			model.addAttribute("processError", "There was an error processing your request. Please try again.");
			return "login";
		}
	}
	
	@RequestMapping(value="/success")
	public String redirectToSuccess(Model model, @ModelAttribute("calendar") Calendar calendar){
		return "success";
	}
}
