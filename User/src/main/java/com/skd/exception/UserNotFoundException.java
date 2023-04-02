package com.skd.exception;

import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
    String message;

    public UserNotFoundException(String message) {
        super(message);
    }
}
