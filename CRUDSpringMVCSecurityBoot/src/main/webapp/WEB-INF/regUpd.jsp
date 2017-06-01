<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Form Page</title>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>
<body>
<form:form method="PUT" commandName="form" action="/registerUpdate/${form.id}">
    <table>
        <tr>
            <td>Name:</td>
            <td><form:input path="name" value="${form.name}"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><form:input path="phone" value="${form.phone}"/></td>
            <td><form:errors path="phone" cssClass="error"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="email" value="${form.email}"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="hidden" name="_method" value="put">
                <input type="submit" value="update">
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>