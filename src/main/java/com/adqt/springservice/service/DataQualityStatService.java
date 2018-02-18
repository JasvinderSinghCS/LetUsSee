package com.adqt.springservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import com.adqt.springservice.dto.DataQualityStatDTO;


@Service
public class DataQualityStatService {

	@Autowired
	private ThreadPoolProviderService threadPoolProvider;
	
	public void lauchDataQuality(String tableName) {
		launchDataAccuracy(tableName);
		//launchDataCompletenss(tableName);
		//launchDataConformity(tableName);
		//launchDataConsistency(tableName);
	}
	
	@MessageMapping("/accuracy")
	@SendTo("/dataquality/accuracy")
	public DataQualityStatDTO launchDataAccuracy(String tableName) {
		while(true) {
		 try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
		dataQualityDTO.setTableName("accuracy");
		dataQualityDTO.setQualifiedRowCount(47);
		dataQualityDTO.setTotalRowCount(100);
		return dataQualityDTO;		
		}
	}
	
	/*@MessageMapping("/completness")
	@SendTo("/dataquality/completness")
	public void launchDataCompletenss(String tableName) {
		while(true) {
		DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
		dataQualityDTO.setTableName("completness");
		dataQualityDTO.setQualifiedRowCount(47);
		dataQualityDTO.setTotalRowCount(100);
		return dataQualityDTO;		
		}
	}
	
	@MessageMapping("/conformity")
	@SendTo("/dataquality/conformity")
	public void launchDataConformity(String tableName) {
		while(true) {
		DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
		dataQualityDTO.setTableName("conformity");
		dataQualityDTO.setQualifiedRowCount(47);
		dataQualityDTO.setTotalRowCount(100);
		return dataQualityDTO;
		}
	}
	
	@MessageMapping("/consistency")
	@SendTo("/dataquality/consistency")
	public void launchDataConsistency(String tableName) {
		while(true) {
		DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
		dataQualityDTO.setTableName("consistency");
		dataQualityDTO.setQualifiedRowCount(47);
		dataQualityDTO.setTotalRowCount(100);
		return dataQualityDTO;
		}
	}*/
	
}