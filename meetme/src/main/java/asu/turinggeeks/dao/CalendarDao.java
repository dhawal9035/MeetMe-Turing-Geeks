package asu.turinggeeks.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import asu.turinggeeks.mapper.CalendarRowMapper;
import asu.turinggeeks.model.Calendar;

@Repository
public class CalendarDao {

	@Autowired
	DataSource dataSource;
	String query = null;
	JdbcTemplate jdbcTemplate;
	
	public boolean insertForManualCalendar(String[] start, String[] end, Calendar calendar, String emailId, String uuid) {
		int length = start.length;
		try {
			query = "INSERT INTO event_info " + "(event_name, email_id, event_description, guest_email, uuid) VALUES(?,?,?,?,?)";
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(query, new Object[] { calendar.getEventName(), emailId, calendar.getEventDescription(),
					calendar.getGuestEmail(), uuid });

			query = "SELECT event_id from event_info where uuid=?";
				int eventId = jdbcTemplate.queryForObject(query, new Object[] {uuid}, Integer.class);

			query = "INSERT into event_time_details " + "(event_id,start_time,end_time) VALUES(?,?,?)";
			for (int i = 0; i < length; i++) {
				jdbcTemplate.update(query, new Object[] { eventId, start[i], end[i] });
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public String getFirstName(String email) {
		query="Select first_name from user_info where email_id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		String firstName = jdbcTemplate.queryForObject(query, new Object[] {email},String.class);
		return firstName;
		
	}

	public int getEventId(String uuid) {
		query = "SELECT event_id from event_info where uuid=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		int eventId = jdbcTemplate.queryForObject(query, new Object[] {uuid}, Integer.class);
		return eventId;
	}

	@SuppressWarnings("unchecked")
	public List<Calendar> getAllEventDetails(int eventId) {
		List<Calendar> probableTimings = null;
		query = "select EI.event_name, EI.event_description, ET.start_time, ET.end_time from event_info as EI, event_time_details as ET where EI.event_id = ET.event_id and EI.event_id=?";
		jdbcTemplate = new JdbcTemplate(dataSource);
		probableTimings = jdbcTemplate.query(query, new Object[] {eventId}, new CalendarRowMapper());
		return probableTimings;
	}

	public void storeUserResponse(String guestMail, String[] checkedTimings, String uuid) {
		query = "INSERT into guest_response " + " (uuid, guest_email,preferred_time) VALUES (?,?,?)";
		jdbcTemplate = new JdbcTemplate(dataSource);
		for(int i=0;i<checkedTimings.length;i++){
			jdbcTemplate.update(query, new Object[] { uuid, guestMail, checkedTimings[i]});
		}
	}
	
	

}
