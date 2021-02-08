package com.softserve.itacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Entity")  // 404
public class NullEntityReference extends RuntimeException {
    public NullEntityReference(Long id, String entityName) {
        super("No " + entityName + " for id: " + id);
    }

}
