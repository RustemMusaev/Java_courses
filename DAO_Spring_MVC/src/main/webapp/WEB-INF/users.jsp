<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <tr><th>id</th><th>Name</th><th>Age</th></tr>
    <c:forEach items="${requestScope.users}" var="currentUser">
        <tr>
            <td><c:out value="${currentUser.id}"/></td>
            <td><c:out value="${currentUser.name}"/></td>
            <td><c:out value="${currentUser.age}"/></td>
            <td>
                <form action="/users/${currentUser.getId()}/Car" method="GET">
                    <input type="submit" value="find,add car">
                </form>
            </td>
            <td>
                <form action="/users/${currentUser.getId()}" method="POST">
                    <input type="submit" value="update">
                </form>
            </td>
            <td><form:form action="/users/${currentUser.getId()}" method="DELETE">
                    <input type="submit" value="delete">
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>
    <form:form modelAttribute="user" action="users" method="post">
        <form:input path="id" />
        <form:input path="name" />
        <form:input path="age" />
        <input type="submit" value="add new user" />
    </form:form>

</body>
</html>
