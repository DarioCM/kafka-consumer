package org.example.kafkaconsumer.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;


@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "java-testTopic-1" , groupId = "group_id")
    public void consume(String message) {
        logger.info("Consumed message:  {} " , message);
    }

}
