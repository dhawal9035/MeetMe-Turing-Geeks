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
		String query="INSERT INTO user_info "+ "(email_id, first_name, last_name, password) VALUES (?,?,?,?)";
		JdbcTemplate jdbcTemplate= new JdbcTemplate(dataSource);
		
		int info = jdbcTemplate.update(query, new Object[]{userForm.getEmail(), userForm.getFirstName(), userForm.getLastName(), userForm.getPassword()});
		
		query = "INSERT INTO user_roles " + "(email_id, user_role) VALUES (?,?)";
		
		int roles  = jdbcTemplate.update(query, new Object[]{userForm.getEmail(), "ROLE_USER"});
		
		if(info == 1 && roles == 1)
			return true;
		else
			return false;
	}
}
