<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Online Chat</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/sockjs-client/0.3.4/sockjs.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.2/stomp.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <script src="<c:url value="/js/script.js" />" defer></script>
</head>
<body onload="isLogin()">
<h2>Online Chat</h2>
<button type="button" class="btn btn-default" data-container="body" data-toggle="popover" data-placement="right" data-content="Vivamus sagittis lacus vel augue laoreet rutrum faucibus.">
    Popover on right
</button>
<div id="buttons-hbox">
    <button onclick="document.getElementById('id01').style.display='block'" id="singUpButton">Sign Up</button>
    <button onclick="document.getElementById('id02').style.display='block'" id="createUserButton">Create a new user</button>
</div>
<br>
<div id="id01" class="modal">
    <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">×</span>
    <form class="modal-content animate" id="singUpForm" onsubmit="return false;">
        <div class="container">
            <label><b>login</b></label>
            <input type="text" placeholder="Enter Email" id="username" name="username" required>

            <label><b>Password</b></label>
            <input type="password" placeholder="Enter Password" id="pwd" name="pwd" required>

            <div class="clearfix">
                <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
                <button type="submit" id="sigUpButtom" class="signupbtn">Sign Up</button>
            </div>
        </div>
    </form>
</div>
<div id="id02" class="modal">
    <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">×</span>
        <form:form modelAttribute="userRegistration" action="/registration" method="post" class="modal-content animate" enctype="multipart/form-data" id="userRegistration">
        <div class="container">
            <form:label path="login">login</form:label>
            <form:input required="login" path="login" />
            <form:label path="password">password</form:label>
            <form:input required="password" path="password" />
            <form:label path="name">name</form:label>
            <form:input required="name" path="name" />
            <form:label path="surname">surname</form:label>
            <form:input required="surname" path="surname" />
            <form:label path="phone">phone</form:label>
            <form:input required="phone" path="phone" />
            <form:label path="email">email</form:label>
            <form:input required="email" path="email" />
            Select profile file<form:input type="file" path="file" />
            <div class="clearfix">
                <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
                <button type="submit" class="signupbtn" id="regButtom">Create a new user</button>
            </div>
        </div>
    </form:form>
</div>
<div id="chat"></div>
<div id="usersStatus"></div>
</body>
</html>