package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO{
	List<CategoryModel> findAll();
	
}
