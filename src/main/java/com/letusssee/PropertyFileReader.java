package com.letusssee;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.List;

@Configuration
@PropertySource("classpath:config.properties")
public class PropertyFileReader {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Value("${dataStoreHost:http://www.googleapis.com//v2//bigquery}")
	private volatile String dataStoreHost;
	
	@Value("${dataStoreJdbcPort:443}")
	private volatile String dataStoreJdbcPort;
	
	@Value("${ProjectId}")
	private volatile String ProjectId;
	
	@Value("${OAuthPvtKeyPath}")
	private volatile String OAuthPvtKeyPath;
	
	@Value("${OAuthServiceAcctEmail}")
	private volatile String OAuthServiceAcctEmail;
	

	public String getDataStoreHost() {
		return dataStoreHost;
	}

	public void setDataStoreHost(String dataStoreHost) {
		this.dataStoreHost = dataStoreHost;
	}

	public String getDataStoreJdbcPort() {
		return dataStoreJdbcPort;
	}

	public void setDataStoreJdbcPort(String dataStoreJdbcPort) {
		this.dataStoreJdbcPort = dataStoreJdbcPort;
	}

	public String getProjectId() {
		return ProjectId;
	}

	public void setProjectId(String projectId) {
		ProjectId = projectId;
	}

	public String getOAuthPvtKeyPath() {
		return OAuthPvtKeyPath;
	}

	public void setOAuthPvtKeyPath(String oAuthPvtKeyPath) {
		OAuthPvtKeyPath = oAuthPvtKeyPath;
	}

	public String getOAuthServiceAcctEmail() {
		return OAuthServiceAcctEmail;
	}

	public void setOAuthServiceAcctEmail(String oAuthServiceAcctEmail) {
		OAuthServiceAcctEmail = oAuthServiceAcctEmail;
	}
	

}
