package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import asu.turinggeeks.model.Calendar;

@SuppressWarnings("rawtypes")
public class CalendarResultSetExtractor implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		Calendar calendar = new Calendar();
		calendar.setEventName(rs.getString("event_name"));
		calendar.setEventDescription(rs.getString("event_description"));
		calendar.setStartTime(rs.getString("start_time"));
		calendar.setEndTime(rs.getString("end_time"));
		System.out.println(calendar.getEventName());
		System.out.println(calendar.getEventDescription());
		System.out.println(calendar.getStartTime());
		System.out.println(calendar.getEndTime());
		return calendar;
		
	}

}
