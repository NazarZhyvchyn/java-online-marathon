package com.softserve.itacademy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;


@RunWith(JUnitPlatform.class)
public class TaskServiceTest {
    private static UserService userService;
    private static ToDoService toDoService;
    private static TaskService taskService;
    private User user;
    private ToDo todo;
    private LocalDateTime time = LocalDateTime.now();

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        taskService = annotationConfigContext.getBean(TaskService.class);
        annotationConfigContext.close();
    }
    @BeforeEach public void initialize() {
    	userService.getAll().clear();
    	toDoService.getAll().clear();
    	taskService.getAll().clear();
    	user = new User("Ira", "Ilika", "irailika@gmail.com", "1234123");
    	todo = new ToDo("Required",time,user);
    }
    @Test
    public void checkAddTask() {
    	Task task = new Task("Make coffee",Priority.HIGH);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(task, todo);
    	Assertions.assertEquals(task, user.getMyTodos().get(0).getTasks().get(0));
    }
    
    @Test
    public void checkUpdateTask() {
    	Task task = new Task("Make coffee",Priority.HIGH);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(task, todo);
    	Task expected = new Task("Make coffee",Priority.LOW);
    	taskService.updateTask(expected);
    	
    	Assertions.assertEquals(expected, user.getMyTodos().get(0).getTasks().get(0));
    }
    
    @Test
    public void checkDeleteTask() {
    	Task task = new Task("Make coffee",Priority.HIGH);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(task, todo);
    	
    	taskService.deleteTask(task);
    	Assertions.assertEquals(true, user.getMyTodos().get(0).getTasks().isEmpty());
    }
    
    @Test
    public void checkGetAll() {
    	Task task1 = new Task("Make coffee",Priority.HIGH);
    	Task task2 = new Task("Make tea",Priority.MEDIUM);
    	Task task3 = new Task("Sleep",Priority.LOW);
    	
    	List<Task> expected = new ArrayList<Task>();
    	expected.add(task1);
    	expected.add(task2);
    	expected.add(task3);
    	
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	
    	taskService.addTask(task1, todo);
    	taskService.addTask(task2, todo);
    	taskService.addTask(task3, todo);
    	List<Task> actual = taskService.getAll();
    	
    	Collections.sort(expected, (Task t1,Task t2) -> {
    		return t1.getName().compareTo(t2.getName());
    	});
    	Collections.sort(actual, (Task t1,Task t2) -> {
    		return t1.getName().compareTo(t2.getName());
    	});
    	
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void checkGetByToDo() {
    	Task task1 = new Task("Make coffee",Priority.HIGH);
    	Task task2 = new Task("Make tea",Priority.MEDIUM);
    	Task task3 = new Task("Sleep",Priority.LOW);
    	
    	Task task4 = new Task("Walk",Priority.LOW);
    	
    	List<Task> expected = new ArrayList<Task>();
    	expected.add(task1);
    	expected.add(task2);
    	expected.add(task3);
    	
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	
    	taskService.addTask(task1, todo);
    	taskService.addTask(task2, todo);
    	taskService.addTask(task3, todo);
    	
    	User newUser = new User("new", "user", "newus@email", "1234");
    	ToDo newtodo = new ToDo("newtodo", time, newUser);
    	
    	userService.addUser(newUser);
    	toDoService.addTodo(newtodo, newUser);
    	taskService.addTask(task4, newtodo);
    	
    	List<Task> actual = taskService.getByToDo(todo);
    	
    	Collections.sort(expected, (Task t1,Task t2) -> {
    		return t1.getName().compareTo(t2.getName());
    	});
    	Collections.sort(actual, (Task t1,Task t2) -> {
    		return t1.getName().compareTo(t2.getName());
    	});
    	
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void checkGetByToDoName() {
    	Task task1 = new Task("Make coffee",Priority.HIGH);
    	Task task2 = new Task("Make tea",Priority.MEDIUM);
    	Task task3 = new Task("Sleep",Priority.LOW);
    	
    	Task task4 = new Task("Walk",Priority.LOW);
    	
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	
    	taskService.addTask(task1, todo);
    	taskService.addTask(task2, todo);
    	taskService.addTask(task3, todo);
    	
    	User newUser = new User("new", "user", "newus@email", "1234");
    	ToDo newtodo = new ToDo("newtodo", time, newUser);
    	
    	userService.addUser(newUser);
    	toDoService.addTodo(newtodo, newUser);
    	taskService.addTask(task4, newtodo);
    	
    	Task expected = task2;
    	
    	Assertions.assertEquals(expected, taskService.getByToDoName(todo, "Make tea"));
    }
    
    @Test
    public void checkGetByUserName() {
    	Task task1 = new Task("Make coffee",Priority.HIGH);
    	Task task2 = new Task("Make tea",Priority.MEDIUM);
    	Task task3 = new Task("Sleep",Priority.LOW);
    	
    	Task task4 = new Task("Walk",Priority.LOW);
    	
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	
    	taskService.addTask(task1, todo);
    	taskService.addTask(task2, todo);
    	taskService.addTask(task3, todo);
    	
    	User newUser = new User("new", "user", "newus@email", "1234");
    	ToDo newtodo = new ToDo("newtodo", time, newUser);
    	
    	userService.addUser(newUser);
    	toDoService.addTodo(newtodo, newUser);
    	taskService.addTask(task4, newtodo);
    	
    	Task expected = task4;
    	
    	Assertions.assertEquals(expected, taskService.getByUserName(newUser, "Walk"));
    }

    // TODO, other tests
}
