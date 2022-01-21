package com.example.user.template.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.core.Convert;
import com.example.core.InitSelectSql;


public class TemplateBase {
	protected DataSource dataSource;
	protected JdbcTemplate jdbcTemplateObject;
	protected Convert convert = new Convert();
	protected String tables;
	protected String join = "";

	protected String sql = "";
	
	protected ArrayList<Object> param = null;
	
	public TemplateBase() {}
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	};
	
	public void initParam() {
		this.param = new ArrayList<Object>();
	}
	public void addParam(Object value) {
		this.param.add(value);
	}
	public Long executeCount() {
		this.mergeSql();
		Map<String, Object> count = jdbcTemplateObject.queryForMap(this.sql);
		return (Long) count.get("count");
	}
	public void mergeSql() {}
}
