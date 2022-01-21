package com.example.user.template;

import java.util.List;

import com.example.core.InitSelectSql;
import com.example.user.dto.VersionDto;
import com.example.user.mapper.VersionMapper;
import com.example.user.template.base.TemplateBase;

public class VersionTemplate extends TemplateBase{
	
	public InitSelectSql productSql = null;
	
	public VersionTemplate() {}
	
	public void mergeSql() {
		this.sql = productSql.getSql();	
		System.out.println(sql);
		productSql = null;
	}
	public void initGetCount() {
		productSql = new InitSelectSql("count(*) count","v_product p");
	}
	public void initGetVersion() {	
		productSql = new InitSelectSql("version_id id,version_title title,version_alias alias","v_product p","true");
	};	
	public void addGetById(long id) {
		productSql.addWhere("p.id",id);
	}
	public void addGetByVersionId(long version_id) {
		productSql.addWhere("p.version_id",version_id);
	}
	public void addGetByAlias(String alias) {
		productSql.addWhere("p.alias",alias);
	}
	public void addGetByVersionAlias(String version_alias) {
		productSql.addWhere("p.version_alias",version_alias);
	}
	
	public List<VersionDto> execute() {
		mergeSql();
		List<VersionDto> versions = jdbcTemplateObject.query(this.sql, new VersionMapper());
		return versions;
	};
}
