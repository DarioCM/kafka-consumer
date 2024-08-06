package org.example.kafkaconsumer;

import org.example.dto.Customer;
import org.example.kafkaconsumer.consumer.KafkaMessageListener;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static kafka.server.IntegrationTestUtils.send;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class KafkaConsumerApplicationTests {

    Logger logger = LoggerFactory.getLogger(KafkaConsumerApplicationTests.class);

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @DynamicPropertySource
    public static void initKafkaConsumer(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }


    @Test
    public void testConsumeEventsFromKafkaTopic() {
        logger.info("Sending Customer to Kafka Topic");
        kafkaTemplate.send("java-testTopic-Cust",  new Customer(1, "John", "sdsad@dfdf.com", "1212112"));
        logger.info("Customer sent to Kafka Topic");
    }

}
