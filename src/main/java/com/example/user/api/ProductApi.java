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
import com.example.user.dto.ProductDto;
import com.example.user.dto.VersionDto;
import com.example.user.template.ProductTemplate;
import com.example.user.template.VersionTemplate;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/product")
public class ProductApi extends ApiBase{
	public static Logger logger = LoggerFactory.getLogger(ProductApi.class);
	ProductTemplate productJDBC = (ProductTemplate)context.getBean("productTemplate");
	VersionTemplate versionJDBC = (VersionTemplate)context.getBean("versionTemplate");
	@GetMapping
	public ResponseEntity<List<ProductDto>> gets(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="version_id",defaultValue="-1", required = false) long version_id,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias,
			@RequestParam(value ="version_alias",defaultValue="", required = false) String version_alias,			
			@RequestParam(value ="price", required = false) double[] price,
			@RequestParam(value ="sale_price", required = false) double[] sale_price,
			

			
			@RequestParam(value ="offset",defaultValue="-1", required = false) long offset,
			@RequestParam(value ="limit",defaultValue="-1", required = false) long limit
	) {

		Boolean statusGet = true;
		productJDBC.initGetProduct();
		if(!alias.equals("")){
			productJDBC.addGetByAlias(alias);
		}
		if(!version_alias.equals("")){
			productJDBC.addGetByVersionAlias(version_alias);
		}
		
		if(id != -1){
			productJDBC.addGetById(id);
		}
		if(version_id != -1){
			productJDBC.addGetByVersionId(version_id);
		}
		if(price != null) {
			if(price.length > 1) {
				productJDBC.addGetByPrice(price[0], price[1]);
			}
		}
		if(sale_price != null) {
			if(sale_price.length > 1) {
				productJDBC.addGetBySalePrice(sale_price[0], sale_price[1]);
			}
		}
		if(offset > -1) {
			productJDBC.setOffset(offset);
		}
		if(limit > -1) {
			productJDBC.setLimit(limit);
		}
		List<ProductDto> products = null;
		if(statusGet) {
			products = productJDBC.execute(); 		
		}
		if(products == null || products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
	@GetMapping("/count")
	public Long getCount(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="version_id",defaultValue="-1", required = false) long version_id,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias,
			@RequestParam(value ="version_alias",defaultValue="", required = false) String version_alias,			
			@RequestParam(value ="price", required = false) double[] price,
			@RequestParam(value ="sale_price", required = false) double[] sale_price
	) {
		Boolean statusGet = true;
		productJDBC.initGetCount();
		if(!alias.equals("")){
			productJDBC.addGetByAlias(alias);
		}
		if(!version_alias.equals("")){
			productJDBC.addGetByVersionAlias(version_alias);
		}
		if(id != -1){
			productJDBC.addGetById(id);
		}
		if(version_id != -1){
			productJDBC.addGetByVersionId(version_id);
		}
		if(price != null) {
			if(price.length > 1) {
				productJDBC.addGetByPrice(price[0], price[1]);
			}
		}
		if(sale_price != null) {
			if(sale_price.length > 1) {
				productJDBC.addGetBySalePrice(sale_price[0], sale_price[1]);
			}
		}
		
		if(statusGet) {
			return productJDBC.executeCount();		
		}else {
			return (long)0;
		}
	}
	@GetMapping("/category")
	public ResponseEntity<List<ProductDto>> getByCategory(
			@RequestParam(value ="alias",required = false) String[] alias,
			@RequestParam(value ="parent_alias",defaultValue="", required = false) String parent_alias,			
			@RequestParam(value ="price", required = false) double[] price,
			@RequestParam(value ="query",defaultValue="", required = false) String query,
			@RequestParam(value ="sale_price", required = false) double[] sale_price,
			@RequestParam(value ="offset",defaultValue="-1", required = false) long offset,
			@RequestParam(value ="limit",defaultValue="-1", required = false) long limit
	) {
		Boolean statusGet = false;
		List<ProductDto> products = null;
		productJDBC.initGetProduct();	
		productJDBC.initProductCategoryGet();
		if(alias != null){
			statusGet = true;
			productJDBC.addGetByCategoryAlias(alias);
		}
		productJDBC.initCategoryGet();
		if(!parent_alias.equals("")){
			statusGet = true;
			productJDBC.addGetByCategoryParentAlias(parent_alias);
		}
		if(price != null) {
			if(price.length > 1) {
				statusGet = true;
				productJDBC.addGetByPrice(price[0], price[1]);
			}
		}
		if(sale_price != null) {
			if(sale_price.length > 1) {
				statusGet = true;
				productJDBC.addGetBySalePrice(sale_price[0], sale_price[1]);
			}
		}
		if(offset > -1) {
			productJDBC.setOffset(offset);
		}
		if(limit > -1) {
			productJDBC.setLimit(limit);
		}
		if(!query.equals("")){
			productJDBC.addGetByQuery(query);
		}
		if(statusGet) {
			products = productJDBC.execute(); 	
		}
		if(products == null || products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}
	@GetMapping("/category/count")
	public Long getCountByCategory(
			@RequestParam(value ="alias",required = false) String[] alias,
			@RequestParam(value ="parent_alias",defaultValue="", required = false) String parent_alias,			
			@RequestParam(value ="query",defaultValue="", required = false) String query,
			@RequestParam(value ="price", required = false) double[] price,
			@RequestParam(value ="sale_price", required = false) double[] sale_price
	) {

		Boolean statusGet = false;
		productJDBC.initGetCount();	
		productJDBC.initProductCategoryGet();
		if(alias != null){
			statusGet = true; 
			productJDBC.addGetByCategoryAlias(alias);
		}
		productJDBC.initCategoryGet();
		if(!parent_alias.equals("")){
			statusGet = true; 
			productJDBC.addGetByCategoryParentAlias(parent_alias);
		}
		if(price != null) {
			if(price.length > 1) {
				statusGet = true; 
				productJDBC.addGetByPrice(price[0], price[1]);
			}
		}
		if(sale_price != null) {
			if(sale_price.length > 1) {
				statusGet = true; 
				productJDBC.addGetBySalePrice(sale_price[0], sale_price[1]);
			}
		}
		if(!query.equals("")){
			productJDBC.addGetByQuery(query);
		}
		if(statusGet) {
			return productJDBC.executeCount();		
		}else {
			return (long)0;
		}
	}
	
	@GetMapping("/version")
	public ResponseEntity<List<VersionDto>> getVersionsProduct(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias	
	) {
		Boolean statusGet = false;
		List<VersionDto> versions = null;
		versionJDBC.initGetVersion();
		if(!alias.equals("")){
			statusGet = true;
			versionJDBC.addGetByAlias(alias);
		}else if(id != -1){
			statusGet = true;
			versionJDBC.addGetById(id);
		}
		if(statusGet) {
			versions = versionJDBC.execute();
		}
		if(versions == null || versions.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<VersionDto>>(versions,HttpStatus.OK);
	}
	
}
