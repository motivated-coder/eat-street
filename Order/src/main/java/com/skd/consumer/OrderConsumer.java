package com.skd.consumer;

import com.skd.dto.OrderDTO;
import com.skd.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = "Topic1", groupId = "myGroup")
    public void consume(OrderDTO orderDTO) {
        log.info("Order received {}",orderDTO);
        orderService.placeOrder(orderDTO);
    }
}
