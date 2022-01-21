package com.example.core;

public class InitView {
	public InitView() {
		
	}
	public String getViewProduct() {
		String SQL = " drop view if exists v_product ;"
				+ " create view v_product as "
				+ " select p.id id,p.title title,p.alias alias "
					+ " ,content,description,price,sale_price,sale_date,image_id "
					+ " ,v.id version_id,v.title version_title,v.alias version_alias "
				+ " from product p,product_version v "
				+ " where p.id=v.product_id "
					+" and p.status = true and p.trash = false"
					+" and v.status = true and v.trash = false";
		return SQL;
	}
	public String getViewProductRating() {
		String SQL = " drop view if exists v_product_rating ;"
				+ " create view v_product_rating as "
				+ " select id,product_id,user_id,vote,content,create_date,update_date"
				 +" from product_rating r  "
				 +" where  r.status = true and r.trash = false ";
		return SQL;
	}
	public String getViewProductCategory() {
		String SQL = " drop view if exists v_product_category ;"
				+ " create view v_product_category as "
				+ " select pc.product_id,pc.category_id,c.title,c.alias,c.parent_id "
				+ " from product_category pc,category c"
				+ " where pc.category_id = c.id "
					+ " and pc.status = true and pc.trash = false "
					+ " and c.status = true and c.trash = false ";
		return SQL;
	}
	public String getViewImage() {
		String SQL = " drop view if exists v_image ;"
				+ " create view v_image as "
				+ " select i.id id,i.title title,i.alias alias,i.url"
				+ " from image i"
				+ " where i.status = true and i.trash = false";
		return SQL;
	}
	public String getViewCategory() {
		String SQL = " drop view if exists v_category ;"
				+ " create view v_category as "
				+ " select c.id,c.title,c.alias,c.parent_id"
				+" from category c"
				+" where c.status = true and c.trash = false ";
		
		return SQL;
	}
	public String getViewPost() {
		String SQL = " drop view if exists v_post ;"
				+ " create view v_post as "
				+ " select p.id,p.title,p.alias,p.image_id,p.auth_id,p.content,p.summary,p.create_date,p.update_date"
				+" from post p "
				+" where p.status = true and p.trash = false";	
		return SQL;
	}
	public String getViewTopic() {
		String SQL = " drop view if exists v_topic ;"
				+ " create view v_topic as "
				+ " select t.id,t.title,t.alias,t.parent_id"
				+" from topic t"
				+" where t.status = true and t.trash = false";	
		return SQL;
	}
	public String getViewPostTopic() {
		String SQL = " drop view if exists v_post_topic ;"
				+ " create view v_post_topic as "
				+ " select pt.topic_id,pt.post_id,t.title,t.alias,t.parent_id"
				+" from topic t,post_topic pt "
				+" where t.id = pt.topic_id "
				+" and t.status = true and t.trash = false"
				+" and pt.status = true and pt.trash = false";	
		return SQL;
	}
	public String getViewAbout() {
		String SQL = " drop view if exists v_about ;"
				+ " create view v_about as "
				+ " select a.id,a.title,a.alias,a.value"
				+" from about a"
				+" where a.status = true and a.trash = false";	
		return SQL;
	}
	
	public String getViewUser() {
		String SQL = " drop view if exists v_user ;"
				+ " create view v_user as "
				+ " select a.id, a.username,a.password,a.type_id "
				+ " ,a.image_id,a.fullname,a.email,a.phone,a.birth_date,a.level "
				+ " from user_account a"
				+ " where a.status = true and a.trash = false ";	
		return SQL;
	}
	
}
