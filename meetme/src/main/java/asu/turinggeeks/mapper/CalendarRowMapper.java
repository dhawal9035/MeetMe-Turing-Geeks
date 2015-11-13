package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class CalendarRowMapper implements RowMapper {
	
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		CalendarResultSetExtractor extractor = new CalendarResultSetExtractor();
	    return extractor.extractData(rs);
	}
}