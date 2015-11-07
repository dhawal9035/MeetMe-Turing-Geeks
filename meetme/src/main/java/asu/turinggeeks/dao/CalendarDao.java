package asu.turinggeeks.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import asu.turinggeeks.model.Calendar;

@Repository
public class CalendarDao {

	@Autowired
	DataSource dataSource;
	String query = null;
	JdbcTemplate jdbcTemplate;
	
	public boolean insertForManualCalendar(String[] startDate, String[] startTime, String[] endDate, String[] endTime,
			Calendar calendar, String emailId) {
		int length = startDate.length;
		String[] start = new String[length];
		String[] end = new String[length];
		/*String guestEmail = calendar.getGuestEmail();
		
		 * String singleMail[]= guestEmail.split(","); int totalEmailId =
		 * singleMail.length;
		 */
		for (int i = 0; i < length; i++) {
			start[i] = startDate[i] + " " + startTime[i];
			end[i] = endDate[i] + " " + endTime[i];
		}
		try {
			query = "INSERT INTO event_info " + "(event_name, email_id, event_description, guest_email) VALUES(?,?,?,?)";
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update(query, new Object[] { calendar.getEventName(), emailId, calendar.getEventDescription(),
					calendar.getGuestEmail() });

			query = "SELECT event_id from event_info where email_id = ? and event_name=?";
				int eventId = jdbcTemplate.queryForObject(query, new Object[] {emailId,calendar.getEventName()}, Integer.class);

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

}
