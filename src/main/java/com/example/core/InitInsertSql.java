package com.example.core;

public class InitInsertSql {
	protected String table = "";
	protected String columns = "";
	protected String values = "";
	protected String returning = "";
	
	public InitInsertSql(String table) {
		this.table = table;
	}
	public void setReturn(String key) {
		returning = key;
	}
	public void addValue(String key) {
		if(!columns.equals("")) {
			columns+=",";
		}
		columns+=key;
		
		if(!values.equals("")) {
			values+=",";
		}
		values+="NULL";
	}
	public void addValue(String key,long value) {
		if(!columns.equals("")) {
			columns+=",";
		}
		columns+=key;
		if(!values.equals("")) {
			values+=",";
		}
		values+=value;
	}
	public void addValue(String key,String value) {
		addValue(key,value,false);
	}
	
	public void addValue(String key,String value,Boolean nvarchar) {
		if(!columns.equals("")) {
			columns+=",";
		}
		columns+=key;
		
		if(!values.equals("")) {
			values+=",";
		}
		if(nvarchar) {
			values+="N'"+value+"'";
		}else {
			values+="'"+value+"'";
		}
	}
	public String getSql() {
		String sql = " ";
		if(!table.equals("")) {
			sql += " insert into "+table;
		}
		if(!columns.equals("")) {
			sql += " ( "+columns+" ) ";
		}
		if(!values.equals("")) {
			sql += " values("+values+")";
		}
		if(!returning.equals("")) {
			sql += " RETURNING "+returning;
		}
		return sql;
	}
}
