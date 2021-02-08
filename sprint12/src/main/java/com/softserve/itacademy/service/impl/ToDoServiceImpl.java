package com.softserve.itacademy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;

@Service
public class ToDoServiceImpl implements ToDoService {

    private UserService userService;

    @Autowired
    public ToDoServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public ToDo addTodo(ToDo todo, User user) {
        User currentUser = userService.readUser(user.getEmail());
        if(currentUser==null)return null;
        if(currentUser.getMyTodos().stream().filter(streamToDo -> streamToDo.getTitle().equals(todo.getTitle()))
        		.count()==0) {
        	currentUser.getMyTodos().add(todo);
        }
        return currentUser.getMyTodos().stream().filter(streamToDo -> streamToDo.getTitle().equals(todo.getTitle()))
        		.findAny().get();
    }

    public ToDo updateTodo(ToDo todo) {
        ToDo forUpdating = userService.getAll().stream().flatMap(user -> user.getMyTodos().stream())
        		.filter(streamTodo -> streamTodo.getTitle().equals(todo.getTitle())).findFirst().orElse(null);
        if(forUpdating == null)return null;
        else {
        	forUpdating.setCreatedAt(todo.getCreatedAt());
        	forUpdating.setOwner(todo.getOwner());
        	forUpdating.setTasks(todo.getTasks());
        }
        return forUpdating;
    }

    public void deleteTodo(ToDo todo) {
    	for(int i =0;i< userService.getAll().size();i++) {
    		for(int j =0;j<userService.getAll().get(i).getMyTodos().size();j++) {
    			if(userService.getAll().get(i).getMyTodos().get(j).getTitle().equals(todo.getTitle())) {
    				userService.getAll().get(i).getMyTodos().remove(j);
    				return;
    			}
    		}
    	}
    }

    public List<ToDo> getAll() {
        return userService.getAll().stream().flatMap(user -> user.getMyTodos().stream()).collect(Collectors.toList());
    }

    public List<ToDo> getByUser(User user) {
        return userService.getAll().stream().filter(streamUser -> streamUser.getEmail().equals(user.getEmail()))
        		.flatMap(streamUser -> streamUser.getMyTodos().stream()).collect(Collectors.toList());
    }

    public ToDo getByUserTitle(User user, String title) {
    	return userService.getAll().stream().filter(streamUser -> streamUser.getEmail().equals(user.getEmail()))
    			.flatMap(streamUser -> streamUser.getMyTodos().stream())
    			.filter(todo -> todo.getTitle().equals(title)).findFirst().orElse(null);
    }

}
