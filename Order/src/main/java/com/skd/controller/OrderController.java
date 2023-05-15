package com.skd.controller;

import com.skd.dto.AddressDTO;
import com.skd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    @PostMapping("/{order_id}/selectAddress")
    public ResponseEntity<?> addAddress(@PathVariable Long order_id, @RequestBody AddressDTO addressDTO){
        orderService.addAddressToOrder(order_id,addressDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
