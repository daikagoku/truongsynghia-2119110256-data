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
import com.example.user.dto.AboutDto;
import com.example.user.template.AboutTemplate;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/about")
public class AboutApi  extends ApiBase{
	public static Logger logger = LoggerFactory.getLogger(AboutApi.class);
	AboutTemplate aboutJDBC = (AboutTemplate)context.getBean("aboutTemplate");
	@GetMapping
	public ResponseEntity<List<AboutDto>> get(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias,
			@RequestParam(value ="offset",defaultValue="0", required = false) long offset,
			@RequestParam(value ="limit",defaultValue="-1", required = false) long limit
	) {
		List<AboutDto> abouts = null; 
		aboutJDBC.initGetAbout();
		if(id != -1) {
			aboutJDBC.addGetById(id);
		}
		if(!alias.equals("")) {
			aboutJDBC.addGetByAlias(alias);
		}
		
		if(offset > -1) {
			aboutJDBC.setOffset(offset);
		}
		if(limit > -1) {
			aboutJDBC.setLimit(limit);
		}
		abouts = aboutJDBC.execute();
		if(abouts ==null || abouts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AboutDto>>(abouts,HttpStatus.OK);
	}
}
