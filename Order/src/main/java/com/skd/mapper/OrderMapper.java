package com.skd.mapper;

import com.skd.dto.MenuItemDTO;
import com.skd.dto.OrderDTO;
import com.skd.dto.OrderItemDTO;
import com.skd.entity.MenuItem;
import com.skd.entity.Order;
import com.skd.entity.OrderItem;
import com.skd.enums.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order toOrder(OrderDTO orderDTO){
        return Order.builder()
                .orderState(OrderState.valueOf(orderDTO.getOrderState()))
                .orderType(OrderType.valueOf(orderDTO.getOrderType()))
                .placedDate(orderDTO.getPlacedDate())
                .total(orderDTO.getTotal())
                .deliveryAddress(orderDTO.getDeliveryAddress())
                .orderItems(toOrderItems(orderDTO.getOrderItemDTOS()))
                .userId(orderDTO.getUserId())
                .restaurantId(orderDTO.getRestaurantId())
                .build();
    }

    private List<OrderItem> toOrderItems(List<OrderItemDTO> orderItemDTOS) {
        return orderItemDTOS.stream().map(orderItemDTO ->
                OrderItem.builder()
                        .orderItemType(OrderItemType.valueOf(orderItemDTO.getOrderItemType()))
                        .quantity(orderItemDTO.getQuantity())
                        .menuItem(toMenuItem(orderItemDTO.getMenuItemDTO()))
                        .build()
                ).collect(Collectors.toList());
    }

    private MenuItem toMenuItem(MenuItemDTO menuItemDTO) {
        return MenuItem.builder()
                .id(UUID.randomUUID())
                .menuItemId(menuItemDTO.getMenuItemId())
                .name(menuItemDTO.getName())
                .description(menuItemDTO.getDescription())
                .price(menuItemDTO.getPrice())
                .itemType(ItemType.valueOf(menuItemDTO.getItemType()))
                .itemUnit(ItemUnit.valueOf(menuItemDTO.getItemUnit()))
                .restaurantId(menuItemDTO.getRestaurantId())
                .build();
    }

    public OrderDTO toOrderDTO(Order order){
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .orderState(order.getOrderState().name())
                .orderType(order.getOrderType().name())
                .placedDate(order.getPlacedDate())
                .total(order.getTotal())
                .deliveryAddress(order.getDeliveryAddress())
                .orderItemDTOS(toOrderItemDTOs(order.getOrderItems()))
                .userId(order.getUserId())
                .restaurantId(order.getRestaurantId())
                .build();
    }

    private List<OrderItemDTO> toOrderItemDTOs(List<OrderItem> orderItems) {
        return orderItems.stream().map(orderItem ->
                OrderItemDTO.builder()
                        .orderItemId(orderItem.getOrderItemId())
                        .orderItemType(orderItem.getOrderItemType().name())
                        .quantity(orderItem.getQuantity())
                        .menuItemDTO(toMenuItemDTO(orderItem.getMenuItem()))
                        .build()
        ).collect(Collectors.toList());
    }


    private MenuItemDTO toMenuItemDTO(MenuItem menuItem) {
        return MenuItemDTO.builder()
                .id(menuItem.getId())
                .menuItemId(menuItem.getMenuItemId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .itemType(menuItem.getItemType().name())
                .itemUnit(menuItem.getItemUnit().name())
                .restaurantId(menuItem.getRestaurantId())
                .build();
    }
}
