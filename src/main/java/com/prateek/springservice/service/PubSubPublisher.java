package com.prateek.springservice.service;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.rpc.ApiException;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class PubSubPublisher {
    public static void publishMessages() throws Exception {

        TopicName topicName = TopicName.of("gcp-data-engineer-188205", "stock_tata_steel");
        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).build();
            BufferedReader br = new BufferedReader(new FileReader(new File("/home/amitashukla0906/LetUsSee/let_us_see/src/main/java/templates/TataSteelDataSet.csv")));
            for (int i = 0; i < 4500; i++) {
                final String message = br.readLine();
                ByteString data = ByteString.copyFromUtf8(message);
                PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
                ApiFuture<String> future = publisher.publish(pubsubMessage);
                // add an asynchronous callback to handle success / failure
                ApiFutures.addCallback(future, new ApiFutureCallback<String>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        if (throwable instanceof ApiException) {
                            ApiException apiException = ((ApiException) throwable);
                            // details on the API exception
                            System.out.println(apiException.getStatusCode().getCode());
                            System.out.println(apiException.isRetryable());
                        }
                        System.out.println("Error publishing message : " + message);
                    }

                    @Override
                    public void onSuccess(String messageId) {
                        // Once published, returns server-assigned message ids (unique within the topic)
                        System.out.println(message + " :"+ messageId);
                    }
                });
            }
        } finally {
            if (publisher != null) {
                publisher.shutdown();
            }
        }
    }

    public static void main(String... args) throws Exception {
        publishMessages();
    }

}
