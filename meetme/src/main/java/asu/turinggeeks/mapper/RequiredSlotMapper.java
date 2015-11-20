package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class RequiredSlotMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		RequiredSlotResultSetExtractor extractor = new RequiredSlotResultSetExtractor();
	    return extractor.extractData(rs);
	}

}
