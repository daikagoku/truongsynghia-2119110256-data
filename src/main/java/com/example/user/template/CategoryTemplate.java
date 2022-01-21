package com.example.user.template;

import java.util.List;

import com.example.core.InitSelectSql;
import com.example.user.dto.CategoryDto;
import com.example.user.mapper.CategoryMapper;
import com.example.user.template.base.TemplateBase;

public class CategoryTemplate extends TemplateBase {
	public InitSelectSql categorySql = null;
	public InitSelectSql parentSql = null;
	public CategoryTemplate() {
		this.tables = " v_category ";
	}
	
	public void mergeSql() {
		if(parentSql != null) {
			categorySql.addSubWhere("t.parent_id", parentSql.getSql(), "in");
		}
		this.sql = categorySql.getSql();	
		System.out.println(sql);
		categorySql = null;
		parentSql = null;
	}
	public List<CategoryDto> execute() {
		mergeSql();
		List<CategoryDto> categorys = jdbcTemplateObject.query(this.sql, new CategoryMapper());
		return categorys;
	};
	public void initGetCategory() {	
		categorySql = new InitSelectSql("*","v_category t");
	};	

	public void addGetById(long id) {
		categorySql.addWhere("t.id",id);
	}
	public void addGetByAlias(String alias) {
		categorySql.addWhere("t.alias",alias);
	}
	public void initGetParentCategory() {	
		parentSql = new InitSelectSql("pt.id","v_category pt");
	};
	public void addGetByParentId(long id) {
		parentSql.addWhere("pt.id",id);
	}
	public void addGetByParentAlias(String alias) {
		parentSql.addWhere("pt.alias",alias);
	}
}
