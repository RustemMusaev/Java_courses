<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "/users/${requestScope.userid}/Car" method = "POST">
    <table border = "0">

        <tr>
            <td><b>Car id</b></td>
            <td><input type = "text" name = "carid" required placeholder = "car id" size = "70"/></td>
        </tr>
        <tr>
            <td><b>Car model</b></td>
            <td><input type = "text" name = "model" required placeholder = "imput name car model = " size = "70"/></td>
        </tr>

        <tr>
            <td><b>Car color</b></td>
            <td><input type = "text" name = "color" required placeholder = "imput car color" size = "65"/></td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" value = "add car for user id=${requestScope.userid}"/></td>
        </tr>
    </table>
</form>
</body>
</html>