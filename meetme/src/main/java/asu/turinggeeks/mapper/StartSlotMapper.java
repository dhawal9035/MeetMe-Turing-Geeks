package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class StartSlotMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		StartSlotResultSetExtractor extractor = new StartSlotResultSetExtractor();
	    return extractor.extractData(rs);
	}

}
