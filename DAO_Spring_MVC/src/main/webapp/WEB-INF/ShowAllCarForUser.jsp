<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <c:forEach var="car" items="${carList}">
        <tr>
            <td>${car.getId()}</td>
            <td><c:out value="${car.getModel()}" /></td>
            <td></td>
            <td><c:out value="${car.getColor()}" /></td>
            <td>
                <form action="/User/${car.getUser().getId()}/Car/${car.getId()}" method="POST">
                    <input type="hidden" name="_method" value="delete">
                    <input type="submit" value="delete">
                </form>
            </td>
            <td>
                <form action="/WEB-INF/CarUpdate.jsp?carid=${car.getId()}&model=${car.getModel()}&color=${car.getColor()}&userid=${car.getUser().getId()}" method="POST">
                    <input type="submit" value="update">
                </form>
            </td>
            </tr>
    </c:forEach>
</table>
<form action="/User" method="GET">
    <p>При нажатие на кнопку получим всех пользователей:</p>
    <input type="submit" value="Получить всех пользователей" />
</form>
</body>
</html>