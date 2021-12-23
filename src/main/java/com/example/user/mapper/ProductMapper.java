package com.example.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.example.user.dto.ProductDto;

public class ProductMapper implements RowMapper<ProductDto> {
	public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		   ProductDto product = new ProductDto();
		   product.setId(rs.getInt("id"));
		   product.setTitle(rs.getString("title"));
		   product.setAlias(rs.getString("alias"));
		   product.setContent(rs.getString("content"));
		   product.setDescription(rs.getString("description"));	 
		   
		   String image = rs.getString("image");	
		   if(!rs.wasNull()) {
			   product.setImage(image);
		   }
		   
		   Float rating = rs.getFloat("rating");	
		   product.setRating(rating);
		   float price = rs.getFloat("price");
		   if(!rs.wasNull()) {
			   product.setPrice(price);
		   }	
		   float salePrice = rs.getFloat("sale_price");
		   if(!rs.wasNull()) {
			   product.setSalePrice(salePrice);
		   }
		   
		   Long versionId = rs.getLong("version_id");
		   if(!rs.wasNull()) {
			   product.setVersionId(versionId);	
		   }
		   product.setVersionTitle(rs.getString("version_title"));
		   product.setVersionAlias(rs.getString("version_alias"));
		   return product;
	}
}