package com.skd.mapper;

import com.skd.dto.CartDTO;
import com.skd.dto.CartItemDTO;
import com.skd.dto.MenuItemDTO;
import com.skd.entity.Cart;
import com.skd.entity.CartItem;
import com.skd.entity.MenuItem;
import com.skd.enums.ItemType;
import com.skd.enums.ItemUnit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    public Cart toCart(CartDTO cartDTO) {
        return Cart.builder()
                .userid(cartDTO.getUserId())
                .cartItems(toCartItem(cartDTO.getCartItems()))
                .totalPrice(calculateTotalPrice(cartDTO))
                .build();
    }

    private BigDecimal calculateTotalPrice(CartDTO cartDTO) {
        BigDecimal totalPrice = cartDTO.getCartItems()
                .stream()
                .map(cartItemDTO ->
                        cartItemDTO.getMenuItem().getPrice()
                                .multiply(BigDecimal.valueOf(cartItemDTO.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPrice;
    }

    private List<CartItem> toCartItem(List<CartItemDTO> cartItems) {

        return cartItems.stream().map(cartItemDTO ->
                        CartItem.builder()
                                .id(cartItemDTO.getId()==null?null: cartItemDTO.getId())
                                .menuItem(toMenuItem(cartItemDTO.getMenuItem()))
                                .quantity(cartItemDTO.getQuantity())
                                .build())
                .collect(Collectors.toList());
    }

    private MenuItem toMenuItem(MenuItemDTO menuItemDTO) {
        return MenuItem.builder()
                .id(menuItemDTO.getId())
                .name(menuItemDTO.getName())
                .description(menuItemDTO.getDescription())
                .price(menuItemDTO.getPrice())
                .itemType(ItemType.valueOf(menuItemDTO.getItemType()))
                .itemUnit(ItemUnit.valueOf(menuItemDTO.getItemUnit()))
                .restaurantId(menuItemDTO.getRestaurantId())
                .build();
    }

    public CartDTO toCartDTO(Cart cart){
        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUserid())
                .cartItems(toCartItemDTO(cart.getCartItems()))
                .totalPrice(cart.getTotalPrice())
                .build();
    }

    private List<CartItemDTO> toCartItemDTO(List<CartItem> cartItems) {
        return cartItems.stream().map(cartItem -> CartItemDTO.builder()
                        .id(cartItem.getId())
                        .menuItem(toMenuItemDTO(cartItem.getMenuItem()))
                        .quantity(cartItem.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    private MenuItemDTO toMenuItemDTO(MenuItem menuItem) {
        return MenuItemDTO.builder()
                .id(menuItem.getId())
                .name(menuItem.getName())
                .description(menuItem.getDescription())
                .price(menuItem.getPrice())
                .itemType(menuItem.getItemType().name())
                .itemUnit(menuItem.getItemUnit().name())
                .restaurantId(menuItem.getRestaurantId())
                .build();
    }
}