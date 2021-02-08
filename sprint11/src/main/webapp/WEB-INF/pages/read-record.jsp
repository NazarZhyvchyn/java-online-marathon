<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Read Record from Address Book</title>
</head>
<body>

    <%@include file="jspf/header.jspf"%>

    <table>
        <tr>
            <td>First name: </td>
            <td><b><%=request.getAttribute("firstname")%></b></td>
        </tr>
        <tr>
            <td>Last name: </td>
            <td><b><%=request.getAttribute("lastname")%></b></td>
        </tr>
        <tr>
            <td>Address: </td>
            <td><b><%=request.getAttribute("address")%></b></td>
        </tr>
    </table>
</body>
</html>
