<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action = "/users/${user.id}" method = "PUT">
    <table border = "0" items="${requestScope.user}" var="user">
        <tr>
            <td><b>User name</b></td>
            <td><input type = "text" name = "name" placeholder="${user.getName()}" size = "70"/></td>
        </tr>
        <tr>
            <td><b>user age</b></td>
            <td><input type = "text" name = "age" placeholder="${user.getAge()}" size = "65"/></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "update user"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
