package com.softserve.itacademy;

import java.time.LocalDateTime;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = annotationConfigContext.getBean(UserService.class);
        ToDoService toDoService = annotationConfigContext.getBean(ToDoService.class);
        TaskService taskService = annotationConfigContext.getBean(TaskService.class);
        annotationConfigContext.close();
        
        User ira = new User("Ira", "Leleka", "iralel@gmail.com", "1233445");
        User oleg = new User("Oleg", "Hanok", "olehganok@gmail.com", "88888888");
        User yra = new User("Yra", "Lampa", "lampoyra@gmail.com", "11223344");
        
        ToDo required = new ToDo("Required", LocalDateTime.now(), ira);
        ToDo usual = new ToDo("Usual", LocalDateTime.now(), oleg);
        ToDo nonRequired = new ToDo("Not required", LocalDateTime.now(), yra);
        ToDo dependOnWeathe = new ToDo("Depends on weather", LocalDateTime.now(), ira);
        
        Task task1 = new Task("Make homework", Priority.HIGH);
        Task task2 = new Task("Walk with cat", Priority.LOW);
        Task task3 = new Task("Walk with dog", Priority.HIGH);
        Task task4 = new Task("Sleep", Priority.MEDIUM);
        Task task5 = new Task("Wash hands", Priority.HIGH);
        
        //1.Add new user to the Application.
        System.out.println("1.Add new user to the Application.");
        userService.addUser(yra);
        userService.addUser(oleg);
        userService.addUser(ira);
        System.out.println("Result:");
        System.out.println(userService.getAll());
        
        
        //2.Update/Delete/Gets existing users.
        System.out.println();
        System.out.println("2.Update/Delete/Gets existing users.");
        System.out.println("Delete Yra");
        userService.deleteUser(yra);
        System.out.println("Result: ");
        System.out.println(userService.getAll());
        
        System.out.println("Update Ira");
        userService.updateUser(new User("Ira", "Vorona", "iralel@gmail.com", "09865123"));
        System.out.println("Result:");
        System.out.println(userService.getAll());
        
        System.out.println("Get Oleg");
        System.out.println("Result:");
        System.out.println(userService.readUser("olehganok@gmail.com"));
        
        //3.Add new To-Do List to an existing user.
        System.out.println();
        System.out.println("3.Add new To-Do List to an existing user.");
        toDoService.addTodo(required, ira);
        toDoService.addTodo(usual, oleg);
        toDoService.addTodo(nonRequired, ira);
        toDoService.addTodo(dependOnWeathe, oleg);
        System.out.println("Result:");
        System.out.println(userService.getAll());
        
        //4.Update/Delete/Gets existing 'To-Do Lists'.
        System.out.println();
        System.out.println("4.Update/Delete/Gets existing 'To-Do Lists'");
        System.out.println("Update:");
        toDoService.updateTodo(new ToDo("Required", LocalDateTime.MAX, ira));
        System.out.println("Result:");
        System.out.println(userService.getAll());
        System.out.println("Delete:");
        toDoService.deleteTodo(nonRequired);
        System.out.println("Result:");
        System.out.println(userService.getAll());
        
        //5.Add new Task to an existing 'To-Do List'.
        System.out.println();
        System.out.println("Add new Task to an existing 'To-Do List'.");
        taskService.addTask(task1, required);
        taskService.addTask(task2, dependOnWeathe);
        taskService.addTask(task3, required);
        taskService.addTask(task4, usual);
        taskService.addTask(task5, usual);
        System.out.println("Result:");
        System.out.println(userService.getAll());
        
        //6.Update/Delete/Gets existing 'Tasks'.
        System.out.println();
        System.out.println("Update/Delete/Gets existing 'Tasks'.");
        System.out.println("Update Walk with cat");
        taskService.updateTask(new Task("Walk with cat", Priority.HIGH));
        System.out.println("Result:");
        System.out.println(userService.getAll());
        
        
    }

}
