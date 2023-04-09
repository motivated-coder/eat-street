package com.skd.service;

import com.skd.dto.CartDTO;
import com.skd.entity.Cart;
import com.skd.mapper.CartMapper;
import com.skd.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public CartDTO registerToCart(CartDTO cartDTO) {
        Cart cart = cartRepository.save(cartMapper.toCart(cartDTO));
        return cartMapper.toCartDTO(cart);
    }
}
