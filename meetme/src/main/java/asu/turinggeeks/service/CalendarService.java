package asu.turinggeeks.service;

import java.util.List;

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

	public int getEventId(String uuid) {
		return calendarDao.getEventId(uuid);
	}

	public List<Calendar> getAllEventDetails(int eventId) {
		List<Calendar> probableTimings = calendarDao.getAllEventDetails(eventId);
		return probableTimings;
	}

	public void storeUserResponse(String guestMail, String[] checkedTimings, String uuid) {
		calendarDao.storeUserResponse(guestMail,checkedTimings,uuid);
	}
	
}

	
