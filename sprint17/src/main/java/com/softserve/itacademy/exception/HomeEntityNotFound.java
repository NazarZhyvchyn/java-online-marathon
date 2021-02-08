package com.softserve.itacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such home found")  // 404
public class HomeEntityNotFound extends NullEntityReference {
    public HomeEntityNotFound(Long id) {
        super(id, "home");
    }
}
