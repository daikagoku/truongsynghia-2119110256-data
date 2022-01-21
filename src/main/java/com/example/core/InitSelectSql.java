package com.example.core;

public class InitSelectSql {
	protected String select = "";
	protected String from = "";
	protected String where = "";
	protected String limit = "all";
	protected String offset = "0";
	
	public InitSelectSql(String select,String from) {
		this.select = select;
		this.from = from;
	}
	public InitSelectSql(String select,String from, String where) {
		this.select = select;
		this.from = from;
		this.where=where;
	}
	public void addSubSelect(String sql) {
		addSelect("( " +sql+" )");
	}
	public void addSelect(String value) {
		if(!(this.select.equals(""))) {
			this.select+=",";
		}
		this.select += " "+value+" ";
	}
	
	public void addFrom(String value) {
		if(!this.from.equals("")) {
			this.from+=",";
		}
		this.from += " "+value+" ";
	}
	public void addSubWhere(String key,String value,String operator) {
		String sql = key+" "+operator+" ( "+value+" ) ";
		addWhere(sql);
	}
	public void addWhere(String key,double value) {
		addWhere(key,value,"=");
	}
	public void addWhere(String key,double value,String operator) {
		String sql = getWhere(key,value,operator);
		addWhere(sql);
	}
	public void addWhere(String key,long value) {
		addWhere(key,value,"=");
	}
	public void addWhere(String key,long value,String operator) {
		String sql = getWhere(key,value,operator);
		addWhere(sql);
	}
	
	public void addWhereBetween(String key,long start,long end) {
		String sql = key+" between "+start+" and "+end;
		addWhere(sql);
	}
	public void addWhereBetween(String key,double start,double end) {
		String sql = key+" between "+start+" and "+end;
		addWhere(sql);
	}
	public void addWhere(String key, long[] values) {
		String sql = "";
		for(long value : values) {
			if(sql.equals("")) {
				sql = getWhere(key,value);
			}else{
				sql += " or "+getWhere(key,value);
			}
		}
		addWhereGroup(sql);
	}
	public void addWhere(String key, String[] values) {
		String sql = "";
		for(String value : values) {
			if(sql.equals("")) {
				sql = getWhere(key,value);
			}else{
				sql += " or "+getWhere(key,value);
			}
		}
		addWhereGroup(sql);
	}
	public void orWhere(String key,String value) {
		orWhere(key,value,true);
	}
	public void orWhere(String key,String value,boolean operator) {
		String sql = getWhere(key,value,operator);
		orWhere(sql);
	}	
	public void addWhere(String key,String value) {
		addWhere(key,value,true);
	}
	public void addWhere(String key,String value,boolean operator) {
		String sql = getWhere(key,value,operator);
		addWhere(sql);
	}	
	public void addWhere(String key,boolean value) {
		addWhere(key,value,true);
	}
	public void addWhere(String key,boolean value,boolean operator) {
		String sql = getWhere(key,value,operator);
		addWhere(sql);
	}
	public void orWhere(String sql) {
		if(!sql.equals("")){
			if(this.where != "") {
				this.where+= " or ";
			}
			this.where += sql;
		}
	}
	public void orWhereGroup(String sql) {
		orWhere(" ( "+ sql + " ) ");
	}
	public void addWhere(String sql) {
		if(!sql.equals("")){
			if(this.where != "") {
				this.where+= " and ";
			}
			this.where += sql;
		}
	}
	public void addWhereGroup(String sql) {
		addWhere(" ( "+ sql + " ) ");
	}
	public String getWhere(String key,String value) {
		return getWhere(key,value,true);
	}
	public String getWhere(String key,String value,boolean operator) {
		String sql = key+" ";
		if(!operator) {
			sql+=" not ";
		}
		sql+=" like '"+value+"'";
		return sql;
	}
	public String getWhere(String key,long value) {
		return getWhere(key,value,"=");
	}
	public String getWhere(String key,long value,String operator) {
		String sql = key+" "+operator+" "+value;
		return sql;
	}
	public String getWhere(String key,double value) {
		return getWhere(key,value,"=");
	}
	public String getWhere(String key,double value,String operator) {
		String sql = key+" "+operator+" "+value;
		return sql;
	}
	public String getWhere(String key,boolean value) {
		return getWhere(key,value) ;
	}
	public String getWhere(String key,boolean value,boolean operator) {
		String sql = key+" is ";
		if(!operator) {
			sql+=" not ";
		}
		sql+=value;
		return sql;
	}
	
	public void setLimit() {
		this.limit = "all";
	}
	public void setLimit(long limit) {
		this.limit = limit+"";
	}
	public void setOffset() {
		this.offset = "0";
	}
	public void setOffset(long offset) {
		this.offset = offset+"";
	}
	
	public String getSql() {
		String sql = " ";
		if(!select.equals("")) {
			sql += " select "+select;
		}
		if(!from.equals("")) {
			sql += " from "+from;
		}
		if(!where.equals("")) {
			sql += " where "+where;
		}
		sql += " limit "+limit;
		sql += " offset "+offset;
		return sql;
	}
	
}
