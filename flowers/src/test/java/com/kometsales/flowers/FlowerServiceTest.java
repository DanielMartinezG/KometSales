package com.kometsales.flowers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kometsales.flowers.exception.ServiceException;
import com.kometsales.flowers.flowers.dto.UploadFlowersDto;
import com.kometsales.flowers.flowers.services.FlowerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlowerServiceTest {
	@Autowired
	private FlowerService flowerService;
	
	@Test
	public void validateRequestMultipartNullTest() {
		try {
			UploadFlowersDto uploadFlowers = new UploadFlowersDto();
			uploadFlowers.setEmail("danielmartinezg95@gmail.com");
			flowerService.validateRequest(uploadFlowers);
			assertTrue(false);
		}catch(ServiceException e) {
			assertTrue(true);
		}
	}
	

}
