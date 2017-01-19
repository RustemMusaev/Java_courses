<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "/users/${user.id}" method = "POST">
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
                <input type="hidden" name="_method" value="PUT">
                <input type = "submit" value = "update user"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
