<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create new Record in Address Book</title>
    <style>
        .error-report {
            border-left: 5px solid red;
            background-color: lightgrey;
            padding: 5px;
            margin-bottom: 10px;
        }
        .error-report strong { color: crimson; }
        input[type='reset'] { color: maroon; }
        input[type='submit'] { color: darkblue; }
    </style>
</head>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>

<% if (request.getAttribute("error") != null) {%>
<div class="error-report">
    An error occurred! <strong><%=request.getAttribute("error")%></strong>
    <br>
    Please try again!
</div>
<% }%>

<form action="/records/create" method="post">
    <table>
        <tr>
            <td>
                <label for="firstname">First name: </label>
            </td>
            <td>
                <input type="text" id="firstname" name="firstname"
                       placeholder="Олександр" required>
            </td>
        </tr>
        <tr>
            <td>
                <label for="lastname">Last name: </label>
            </td>
            <td>
                <input type="text" id="lastname" name="lastname"
                       placeholder="Бутрим" required>
            </td>
        </tr>
        <tr>
            <td>
                <label for="address">Address: </label>
            </td>
            <td>
                <textarea name="address" id="address" cols="20" rows="2"
                          placeholder="Address #1" required></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Create">
            </td>
            <td>
                <input type="reset" value="Clear">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
