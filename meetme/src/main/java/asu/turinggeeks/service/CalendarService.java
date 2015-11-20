package asu.turinggeeks.service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asu.turinggeeks.dao.CalendarDao;
import asu.turinggeeks.model.Calendar;

@Service
public class CalendarService {
	
	@Autowired
	CalendarDao calendarDao;
	
	public boolean insertForManualCalendar(String[] start, String[] end, Calendar calendar, String emailId, String uuid) {
		
		return calendarDao.insertForManualCalendar(start, end, calendar, emailId, uuid);
	}
	
public boolean insertForGoogleCalendar(List<Calendar> calendar, String emailId) {
		
		return calendarDao.insertForGoogleCalendar(calendar, emailId);
	}
	
	public boolean insertForGoogleCalendar(String[] start, String[] end, Calendar calendar, String emailId, String uuid) {
		
		return calendarDao.insertForGoogleCalendar(start, end, calendar, emailId, uuid);
	}


	public int getEventId(String uuid) {
		return calendarDao.getEventId(uuid);
	}

	public List<Calendar> getAllEventDetails(int eventId) {
		List<Calendar> probableTimings = calendarDao.getAllEventDetails(eventId);
		return probableTimings;
	}

	public void storeRequiredUserResponse(String guestRequiredMail, String[] checkedTimings, String uuid) {
		calendarDao.storeRequiredUserResponse(guestRequiredMail,checkedTimings,uuid);
	}
	
	public void storeOptionalUserResponse(String guestOptionalMail, String[] checkedTimings, String uuid) {
		calendarDao.storeOptionalUserResponse(guestOptionalMail,checkedTimings,uuid);
	}

	public String checkUserType(String guestMail, String uuid) {
		return calendarDao.checkUserType(guestMail,uuid);
	}

	public String getUuid(String emailId) {
		return calendarDao.getUuid(emailId);
	}

	public List<Calendar> getAllEvents(String emailId) {
		return calendarDao.getAllEvents(emailId);
	}

	public List<Calendar> getStartSlot(int eventId) {
		return calendarDao.getStartSlot(eventId);
	}

	public List<Calendar> getEndSlot(int eventId) {
		return calendarDao.getEndSlot(eventId);
	}

	public List<Calendar> getRequiredSlot(String uuid) {
		return calendarDao.getRequiredSlot(uuid);
	}

	public List<Calendar> getOptionalSlot(String uuid) {
		return calendarDao.getOptionalSlot(uuid);
	}

	public int getRequiredCounter(String uuid) {
		return calendarDao.getRequiredCounter(uuid);
	}

	public int getResponseCounter(String uuid) {
		return calendarDao.getResponseCounter(uuid);
	}
	
	public JSONObject fetchCalendarData(String emailId) {
		return calendarDao.fetchCalendarData(emailId);
	}
	
}

	
