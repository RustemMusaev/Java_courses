<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <c:forEach var="i" begin="1" end="${requestScope.listPageCount[1]}">
        <c:out value="${i}" /> <a href="/users/${i}">Test ${i} </a>
        <br />
    </c:forEach>
    <p>
        currentPage: <c:out value="${requestScope.listPageCount[0]}" />
    </p>
    <tr><th>title</th><th>date</th><th>message</th><th>picture</th></tr>
    <c:forEach items="${requestScope.articleDtoList}" var="articleDtoList">
        <tr>
            <td><c:out value="${articleDtoList.title}"/></td>
            <td><c:out value="${articleDtoList.date}"/></td>
            <td><c:out value="${articleDtoList.message}"/></td>
            <td><c:out value="${articleDtoList.picture}"/></td>
        </tr>
    </c:forEach>
</table>
<br />
<br />
    <form:form modelAttribute="articleDto" action="users" method="post">
        <form:input path="title" placeholder="Enter a title" required=""/>
        <form:input path="message" />
        <form:input path="picture" />
        <input type="submit" value="add news" />
    </form:form>
<br />
<br />
<a href="/users/count/10">Test count/10 </a><a href="/users/count/20">Test count/20 </a><a href="/users/count/50">Test count/50 </a>
</body>
</html>
