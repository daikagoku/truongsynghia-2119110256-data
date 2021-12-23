package com.example.user.dto;

import com.example.core.Convert;

public class ProductDto {
	private long id;
	private String title;
	private String alias;
	private String content;
	private String description;
	
	private Float rating;
	private String image;
	private Float price;
	private Float salePrice;
	
	private long versionId;
	private String versionTitle;
	private String versionAlias;
	
	@SuppressWarnings("unused")
	private Convert convert = new Convert();
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
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}
	
	public long getVersionId() {
		return versionId;
	}
	public void setVersionId(long versionId) {
		this.versionId = versionId;
	}
	public String getVersionTitle() {
		return versionTitle;
	}
	public void setVersionTitle(String versionTitle) {
		this.versionTitle = versionTitle;
	}
	public String getVersionAlias() {
		return versionAlias;
	}
	public void setVersionAlias(String versionAlias) {
		this.versionAlias = versionAlias;
	}

}
