package com.adqt.springservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

	public void lauchDataQuality(final String tableName) {
		threadPoolProvider.execute(new Runnable() {
			@Override
			public void run() {
				launchDataAccuracy(tableName);
			}
		});
		threadPoolProvider.execute(new Runnable() {
			@Override
			public void run() {
				launchDataCompletenss(tableName);
			}
		});
		threadPoolProvider.execute(new Runnable() {
			@Override
			public void run() {
				launchDataConformity(tableName);
			}
		});
		threadPoolProvider.execute(new Runnable() {
			@Override
			public void run() {
				launchDataConsistency(tableName);
			}
		});
	}

	public void launchDataAccuracy(String tableName) {
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
			dataQualityDTO.setTableName("accuracy");
			dataQualityDTO.setQualifiedRowCount(47);
			dataQualityDTO.setTotalRowCount(100);
			broker.convertAndSend("/dataquality/accuracy", dataQualityDTO);
		}
	}

	public void launchDataCompletenss(String tableName) {
		while (true) {
			try {
				Thread.sleep(12000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
			dataQualityDTO.setTableName("completness");
			dataQualityDTO.setQualifiedRowCount(47);
			dataQualityDTO.setTotalRowCount(100);
			broker.convertAndSend("/dataquality/completness", dataQualityDTO);
		}
	}

	public void launchDataConformity(String tableName) {
		while (true) {
			try {
				Thread.sleep(22000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
			dataQualityDTO.setTableName("conformity");
			dataQualityDTO.setQualifiedRowCount(47);
			dataQualityDTO.setTotalRowCount(100);
			broker.convertAndSend("/dataquality/conformity", dataQualityDTO);
		}
	}

	public void launchDataConsistency(String tableName) {
		while (true) {
			try {
				Thread.sleep(9000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DataQualityStatDTO dataQualityDTO = new DataQualityStatDTO();
			dataQualityDTO.setTableName("consistency");
			dataQualityDTO.setQualifiedRowCount(47);
			dataQualityDTO.setTotalRowCount(100);
			broker.convertAndSend("/dataquality/consistency", dataQualityDTO);
		}
	}

}