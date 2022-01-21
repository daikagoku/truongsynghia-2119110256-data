package com.example.user.template;

import java.util.List;

import com.example.core.InitSelectSql;
import com.example.user.dto.TopicDto;
import com.example.user.mapper.TopicMapper;
import com.example.user.template.base.TemplateBase;

public class TopicTemplate extends TemplateBase {
	public InitSelectSql topicSql = null;
	public InitSelectSql parentSql = null;
	public TopicTemplate() {
		this.tables = " v_topic ";
	}

	public void mergeSql() {
		if(parentSql != null) {
			topicSql.addSubWhere("t.parent_id", parentSql.getSql(), "in");
		}
		this.sql = topicSql.getSql();	
		System.out.println(sql);
		topicSql = null;
		parentSql = null;
	}
	public List<TopicDto> execute() {
		mergeSql();
		List<TopicDto> topics = jdbcTemplateObject.query(this.sql, new TopicMapper());
		return topics;
	};
	public void initGetCount() {
		topicSql = new InitSelectSql("count(*) count","v_topic p");
	}
	public void initGetTopic() {	
		topicSql = new InitSelectSql("*","v_topic t");
	};	

	public void addGetById(long id) {
		topicSql.addWhere("t.id",id);
	}
	public void addGetByAlias(String alias) {
		topicSql.addWhere("t.alias",alias);
	}
	public void initGetParentTopic() {	
		parentSql = new InitSelectSql("pt.id","v_topic pt");
	};
	public void addGetByParentId(long id) {
		parentSql.addWhere("pt.id",id);
	}
	public void addGetByParentAlias(String alias) {
		parentSql.addWhere("pt.alias",alias);
	}
}
