package com.adqt.springservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.adqt.springservice.dto.DataQualityStatDTO;


@Service
public class DataQualityStatService {

	@Autowired
	private ThreadPoolProviderService threadPoolProvider;

	@Autowired
	private SimpMessagingTemplate broker;

	@Autowired
	public DataQualityStatService(final SimpMessagingTemplate broker) {
		this.broker = broker;
	}
	
	public void lauchDataQuality(String tableName) {
		launchDataAccuracy(tableName);
		//launchDataCompletenss(tableName);
		//launchDataConformity(tableName);
		//launchDataConsistency(tableName);
	}
	
	@Scheduled(fixedRate = 8000)
	public void launchDataAccuracy(String tableName) {
		DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
		dataQualityDTO.setTableName("accuracy");
		dataQualityDTO.setQualifiedRowCount(47);
		dataQualityDTO.setTotalRowCount(100);
		broker.convertAndSend("/dataquality/accuracy", dataQualityDTO); ;		
	}
	
	@MessageMapping("/completness")
	@SendTo("/dataquality/completness")
	public DataQualityStatDTO launchDataCompletenss(String tableName) {
		DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
		dataQualityDTO.setTableName("completness");
		dataQualityDTO.setQualifiedRowCount(47);
		dataQualityDTO.setTotalRowCount(100);
		return dataQualityDTO;		
	}
	
	@MessageMapping("/conformity")
	@SendTo("/dataquality/conformity")
	public DataQualityStatDTO launchDataConformity(String tableName) {
		DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
		dataQualityDTO.setTableName("conformity");
		dataQualityDTO.setQualifiedRowCount(47);
		dataQualityDTO.setTotalRowCount(100);
		return dataQualityDTO;		
	}
	
	@MessageMapping("/consistency")
	@SendTo("/dataquality/consistency")
	public DataQualityStatDTO launchDataConsistency(String tableName) {
		DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
		dataQualityDTO.setTableName("consistency");
		dataQualityDTO.setQualifiedRowCount(47);
		dataQualityDTO.setTotalRowCount(100);
		return dataQualityDTO;		
	}
	
}