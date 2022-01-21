package com.example.user.template;

import java.util.List;

import com.example.core.InitSelectSql;
import com.example.user.dto.AboutDto;
import com.example.user.mapper.AboutMapper;
import com.example.user.template.base.TemplateBase;

public class AboutTemplate extends TemplateBase{
	public InitSelectSql aboutSql = null;
	public AboutTemplate() {}
	public void mergeSql() {
		
		this.sql = aboutSql.getSql();	
		System.out.println(sql);
		aboutSql = null;
	}
	public void initGetAbout() {
		aboutSql = new InitSelectSql("*","v_about a");
	}
	
	public List<AboutDto> execute() {
		mergeSql();
		List<AboutDto> abouts = jdbcTemplateObject.query(this.sql, new AboutMapper());
		return abouts;
	};
	
	
	public void setOffset(long offset) {
		aboutSql.setOffset(offset);
		
	}
	public void setLimit(long limit) {
		aboutSql.setLimit(limit);
		
	}
	public void addGetById(long id) {
		aboutSql.addWhere("a.id",id);
	}
	public void addGetByAlias(String alias) {
		aboutSql.addWhere("a.alias",alias);
	}
}
