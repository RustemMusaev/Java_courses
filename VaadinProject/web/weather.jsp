<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="./style.css" />
    <link rel="stylesheet" type="text/css" href="WEB-INF/style.css" />
    <link rel="stylesheet" type="text/css" href="/WEB-INF/style.css" />
    <title>Weather</title>
</head>
<body>
<br><hr>
<div>
    <div class="outset" items="${requestScope.moscow}" var="moscow">
        Moscow weather ${pageContext.request.contextPath}
        <p>Current Temp<c:out value="${moscow.currentTemp}"/> C</p>
        <p>Tomorrow Temp<c:out value="${moscow.nextTemp}"/> C</p>
    </div>
    <div class="outset" items="${requestScope.novosibirsk}" var="novosibirsk">
        Novosibirsk weather
        <p>Current Temp<c:out value="${novosibirsk.currentTemp}"/> C</p>
        <p>Tomorrow Temp<c:out value="${novosibirsk.nextTemp}"/> C</p>
    </div>
    <div class="outset" items="${requestScope.stPetersburg}" var="stPetersburg">
        StPetersburg weather
        <p>Current Temp<c:out value="${stPetersburg.currentTemp}"/> C</p>
        <p>Tomorrow Temp<c:out value="${stPetersburg.nextTemp}"/> C</p>
    </div>
    <div class="outset">
        <div id="USD">Доллар США $ — ${requestScope.getUSD} руб.</div>
        <div id="EUR">Евро € — ${requestScope.getEUR} руб.</div>
    </div>
    <div class="outset">
        <div id="date">date  — ${requestScope.dateFormat}</div>
        <div id="ipadress">ipadress — ${requestScope.ipAddress}</div>
    </div>
</div>
<br><hr>
</body>
</html>
