package com.skd.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class OrderConsumer {

    @KafkaListener(topics = "Topic1", groupId = "myGroup")
    public void consume() {
        log.info("Message received");
    }
}
