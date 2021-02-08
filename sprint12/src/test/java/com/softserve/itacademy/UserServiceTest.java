package com.softserve.itacademy;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
public class UserServiceTest {
    private static UserService userService;

    User Ivan = new User("Ivan","Schek","ivanshs@gmail.com","1234");
    User Oleg = new User("Oleg","Grek","oleggrek@gmail.com","1234");
    User Ira = new User("Ira","Golub","iragolub@gmail.com","1234");
    User Yra = new User("Yra","Sonce","yrasonce@gmail.com","1234");
    User Olia = new User("Olia","Leniv","oliliniv@gmail.com","1234");
    User Den = new User("Den","Zariy","denzar@gmail.com","1234");

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        annotationConfigContext.close();

    }

    @BeforeEach
    public void initialize() {
        userService.getAll().clear();
    }

    @BeforeAll
    static void beforeAll() {
        userService = new UserServiceImpl();
    }

    @AfterEach
    void tearDown() {
        userService.getAll().clear();
    }

    @Test
    public void checkAddUser() {
        List<User> expected = new ArrayList<User>();
        expected.add(Ivan);

        userService.addUser(Ivan);
        List<User> actual = userService.getAll();

        Assertions.assertEquals(expected, actual);
    }



    @Test
    public void checkUpdateUser() {
        User Ivan = new User("Ivan","Schek","ivanshs@gmail.com","123");
        userService.addUser(Ivan);
        User expected = new User("Ivan","Schek","ivanshs@gmail.com","1");
        userService.updateUser(expected);
        Assertions.assertEquals(expected, userService.readUser("ivanshs@gmail.com"));
    }
    @Test
    public void checkDeleteUser() {
        List<User> expected = new ArrayList<User>();
        userService.addUser(Ivan);
        userService.deleteUser(Ivan);
        List<User> actual = userService.getAll();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void checkReadUser() {
        List<User> expected = new ArrayList<User>();
        userService.addUser(Ivan);
        userService.deleteUser(Ivan);
        List<User> actual = userService.getAll();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addNulluser() {
        userService.addUser(null);
        Assertions.assertEquals(new ArrayList<User>(), userService.getAll());
    }

    @Test
    public void addExsitingUser() {
        List<User> expected = new ArrayList<User>();
        expected.add(Den);
        expected.add(Oleg);
        expected.add(Ira);

        userService.addUser(Den);
        userService.addUser(Oleg);
        userService.addUser(Ira);
        userService.addUser(Den);
        List<User> actual = userService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void returnedCorrectUserWhenAdd() {
        User expected = Ivan;
        Assertions.assertEquals(expected, userService.addUser(Ivan));
    }


}
