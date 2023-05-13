package com.skd.consumer;

import com.skd.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderConsumer {

    @KafkaListener(topics = "Topic1", groupId = "myGroup")
    public void consume(OrderDTO orderDTO) {
        log.info("Message received");
    }
}
