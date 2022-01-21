package com.example.user.template;

import java.util.List;
import java.util.Map;

import com.example.core.InitInsertSql;
import com.example.core.InitSelectSql;
import com.example.core.InitUpdateSql;
import com.example.core.entity.UserAccountEntity;
import com.example.user.dto.TopicDto;
import com.example.user.dto.UserDto;
import com.example.user.mapper.TopicMapper;
import com.example.user.mapper.UserMapper;
import com.example.user.template.base.TemplateBase;

public class UserTemplate extends TemplateBase{
	public UserTemplate() {
		
	}
	public InitSelectSql userSql = null;
	public Boolean update(UserDto userAccount) {
		
		Boolean access = false;
		try {
			InitUpdateSql updateSql = new InitUpdateSql("user_account");
			updateSql.addWhere("id",userAccount.getId());
			updateSql.addSet("fullname",userAccount.getFullname());
			updateSql.addSet("email",userAccount.getEmail());
			updateSql.addSet("phone",userAccount.getPhone());
			
			jdbcTemplateObject.execute(updateSql.getSql());
			access = true;
			System.out.println(updateSql.getSql());
		}catch(Exception e) {
			System.out.println("Something went wrong."+e);
		}	
		return  access;
	}
	public Boolean register(String username,String password) {
				
		Boolean access = false;
		try {
			InitInsertSql accountSql = new InitInsertSql("user_account");
			accountSql.addValue("username",username);
			accountSql.addValue("password",password);
			accountSql.addValue("type_id",0);
			jdbcTemplateObject.execute(accountSql.getSql());
			access = true;
			System.out.println("register:"+username+" "+password);
		}catch(Exception e) {
			System.out.println("Something went wrong."+e);
		}	
		return  access;
	}
	public long login(String username,String password) {
		
		long id = -1;
		try {
			InitSelectSql userSql = new InitSelectSql("*","user_account u");
			userSql.addWhere("username",username);
			userSql.addWhere("password",password);
			List<UserDto> users = jdbcTemplateObject.query(userSql.getSql(), new UserMapper());
			
			if(!users.isEmpty()) {
				System.out.println("login:"+username+" "+password);
				id = users.get(0).getId();
			}
		}catch(Exception e) {
			System.out.println("Something went wrong."+e);
		}	
		return  id;
	}
	public void mergeSql() {
		this.sql = userSql.getSql();	
		System.out.println(sql);
		userSql = null;
	}
	public void initGetUser() {
		userSql = new InitSelectSql("*","user_account u");
	}
	public void initGetCount() {
		userSql = new InitSelectSql("count(*) count","user_account u");
		InitSelectSql imageSql = new InitSelectSql("url image","v_image i");
		imageSql.addWhere("u.image_id = i.id");
		
		userSql.addSubSelect(imageSql.getSql());
	}
	public void addGetByUsername(String username) {
		userSql.addWhere("u.username",username);
	}
	public void addGetById(long id) {
		userSql.addWhere("u.id",id);
	}
	public void addGetByPassword(String password) {
		userSql.addWhere("u.password",password);
	}
	public List<UserDto> execute() {
		mergeSql();
		List<UserDto> users = jdbcTemplateObject.query(this.sql, new UserMapper());
		return users;
	};
}
