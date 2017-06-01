<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/style1.css" type="text/css">
</head>
<body>
<form:form action="/users/${currentUser.getId()}" method="PUT" modelAttribute="user">
    <table border="0" items="${requestScope.currentUser}" var="currentUser">
        <tr>
            <td>
                <form:label path="name"> Name </form:label>
                <form:input path="name" placeholder="${currentUser.getName()}"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="surname"> Surname </form:label>
                <form:input path="surname" placeholder="${currentUser.getSurname()}"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="birthday"> Date, format mm/DD/yyyy </form:label>
                <form:input path="birthday" value="01/01/1980" placeholder="${currentUser.getBorn()}"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="login"> Login </form:label>
                <form:input path="login" placeholder="${currentUser.getLogin()}"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password"> Password </form:label>
                <form:input path="password" placeholder="*****"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="aboutMe"> About Me </form:label>
                <form:input path="aboutMe" placeholder="${currentUser.getAboutMe()}"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="address"> Address </form:label>
                <form:input path="address" placeholder="${currentUser.getAddress()}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
