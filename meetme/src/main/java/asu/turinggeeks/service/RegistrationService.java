package asu.turinggeeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asu.turinggeeks.dao.RegistrationDao;
import asu.turinggeeks.model.UserInfo;

@Service
public class RegistrationService {
	
	@Autowired
	RegistrationDao registrationDao;
	
	public boolean registerUser(UserInfo userForm){
		
		return registrationDao.registerUser(userForm);
	}
	
}
