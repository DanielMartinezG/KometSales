package com.kometsales.flowers.flowers.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kometsales.flowers.exception.ServiceException;
import com.kometsales.flowers.flowers.dto.FlowerReadDto;
import com.kometsales.flowers.flowers.dto.UploadFlowersDto;
import com.kometsales.flowers.flowers.entity.Flower;
import com.kometsales.flowers.flowers.repository.FlowerRepository;
import com.kometsales.flowers.utils.FileUtils;
import com.kometsales.flowers.utils.email.EmailSender;
import com.kometsales.flowers.utils.email.Mail;

@Service
public class FlowerService {
	Logger logger = LoggerFactory.getLogger(FlowerService.class);
	
	@Autowired
	private FlowerRepository flowerRepository;
	@Autowired
	private EmailSender emailSender;
	
	@Async
	public void uploadFlowers(UploadFlowersDto uploadFlowersDto) throws ServiceException {
		sleep();
		List<FlowerReadDto> processedFlowers = processFile(uploadFlowersDto);
		//Send report email
		sendCustomerNotification(uploadFlowersDto.getEmail(), processedFlowers);
		
	}
	
	
	public UploadFlowersDto mapperToDto(MultipartFile multiPartFile,String email) throws ServiceException{
		UploadFlowersDto uploadFlowersDto = new UploadFlowersDto();
		try {
			uploadFlowersDto.setEmail(email);
			uploadFlowersDto.setMultipartFile(multiPartFile);
			uploadFlowersDto.setInputStream(multiPartFile.getInputStream());
		} catch (IOException e) {
			logger.error(ServiceException.INPUT_STREAM_ERROR, e);
			throw new ServiceException(ServiceException.INPUT_STREAM_ERROR,e);
		}
		
		return uploadFlowersDto;
	}
	
	public void validateRequest(UploadFlowersDto uploadFlowersDto) throws ServiceException {
		StringBuilder errors = new StringBuilder();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UploadFlowersDto>> violations = validator.validate(uploadFlowersDto);
		
		if(!violations.isEmpty()) {
			for (ConstraintViolation<UploadFlowersDto> violation : violations) {
				errors.append("-" + violation.getMessage() + "\n");
			}
			throw new ServiceException(errors.toString());
		}
	}
	
	
	private void sendCustomerNotification(String email, List<FlowerReadDto> flowers) {
		int successFlowers = 0;
		int failedFlowers = 0;
		StringBuilder message = new StringBuilder();
		
		
		for (FlowerReadDto flowerReadDto : flowers) {
			if(flowerReadDto.getViolations().isEmpty()) {
				successFlowers ++;
			}else {
				failedFlowers ++;
			}
		}
		
		message.append("The number of flowers with errors are = " + failedFlowers + "\n");
		message.append("The number of successful flowers are= " + successFlowers + "\n");
		
		Mail mail = new Mail("danielmartinezg95@gmail.com", email, "Flowers Uploaded", message.toString());
		emailSender.sendEmail(mail);
	}
	
	private List<FlowerReadDto> processFile(UploadFlowersDto uploadFlowersDto) throws ServiceException {
		List<FlowerReadDto> flowersDto = new ArrayList<FlowerReadDto>();
		try {
			//Convert file in to Domain object
			flowersDto = FileUtils.convertCsvToObject(FlowerReadDto.class, uploadFlowersDto.getMultipartFile().getInputStream());
			//Validate DTO atributes whit bean validation
			List<Flower> listSuccess = validateAttributes(flowersDto);
			if(!listSuccess.isEmpty()) {
				flowerRepository.saveAll(listSuccess);
			}
		} catch (IOException e) {
			logger.error(ServiceException.READ_FILE_ERROR, e);
			throw new ServiceException(ServiceException.READ_FILE_ERROR,e);
		}
		return flowersDto;
	}

	private List<Flower> validateAttributes(List<FlowerReadDto> flowersDto) throws ServiceException {
		List<Flower> listSuccess = new ArrayList<Flower>();
		for (FlowerReadDto flowerReadDto : flowersDto) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			flowerReadDto.setViolations(validator.validate(flowerReadDto));
			if(flowerReadDto.getViolations().isEmpty()) {
				listSuccess.add(Flower.convertDtoToFlower(flowerReadDto));
			}
		}
		return listSuccess;
	}
	
	
	
	private void sleep() throws ServiceException{
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			logger.error(ServiceException.SLEEP_ERROR, e);
			throw new ServiceException(ServiceException.SLEEP_ERROR,e);
		}
	}
		
}
