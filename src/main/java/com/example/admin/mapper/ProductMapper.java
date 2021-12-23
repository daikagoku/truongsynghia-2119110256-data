package com.example.admin.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.core.entity.ProductEntity;

public class ProductMapper  implements RowMapper<ProductEntity> {
	public ProductEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		   ProductEntity product = new ProductEntity();
		   product.setId(rs.getLong("id"));
		   product.setTitle(rs.getString("title"));
		   product.setAlias(rs.getString("alias"));
		   product.setContent(rs.getString("content"));
		   product.setDescription(rs.getString("description"));	
		   product.setStatus(rs.getBoolean("status"));
		   product.setTrash(rs.getBoolean("trash"));
		   return product;
	}
}
