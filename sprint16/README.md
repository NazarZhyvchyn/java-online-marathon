# Java Online Marathon
## ‘ToDo List’ Project

Please, create test layer and implement **unit tests** for next layers:

-  Repository

-  Service

Create **integration test** with DB.

Implement **logging** in all controllers in project.

Use JUnit5 for implementation tests.

Submit links to github repository and make short video (2-5 minutes) where demonstrate the functionality as the result of your work!


## Set Up DB

If you want to configure and fill your DB automatic you need to create 'todolist' DB in your server and to set properties 
spring.jpa.hibernate.ddl-auto=create
spring.datasource.initialization-mode=always
in application.properties file

When application starts your DB will be filled data from data.sql file from resources folder

There are three users with ADMIN and USER roles in DB.

| Login         | Password | Role  |
| ------------- |:--------:|:-----:|
| mike@mail.com | 1111     | ADMIN |
| nick@mail.com | 2222     | USER  |
| nora@mail.com | 3333     | USER  |

User with Admin role has access to all data and resources in DB
