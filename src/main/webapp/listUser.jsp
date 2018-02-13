<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 04.02.2018
  Time: 21:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1">
    <tr>
        <th>User Id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>email</th>
        <th>dob</th>
        <th colspan="2">Action</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.dob}"/></td>
            <td><a href="UserController?action=edit&id=${user.id}">Edit</a> </td>
            <td><a href="UserController?action=delete&id=${user.id}">Delete</a> </td>

        </tr>
    </c:forEach>
</table>
<p><a href="UserController?action=create">Create</a></p>
</body>
</html>
