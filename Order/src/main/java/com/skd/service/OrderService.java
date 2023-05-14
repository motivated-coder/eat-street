package com.skd.service;

import com.skd.dto.OrderDTO;
import com.skd.entity.MenuItemPK;
import com.skd.entity.Order;
import com.skd.mapper.OrderMapper;
import com.skd.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    public Order placeOrder(OrderDTO orderDTO){
        Order order = orderMapper.toOrder(orderDTO);
        order.getOrderItems().stream().forEach(orderItem -> {
            orderItem.setOrder(order);
        });
        Order placedOrder = orderRepository.save(order);
        log.info("Order successfully placed with id {}", placedOrder.getOrderId());
        return placedOrder;
    }
}
