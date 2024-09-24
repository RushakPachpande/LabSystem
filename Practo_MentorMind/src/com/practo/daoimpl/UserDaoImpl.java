package com.practo.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.practo.dao.UserDao;
import com.practo.database.DatabaseConnection;
import com.practo.entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User getUserById(int userId) throws SQLException {
		Connection connection;
		connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM User WHERE user_id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, userId);
		ResultSet resultSet = statement.executeQuery();

		User user = null;
		if (resultSet.next()) {
			user = new User();
			user.setUserId(resultSet.getInt("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setRole(resultSet.getString("role"));
		}

		resultSet.close();
		statement.close();
		connection.close();
		return user;

	}

	public User login(String username, String password) throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM User WHERE username = ? AND password = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet resultSet = statement.executeQuery();

		User user = null;
		if (resultSet.next()) {
			user = new User();
			user.setUserId(resultSet.getInt("user_id"));
			user.setUsername(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
		}

		resultSet.close();
		statement.close();
		connection.close();

		return user;
	}

}
