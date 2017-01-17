<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <title>User room</title>
</head>
<body>
<table style=" border-style: solid; border-width:1px; width: 600px; border-collapse: collapse;">
    <thead>
    <tr style="background-color: gray;">
        <td style="width: 30px;">id</td>
        <td style="width: 80px;">name</td>
        <td style="width: 80px;">age</td>
        <td></td>
        <td style="width: 80px;">action</td>
        <td style="width: 80px;">action</td>
    </tr>
    </thead>
    <c:choose>
        <c:when test="${namedelete!=null}">
            You are delete user = ${namedelete}
            <br />
        </c:when>
    </c:choose>
    <c:choose>
    <c:when test="${idupdate!=null}">
        You are update user id = ${idupdate}
        <br />
    </c:when>
    </c:choose>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.getId()}</td>
            <td><c:out value="${user.getName()}" /></td>
            <td></td>
            <td><c:out value="${user.getAge()}" /></td>
            <td>
                <a href="User?id=${user.getId()}">delete</a>
            </td>
            <td>
                <form action="CarAdd.jsp?userid=${user.getId()}" method="POST">
                    <input type="submit" value="add car">
                </form>
            </td>
            <td>
                <form action="User/${user.getId()}/Car" method="GET">
                    <input type="submit" value="find all car">
                </form>
            </td>
            <td>
                <form action="UserUpdate.jsp?id=${user.getId()}&name=${user.getName()}&age=${user.getAge()}" method="POST">
                    <input type="submit" value="update">
                </form>
            </td>
            <td>
                <form action="User/${user.getId()}" method="POST">
                    <input type="hidden" name="_method" value="delete">
                    <input type="submit" value="delete">
                </form>
            </td>
        </tr>
    </c:forEach>
    <td>
        <form action="/UserAdd.jsp" method="POST">
            <input type="submit" value="create user" />
        </form>
    </td>
</table>
</body>
</html>