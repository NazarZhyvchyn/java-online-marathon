package com.softserve.itacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such User")  // 404
public class UserEntityNotFound extends NullEntityReference {
    public UserEntityNotFound(Long id) {
        super(id, "user");
    }
}

