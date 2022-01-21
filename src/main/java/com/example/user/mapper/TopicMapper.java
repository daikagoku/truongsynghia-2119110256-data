package com.example.user.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.user.dto.TopicDto;

public class TopicMapper implements RowMapper<TopicDto> {
	public TopicDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		TopicDto topic = new TopicDto();
		topic.setId(rs.getInt("id"));
		topic.setTitle(rs.getString("title"));
		topic.setAlias(rs.getString("alias"));

		Long parentId = rs.getLong("parent_id");
		if (!rs.wasNull()) {
			topic.setParentId(parentId);
		}
		return topic;
	}
}