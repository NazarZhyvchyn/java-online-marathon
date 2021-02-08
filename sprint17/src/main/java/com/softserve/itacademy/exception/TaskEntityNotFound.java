package com.softserve.itacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Task")  // 404
public class TaskEntityNotFound extends NullEntityReference {
    public TaskEntityNotFound(Long id) {
        super(id, "task");
    }
}
