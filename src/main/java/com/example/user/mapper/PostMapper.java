package com.example.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.user.dto.PostDto;

public class PostMapper implements RowMapper<PostDto> {
	public PostDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		   PostDto post = new PostDto();
		   post.setId(rs.getLong("id"));
		   post.setAuthId(rs.getLong("auth_id"));
		   post.setTitle(rs.getString("title"));
		   post.setAlias(rs.getString("alias"));
		   post.setContent(rs.getString("content"));
		   post.setSummary(rs.getString("summary"));	
		   return post;
	}
}
