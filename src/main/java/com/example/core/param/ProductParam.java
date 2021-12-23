package com.example.core.param;

public class ProductParam {
	private long id;
	private String operatorId = "=";
	
	private String alias;
	private String operatorAlias = "like";
	
	private String title;
	private String operatorTitle = "like";
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorAlias() {
		return operatorAlias;
	}
	public void setOperatorAlias(String operatorAlias) {
		this.operatorAlias = operatorAlias;
	}
	public String getOperatorTitle() {
		return operatorTitle;
	}
	public void setOperatorTitle(String operatorTitle) {
		this.operatorTitle = operatorTitle;
	}
}
