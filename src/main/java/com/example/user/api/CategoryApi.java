package com.example.user.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.api.base.ApiBase;
import com.example.user.dto.CategoryDto;
import com.example.user.template.CategoryTemplate;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/category")
public class CategoryApi extends ApiBase{
	public static Logger logger = LoggerFactory.getLogger(CategoryApi.class);
	CategoryTemplate categoryJDBC = (CategoryTemplate)context.getBean("categoryTemplate");
	@GetMapping
	public ResponseEntity<List<CategoryDto>> get(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="parent_id",defaultValue="-1", required = false) long parent_id,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias,
			@RequestParam(value ="parent_alias",defaultValue="", required = false) String parent_alias
	) {
		Boolean statusGet = false;
		List<CategoryDto> categorys = null;
		categoryJDBC.initGetCategory();
		if(id != -1) {
			statusGet = true;
			categoryJDBC.addGetById(id);
		}
		if(!alias.equals("")){
			statusGet = true;
			categoryJDBC.addGetByAlias(alias);
		}
		categoryJDBC.initGetParentCategory();
		if(parent_id != -1){
			statusGet = true;
			categoryJDBC.addGetByParentId(parent_id);
		}
		if(!parent_alias.equals("")){
			statusGet = true;
			categoryJDBC.addGetByParentAlias(parent_alias);
		}
		if(statusGet) {
			categorys = categoryJDBC.execute();
		}
		if(categorys == null || categorys.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CategoryDto>>(categorys,HttpStatus.OK);
	}
}
