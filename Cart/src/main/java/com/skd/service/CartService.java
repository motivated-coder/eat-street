package com.skd.service;

import com.skd.dto.CartDTO;
import com.skd.entity.Cart;
import com.skd.mapper.CartMapper;
import com.skd.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    private final KafkaTemplate<String,Object> kafkaTemplate;

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
        return cartMapper.toCartDTO(updatedCart);
    }
}
