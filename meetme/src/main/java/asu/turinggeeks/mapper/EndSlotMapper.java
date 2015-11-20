package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class EndSlotMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		EndSlotResultSetExtractor extractor = new EndSlotResultSetExtractor();
	    return extractor.extractData(rs);
	}

}
