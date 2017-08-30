<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>MyApp</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<h1>
<span class="succes">
    <c:if test="${not empty succesMessage}">
        Message : ${succesMessage}
    </c:if>
</span>
</h1>
<a class="returnIndexPage" href="/">return Login Form</a>
</body>

</html>