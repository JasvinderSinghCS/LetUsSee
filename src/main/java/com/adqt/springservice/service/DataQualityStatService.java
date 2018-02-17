package com.adqt.springservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.adqt.springservice.dto.DataQualityStatDTO;

@Service
public class DataQualityStatService {
	
	@Autowired
    private SimpMessagingTemplate simpleMessagingTemplate;

	public void lauchDataQuality(String tableName) {
		while(true) {
		try {
			Thread.sleep(120000);
			DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
			dataQualityDTO.setTableName("Inkalab Zindabad");
			dataQualityDTO.setQualifiedRowCount(47);
			dataQualityDTO.setTotalRowCount(100);
			
			simpleMessagingTemplate.convertAndSend("/app/dataquality", dataQualityDTO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}
	}
	
}
