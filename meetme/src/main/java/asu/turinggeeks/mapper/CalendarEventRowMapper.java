package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class CalendarEventRowMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		CalendarEventResultSetExtractor extractor = new CalendarEventResultSetExtractor();
	    return extractor.extractData(rs);
	}
}
