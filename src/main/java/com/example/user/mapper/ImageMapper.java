package com.example.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.user.dto.ImageDto;

public class ImageMapper implements RowMapper<ImageDto> {
	public ImageDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		ImageDto post = new ImageDto();
		   post.setId(rs.getLong("id"));
		   post.setTitle(rs.getString("title"));
		   post.setAlias(rs.getString("alias"));
		   post.setUrl(rs.getString("url"));
		   post.setPosition(rs.getString("position"));
		   Long index = rs.getLong("index");
		   if(!rs.wasNull()){
			   post.setIndex(index);
		   }
		   return post;
	}
}