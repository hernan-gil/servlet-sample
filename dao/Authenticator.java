package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;

import servicelocator.ServiceLocator;

public class Authenticator {

	public String authenticate(String username, String password) {
		Properties prop = new Properties();
		InputStream inputStream = ServiceLocator.class.getClassLoader().getResourceAsStream("queries.properties");
		Connection conn = null;
		PreparedStatement pstmt  = null;
		ResultSet rs    = null;
		String query = "";
		String result = "failure";
		try {
			prop.load(inputStream);
			query = prop.getProperty("SQL.selectUser");
			conn = ServiceLocator.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = "success";
			} else {
				result = "failure";
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}