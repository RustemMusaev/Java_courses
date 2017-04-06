<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
    <title>Car room</title>
</head>
<body>
<table style=" border-style: solid; border-width:1px; width: 600px; border-collapse: collapse;">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 30px;">id</td>
        <td style="width: 80px;">model</td>
        <td style="width: 80px;">color</td>
        <td></td>
        <td style="width: 80px;">action</td>
        <td style="width: 80px;">action</td>
    </tr>
    </thead>
    <c:forEach var="car" items="${requestScope.cars}">
        <tr>
            <td>${car.getId()}</td>
            <td><c:out value="${car.getModel()}" /></td>
            <td></td>
            <td><c:out value="${car.getColor()}" /></td>
            <td>
                <form:form action="/users/${car.getUser().getId()}/Car/${car.getId()}" method="DELETE">
                    <input type="submit" value="delete">
                </form:form>
            </td>
            <td>
                <form action="/users/${car.getUser().getId()}/Car/${car.getId()}" method="POST">
                    <input type="submit" value="update">
                </form>
            </td>
            </tr>
    </c:forEach>
</table>
<form:form modelAttribute="car" action="/users/${requestScope.userid}/Car" method="POST">
    <form:input path="model" />
    <form:input path="color" />
    <input type="submit" value="add new car" />
</form:form>
<form action="/users" method="GET">
    <p>При нажатие на кнопку получим всех пользователей:</p>
    <input type="submit" value="Получить всех пользователей" />
</form>
</body>
</html>