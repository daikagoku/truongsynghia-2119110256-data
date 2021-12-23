package com.example.user.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.dto.ProductDto;
import com.example.user.template.ProductTemplate;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductApi {
	public static Logger logger = LoggerFactory.getLogger(ProductApi.class);
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	ProductTemplate productJDBC = (ProductTemplate)context.getBean("productTemplate");
	@GetMapping
	public ResponseEntity<List<ProductDto>> get(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="versionId",defaultValue="-1", required = false) long versionId,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias,
			@RequestParam(value ="versionAlias",defaultValue="", required = false) String versionAlias
	) {
		List<ProductDto> products; 
		if(id != -1 && versionId!=-1) {
			products = productJDBC.get(id,versionId);
			System.out.println("Request by id = "+id+" version id ="+ versionId);
		}else if(!alias.equals("") && !versionAlias.equals("")){
			products = productJDBC.get(alias,versionAlias);
			System.out.println("Request by alias = '"+alias+"' + version = '"+versionAlias+"'");
		}else {
			products = productJDBC.getAll();
			System.out.println("Request all");
		}
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
}
