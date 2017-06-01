<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Form Page</title>
    <style>
        .error {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>
<body>
<form:form method="POST" commandName="user" action="/users/update/${user.id}">
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" value="${user.name}"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td><form:input path="surname" value="${user.surname}"/></td>
            <td><form:errors path="surname" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <fmt:formatDate value="${user.birthday}" type="date" pattern="dd/MM/yyyy" var="userDate"/>
            <td><form:input path="birthday" value="${userDate}"/></td>
            <td><form:errors path="birthday" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><form:input path="login" value="${user.login}"/></td>
            <td><form:errors path="login" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password path="password" value="${user.password}"/></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>

        <tr>
            <td>About Me:</td>
            <td><form:input path="aboutMe" value="${user.aboutMe}"/></td>
            <td><form:errors path="aboutMe" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><form:input path="address" value="${user.address}"/></td>
            <td><form:errors path="address" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Update"></td>
        </tr>
    </table>
</form:form>

</body>
</html>