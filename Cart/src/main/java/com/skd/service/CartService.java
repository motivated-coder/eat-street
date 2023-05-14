package com.skd.service;

import com.skd.dto.CartDTO;
import com.skd.dto.MenuItemDTO;
import com.skd.dto.OrderDTO;
import com.skd.dto.OrderItemDTO;
import com.skd.entity.Cart;
import com.skd.entity.CartItem;
import com.skd.mapper.CartMapper;
import com.skd.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public CartDTO registerToCart(CartDTO cartDTO) {
        Cart cart = cartMapper.toCart(cartDTO);
        cart.getCartItems().stream().forEach(cartItem -> cartItem.setCart(cart));
        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toCartDTO(savedCart);
    }

    public CartDTO updateCart(CartDTO cartDTO) {
        Cart oldCart = cartRepository.findById(cartDTO.getId()).orElse(new Cart());
        Cart newCart = cartMapper.toCart(cartDTO);
        newCart.setId(oldCart.getId());
        newCart.getCartItems().stream().forEach(cartItem -> cartItem.setCart(newCart));
        Cart updatedCart = cartRepository.save(newCart);
        OrderDTO orderToBePlaced = buildOrderDTO(updatedCart);
        kafkaTemplate.send("Topic1", orderToBePlaced);
        return cartMapper.toCartDTO(updatedCart);
    }

    public OrderDTO buildOrderDTO(Cart cart) {
        return OrderDTO.builder()
                .orderState("NEW")
                .orderType(determineOrderType(cart))
                .placedDate(new Date())
                .total(cart.getTotalPrice())
                .orderItemDTOS(toOrderItemDTOs(cart.getCartItems()))
                .userId(cart.getUserid())
                .restaurantId(cart.getCartItems().get(0).getMenuItem().getRestaurantId())
                .build();
    }

    private List<OrderItemDTO> toOrderItemDTOs(List<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> OrderItemDTO.builder()
                        .orderItemType(cartItem.getMenuItem().getItemType().name())
                        .quantity(cartItem.getQuantity())
                        .menuItemDTO(toMenuItemDto(cartItem))
                        .build())
                .collect(Collectors.toList());
    }

    private MenuItemDTO toMenuItemDto(CartItem cartItem) {
        return MenuItemDTO.builder()
                .id(cartItem.getMenuItem().getId())
                .menuItemId(cartItem.getMenuItem().getMenuItemId())
                .restaurantId(cartItem.getMenuItem().getRestaurantId())
                .name(cartItem.getMenuItem().getName())
                .description(cartItem.getMenuItem().getDescription())
                .price(cartItem.getMenuItem().getPrice())
                .itemType(cartItem.getMenuItem().getItemType().name())
                .itemUnit(cartItem.getMenuItem().getItemUnit().name())
                .build();
    }

    private String determineOrderType(Cart cart) {
        StringBuilder s = new StringBuilder();
        cart.getCartItems().stream()
                .forEach(cartItem -> {
                    if (cartItem.getMenuItem().getItemType().toString().contains("NON"))
                        s.append("N");
                    else
                        s.append("V");
                });
        String orderState = s.toString();
        if (orderState.contains("V") && orderState.contains("N"))
            return "MIX";
        else if (orderState.contains("V"))
            return "VEG";
        else
            return "NON_VEG";
    }
}
