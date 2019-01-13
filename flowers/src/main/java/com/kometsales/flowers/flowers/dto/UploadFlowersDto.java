package com.kometsales.flowers.flowers.dto;


import java.io.InputStream;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class UploadFlowersDto {
	@NotNull(message = "You must upload a file that is not empty")
	MultipartFile multipartFile;
	@NotNull(message = "Email can't be null")
	@Email(message = "Email should be valid")
	String email;
	InputStream inputStream;
	
	
	
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
