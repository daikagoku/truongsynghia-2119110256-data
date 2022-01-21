package com.example.user.api;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import org.json.JSONObject;
import org.json.JSONString;

import com.example.core.entity.UserAccountEntity;
import com.example.user.api.base.ApiBase;
import com.example.user.dto.UserDto;
import com.example.user.template.UserTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserApi extends ApiBase{
	public static Logger logger = LoggerFactory.getLogger(UserApi.class);
	UserTemplate userJDBC = (UserTemplate)context.getBean("userTemplate");
	ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping()
	public ResponseEntity<UserDto> get(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id
	) {
		if(id != -1) {
			userJDBC.initGetUser();
			userJDBC.addGetById(id);
			List<UserDto>  users = userJDBC.execute();
			if(users == null || users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<UserDto>(users.get(0),HttpStatus.OK);
			}
		}			
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@PutMapping()
	public ResponseEntity<Boolean> put(
			@RequestBody UserDto userAccount
	) {
		if(userAccount != null) {
			Boolean access = userJDBC.update(userAccount);
			if(access) {
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(false,HttpStatus.OK);
			}
		}			
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/login")
	public ResponseEntity<Long> login(
			@RequestBody UserAccountEntity userAccount
	) {
		if(userAccount != null && !userAccount.getUsername().equals("") && !userAccount.getPassword().equals("")) {
			Long id = userJDBC.login(userAccount.getUsername(), userAccount.getPassword());
			if(id != -1) {
				return new ResponseEntity<Long>(id,HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}			
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/register")
	public ResponseEntity<Long> register(
			@RequestBody UserAccountEntity userAccount
	) {
		if(userAccount != null && !userAccount.getUsername().equals("") && !userAccount.getPassword().equals("")) {
			userJDBC.initGetCount();
			userJDBC.addGetByUsername(userAccount.getUsername());
			long count = userJDBC.executeCount();
			if(count != 0) {
				return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
			}else {
				Boolean access = userJDBC.register(userAccount.getUsername(), userAccount.getPassword());
				if(access) {
					return login(userAccount);
				}else {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}				
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
