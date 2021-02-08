> ### Record a short video (5 - 10 minutes) where demonstrate the functionality your Web Application and publish it in your [<u>YouTube</u>](https://www.youtube.com) channel.

***

Using **Servlet API**, **JSP**, **HTML** and **CSS** create a simple Web Application for working with the **To-Do List**.

Created Web Application should be able to:

1.  **Create** a new **Task** with given **Priority**.

2.  **View** info about the **Task** from the **To-Do List**.

3.  **Edit** the **Task** from the **To-Do List**.

4.  **Delete** the **Task** from the **To-Do List**.

5.  **Show** **all** **Tasks** from the **To-Do List**.

For all given above operations should be used a ***Web Interface***.

The below given **list of all routes** and **examples of pages**:

1\. Route **http://&lt;host-name:port&gt;/home**

***Home page*** with references to other pages.

<p align="center">
  <img src="https://i.ibb.co/h8TZS8g/image1.png">
</p>

2\. Route **http://&lt;host-name:port&gt;/create-task**

The page for ***creating a new task and saving*** its in the To-Do List.

<p align="center">
  <img src="https://i.ibb.co/s1kWWmz/image2.png">
</p>

The **Priority** is an enum with values ***'Low'***, ***'Medium'***, ***'High'***

If a ***task with a given name already exists*** in the To-Do List, then make ***redirect to the same page*** and ***display info about error***.

<p align="center">
  <img src="https://i.ibb.co/hMqmT73/image3.png">
</p>

3\. Route **http://&lt;host-name:port&gt;/tasks-list**

Page with ***all tasks*** from To-Do List organized in a table and ***hyperlinks buttons*** for **reading**, **editing** and **deleting** tasks.

<p align="center">
  <img src="https://i.ibb.co/GkqkWPk/image4.png">
</p>

4\. Route **http://&lt;host-name:port&gt;/read-task?id=&lt;task ID&gt;**

The page for ***viewing info about task*** that correspond to ID
specified through the request parameters.

<p align="center">
  <img src="https://i.ibb.co/b7cR23X/image5.png">
</p>

If the ***task with the given ID is not found*** in the To-Do List, then make ***redirect to the error page*** and ***return the status code* 404**.

<p align="center">
  <img src="https://i.ibb.co/tbWR3jD/image6.png">
</p>

5\. Route **http://&lt;host-name:port&gt;/edit-task?id=&lt;task ID&gt;**

The page for ***edit an existing task*** that correspond to ID specified through the request parameters.

<p align="center">
  <img src="https://i.ibb.co/nwPw6yg/image7.png">
</p>

If the ***task with the given ID is not found*** in the To-Do List, then make ***redirect to the error page*** and ***return the status code* 404**.

<p align="center">
  <img src="https://i.ibb.co/Rg1ms0m/image8.png">
</p>

6\. Route **http://&lt;host-name:port&gt;/delete-task?id=&lt;task ID&gt;**

The '**Delete**' button ***deletes the task*** that correspond to ID specified through the request parameters from To-Do List and the table

<p align="center">
  <img src="https://i.ibb.co/BGw0cdH/image9.png">
</p>

\* Forbidden to use **pure JavaScript** and **JavaScript libraries** like **jQuery**.

\* All pages should be contains a **valid HTML** and **CSS** code.
