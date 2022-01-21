package com.example.user.template;

import java.util.Calendar;
import java.util.List;

import com.example.core.InitSelectSql;
import com.example.user.dto.ProductDto;
import com.example.user.mapper.ProductMapper;
import com.example.user.template.base.TemplateBase;


public class ProductTemplate extends TemplateBase{
	
	
	public InitSelectSql productSql = null;
	public InitSelectSql productCategorySql = null;
	public InitSelectSql categorySql = null;
	
	public ProductTemplate() {
		this.tables = " v_product";
	}
	
	public void mergeSql() {
		if(productCategorySql != null) {
			if(categorySql != null) {
				productCategorySql.addSubWhere("pc.parent_id",categorySql.getSql(),"in");
			}
			productSql.addSubWhere("p.id",productCategorySql.getSql(),"in");
		}
		this.sql = productSql.getSql();	
		System.out.println(sql);
	
		productSql = null;
		categorySql = null;
		productCategorySql = null;
	}
	public void initGetCount() {
		productSql = new InitSelectSql("count(*) count","v_product p");
	}
	public void initGetProduct() {	
		productSql = new InitSelectSql("*","v_product p");
		InitSelectSql imageSql = new InitSelectSql("url image","v_image i");
		imageSql.addWhere("p.image_id = i.id");
		
		InitSelectSql ratingSql = new InitSelectSql("AVG(vote) rating","v_product_rating r");
		ratingSql.addWhere("r.product_id = p.id");
		
		productSql.addSubSelect(imageSql.getSql());
		productSql.addSubSelect(ratingSql.getSql());
	};	

	public List<ProductDto> execute() {
		mergeSql();
		List<ProductDto> products = jdbcTemplateObject.query(this.sql, new ProductMapper());
		return products;
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
	public void addGetByPrice(double start,double end) {
		if(start > -1 && end > -1) {
			productSql.addWhereBetween("p.price",start,end);
		}else {
			if(start > -1 && end < 0) {
				addGetByPrice(start,">");
			}else if(start < 0 && end > -1) {
				addGetByPrice(end,"<");
			}
		}
	}
	public void addGetByPrice(double price,String operator) {
		productSql.addWhere("p.price",price,operator);
	}
	
	
	public void addGetBySalePrice(double start,double end) {
		if(start > -1 && end > -1) {
			productSql.addWhereBetween("p.sale_price",start,end);
		}else {
			if(start > -1 && end < 0) {
				addGetBySalePrice(start,">");
			}else if(start < 0 && end > -1) {
				addGetBySalePrice(end,"<");
			}
		}
	}
	public void addGetBySalePrice(double price,String operator) {
		productSql.addWhere("p.sale_price",price,operator);
	}
	public void addGetBySalePriceDate(Calendar start,Calendar end) {
		
	}
	public void addGetBySaleDate(Calendar date,String operator) {
		
	}
	public void setOffset(long offset) {
		productSql.setOffset(offset);
		
	}
	public void setLimit(long limit) {
		productSql.setLimit(limit);
		
	}
	
	
	public void initProductCategoryGet() {			
		productCategorySql = new InitSelectSql("pc.product_id","v_product_category pc");		
	};		
	public void addGetByCategoryAlias(String[] alias) {
		productCategorySql.addWhere("pc.alias", alias);
		
	}
	
	public void initCategoryGet() {			
		categorySql = new InitSelectSql("c.id","v_category c");		
	};
	public void addGetByCategoryParentAlias(String parent_alias) {
		categorySql.addWhere("c.alias", parent_alias);
		
	}

	public void addGetByQuery(String query) {
		productSql.orWhere("p.title", "%"+query+"%");
		
	}
}
