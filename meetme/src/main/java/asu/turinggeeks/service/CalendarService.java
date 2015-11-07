package asu.turinggeeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asu.turinggeeks.dao.CalendarDao;
import asu.turinggeeks.model.Calendar;

@Service
public class CalendarService {
	
	@Autowired
	CalendarDao calendarDao;
	
	public boolean insertForManualCalendar(String[] startDate, String[] startTime, String[] endDate, String[] endTime,
			Calendar calendar, String emailId) {
		
		return calendarDao.insertForManualCalendar(startDate, startTime, endDate, endTime, calendar, emailId);
	}
	
}

	
