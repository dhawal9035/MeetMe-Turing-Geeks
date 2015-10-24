package asu.turinggeeks.dao;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import asu.turinggeeks.model.UserInfo;

@Repository
public class LoginDao {
	
	@Autowired
	DataSource dataSoruce;
	JdbcTemplate jdbcTemplate;
	String query=null;
	
	public boolean checkUser(UserInfo userForm) {
		
		query = "Select count(0) from user_info where email_id = ?";
		jdbcTemplate = new JdbcTemplate(dataSoruce);
		
		int valid = jdbcTemplate.queryForObject(query, new Object[] {userForm.getEmail()},Integer.class);
		if(valid == 0)
			return false;
		else
			return true;
	}

	public boolean updatePassword(UserInfo userForm) {
		jdbcTemplate = new JdbcTemplate(dataSoruce);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
		
		query = "Update user_info set password = ? where email_id = ?";
		int passwordCheck = jdbcTemplate.update(query, new Object[] {userForm.getPassword(), userForm.getEmail()});
		if(passwordCheck == 0)
			return false;
		else
			return true;
	}
	
}
