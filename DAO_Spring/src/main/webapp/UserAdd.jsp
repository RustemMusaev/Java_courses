<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "User" method = "POST">
    <table border = "0">

        <tr>
            <td><b>User id</b></td>
            <td><input type = "text" name = "id" required placeholder = "imput id user" size = "70"/></td>
        </tr>
        <tr>
            <td><b>User name</b></td>
            <td><input type = "text" name = "name" required placeholder = "imput name user" size = "70"/></td>
        </tr>

        <tr>
            <td><b>user age</b></td>
            <td><input type = "text" name = "age" required placeholder = "imput user age" size = "65"/></td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" value = "add user"/></td>
        </tr>
    </table>
</form>
</body>
</html>
