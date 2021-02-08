package com.softserve.itacademy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;

@Service
public class TaskServiceImpl implements TaskService {

    private ToDoService toDoService;

    @Autowired
    public TaskServiceImpl(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    public Task addTask(Task task, ToDo todo) {
        ToDo currentToDo = toDoService.getAll().stream().filter(streamToDo -> streamToDo.getTitle().equals(todo.getTitle())).findFirst().orElse(null);
        if(currentToDo==null)return null;
        currentToDo.getTasks().add(task);
        return currentToDo.getTasks().stream().filter(streamTask -> streamTask.getName().equals(task.getName())).findFirst().orElse(null);
    }

    public Task updateTask(Task task) {
    	Task taskForUpdating = toDoService.getAll().stream().flatMap(todo -> todo.getTasks().stream())
    			.filter(streamTask -> streamTask.getName().equals(task.getName())).findFirst().orElse(null);
    	if(taskForUpdating==null) return null;
    	taskForUpdating.setPriority(task.getPriority());
    	return taskForUpdating;
    }

    public void deleteTask(Task task) {
    	for(int i =0; i< toDoService.getAll().size();i++) {
    		for(int j =0;j< toDoService.getAll().get(i).getTasks().size();j++) {
    			if(toDoService.getAll().get(i).getTasks().get(j).getName().equals(task.getName())) {
    				toDoService.getAll().get(i).getTasks().remove(j);
    				return;
    			}
    		}
    	}
    }

    public List<Task> getAll() {
        return toDoService.getAll().stream().flatMap(todo->todo.getTasks().stream()).collect(Collectors.toList());
    }

    public List<Task> getByToDo(ToDo todo) {
        return toDoService.getAll().stream().filter(streamToDo -> streamToDo.getTitle().equals(todo.getTitle()))
        		.flatMap(streamToDo -> streamToDo.getTasks().stream()).collect(Collectors.toList());
    }

    public Task getByToDoName(ToDo todo, String name) {
    	return toDoService.getAll().stream().filter(streamToDo -> streamToDo.getTitle().equals(todo.getTitle()))
    			.flatMap(streamToDo -> streamToDo.getTasks().stream())
    			.filter(task -> task.getName().equals(name)).findFirst().orElse(null);
    }

    public Task getByUserName(User user, String name) {
        return toDoService.getAll().stream().filter(todo -> todo.getOwner().getEmail().equals(user.getEmail()))
        		.flatMap(todo -> todo.getTasks().stream())
        		.filter(task -> task.getName().equals(name)).findFirst().orElse(null);
    }

}
