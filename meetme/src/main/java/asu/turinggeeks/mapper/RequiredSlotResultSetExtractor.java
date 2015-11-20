package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;

import asu.turinggeeks.model.Calendar;

@SuppressWarnings("rawtypes")
public class RequiredSlotResultSetExtractor implements ResultSetExtractor {

	public Object extractData(ResultSet rs) throws SQLException {
		Calendar calendar = new Calendar();
		calendar.setRequiredTime(rs.getString("preferred_time")); 
		return calendar;
	}

}
