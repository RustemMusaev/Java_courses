<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "User/${param.userid}/Car" method = "POST">
    <table border = "0">

        <tr>
            <td><b>Car id</b></td>
            <td><input type = "text" name = "carid" required placeholder = "${param.userid}" size = "70"/></td>
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
            <td colspan = "2"><input type = "submit" value = "add car for user id=${param.userid}"/></td>
        </tr>
    </table>
</form>
</body>
</html>