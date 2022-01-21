package com.example.user.template;

import java.util.List;

import com.example.core.InitSelectSql;
import com.example.user.dto.PostDto;
import com.example.user.mapper.PostMapper;
import com.example.user.template.base.TemplateBase;

public class PostTemplate extends TemplateBase{
	public PostTemplate() {}
	public InitSelectSql postSql = null;
	public InitSelectSql topicSql = null;
	public InitSelectSql postTopicSql = null;
	public void initGetPost() {
		postSql = new InitSelectSql("*","v_post p");
	}
	public void mergeSql() {
		
		if(postTopicSql != null) {
			if(topicSql != null) {
				postTopicSql.addSubWhere("pt.parent_id",topicSql.getSql(),"in");
			}
			postSql.addSubWhere("p.id",postTopicSql.getSql(),"in");
		}
		this.sql = postSql.getSql();	
		System.out.println(sql);
		postSql = null;
		topicSql = null;
		postTopicSql = null;
	}
	public List<PostDto> execute() {
		mergeSql();
		List<PostDto> posts = jdbcTemplateObject.query(this.sql, new PostMapper());
		return posts;
	};
	public void addGetById(long id) {
		postSql.addWhere("p.id",id);
	}
	public void addGetByAlias(String alias) {
		postSql.addWhere("p.alias",alias);
	}
	
	
	public void initGetPostByParentTopic() {
		topicSql = new InitSelectSql("t.id","v_topic t");
	}
	public void addGetByParentTopicId(long id){
		topicSql.addWhere("pt.id",id);
	}

	public void addGetByParentTopicAlias(String alias){
		topicSql.addWhere("pt.alias",alias);
	}
	
	
	public void initGetPostByTopic() {
		postTopicSql = new InitSelectSql("pt.post_id","v_post_topic pt");
	}
	public void addGetByTopicId(long id){
		postTopicSql.addWhere("pt.topic_id",id);
	}

	public void addGetByTopicAlias(String alias){
		postTopicSql.addWhere("pt.alias",alias);
	}
	
	public void setOffset(long offset) {
		postSql.setOffset(offset);
		
	}
	public void setLimit(long limit) {
		postSql.setLimit(limit);
		
	}

}
