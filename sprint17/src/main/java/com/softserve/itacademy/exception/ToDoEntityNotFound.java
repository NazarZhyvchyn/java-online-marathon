package com.softserve.itacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such ToDo")  // 404
public class ToDoEntityNotFound extends NullEntityReference {
    public ToDoEntityNotFound(Long id) {
        super(id, "toDo");
    }
}
