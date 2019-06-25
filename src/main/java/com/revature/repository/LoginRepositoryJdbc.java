package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.Login;
import com.revature.util.ConnectionUtil;

/**
 *
 * This is the Account DAO (Data Access Object).
 *
 * Defines CRUD operations for this particular pojo. NO BUSINESS LOGIC SHOULD BE
 * PRESENT on these kind of Objects.
 */

public class LoginRepositoryJdbc implements LoginRepository {

	private static final Logger LOGGER = Logger.getLogger(LoginRepositoryJdbc.class);

	@Override
	public Login findByUsername(String login_username) {
		Login login = new Login();
		LOGGER.trace("Entering new Info: " + login);
		try (Connection connection = ConnectionUtil.getConnection()) {
			int parameterIndex = 0;
			String sql = "SELECT * FROM LOGIN WHERE A_USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(++parameterIndex, login_username);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String user_name = result.getString("LOGIN_USERNAME");
				if (user_name.equals(login_username)) {
					login.setLogin_username(result.getString("LOGIN_USERNAME"));
					login.setLogin_password(result.getString("LOGIN_PASSWORD"));
				}
			}

		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return login;
	}

}
