<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Online Chat</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/styleReg.css" />" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="<c:url value="/js/script.js" />" defer></script>
</head>
<body onload="isLogin()">
<h2>Online Chat</h2>
<br>
<div class="parent">
    <div class="block">
        <form onsubmit="return false">
            <fieldset>
                <legend>Please, login for continue..</legend>
                <p>
                    <span>login: </span>
                </p>
                <p>
                    <input id="login" type="text" required/><br/>
                </p>
                <p>
                    <span>password: </span>
                </p>
                <p>
                    <input id="password" type="password" required/><br/>
                </p>
                <span id="uploaderror" style="color: red"></span>
                <p>
                    <input type="submit" id="signIn" value="Submit"/>
                    <input type="reset" id="reset">
                </p>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>