<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/style1.css" type="text/css">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="<c:url value="/js/script.js" />" defer></script>
</head>
<body>
<h1>
    Hello
</h1>
<p><a href="/register"> create new user</a></p>
<button class="buttonSignOut" onclick="logout()">Sign out</button>
<p>
    <c:set var="pageCount" scope="session" value="${requestScope.pageCount}"/>
    <c:if test="${pageCount > 0}">
        <c:forEach var="i" begin="1" end="${requestScope.pageCount}">
            <a href="/users/page/${i}">${i} </a>
        </c:forEach>
    </c:if>

</p>
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
    <c:forEach items="${requestScope.users}" var="currentUser">
        <tr>
            <td><c:out value="${currentUser.getName()}"/></td>
            <td><c:out value="${currentUser.getSurname()}"/></td>
            <td><c:out value="${currentUser.getBirthday()}"/></td>
            <td><c:out value="${currentUser.getLogin()}"/></td>
            <td><c:out value="${currentUser.getAboutMe()}"/></td>
            <td><c:out value="${currentUser.getAddress()}"/></td>
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
</body>
</html>