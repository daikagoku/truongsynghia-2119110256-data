package com.example.user.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.user.dto.ProductDto;
import com.example.user.mapper.ProductMapper;
import com.example.core.Convert;
import com.example.core.InitData;

public class ProductTemplate {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private Convert convert = new Convert();
	private InitData init = new InitData();
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}	
	public void runInit() {
	}
	public int getCount() {
		String SQL = "select count(*) as count "
				+" from product p,product_version v "
				+" where p.id=v.productId ";
		Map<String, Object> count = jdbcTemplateObject.queryForMap(SQL);
		return (int) count.get("count");

	}
	public List<ProductDto> get(long id,long version_id) {
		String SQL = "select p.id id,p.title title,p.alias alias"
				+",content,description,price,sale_price,"
				+"(select i.url image"
						+" from image i,image_using u "
						+" where  i.id = u.image_id "
				 				+" and u.position_id = v.id "
				 				+" and u.position like 'product-version' "
				 				+" and u.status = true and u.trash = false"
								+" and i.status = true and i.trash = false"
				+"),"
				+"(select AVG(vote) rating "
						 +" from product_rating r  "
						 +" where  r.product_id = p.id  " 
							 +" and r.status = true and r.trash = false "
				+"),"
				+"v.id version_id,v.title version_title,v.alias version_alias"
				+" from product p,product_version v"
				+" where p.id = ? and v.id = ?"
					+" and p.id=v.product_id"
					+" and p.status = true and p.trash = false"
					+" and v.status = true and v.trash = false";
		List<ProductDto> products = jdbcTemplateObject.query(SQL, new ProductMapper(),new Object[] { id,version_id});
		System.out.println(SQL);
		return products;
	}
	public List<ProductDto> get(String alias,String versionAlias) {
		String SQL = "select p.id id,p.title title,p.alias alias"
				+",content,description,price,sale_price,"
				+"(select i.url image"
						+" from image i,image_using u "
						+" where  i.id = u.image_id "
				 				+" and u.position_id = v.id "
				 				+" and u.position like 'product-version' "
				 				+" and u.status = true and u.trash = false"
								+" and i.status = true and i.trash = false"
				+"),"
				+"(select AVG(vote) rating "
						 +" from product_rating r  "
						 +" where  r.product_id = p.id  " 
							 +" and r.status = true and r.trash = false "
				+"),"
				+"v.id version_id,v.title version_title,v.alias version_alias"
				+" from product p,product_version v"
				+" where p.alias like ? and v.alias like ?"
					+" and p.id=v.product_id"
					+" and p.status = true and p.trash = false"
					+" and v.status = true and v.trash = false";
		List<ProductDto> products = jdbcTemplateObject.query(SQL, new ProductMapper(),new Object[] { alias,versionAlias});
		System.out.println(SQL);
		return products;
	}
	public List<ProductDto> getAll() {
		String SQL = "select p.id id,p.title title,p.alias alias"
				+",content,description,price,sale_price,"
				+"(select i.url image"
						+" from image i,image_using u "
						+" where  i.id = u.image_id "
				 				+" and u.position_id = v.id "
				 				+" and u.position like 'product-version' "
				 				+" and u.status = true and u.trash = false"
								+" and i.status = true and i.trash = false"
				+"),"
				+"(select AVG(vote) rating "
						 +" from product_rating r  "
						 +" where  r.product_id = p.id  " 
							 +" and r.status = true and r.trash = false "
				+"),"
				+"v.id version_id,v.title version_title,v.alias version_alias"
				+" from product p,product_version v"
				+" where p.id=v.product_id"
					+" and p.status = true and p.trash = false"
					+" and v.status = true and v.trash = false";
		System.out.println(SQL);
		List<ProductDto> products = jdbcTemplateObject.query(SQL, new ProductMapper());
		return products;
	}

}
