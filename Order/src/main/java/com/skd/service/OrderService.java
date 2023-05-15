package com.skd.service;

import com.skd.dto.AddressDTO;
import com.skd.dto.OrderDTO;
import com.skd.entity.Address;
import com.skd.entity.MenuItemPK;
import com.skd.entity.Order;
import com.skd.mapper.OrderMapper;
import com.skd.repository.AddressRepository;
import com.skd.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
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

    @Transactional
    public void addAddressToOrder(Long order_id, AddressDTO addressDTO) {
        Order order = orderRepository.findById(order_id)
                .orElseThrow(()-> new NullPointerException("Order doesn't exist with id "+order_id));

        Address address = orderMapper.toAddress(addressDTO);
        address.setOrder(order);

        addressRepository.saveAndFlush(address);

        order.setAddress(address);
    }
}
