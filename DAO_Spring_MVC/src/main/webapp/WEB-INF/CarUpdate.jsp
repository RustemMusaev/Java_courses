<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "/users/${car.getUser().getId()}/Car/${car.getId()}" method = "POST">
    <table border = "0" var="car" items="${requestScope.car}">
        <tr>
            <td><b>car model</b></td>
            <td><input type = "text" name = "model" placeholder="${car.getModel()}" size = "70"/></td>
        </tr>
        <tr>
            <td><b>car color</b></td>
            <td><input type = "text" name = "color" placeholder="${car.getColor()}" size = "65"/></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type="hidden" name="_method" value="PUT">
                <input type = "submit" value = "update car"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
