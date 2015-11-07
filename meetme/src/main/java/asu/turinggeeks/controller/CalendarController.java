package asu.turinggeeks.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asu.turinggeeks.model.Calendar;
import asu.turinggeeks.service.CalendarService;

@Controller
public class CalendarController {
	
	@Autowired
	CalendarService calendarService;
	
	@RequestMapping(value="/calendar", method=RequestMethod.GET)
	public String showCalendar(@ModelAttribute("calendar") Calendar calendar, Model model){
		return "calendar";
	}
	
	@RequestMapping(value="/manualCalendar", method=RequestMethod.POST)
	public String addCalendar(@ModelAttribute("calendar") Calendar calendar, Model model, HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailId= auth.getName();
		System.out.println(emailId);
		String guestMail=calendar.getGuestEmail();
		System.out.println(guestMail);
		String counterString = (String) request.getParameter("counter");
		System.out.println(counterString);
		if (!StringUtils.isEmpty(counterString)) {
			int counter=Integer.parseInt(counterString);
			System.out.println();
			String []startDate = new String[counter];
			String []startTime = new String[counter];
			String []endDate = new String[counter];
			String []endTime = new String[counter];
			for(int i=0; i < counter; i++){
				startDate[i] = (String) request.getParameter("startDate"+i+"");
				startTime[i] = (String) request.getParameter("startTime"+i+"");
				endDate[i] = (String) request.getParameter("endDate"+i+"");
				endTime[i] = (String) request.getParameter("endTime"+i+"");
			}
			boolean check = calendarService.insertForManualCalendar(startDate, startTime, endDate, endTime, calendar, emailId);
			if(check)
				return "success";
			else
				return "failure";
		}
		else
			return "failure";
		
	}
}
