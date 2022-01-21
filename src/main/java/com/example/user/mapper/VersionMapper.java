package com.example.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.user.dto.VersionDto;

public class VersionMapper implements RowMapper<VersionDto> {
	public VersionDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		   VersionDto ver = new VersionDto();
		   ver.setId(rs.getInt("id"));
		   ver.setTitle(rs.getString("title"));
		   ver.setAlias(rs.getString("alias")); 
		   
		   return ver;
	}
}
