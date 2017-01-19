<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action = "/users/${car.getUser().getId()}/Car/${car.getId()}" method = "PUT">
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
                <input type = "submit" value = "update car"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
