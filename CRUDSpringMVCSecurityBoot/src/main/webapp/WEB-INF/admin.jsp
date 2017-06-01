<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/style1.css" type="text/css">
    </head>
<body>
<h1>
    Hello
</h1>
<p><a href="/register"> create new form</a></p>

<hr>
<table>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>date of birthday</th>
        <th>login</th>
        <th>aboutMe</th>
        <th>address</th>
    </tr>
    <c:forEach items="${requestScope.forms}" var="forms">
        <tr>
            <td><c:out value="${forms.getId()}"/></td>
            <td><c:out value="${forms.getName()}"/></td>
            <td><c:out value="${forms.getPhone()}"/></td>
            <td><c:out value="${forms.getEmail()}"/></td>
            <td>
                <form action="/forms/${forms.getId()}" method="GET">
                    <input type="submit" value="update">
                </form>
            </td>
            <td>
                <form:form action="/forms/${forms.getId()}" method="DELETE">
                    <input type="hidden" name="_method" value="delete">
                    <input type="submit" value="delete">
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>