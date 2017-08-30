<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>MyApp</title>
    <link href="../css/style.css" rel="stylesheet">
</head>
<body>
<h1> Form authentication </h1>
<form action="/login" method="POST">
    <div class="container">
        <label><b>Login</b></label>
        <input type="text" placeholder="Enter Username" name="login" required>
        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="pwd" required>
        <button type="submit">Login</button>
    </div>
</form>
<span class="error">
    <c:if test="${not empty errors}">
        Error : ${errors}
    </c:if>
</span>
</span>
</body>

</html>