package net.mocca.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdbcTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(JdbcTemplate.class);
	
	public void executeUpdate(String sql, Object... parameteres) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < parameteres.length; i++) {
				pstmt.setObject(i+1, parameteres[i]);
			}
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public <T> T executeQuery(String sql, RowMapper<T> rm, Object... parameteres) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < parameteres.length; i++) {
				pstmt.setObject(i+1, parameteres[i]);
			}
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				logger.warn("UserDAO: User not found!");
				return null;
			}
			return rm.mapRow(rs);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
