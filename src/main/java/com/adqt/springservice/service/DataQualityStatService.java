package com.adqt.springservice.service;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.adqt.springservice.dto.DataQualityStatDTO;

@Service
public class DataQualityStatService {

	@SendTo("/app/dataquality")
	public DataQualityStatDTO lauchDataQuality(String tableName) {
		while(true) {
		try {
			Thread.sleep(120000);
			DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
			dataQualityDTO.setTableName("Inkalab Zindabad");
			dataQualityDTO.setQualifiedRowCount(47);
			dataQualityDTO.setTotalRowCount(100);
			
			return dataQualityDTO;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}
	}
	
}
