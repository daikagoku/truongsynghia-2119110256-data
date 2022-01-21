package com.example.user.template;

import java.util.List;

import com.example.core.InitSelectSql;
import com.example.user.dto.ImageDto;
import com.example.user.mapper.ImageMapper;
import com.example.user.template.base.TemplateBase;

public class ImageTemplate extends TemplateBase{
	public ImageTemplate() {
		this.tables = "v_image";
	}
	public InitSelectSql imageSql = null;
	public InitSelectSql productSql = null;
	
	
	public void mergeSql() {
		if(productSql != null) {
			imageSql.addSubWhere("i.item_id", productSql.getSql(), "in");
		}
		this.sql = imageSql.getSql();	
		System.out.println(sql);
		imageSql = null;
		productSql = null;
	}
	public List<ImageDto> execute() {
		mergeSql();
		List<ImageDto> categorys = jdbcTemplateObject.query(this.sql, new ImageMapper());
		return categorys;
	};
	public void initGetImage() {	
		imageSql = new InitSelectSql("*","v_image i");
	};	
	public void addGetByPosition(String[] position) {
		imageSql.addWhere("i.position",position);
	};
	
	public void addGetByItemId(long[] item_id) {
		imageSql.addWhere("i.item_id",item_id);
	};
	
	
	public void initGetOfProduct() {	
		productSql = new InitSelectSql("p.id","v_product p");
	};	
	public void addGetByProductId(Long id) {
		productSql.addWhere("p.id",id);
	};
	public void addGetByProductAlias(String alias) {
		productSql.addWhere("p.alias",alias);
	};
	
	public void addGetByProductVersionAlias(String alias) {
		productSql.addWhere("p.version_alias",alias);
	};
}
