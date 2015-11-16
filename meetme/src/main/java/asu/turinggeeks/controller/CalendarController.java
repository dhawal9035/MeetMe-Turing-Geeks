package asu.turinggeeks.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import asu.turinggeeks.model.Calendar;
import asu.turinggeeks.service.CalendarService;
import asu.turinggeeks.service.MailService;

@Controller
public class CalendarController {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	CalendarService calendarService;
	
	@RequestMapping(value="/calendar", method=RequestMethod.GET)
	public String showCalendar(@ModelAttribute("calendar") Calendar calendar, Model model){
		return "calendar";
	}
	
	@RequestMapping(value="/calendarEvent", method=RequestMethod.GET)
	public String calendarInfo(@ModelAttribute("calendarInfo") Calendar calendar, Model model) {
		return "success";
	}
	
	@RequestMapping(value="/dashboard_landing", method=RequestMethod.GET)
	public String calendarEvent(@ModelAttribute("calendarInfo") Calendar calendar, Model model) {
		return "dashboard_landing";
	}
	
	@RequestMapping(value="/manualCalendar", method=RequestMethod.POST)
	public String addCalendar(@ModelAttribute("calendar") Calendar calendar, Model model, HttpServletRequest request){
		String uuid = UUID.randomUUID().toString();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailId= auth.getName();
		String counterString = (String) request.getParameter("counter");
		if (!StringUtils.isEmpty(counterString)) {
			int counter=Integer.parseInt(counterString);
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
			int length = startDate.length;
			String[] start = new String[length];
			String[] end = new String[length];
			for (int i = 0; i < length; i++) {
				start[i] = startDate[i] + " " + startTime[i];
				end[i] = endDate[i] + " " + endTime[i];
			}
			
			boolean check = calendarService.insertForManualCalendar(start, end, calendar, emailId, uuid);
			if(check){
				mailService.sendRequiredInvite(emailId,calendar,uuid);
				mailService.sendOptionalInvite(emailId,calendar,uuid);
				return "success";
			}
			else
				return "error";
		}
		else
			return "error";
		
	}
	
	@RequestMapping(value="/submitTiming/{uuid}", method=RequestMethod.GET)
	public String userResponse(@PathVariable String uuid, Model model){
		int eventId = calendarService.getEventId(uuid);
		model.addAttribute(uuid);
		List<Calendar> probableTimings = calendarService.getAllEventDetails(eventId);
		model.addAttribute("probableTimings", probableTimings);
		return "eventResponse";
	}
	
	@RequestMapping(value="userResponse", method=RequestMethod.POST)
	public String saveResponse(Model model, HttpServletRequest request){
		String guestMail=request.getParameter("email");
		String[] checkedTimings = request.getParameterValues("timing");
		String uuid = request.getParameter("uuid");
		String userType = calendarService.checkUserType(guestMail, uuid);
		System.out.println(userType);
		if(userType.equals("required"))
			calendarService.storeRequiredUserResponse(guestMail,checkedTimings,uuid);
		else if(userType.equals("optional"))
			calendarService.storeOptionalUserResponse(guestMail,checkedTimings,uuid);
		else {
			model.addAttribute("error", "error");
			return "submitTiming/"+uuid+"";
		}
		return "response";
	}
}
