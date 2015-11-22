package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import asu.turinggeeks.model.Calendar;

@SuppressWarnings("rawtypes")
public class CalendarResultSetExtractor implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		Calendar calendar = new Calendar();
		calendar.setEventName(rs.getString(1));
		calendar.setEventDescription(rs.getString(2));
		calendar.setStartTime(rs.getString(3));
		calendar.setEndTime(rs.getString(4));
		return calendar;
		
	}

}
