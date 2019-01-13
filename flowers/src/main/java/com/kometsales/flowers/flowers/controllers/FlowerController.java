package com.kometsales.flowers.flowers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kometsales.flowers.exception.ServiceException;
import com.kometsales.flowers.flowers.dto.UploadFlowersDto;
import com.kometsales.flowers.flowers.services.FlowerService;

@RestController
@RequestMapping("/flower")
public class FlowerController {
	@Autowired
	private FlowerService flowerService;
	
	
	@CrossOrigin(origins= "*", methods=RequestMethod.POST, allowedHeaders="*")
	@RequestMapping(value = "upload", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> processFile(@RequestParam("multiPartFile") MultipartFile multiPartFile, @RequestParam("email") String email) throws ServiceException {
		// converts the request in uploadFlowersDto and validate the request data
		UploadFlowersDto uploadFlowersDto = flowerService.mapperToDto(multiPartFile, email);
		flowerService.validateRequest(uploadFlowersDto);
		flowerService.uploadFlowers(uploadFlowersDto);
		
		return ResponseEntity.status(HttpStatus.OK).body("The file has been received, in a few minutes it will be processed.");
	}
	
}
