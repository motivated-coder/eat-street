package com.skd.controller;

import com.skd.dto.CartDTO;
import com.skd.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartDTO cartDTO){
        CartDTO cartDto = cartService.registerToCart(cartDTO);
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

}
