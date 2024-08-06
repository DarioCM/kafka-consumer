package org.example.kafkaconsumer.consumer;


import org.example.dto.Customer;
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

    @KafkaListener(topics = "java-testTopic-Cust" , groupId = "group_id")
    public void consumeCustomer(Customer customer) {
        logger.info("Consumed customer:  {} " , customer.toString());
    }


}
