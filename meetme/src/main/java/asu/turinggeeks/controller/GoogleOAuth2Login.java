
/*
package com.crunchify.controller;
 
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

*/
 
/*
 * author: Crunchify.com
 * 
 */
 
/*
@Controller
public class GoogleOAuth2Login {
 
	
	@RequestMapping("/index")
	public ModelAndView welcomePage() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("", "message", message);
	}
	//@RequestMapping("/plussampleservlet")
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}

	
	@RequestMapping("/oauth2callback?*")
	public ModelAndView CallSampleredirectServlet( 
	        HttpServletRequest request, HttpServletResponse response){ 
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}

	
@RequestMapping("/plussampleservlet")
public @ResponseBody void CallSampleServlet( 
        HttpServletRequest request, HttpServletResponse response) throws IOException{
		
	String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World from plus sample servlet, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
	    
    
		 	GoogleAuthorizationCodeFlow authFlowCalendar = Utils.initializeFlowCalendar();
		    UserService userService = UserServiceFactory.getUserService();
		    
		    
		    
		    //User user = userService.getCurrentUser();
		    //  Credential credential_calendar = authFlowCalendar.loadCredential(user.getUserId());
		    
		    
		    
		    response.sendRedirect(authFlowCalendar.newAuthorizationUrl().setRedirectUri(Utils.getRedirectUri(request)).build());	   
		    return ;
		    
		    //----Credential credential = authFlow.loadCredential(Utils.getUserId(req));
		
	}
}

*/