package org.example.kafkaconsumer.consumer;


import org.example.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;


@Service
public class KafkaMessageListener {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @RetryableTopic(attempts = "4" )
   @KafkaListener(topics = "java-testTopic-1" , groupId = "group_id")
    public void consume(String message) {
        logger.info("Consumed message:  {} " , message);
    }

    @RetryableTopic(attempts = "4" )
    @KafkaListener(topics = "java-testTopic-Cust" , groupId = "group_id")
    public void consumeCustomer(Customer customer) {
        logger.info("Consumed customer:  {} " , customer.toString());
    }

    // DLT for Customer topic. if message is not consumed after 4 attempts, it will be sent to DLT
    @DltHandler
    public void dlt(Customer customer) {
        logger.info("Customer sent to DLT:  {} " , customer.toString());
    }


}
