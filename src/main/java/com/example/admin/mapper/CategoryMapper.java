package com.example.admin.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.core.entity.CategoryEntity;

public class CategoryMapper implements RowMapper<CategoryEntity> {
	public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		   CategoryEntity category = new CategoryEntity();
		   category.setId(rs.getLong("id"));
		   category.setTitle(rs.getString("title"));
		   category.setAlias(rs.getString("alias"));
		   long parentId = rs.getLong("parent_id");
		   if(rs.wasNull()) {
			   category.setParentId(parentId);
		   }
		   category.setStatus(rs.getBoolean("status"));
		   category.setTrash(rs.getBoolean("trash"));
		   return category;
	}
}
