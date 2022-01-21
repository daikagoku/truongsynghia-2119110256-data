package com.example.user.dto;

import java.util.Calendar;

public class PostDto {
	private Long id;
	private Long authId;
	private String title;
	private String alias;
	private String content;
	private String summary;
	private String image;
	private Calendar createDate;
	private Calendar updateDate;
	public Long getId() {
		return id;
	}
	public void setId(long i) {
		this.id = i;
	}
	public Long getAuthId() {
		return authId;
	}
	public void setAuthId(Long authId) {
		this.authId = authId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Calendar getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Calendar date) {
		this.createDate = date;
	}
	public Calendar getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Calendar updateDate) {
		this.updateDate = updateDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
