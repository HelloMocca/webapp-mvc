package net.mocca.model;

import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.annotations.Expose;

import net.mocca.controller.PasswordMismatchException;
import net.mocca.controller.UserNotFoundException;
import net.mocca.dao.UserDAO;

public class User {
	@Expose
	@NotNull
	@Size(min = 4, max = 12)
	private String userId;
	@Expose(serialize = false)
	@NotNull
	@Size(min = 4, max = 12)
	private String password;
	@Expose
	@NotNull
	@Size(min = 2, max = 10)
	private String name;
	@Expose
	@Email
	private String email;
	
	private static final Logger logger = LoggerFactory.getLogger(User.class);
    
	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public User() {
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public boolean checkPassword(String _password) {
		return this.password.equals(_password);
	}

	public static boolean login(String userId, String password)
			throws UserNotFoundException, PasswordMismatchException {
		User user = null;
		UserDAO userDAO = new UserDAO();
		if (userId == null) {
			logger.error("User.login: userId is null");
			throw new UserNotFoundException();
		}
		try {
			user = userDAO.select(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (user == null) {
			logger.debug("User Not Found Exception");
			throw new UserNotFoundException();
		}
		if (!(user.checkPassword(password))) {
			logger.debug("Password Mismatch Exception");
			throw new PasswordMismatchException();
		}
		return true;
	}

	public String toString() {
		return this.userId + "," + this.name + "," + this.email;
	}
}
