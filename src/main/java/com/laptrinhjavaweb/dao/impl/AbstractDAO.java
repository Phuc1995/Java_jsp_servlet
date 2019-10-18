package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/jspservletjdbc";
			String user = "root";
			String password = "1234";

			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> result = new ArrayList<T>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			// set parameter
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				result.add(rowMapper.mapRow(resultSet));
			}
			for(T a: result) {
				System.out.println(a);
			}
			
			return result;
			
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	@Override
	public void update(String sql, Object... parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long insert(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for(int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					statement.setLong(index, (long) parameter);
				} else if(parameter instanceof String) {
					statement.setString(index, (String) parameter);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
