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
<div id="buttons-hbox">
    <button onclick="document.getElementById('id01').style.display='block'" id="singUpButton">Sign Up</button>
    <button onclick="document.getElementById('id03').style.display='block'" id="reg">Create a new user</button>
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
            <span id="uploaderror" class="error" style="color: #f44336"></span>
            <div class="clearfix">
                <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">
                    Cancel
                </button>
                <button type="submit" id="sigUpButtom" class="signupbtn">Sign Up</button>
            </div>
        </div>
    </form>
</div>
<div id="id03" class="modal">
    <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">×</span>
    <form class="modal-content animate" enctype="multipart/form-data" id="regform" onsubmit="return false;">
        <div class="container">
            <label><b>login</b></label>
            <input type="text" name="login" placeholder="Enter login" required/><br/><br/>
            <label><b>password</b></label>
            <input type="password" name="password" placeholder="Enter Password" required/><br/><br/>
            <label><b>name</b></label>
            <input type="text" name="name" placeholder="Enter name" required/><br/><br/>
            <label><b>surname</b></label>
            <input type="text" name="surname" placeholder="Enter surname" required/><br/><br/>
            <label><b>phone(10 char)</b></label>
            <input type="text" name="phone" placeholder="Enter phone" required/><br/><br/>
            <label><b>email</b></label>
            <input type="text" name="email" placeholder="Enter email" required/><br/><br/>
            Select profile file<input type="file" id="myfile" name="myfile"/><br/><br/>
            <span id="createErrorMessage" class="error" style="color: #f44336"></span>
            <div class="clearfix">
                <button type="button" onclick="document.getElementById('id03').style.display='none'" class="cancelbtn">
                    Cancel
                </button>
                <button type="submit" class="signupbtn" id="regButtomnew">Create a new user</button>
            </div>
        </div>
    </form>
</div>
<div id="chat"></div>
<div id="usersStatus"></div>
<div id="users"></div>
</body>
</html>