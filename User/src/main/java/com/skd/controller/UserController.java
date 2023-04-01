package com.skd.controller;

import com.skd.dto.UserDTO;
import com.skd.mapper.UserMapper;
import com.skd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        UserDTO registeredUser = userService.register(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
