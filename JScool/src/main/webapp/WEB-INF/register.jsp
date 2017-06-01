<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
<form:form method="POST" commandName="user" action="register">
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" placeholder="Name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td><form:input path="surname" placeholder="Surname"/></td>
            <td><form:errors path="surname" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td><form:input path="birthday" placeholder="dd/MM/yyyy"/></td>
            <td><form:errors path="birthday" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><form:input path="login" placeholder="Login"/></td>
            <td><form:errors path="login" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password path="password" placeholder="Password"/></td>
            <td><form:errors path="password" cssClass="error"/></td>
        </tr>

        <tr>
            <td>About Me:</td>
            <td><form:input path="aboutMe" placeholder="About Me"/></td>
            <td><form:errors path="aboutMe" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Address:</td>
            <td><form:input path="address" placeholder="Address"/></td>
            <td><form:errors path="address" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Register"></td>
        </tr>
    </table>
</form:form>

</body>
</html>