package net.mocca.dao;

import java.sql.SQLException;

import net.mocca.model.*;
import net.mocca.util.JdbcTemplate;
import net.mocca.util.RowMapper;

public class UserDAO {
	public void insert(User user) throws SQLException {
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate("INSERT INTO USERS VALUES(?,?,?,?)", user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public User select(String userId) throws SQLException {
		RowMapper<User> rm = rs ->
			new User(rs.getString("userId"),
					rs.getString("password"), rs.getString("name"),
					rs.getString("email"));
		JdbcTemplate template = new JdbcTemplate();
		return template.executeQuery("SELECT * FROM USERS WHERE userId = ?", rm, userId);
	}

	public void delete(String userId) throws SQLException {
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate("DELETE FROM USERS WHERE userId = ?", userId);
	}

	public void update(User user) throws SQLException {
		JdbcTemplate template = new JdbcTemplate();
		template.executeUpdate("UPDATE USERS set password = ?, name = ?, email = ? where userId = ?",user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}
}
