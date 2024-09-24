package com.practo.dao;

import java.sql.SQLException;

import com.practo.entity.User;

public interface UserDao {

	public User getUserById(int userId) throws SQLException;

	public User login(String username, String password) throws SQLException;
}
