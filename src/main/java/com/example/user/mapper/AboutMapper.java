package com.example.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.user.dto.AboutDto;

public class AboutMapper implements RowMapper<AboutDto> {
	public AboutDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		   AboutDto about = new AboutDto();
		   about.setId(rs.getLong("id"));
		   about.setTitle(rs.getString("title"));
		   about.setAlias(rs.getString("alias"));
		   about.setValue(rs.getString("value"));	
		   return about;
	}
}
