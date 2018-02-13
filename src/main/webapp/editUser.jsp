<%--
  Created by IntelliJ IDEA.
  User: Саша
  Date: 04.02.2018
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="UserController">
    Id <input type="text" readonly name="id" value="${user.id}"></br>
    First name <input type="text" name="firstname" value="${user.firstName}"></br>
    Last name <input type="text" name="lastname" value="${user.lastName}"></br>
    Email <input type="text" name="email" value="${user.email}"></br>
    Dob <input type="text" name="dob" value="${user.dob}"> yyyy-MM-dd </br>
    <input type="submit" value="OK">
</form>
</body>
</html>
