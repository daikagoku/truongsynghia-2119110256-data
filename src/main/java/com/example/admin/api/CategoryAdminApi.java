package com.example.admin.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.api.base.AdminApiBase;
import com.example.admin.template.CategoryTemplate;
import com.example.core.entity.CategoryEntity;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/category")
public class CategoryAdminApi extends AdminApiBase{
	public static Logger logger = LoggerFactory.getLogger(CategoryAdminApi.class);
	CategoryTemplate categoryJDBC = (CategoryTemplate)context.getBean("categoryAdminTemplate");
	@GetMapping("/init")
	public void init() {
		categoryJDBC.init();
	};
	@GetMapping
	public ResponseEntity<List<CategoryEntity>> get(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias
	) {
		List<CategoryEntity> products; 
		if(id != -1) {
			products = categoryJDBC.get(id);
			System.out.println("Request category by id = "+id);
		}else if(!alias.equals("")){
			products = categoryJDBC.get(alias);
			System.out.println("Request category by alias = '"+alias+"'");
		}else {
			products = categoryJDBC.gets();
			System.out.println("Request category all");
		}
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CategoryEntity>>(products,HttpStatus.OK);
	}
	@PostMapping
    public void post(@RequestBody CategoryEntity category) {
    	System.out.println("Post  category one");
    	categoryJDBC.post(category);
    }
	@PostMapping("/multiple")
	public void post(@RequestBody List<CategoryEntity> categorys) {
		System.out.println("Post category list");
		categoryJDBC.posts(categorys);
    }
}
