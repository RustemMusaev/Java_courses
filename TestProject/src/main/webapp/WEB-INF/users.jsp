<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>News</title>
        <meta charset="utf-8">
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="<c:url value="/WEB-INF/script.js"/>"></script>
    </head>
<body>
    <a href="/users/count/10">   - select 10 publication from page -   </a>  ||
        <a href="/users/count/20">   - select 20 publication from page -    </a>  ||
            <a href="/users/count/50">   - select 50 publication from page -    </a>
    <br /><br /><hr>
    Select page :
    <c:forEach var="i" begin="1" end="${requestScope.listPageCount[1]}">
        <a href="/users/${i}">Page №${i} </a>
    </c:forEach>
    <p>currentPage: <c:out value="${requestScope.listPageCount[0]}" /></p>
    <hr>
    <p>Configurae template :</p>
    <label><input type='checkbox' id="checkTitle" checked>Checkbox</label>
    <label><input type="checkbox" id="checkDate" value="Date" checked> Date</label>
    <label><input type="checkbox" id="checkMessage" value="Message" checked> Message</label>
    <label><input type="checkbox" id="checkImage" value="Image" checked> Image</label>
    <br><hr>
    <table border="1">
        <tr>
            <th class="td_title">title</th>
            <th class="td_date">date</th>
            <th class="td_message">message</th>
        </tr>
        <c:forEach items="${requestScope.articleDtoList}" var="articleDtoList">
            <tr>
                <td class="td_title" height="100" width="50"><c:out value="${articleDtoList.title}"/></td>
                <td class="td_date" height="100" width="100"><c:out value="${articleDtoList.date}"/></td>
                <td class="td_message" height="100" width="242"><c:out value="${articleDtoList.message}"/>
                    <c:choose>
                        <c:when test="${articleDtoList.picture != null}">
                            <img class="td_image" src="/getImage/${articleDtoList.picture}" alt="/getImage/${articleDtoList.picture}.jpg" height="60" width="60">
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br />
    <br />
    <hr>
    <br />
    <br />
    <form action="users" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input type="text" name="title" charset="utf-8" required="required" placeholder="Enter a title"/>
        <input type="text" name="message" charset="utf-8" required="required" placeholder="Enter a title"/>
        <input type="submit" value="add news" />
    </form>
</body>
</html>