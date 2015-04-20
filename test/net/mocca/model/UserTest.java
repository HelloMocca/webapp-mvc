package net.mocca.model;

import static org.junit.Assert.*;
import net.mocca.controller.PasswordMismatchException;
import net.mocca.controller.UserNotFoundException;
import net.mocca.dao.UserDAO;
import net.mocca.model.User;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	@Before 
	public void setUp() {
		
	}
	
	@Test
	public void checkPassword() {
		User user = new User("uid","password","name","email");
		boolean result = user.checkPassword("password");
		assertTrue(result);
	}
	
	@Test
	public void loginCheck() throws Exception {
		User member = new User("user1", "password", "name", "email");
		UserDAO userDAO = new UserDAO();
		userDAO.insert(member);
		assertTrue(User.login(member.getUserId(), member.getPassword())) ;
	}
	
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExistedUser() throws Exception {
		User member = new User("user2", "password", "name", "email");
		User.login(member.getUserId(), member.getPassword());
	}
	
	@Test(expected=PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception {
		User member = new User("user2", "password", "name", "email");
		UserDAO userDAO = new UserDAO();
		userDAO.insert(member); 	 
		User.login(member.getUserId(), "잘못된패스워드");
	}
}
