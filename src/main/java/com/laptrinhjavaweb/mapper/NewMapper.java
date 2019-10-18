package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewModel;

public class NewMapper implements RowMapper<NewModel> {

	@Override
	public NewModel mapRow(ResultSet rs) {
		try {
			NewModel news = new NewModel();
			news.setCategoryId(rs.getLong("id"));
			news.setTitle(rs.getString("title"));
			return news;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
