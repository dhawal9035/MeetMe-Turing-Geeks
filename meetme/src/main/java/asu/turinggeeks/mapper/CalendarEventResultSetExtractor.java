package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import asu.turinggeeks.model.Calendar;

@SuppressWarnings("rawtypes")
public class CalendarEventResultSetExtractor implements ResultSetExtractor{
	public Object extractData(ResultSet rs) throws SQLException {
		Calendar calendar = new Calendar();
		calendar.setEventId(rs.getInt("event_id"));
		calendar.setEventName(rs.getString("event_name"));
		calendar.setEventDescription(rs.getString("event_description"));
		calendar.setUuid(rs.getString("uuid"));
		return calendar;		
	}
}
