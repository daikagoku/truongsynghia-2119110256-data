package com.example.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.user.dto.UserDto;



public class UserMapper implements RowMapper<UserDto> {
	public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDto user = new UserDto();
		user.setId(rs.getInt("id"));
		user.setFullname(rs.getString("fullname"));		
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		return user;
	}
}