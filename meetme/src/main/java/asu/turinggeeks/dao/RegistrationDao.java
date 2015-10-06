package asu.turinggeeks.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import asu.turinggeeks.model.UserInfo;

@Repository
public class RegistrationDao {
	
	@Autowired
	DataSource dataSource;
	
	public boolean registerUser(UserInfo userForm){
		String query="INSERT INTO user_info "+ "(first_name, last_name, email_id, password) VALUES (?,?,?,?)";
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
		
		jdbcTemplate.update(query, new Object[]{userForm.getFirstName(), userForm.getLastName(), userForm.getEmail(), userForm.getPassword()});
				
		return true;
	}
}
