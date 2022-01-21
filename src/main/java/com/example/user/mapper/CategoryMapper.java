package com.example.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.user.dto.CategoryDto;

public class CategoryMapper implements RowMapper<CategoryDto> {
	public CategoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		   CategoryDto category = new CategoryDto();
		   category.setId(rs.getInt("id"));
		   category.setTitle(rs.getString("title"));
		   category.setAlias(rs.getString("alias"));
		  		   
		   Long parentId = rs.getLong("parent_id");
		   if(!rs.wasNull()) {
			   category.setParentId(parentId);	
		   }
		   return category;
	}
}