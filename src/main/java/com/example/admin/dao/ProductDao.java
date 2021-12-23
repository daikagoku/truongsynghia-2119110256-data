package com.example.admin.dao;

import java.util.List;

import javax.sql.DataSource;

import com.example.core.entity.ProductEntity;

public interface ProductDao {
	public void setDataSource(DataSource ds);

	int getCount();

	int put(ProductEntity product);

	int delete(long id);

	int post(ProductEntity product);
}
