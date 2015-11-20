package asu.turinggeeks.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class OptionalSlotMapper implements RowMapper {
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		OptionalSlotResultSetExtractor extractor = new OptionalSlotResultSetExtractor();
	    return extractor.extractData(rs);
	}

}
