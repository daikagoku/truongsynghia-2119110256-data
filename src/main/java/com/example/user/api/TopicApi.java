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
import com.example.user.dto.TopicDto;
import com.example.user.template.TopicTemplate;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/topic")
public class TopicApi extends ApiBase{
	public static Logger logger = LoggerFactory.getLogger(TopicApi.class);
	TopicTemplate topicJDBC = (TopicTemplate)context.getBean("topicTemplate");
	@GetMapping
	public ResponseEntity<List<TopicDto>> get(
			@RequestParam(value ="id",defaultValue="-1", required = false) long id,
			@RequestParam(value ="parent_id",defaultValue="-1", required = false) Long parent_id,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias,
			@RequestParam(value ="parent_alias",defaultValue="", required = false) String parent_alias
	) {
		System.out.println("Request topic");
		List<TopicDto> topics = null;
		topicJDBC.initGetTopic();
		if(id != -1) {
			topicJDBC.addGetById(id);
		}
		if(!alias.equals("")){
			topicJDBC.addGetByAlias(alias);
		}
		topicJDBC.initGetParentTopic();
		if(parent_id != -1){
			topicJDBC.addGetByParentId(parent_id);
		}
		if(!parent_alias.equals("")){
			topicJDBC.addGetByParentAlias(parent_alias);
		}
		topics = topicJDBC.execute();
		if(topics == null || topics.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TopicDto>>(topics,HttpStatus.OK);
	}
}
