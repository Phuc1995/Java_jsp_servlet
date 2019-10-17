package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;

public class NewDAO extends AbstractDAO implements INewDAO {
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/jspservletjdbc";
			String user = "root";
			String password= "1234";
			
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		List<NewModel> result = new ArrayList<>();
		String sql = "SELECT * FROM news where categoryid = ?";
		//open connection
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				statement.setLong(1, categoryId);
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					NewModel news = new NewModel();
					news.setCategoryId(resultSet.getLong("id"));
					news.setTitle(resultSet.getString("title"));
					
					result.add(news);
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
				}catch (SQLException e) {
					return null;
				}
				
			}
		}
		
		return null;
	}

}
