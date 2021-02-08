package com.softserve.itacademy.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.softserve.itacademy.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;



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
        user = new User(1, "Ira", "Ivanova", "mail4@mail.com", "abc");
        todo = new ToDo(1, "title", LocalDateTime.of(2020, 9, 19, 14, 5));
    }
    @Test
    public void checkAddTask() {
        Task task = new Task(1, "Make coffee",Priority.HIGH);
        userService.create(user);
        toDoService.create(todo);
        taskService.create(task);
        Assertions.assertEquals(task, user.getMyTodos().get(0).getTasks().get(0));
    }

    @Test
    public void checkUpdateTask() {
        Task task = new Task(1, "Make tea",Priority.HIGH);

        taskService.create(task);
        Task expected = new Task(1, "Make tea",Priority.HIGH);
        taskService.update(expected);

        Assertions.assertEquals(expected, user.getMyTodos().get(0).getTasks().get(0));
    }

    @Test
    public void checkDeleteTask() {
        Task task = new Task(1, "Make tea",Priority.HIGH);

        taskService.create(task);

        taskService.delete(1);
        Assertions.assertEquals(true, user.getMyTodos().get(0).getTasks().isEmpty());
    }

    @Test
    public void checkGetAll() {
        Task task1 = new Task(1, "Make coffee",Priority.HIGH);
        Task task2 = new Task(2, "Make tea",Priority.MEDIUM);
        Task task3 = new Task(3, "Sleep",Priority.LOW);

        List<Task> expected = new ArrayList<Task>();
        expected.add(task1);
        expected.add(task2);
        expected.add(task3);

        userService.create(user);

        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
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
        Task task1 = new Task(1, "Make coffee",Priority.HIGH);
        Task task2 = new Task(2, "Make tea",Priority.MEDIUM);
        Task task3 = new Task(3, "Sleep",Priority.LOW);

        Task task4 = new Task(4, "Walk",Priority.LOW);

        List<Task> expected = new ArrayList<Task>();
        expected.add(task1);
        expected.add(task2);
        expected.add(task3);
        expected.add(task4);

        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
        taskService.create(task4);

        User newUser = new User(4, "New", "User", "newus@email", "1234");


        taskService.create(task4);

        List<Task> actual = taskService.getByTodoId(4);

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
        Task task1 = new Task(1, "Make coffee",Priority.HIGH);
        Task task2 = new Task(2, "Make tea",Priority.MEDIUM);
        Task task3 = new Task(3, "Sleep",Priority.LOW);

        Task task4 = new Task(4, "Walk",Priority.LOW);


        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);

        taskService.create(task4);

        Task expected = task2;

        Assertions.assertEquals(expected, taskService.getByTodoId(1));
    }

    @Test
    public void checkGetByUserName() {
        Task task1 = new Task(1, "Make coffee",Priority.HIGH);
        Task task2 = new Task(2, "Make tea",Priority.MEDIUM);
        Task task3 = new Task(3, "Sleep",Priority.LOW);


        Task task4 = new Task(4, "Walk",Priority.LOW);


        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);




        taskService.create(task4);

        Task expected = task4;

        Assertions.assertEquals(expected, taskService.getByTodoId(4));
    }

}