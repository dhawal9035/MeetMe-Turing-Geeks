package asu.turinggeeks.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.mysql.fabric.xmlrpc.base.Array;

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
	public String calendar(@ModelAttribute("calendar") Calendar calendar, Model model) {
		return "success";
	}
	
	@RequestMapping(value="/dashboard_landing", method=RequestMethod.GET)
	public String calendarEvent(@ModelAttribute("calendar") Calendar calendar, Model model) {
		return "dashboard_landing";
	}
	
	@RequestMapping(value="/schedule")
	public String goToSchedule(@ModelAttribute("calendar") Calendar calendar, Model model) {
		return "success";
	}
	
	@RequestMapping(value="/manualCalendar", method=RequestMethod.POST)
	public String addCalendar(@ModelAttribute("calendar") Calendar calendar, Model model, HttpServletRequest request){
		try{
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
				return "errorPage";
		}
		else
			return "errorPage";
		}
		catch(Exception e){
			e.printStackTrace();
			return "errorPage";
		}
	}
	
	@RequestMapping(value="/submitTiming/{uuid}", method=RequestMethod.GET)
	public String userResponse(@PathVariable String uuid, Model model){
		try{
		int eventId = calendarService.getEventId(uuid);
		model.addAttribute(uuid);
		List<Calendar> probableTimings = calendarService.getAllEventDetails(eventId);
		model.addAttribute("probableTimings", probableTimings);
		return "eventResponse";
		}
		catch(Exception e){
			e.printStackTrace();
			return "errorPage";
		}
	}

	@RequestMapping(value = "/algorithm", method = RequestMethod.GET)
	public List<String> triggerAlgorithm(List<Calendar> startSlot, List<Calendar> endSlot, List<Calendar> requiredSlot, List<Calendar> optionalSlot ) {

		
		/*String[] slotsTimingStart= {"2014/04/23 00:00:00","2014/04/23 2:00:00","2014/04/23 12:00:00"};
		String[] slotsTimingEnd= {
				"2014/04/23 01:00:00",
				"2014/04/23 3:00:00",
				"2014/04/23 14:00:00"};
		String[] reqResponse= {
				"2014/04/23 00:00:00,2014/04/23 01:00:00",
				"2014/04/23 2:00:00,2014/04/23 3:00:00",
				"2014/04/23 2:00:00,2014/04/23 3:00:00",
				"2014/04/23 2:00:00,2014/04/23 3:00:00",
				"2014/04/23 12:00:00,2014/04/23 14:00:00",
				"2014/04/23 12:00:00,2014/04/23 14:00:00"};
		String[] optResponse= {
				"2014/04/23 00:00:00,2014/04/23 01:00:00",
				"2014/04/23 2:00:00,2014/04/23 3:00:00",
				"2014/04/23 2:00:00,2014/04/23 3:00:00",
				"2014/04/23 12:00:00,2014/04/23 14:00:00"};*/
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		String slot = "";
		for(int i =0;i<endSlot.size();i++)
		{
			slot = startSlot.get(i).getStartTime()+","+endSlot.get(i).getEndTime();
			hash.put( slot , new Integer(0));
		}
		
		for(int i=0;i<optionalSlot.size();i++){
			System.out.println("Optional Slot"+optionalSlot.get(i).getOptionalTime());
		}
		for(int i=0;i<requiredSlot.size();i++){
			System.out.println("Required Slot"+requiredSlot.get(i).getRequiredTime());
		}
		
		Integer count = 0;
		for(Calendar slotTemp_var: requiredSlot )
		{
				count = 0;
				String slotTemp = slotTemp_var.getRequiredTime();
				count = hash.get(slotTemp) ;
				if(hash.get(slotTemp) !=null)
					hash.put(slotTemp, ++count);
				System.out.println(slotTemp.split(","));
		}
		for(Calendar slotTemp_var: optionalSlot )
		{
				String slotTemp = slotTemp_var.getOptionalTime();
				count = hash.get(slotTemp) ;
				if (count!=null)
					hash.put(slotTemp, ++count);
		}
		
		System.out.println(hash.toString());
		
		String[] slotTimes = new String[2];
		String temp = new String();
		LocalDateTime dt2;
		TreeMap<Integer, String> tMap = new TreeMap<Integer,String>();
		for( Map.Entry<String, Integer> map : hash.entrySet() )
		{
			temp = map.getKey();
			slotTimes = temp.split(",");
			dt2 = LocalDateTime.parse(slotTimes[0], 
			        DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
			if(dt2.getHourOfDay()<=20 && dt2.getHourOfDay()>=9)
			{
				int i = hash.get(temp);
				hash.put(temp, ++i );
			}
			tMap.put(hash.get(temp), temp);
		}
		
		System.out.println(tMap.entrySet());
		ArrayList<Integer> ctr = new ArrayList<Integer>();
		ctr.addAll(hash.values());
		Collections.sort(ctr);
		List<String> output = new ArrayList<String>();
		for (Map.Entry< Integer,String> map : tMap.entrySet())
			output.add(map.getValue());
		
		System.out.println(output);
		System.out.println(ctr.toString());

		return output;
		
	}

	
	@RequestMapping(value="userResponse", method=RequestMethod.POST)
	public String saveResponse(Model model, HttpServletRequest request){
		try{
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
		catch(Exception e){
			e.printStackTrace();
			return "errorPage";
		}
	}
	
	@RequestMapping(value="meetingTime", method = RequestMethod.GET)
	public String getMeetingPage(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailId= auth.getName();
		List <Calendar> allEvents = calendarService.getAllEvents(emailId);
		model.addAttribute("allEvents",allEvents);
		return "meetingTime";
	}
	
	@RequestMapping(value="getTime/*", method = RequestMethod.GET)
	public String getMeetingTime(Model model, HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailId= auth.getName();
		int eventId= Integer.parseInt(request.getParameter("eventId"));
		String uuid = request.getParameter("uuid");
		//String uuid = "4dc9a7e7-b675-4780-a24f-af8a8de185cd";
		//int eventId=18;
		List<Calendar> startSlot = calendarService.getStartSlot(eventId);
		List<Calendar> endSlot = calendarService.getEndSlot(eventId);
		List<Calendar> requiredSlot = calendarService.getRequiredSlot(uuid);
		List<Calendar> optionalSlot = calendarService.getOptionalSlot(uuid);
		for(int i=0;i<optionalSlot.size();i++){
			System.out.println("Optional Slot"+optionalSlot.get(i).getOptionalTime());
		}
		for(int i=0;i<requiredSlot.size();i++){
			System.out.println("Required Slot"+requiredSlot.get(i).getRequiredTime());
		}

		int responseCounter = calendarService.getResponseCounter(uuid);
		int requiredCounter = calendarService.getRequiredCounter(uuid);
		
		if(responseCounter == requiredCounter){
			System.out.println("Majai Gai "+requiredCounter);
			List<String> output = new ArrayList<String>();
			output = triggerAlgorithm(startSlot, endSlot,requiredSlot, optionalSlot);
			List<String> reverseOutput = new ArrayList<String>(); 
			reverseOutput =	Lists.reverse(output);
			String requiredPeople = calendarService.getRequiredPeople(uuid);
			String optionalPeople = calendarService.getOptionalPeople(uuid);
			System.out.println(optionalPeople);
			mailService.sendPreferredTime(emailId,requiredPeople,optionalPeople, reverseOutput);
			model.addAttribute("reverseOutput",reverseOutput);
			return "preferredTime";
		}
		else{
			System.out.println("Na Majai "+responseCounter);
			List <Calendar> allEvents = calendarService.getAllEvents(emailId);
			model.addAttribute("allEvents",allEvents);
			model.addAttribute("error","error");
			return "meetingTime";
		}
		
		
	}
	
	@RequestMapping(value="calendarFetch", method=RequestMethod.GET)
	@ResponseBody
	public String fetchData(Model model,@ModelAttribute("calendar") Calendar calendar){
		System.out.println("Inside calendarFetch Controller");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailId= auth.getName();
		//You may have to create a JSON Object. Change the return type of the method if returning a JSON obj
		JSONObject data = calendarService.fetchCalendarData(emailId);
		System.out.println("Json Data:"+data);
		model.addAttribute(data);
		return data.toString();
	}
	
	@RequestMapping(value="/submitDB", method= RequestMethod.POST)
    public String submitDB (@ModelAttribute("calendar") Calendar calendar) {
        // add the event details in a string and then insert into the database!
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailId= auth.getName();
		String uuid = UUID.randomUUID().toString();
        String eventName = calendar.getEventName();
        System.out.println(calendar.getGuestRequiredEmail());
        System.out.println(calendar.getGuestOptionalEmail());
        String errormsg = "Failed to store event info";
        System.out.println(eventName);
        if(calendarService.insertEvent(calendar, emailId, uuid)){
        //finally return event name
        return eventName;       
        }
        else        	
            return errormsg;
    }
	
	@RequestMapping(value = "/dataFetch")
	public List<String> triggerGoogleAlgorithm(List<Calendar> startSlot, List<Calendar> endSlot, String[] RefSlots) {
	final DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	String[] startSlots = new String[startSlot.size()];
	String[] EndSlots = new String[startSlot.size()];
	List<String> outSlots = new ArrayList<String>();
	for(int i = 0 ; i<startSlot.size();i++)
	{
		startSlots[i] = (startSlot.get(i).getStartTime());
		EndSlots[i] = endSlot.get(i).getEndTime();
	}

	/*String RefSlots[] = {
		"2015-11-21",
		"2015-11-22"
	};*/

	ArrayList<DateTime> day_arr= new ArrayList<DateTime>();
	DateTime refSlotStart = format.parseDateTime(RefSlots[0] +" 09:00:00"); 
	DateTime refSlotEnd = format.parseDateTime(RefSlots[RefSlots.length-1] + " 18:00:00");	
	HashMap<DateTime, List<Boolean> > mapBool = new HashMap<DateTime,List<Boolean>>();
	HashMap<DateTime, List<DateTime> > mapDateTimeStart = new HashMap<DateTime,List<DateTime>>();
	HashMap<DateTime, List<DateTime> > mapDateTimeEnd = new HashMap<DateTime,List<DateTime>>();
	HashMap<DateTime, List<Boolean> > mapBooleanList= new HashMap<DateTime,List<Boolean>>();
	String strStart = RefSlots[0] + " 00:00:00";
	String strEnd = RefSlots[1] + " 00:00:00";
	DateTime startDay = format.parseDateTime(strStart);
	DateTime EndDay = format.parseDateTime(strEnd);
	DateTime temp = startDay;
	while(!temp.equals(EndDay.plusDays(1)))
	{
		day_arr.add(temp);
		temp = temp.plusDays(1);
	}
	
	System.out.print("Days are " + day_arr);
	System.out.println("start slots are "+ startSlots.toString());
	System.out.println("endslots are" + EndSlots.toString());
	DateTime hgEnd = new DateTime();
	DateTime hg = new DateTime();
	for(DateTime day : day_arr){
		System.out.println("day----------------------------- " + day);
		List<DateTime> list = new ArrayList<DateTime>() ;
		List<DateTime> listEnd = new ArrayList<DateTime>() ;
		refSlotStart = day.plusHours(9);
		refSlotEnd = day.plusHours(18);
		System.out.println("refslotstart " + refSlotStart + "refslotend " + refSlotEnd);
		for(int i = 0 ; i<startSlots.length ; i++)
		{
			System.out.println("startslot "+ startSlots[i]);
			hg= format.parseDateTime(startSlots[i]);
			hgEnd= format.parseDateTime(EndSlots[i]);
			if((hg.getYear()==day.getYear())&&(hg.getMonthOfYear()== day.getMonthOfYear()))
			{
				if(hg.getDayOfMonth()==day.getDayOfMonth())
				{	
					if(hgEnd.isBefore(refSlotEnd) && hg.isAfter(refSlotStart)){
						System.out.println("adding slot " + hg + "->" + hgEnd);
						if (!list.contains(hg))
							list.add(hg);
						if(!list.contains(hgEnd))
							listEnd.add(hgEnd);
					}
				}
				/*else
					list = null;*/	
			}
			/*else
				list = null;*/
			
		}

		mapDateTimeStart.put(day, list);
		mapDateTimeEnd.put(day, listEnd);
		System.out.println();
	}
	for (Map.Entry<DateTime, List<DateTime>> m: mapDateTimeStart.entrySet())
		System.out.println(m.toString());
	DateTime start = new DateTime();
	DateTime end = new DateTime();
	for(DateTime day : day_arr){
		List<Boolean> freeBusy = new ArrayList<Boolean>() ;
		
		for(int i = 0 ; i<19 ; i++)
			freeBusy.add(true);
			mapBooleanList.put(day, freeBusy);
		}
	for(DateTime day : day_arr){
		List<DateTime> startTime= mapDateTimeStart.get(day);
		List<DateTime> EndTime= mapDateTimeEnd.get(day);
		if (startTime.size()!=0)
		for(int i = 0 ; i<startTime.size() ; i++)
		{ 
			start =  startTime.get(i);
			end =  EndTime.get(i);
			double slotTime = Minutes.minutesBetween(start, end).getMinutes()/30.0;
			double slotStart = start.getHourOfDay()- 9 ;
			if(start.getMinuteOfHour() == 30)
				slotStart = slotStart +  0.5;
		 	slotStart*=2;
			for(int j = 0; j<slotTime ; j++)
				mapBooleanList.get(day).set((int)(j+slotStart+1), false);
		}
		
	}
		List<String> output = new ArrayList<String>();
		
		for (DateTime day1 : day_arr){
			refSlotStart = day1.plusHours(9);
			List <Boolean> BoolList = mapBooleanList.get(day1);
			hg = null;
			DateTime outSlotStart=null,outSlotEnd=null;
			for(int i = 1; i<BoolList.size();i++)
			{
				if(BoolList.get(i)==true)
				{
					if (hg==null)
					{
						hg = refSlotStart;
						outSlotStart = hg;
					}
					hg = hg.plusMinutes(30);
				}
				else
				{
					outSlotEnd = hg;
					if(!outSlotEnd.equals(outSlotStart))
						outSlots.add(outSlotStart.toString().substring(0, 19).replace('T',  ' ') + "->"+ outSlotEnd.toString().substring(0, 19).replace('T',  ' '));
					outSlotStart = refSlotStart.plusMinutes(i*30);
					hg = outSlotStart;
				}
			}
			if(outSlotEnd==null)
			{
				System.out.println("outslot start is "+ outSlotStart);
				outSlots.add(outSlotStart.toString().substring(0, 19).replace('T',  ' ') + "->"+ outSlotStart.plusHours(9).toString().substring(0, 19).replace('T',  ' '));
			}
				else if(outSlotEnd.isBefore(hg))
				outSlots.add(outSlotStart.toString().substring(0, 19).replace('T',  ' ') + "->"+ hg.toString().substring(0, 19).replace('T',  ' '));	
			}
			System.out.println(outSlots);
			
		
		return outSlots;
	}
	
	@RequestMapping(value="/googleEvent", method=RequestMethod.GET)
	public String showGoogle(@ModelAttribute("calendar") Calendar calendar, Model model){
		return "googleCreate";
	}
	
	@RequestMapping(value="/googleCreateEvent", method=RequestMethod.POST)
	public String retrieveTime(Model model, HttpServletRequest request, @ModelAttribute("calendar") Calendar calendar){
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailId= auth.getName();*/
		String eventUuid = UUID.randomUUID().toString();
		HttpSession session = request.getSession();
		String emailId = (String) session.getAttribute("email");
		System.out.println(emailId);
		String counterString = (String) request.getParameter("counter");
		if (!StringUtils.isEmpty(counterString)) {
			int counter=Integer.parseInt(counterString);
			String []startDate = new String[counter];
			String []endDate = new String[counter];
			for(int i=0; i < counter; i++){
				startDate[i] = (String) request.getParameter("startDate"+i+"");
				endDate[i] = (String) request.getParameter("endDate"+i+"");
			}
			/*int length = startDate.length;
			String[] start = new String[length];
			String[] end = new String[length];
			for (int i = 0; i < length; i++) {
				start[i] = startDate[i] + " " + startTime[i];
				end[i] = endDate[i] + " " + endTime[i];
			}*/
			String[] slots = new String[2];
			slots[0]=startDate[0];
			slots[1]=endDate[0];
			List<String> output = new ArrayList<String>();
			boolean check = calendarService.insertForGoogleEvent(startDate, endDate, calendar, emailId, eventUuid);
			if(check){
				List<Calendar> userStartSlot = calendarService.getGoogleUserStartSlot(calendar);
				List<Calendar> userEndSlot = calendarService.getGoogleUserEndSlot(calendar);
				System.out.println(slots);
				output = triggerGoogleAlgorithm(userStartSlot, userEndSlot, slots);
				String requiredPeople = calendarService.getGoogleRequiredPeople(eventUuid);
				String optionalPeople = calendarService.getGoogleOptionalPeople(eventUuid);
				System.out.println(optionalPeople);
				mailService.sendPreferredTime(emailId,requiredPeople,optionalPeople, output);
				model.addAttribute("reverseOutput",output);
				return "preferredTime";
			}
			else
				return "error";
		}
		else
			return "error";
	}
	
	@RequestMapping(value="googleFetch", method=RequestMethod.GET)
	@ResponseBody
	public String googleData(Model model,@ModelAttribute("calendar") Calendar calendar, HttpServletRequest request){
		HttpSession session = request.getSession();
		String emailId = (String) session.getAttribute("email");
		//You may have to create a JSON Object. Change the return type of the method if returning a JSON obj
		JSONObject data = calendarService.fetchGoogleData(emailId);
		System.out.println("Json Data:"+data);
		model.addAttribute(data);
		return data.toString();
	}
	
}
