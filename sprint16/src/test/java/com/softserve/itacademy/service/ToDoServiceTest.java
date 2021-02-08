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
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;

public class ToDoServiceTest {
    private static UserService userService;
    private static ToDoService toDoService;
    User Ivan, Ira, Den;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        annotationConfigContext.close();
    }

    @BeforeEach public void initialize() {
        Ivan = new User("Ivan","Schek","ivanshs@gmail.com","1234");
        Ira = new User("Ira","Golub","iragolub@gmail.com","1234");
        Den = new User("Den","Zariy","denzar@gmail.com","1234");
        toDoService.getAll().clear();
        userService.getAll().clear();
        userService.addUser(Ivan);
        userService.addUser(Ira);
        userService.addUser(Den);
    }
    @Test
    public void checkAddToDoToUser() {
        ToDo densTodo1 = new ToDo("Required", LocalDateTime.now(), Den);
        ToDo densTodo2 = new ToDo("Regular", LocalDateTime.now(), Den);
        List<ToDo> expected = new ArrayList<ToDo>();
        expected.add(densTodo1);
        expected.add(densTodo2);
        toDoService.addTodo(densTodo1, Den);
        toDoService.addTodo(densTodo2, Den);
        List<ToDo> actual = userService.readUser("denzar@gmail.com").getMyTodos();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkUpdateToDo() {
        LocalDateTime time = LocalDateTime.now();
        ToDo iratodos=new ToDo("Required", LocalDateTime.MIN, Ira);
        List<ToDo>expected = new ArrayList<ToDo>();
        expected.add(new ToDo("Required",time,Ira));

        toDoService.addTodo(iratodos, Ira);

        List<ToDo>actual = new ArrayList<ToDo>();
        actual.add(toDoService.updateTodo(new ToDo("Required",time,Ira)));

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkDeleteToDo() {
        ToDo iratodos = new ToDo("Required",LocalDateTime.now(),Ira);
        ToDo ivantodos = new ToDo("Rare",LocalDateTime.now(),Ivan);
        ToDo dentodos = new ToDo("Usual",LocalDateTime.now(),Den);

        List<ToDo>expected = new ArrayList<ToDo>();
        expected.add(iratodos);
        expected.add(ivantodos);

        toDoService.addTodo(ivantodos, Ivan);
        toDoService.addTodo(iratodos, Ira);
        toDoService.addTodo(dentodos, Den);

        toDoService.deleteTodo(dentodos);

        List<ToDo> actual = toDoService.getAll();
        Collections.sort(expected, (ToDo t1,ToDo t2) -> {
            return t1.getTitle().compareTo(t2.getTitle());
        });
        Collections.sort(actual, (ToDo t1,ToDo t2) -> {
            return t1.getTitle().compareTo(t2.getTitle());
        });
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkGetAll() {
        List<ToDo> expected = new ArrayList<ToDo>();
        LocalDateTime time = LocalDateTime.now();
        expected.add(new ToDo("Requird", time, Den));
        expected.add(new ToDo("Usual", time, Den));
        expected.add(new ToDo("Rare", time, Ira));
        expected.add(new ToDo("Regular", time, Ivan));

        toDoService.addTodo(new ToDo("Requird", time, Den), Den);
        toDoService.addTodo(new ToDo("Usual", time, Den), Den);
        toDoService.addTodo(new ToDo("Rare", time, Ira), Ira);
        toDoService.addTodo(new ToDo("Regular", time, Ivan), Ivan);

        List<ToDo>actual = toDoService.getAll();

        Collections.sort(expected, (ToDo t1,ToDo t2) -> {
            return t1.getTitle().compareTo(t2.getTitle());
        });
        Collections.sort(actual, (ToDo t1,ToDo t2) -> {
            return t1.getTitle().compareTo(t2.getTitle());
        });

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkGetByUser() {
        ToDo iratodos1 = new ToDo("Required",LocalDateTime.now(),Ira);
        ToDo iratodos2 = new ToDo("Rare",LocalDateTime.now(),Ira);
        ToDo iratodos3 = new ToDo("Usual",LocalDateTime.now(),Ira);
        List<ToDo>expected = new ArrayList<ToDo>();
        expected.add(iratodos1);
        expected.add(iratodos2);
        expected.add(iratodos3);

        toDoService.addTodo(iratodos1, Ira);
        toDoService.addTodo(iratodos2, Ira);
        toDoService.addTodo(iratodos3, Ira);
        List<ToDo> actual = toDoService.getByUser(Ira);
        Collections.sort(expected, (ToDo t1,ToDo t2) -> {
            return t1.getTitle().compareTo(t2.getTitle());
        });
        Collections.sort(actual, (ToDo t1,ToDo t2) -> {
            return t1.getTitle().compareTo(t2.getTitle());
        });
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkGetByUserTitle() {
        LocalDateTime time = LocalDateTime.now();
        ToDo expected = new ToDo("Rec", time, Ira);

        toDoService.addTodo(expected, Ira);

        ToDo actual = toDoService.getByUserTitle(Ira, "Rec");
        Assertions.assertEquals(expected, actual);
    }
}