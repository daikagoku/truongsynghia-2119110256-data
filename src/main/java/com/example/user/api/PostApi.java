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
import com.example.user.dto.PostDto;
import com.example.user.template.PostTemplate;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/post")
public class PostApi extends ApiBase{
	public static Logger logger = LoggerFactory.getLogger(PostApi.class);
	PostTemplate postJDBC = (PostTemplate)context.getBean("postTemplate");
	@GetMapping
	public ResponseEntity<List<PostDto>> get(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias,
			
			@RequestParam(value ="topic_id",defaultValue="-1", required = false) long topic_id,
			@RequestParam(value ="topic_alias",defaultValue="", required = false) String topic_alias,
			
			@RequestParam(value ="parent_id",defaultValue="-1", required = false) long parent_id,
			@RequestParam(value ="parent_alias",defaultValue="", required = false) String parent_alias,
			
			@RequestParam(value ="offset",defaultValue="0", required = false) long offset,
			@RequestParam(value ="limit",defaultValue="-1", required = false) long limit
	) {
		List<PostDto> posts = null; 
		postJDBC.initGetPost();
		if(id != -1) {
			postJDBC.addGetById(id);
		}
		if(!alias.equals("")) {
			postJDBC.addGetByAlias(alias);
		}
		
		
		if(topic_id != -1 || !topic_alias.equals("")) {
			postJDBC.initGetPostByTopic();
			if(topic_id != -1) {
				postJDBC.addGetByTopicId(topic_id);
			}
			if(!topic_alias.equals("")) {
				postJDBC.addGetByTopicAlias(topic_alias);
			}
			
		}
		if(parent_id != -1 || !parent_alias.equals("")) {
			postJDBC.initGetPostByParentTopic();
			if(parent_id != -1) {
				postJDBC.addGetByParentTopicId(parent_id);
			}
			if(!parent_alias.equals("")) {
				postJDBC.addGetByParentTopicAlias(parent_alias);
			}
		}
		
		
		posts = postJDBC.execute();
		if(posts == null || posts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
}
