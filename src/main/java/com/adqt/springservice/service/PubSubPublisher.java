package com.adqt.springservice.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

@Service
public class PubSubPublisher {

	private final Logger logger = LoggerFactory.getLogger(PubSubPublisher.class);

	@PostConstruct
	public void init() {
		try {
			logger.info("************************Starting Process for PubSub****************************");
			publishMessages();
		} catch (Exception e) {
			logger.info("Error occured" + e);
			e.printStackTrace();
		}

	}

	public void publishMessages() throws Exception {
		logger.info("Inside pubSub");
		TopicName topicName = TopicName.of("gcp-data-engineer-188205", "ecom");
		Publisher publisher = null;
		try {
			// Create a publisher instance with default settings bound to the topic
			publisher = Publisher.newBuilder(topicName).build();
			BufferedReader br = new BufferedReader(new FileReader(new File(
					"C:\\Users\\parag.saxena\\Desktop\\BitBucket-DMUtilities\\Hackathon\\LetUsSee\\src\\main\\resources\\EcommerceData.csv")));

			String message;
			for (; (message = br.readLine()) != null;) {
				// final String message = br.readLine();
				ByteString data = ByteString.copyFromUtf8(message);
				PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
				ApiFuture<String> future = publisher.publish(pubsubMessage);
				/*
				 * // add an asynchronous callback to handle success / failure
				 * ApiFutures.addCallback(future, new ApiFutureCallback<String>() {
				 * 
				 * @Override public void onFailure(Throwable throwable) { if (throwable
				 * instanceof ApiException) { ApiException apiException = ((ApiException)
				 * throwable); // details on the API exception
				 * System.out.println(apiException.getMessage()); }
				 * System.out.println("Error publishing message : " + message); }
				 * 
				 * @Override public void onSuccess(String messageId) { // Once published,
				 * returns server-assigned message ids (unique within the topic)
				 * System.out.println(message + " :" + messageId); } });
				 */
			}
		} finally {
			if (publisher != null) {
				publisher.shutdown();
			}
		}
	}

	/*public static void main(String... args) throws Exception {
		publishMessages();
	}*/

}
