package net.mocca.dao;

import static org.junit.Assert.*;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

import net.mocca.model.*;
import net.mocca.util.ConnectionManager;

public class DAOtest {
	private UserDAO userDAO;
	private static final Logger logger = LoggerFactory.getLogger(DAOtest.class);
	
	@Before
	public void setup() {
		userDAO = new UserDAO();
	}
	
	@Test
	public void connection(){
		Connection conn = ConnectionManager.getConnection();
		assertNotNull(conn);
	}
	
	@Test
	public void CRUDTest() throws SQLException{
		//초기화
		User user = new User("test","1234","kim","test@mail.com");
		userDAO.delete(user.getUserId());
		
		//추가 테스트
		userDAO.insert(user);
		User findedUser = userDAO.select(user.getUserId());
		assertEquals(user, findedUser);
		logger.debug(user.getName());
		logger.debug(user.getEmail());
		logger.debug(user.getPassword());
		
		//수정 테스트
		User updateUser = new User("test","1234","gim","test@mail.com");
		userDAO.update(updateUser);
		findedUser = userDAO.select(user.getUserId());
		assertEquals(updateUser, findedUser);
	}
}
