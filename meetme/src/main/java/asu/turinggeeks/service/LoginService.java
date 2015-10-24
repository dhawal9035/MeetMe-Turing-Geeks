package asu.turinggeeks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asu.turinggeeks.dao.LoginDao;
import asu.turinggeeks.model.UserInfo;

@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;

	public boolean checkUser(UserInfo userForm) {
		return loginDao.checkUser(userForm);
	}
	
	public boolean updatePassword(UserInfo userForm){
		return loginDao.updatePassword(userForm);
	}
	
}
