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
import com.example.user.dto.ImageDto;
import com.example.user.template.ImageTemplate;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/image")
public class ImageApi extends ApiBase {
	public static Logger logger = LoggerFactory.getLogger(ImageApi.class);
	ImageTemplate imageJDBC = (ImageTemplate) context.getBean("imageTemplate");

	@GetMapping()
	public ResponseEntity<List<ImageDto>> getImage(
			@RequestParam(value ="item_id", required = false) long[] item_id,
			@RequestParam(value ="position", required = false) String[] position
	) {
		Boolean statusGet = false;
		List<ImageDto> images = null; 
		imageJDBC.initGetImage();
		if(item_id != null) {
			statusGet = true;
			imageJDBC.addGetByItemId(item_id);
		}
		if(position != null) {
			statusGet = true;
			imageJDBC.addGetByPosition(position);
		}
		if(statusGet) {
			images = imageJDBC.execute();
		}
		if(images == null || images.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ImageDto>>(images,HttpStatus.OK);
	}

	@GetMapping("/product")
	public ResponseEntity<List<ImageDto>> getImageOfProduct(
			@RequestParam(value ="item_id", required = false) long[] item_id,
			@RequestParam(value ="position", required = false) String[] position,
			@RequestParam(value ="alias",defaultValue="", required = false) String alias,
			@RequestParam(value ="version_alias",defaultValue="", required = false) String version_alias
	) {
		List<ImageDto> images = null; 
		imageJDBC.initGetImage();
		if(item_id != null) {
			imageJDBC.addGetByItemId(item_id);
		}
		if(position != null) {
			imageJDBC.addGetByPosition(position);
		}
		
		imageJDBC.initGetOfProduct();
		if(!alias.equals("")) {
			imageJDBC.addGetByProductAlias(alias);
		}
		if(!version_alias.equals("")) {
			imageJDBC.addGetByProductVersionAlias(version_alias);
		}
		images = imageJDBC.execute();
		if(images == null || images.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ImageDto>>(images,HttpStatus.OK);
	}
}
