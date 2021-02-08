package com.softserve.itacademy.controller;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class ToDoController {
    private final Logger logger = LoggerFactory.getLogger(ToDoController.class);

    private final UserService userService;
    private final ToDoService toDoService;


    public ToDoController(UserService userService, ToDoService toDoService) {
        this.userService = userService;
        this.toDoService = toDoService;
    }

    @GetMapping("/create/users/{owner_id}")
    public String create(Model model) {
        //ToDo
        logger.info("GET '/create/users/owner_id}': Create toDo");
        model.addAttribute("user", new User());
        return "create-todo";
    }

    @PostMapping("/create/users/{owner_id}")
    public String create(@PathVariable("ownerId") long ownerId,
                         @Validated @ModelAttribute User user,
                         BindingResult result) {
        logger.info("POST '/create/users/{}/add': Add todo[id='{}'] to user[id='{}']",
                ownerId, user.getId(), ownerId);
        if (result.hasErrors()) {
            return "create-todo";
    }

    @GetMapping("/{id}/tasks")
    public String read() {

        return "todo-tasks";
    }

    @GetMapping("/{todo_id}/update/users/{owner_id}")
    public String update((@PathVariable("todo_id") long toDoId,
        @PathVariable("owner_id") long ownerId,
        Model model) {

            logger.info("GET '/{}/update/users/{}': Update toDo[id='{}'] at owner[id='{}']",
                    toDoId, ownerId, toDoId, ownerId);
            user = toDoService.getByUserId(ownerId);
            model.addAttribute("user", user);
            return "update_todo";
    }

    @PostMapping("/{todo_id}/update/users/{owner_id}")
    public String update() {
        //ToDo
        return " ";
    }

    @GetMapping("/{todo_id}/delete/users/{owner_id}")
    public String delete() {
        return " ";
    }

    @GetMapping("/all/users/{user_id}")
    public String getAll(//add needed parameters) {
        //ToDo
        return " ";
    }

    @GetMapping("/{id}/add")
    public String addCollaborator(//add needed parameters) {
        //ToDo
        return " ";
    }

    @GetMapping("/{id}/remove")
    public String removeCollaborator(//add needed parameters) {
        //ToDo
        return " ";
    }
}
