package com.example.admin.dao;

import javax.sql.DataSource;

import com.example.core.entity.CategoryEntity;

public interface CategoryDao {
	public void setDataSource(DataSource ds);

	int getCount();

	int put(CategoryEntity category);

	int delete(long id);

	int post(CategoryEntity category);
}
