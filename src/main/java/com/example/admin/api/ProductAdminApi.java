package com.example.admin.api;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.template.ProductTemplate;
import com.example.core.entity.ProductEntity;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/product")
public class ProductAdminApi {
	public static Logger logger = LoggerFactory.getLogger(ProductAdminApi.class);
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	ProductTemplate productJDBC = (ProductTemplate)context.getBean("productAdminTemplate");
	
	@GetMapping
	public ResponseEntity<List<ProductEntity>> get(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias
	) {
		List<ProductEntity> products; 
		if(id != -1) {
			products = productJDBC.get(id);
			System.out.println("Request by id = "+id);
		}else if(!alias.equals("")){
			products = productJDBC.get(alias);
			System.out.println("Request by alias = '"+alias+"'");
		}else {
			products = productJDBC.getAll();
			System.out.println("Request all");
		}
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductEntity>>(products,HttpStatus.OK);
	}
	@PutMapping
    public void put(@RequestBody ProductEntity product) {
    	System.out.println("Post one");
		productJDBC.put(product);
    }
	@PostMapping
    public void post(@RequestBody ProductEntity product) {
    	System.out.println("Post one");
		productJDBC.post(product);
    }
	@PostMapping("/multiple")
	public void post(@RequestBody List<ProductEntity> products) {
		System.out.println("Post list");
		productJDBC.posts(products);
    }
}
