package asu.turinggeeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asu.turinggeeks.dao.CalendarDao;
import asu.turinggeeks.model.Calendar;

@Service
public class CalendarService {
	
	@Autowired
	CalendarDao calendarDao;
	
	public boolean insertForManualCalendar(String[] start, String[] end, Calendar calendar, String emailId) {
		
		return calendarDao.insertForManualCalendar(start, end, calendar, emailId);
	}
	
}

	
