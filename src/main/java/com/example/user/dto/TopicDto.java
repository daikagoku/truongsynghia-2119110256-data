package com.example.user.dto;

public class TopicDto {
	private long id;
	private String title;
	private String alias;
	
	private long parentId;
	private String parentTitle;
	private String parentAlias;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getParentTitle() {
		return parentTitle;
	}
	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}
	public String getParentAlias() {
		return parentAlias;
	}
	public void setParentAlias(String parentAlias) {
		this.parentAlias = parentAlias;
	}
}
