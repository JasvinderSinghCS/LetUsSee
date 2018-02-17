package com.adqt.springservice.controller;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.adqt.springservice.service.DataQualityStatService;

@org.springframework.web.bind.annotation.RestController
public class DataQualityStatController {
	
	@Autowired 
	private DataQualityStatService dataQualityStatService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method = RequestMethod.GET, value = "/app/ruleValue/{tableName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void startDataQuality(@PathParam("tableName") String tableName) {
		dataQualityStatService.lauchDataQuality(tableName);
	}
	
}
