package com.laptrinhjavaweb.dao;

import java.util.List;

import javax.swing.tree.RowMapper;

public interface GenericDAO<T> {
	<T> List<T> query(String sql, T, Object... parameters);
	void update (String sql, Object... parameters);
	Long insert (String sql, Object... parameters);
	int count(String sql, Object... parameters);
}
