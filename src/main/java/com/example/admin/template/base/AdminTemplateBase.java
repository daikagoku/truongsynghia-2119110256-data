package com.example.admin.template.base;

import com.example.core.InitData;
import com.example.core.InitView;
import com.example.user.template.base.TemplateBase;

public class AdminTemplateBase extends TemplateBase{
	protected InitData initData = new InitData();
	protected InitView initView = new InitView();
	public void init() {
		jdbcTemplateObject.execute(initData.getCategory());
		jdbcTemplateObject.execute(initData.getImage());
		
		
		jdbcTemplateObject.execute(initData.getProduct());
		jdbcTemplateObject.execute(initData.getProductVersion());
		jdbcTemplateObject.execute(initData.getProductRating());
		jdbcTemplateObject.execute(initData.getProductCategory());
		jdbcTemplateObject.execute(initData.getTopic());
		jdbcTemplateObject.execute(initData.getPost());
		jdbcTemplateObject.execute(initData.getPostTopic());
		jdbcTemplateObject.execute(initData.getAbout());
		
		jdbcTemplateObject.execute(initData.getUserType());
		
		
		jdbcTemplateObject.execute(initView.getViewProduct());
		jdbcTemplateObject.execute(initView.getViewProductRating());
		jdbcTemplateObject.execute(initView.getViewProductCategory());
		jdbcTemplateObject.execute(initView.getViewImage());
		jdbcTemplateObject.execute(initView.getViewCategory());
		
		jdbcTemplateObject.execute(initView.getViewUser());
		
		jdbcTemplateObject.execute(initView.getViewAbout());
		jdbcTemplateObject.execute(initView.getViewPost());
		jdbcTemplateObject.execute(initView.getViewTopic());
		jdbcTemplateObject.execute(initView.getViewPostTopic());
		
		
	}
}
