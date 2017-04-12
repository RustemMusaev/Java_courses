var modal1 = document.getElementById('id01');
var modal3 = document.getElementById('id03');
var stompClient;
var users;
var statusBar = $('#users');
window.onclick = function (event) {
    if (event.target == modal1) {
        modal1.style.display = "none";
    }
    if (event.target == modal3) {
        modal3.style.display = "none";
    }
}
function connect() {
    stompClient = null;
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({"Auth-Token": localStorage.getItem("token")}, function (frame) {
        console.log('Connected: /topic/messages ' + frame);
        stompClient.subscribe("/topic/messages", function (messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body));
        });
        stompClient.subscribe("/topic/users", function (messageOutput) {
            console.log("user ---- " + messageOutput);
            showUsersStatus(JSON.parse(messageOutput.body));
        });
    });
    console.log("connect done");
}
function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
function sendMessage() {
    var textMessage = document.getElementById("textMessages").value;
    if (textMessage != undefined) {
        $.ajax({
            url: "/chats/messages",
            type: "POST",
            headers: {
                "Auth-Token": localStorage.getItem("token"),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: "json",
            data: JSON.stringify({'message': textMessage}),
        });
    } else {
        document.getElementById("textMessages");
    }
}
function sendStatus(status) {
    $.ajax({
        url: "/chats/users",
        type: "POST",
        headers: {
            "Auth-Token": localStorage.getItem("token"),
         },
        data: {'STATUS': status},
        success: function (data) {
            console.log("data = " + data);
            if (status == "OFFLINE") {
                console.log("bye status");
                deleteAllCookies();
                $("#singUpButton").show();
                $("#createUserButton").show();
                $("#signOut").remove();
                $("#chat").empty();
                disconnect();
                $("#usersStatus").empty();
            }
        },
        error: function (e) {
            console.log("data error = " + data);
        }
    });
 }
function showMessageOutput(messageOutput) {
    var text = "<p><span class='author'>"+messageOutput.date+" "+ messageOutput.login + "</span> say: " + messageOutput.text + "</p>";
    $("#ExampleMessage").append(text);
    document.getElementById('ExampleMessage').scrollTop = 9999;
}
function showUsersStatus(userStatus) {
    $("#usersStatus").empty();
    if (userStatus != undefined) {
        users = userStatus;
        for (var i = 0; i < userStatus.length; i++) {
            var login = userStatus[i].login;
            $("#usersStatus").append("<div onmouseover='showUser(i)' style='border: double' data-id='" + i + "' class='showUsers' data-id='i'> user " + login + " online " +
                "<div class='span"+i+"'>" +
                "<p>" + userStatus[i].name + " name</p>" +
                "<p>" + userStatus[i].surname + " surname</p>" +
                "<p>" + userStatus[i].phone + " phone</p>" +
                "<p>" + userStatus[i].email + " email</p>" +
                "<p>" + userStatus[i].surname + " surname</p>" +
                "<img src='/OnlineChat/getImage/"+userStatus[i].photo+".jpg' alt='/OnlineChat/getImage/"+userStatus[i].photo+".jpg'>"+
                "</div></div><br>");
            //$(".span"+i+"").hide();
        }
    }
}
function showUser(i) {
    $(".span"+i+"").show();
}
function login() {
    var data = {};
    data["username"] = $("#username").val();
    data["pwd"] = $("#pwd").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/OnlineChat/login",
        data: JSON.stringify(data),
        dataType: 'json',
        xhrFields: {withCredentials: true},
        timeout: 600000,
        success: function (data, textStatus, response) {
            console.log("DONE");
            console.log(data);
            console.log(textStatus);
            console.log(response);
            modal1.style.display = 'none';
            $("#singUpButton").hide();
            $("#createUserButton").hide();
            $("#buttons-hbox").append("<button id='signOut' onclick='logout()'>Sign out</button>");
            $("#chat").append("<div><div class='showMessages' id='ExampleMessage' ><p>" + data["username"] + " login chat</p></div>" +
                "<br /><button onclick='showAllMessages()'>Show last Messages</button>" +
                "<br /><button onclick='clearMessages()'>Clear Messages</button>" +
                "<br /><input type='text' id='textMessages' placeholder='Write a message.....' required/>" +
                "<button onclick='sendMessage()'>Send message</button></div>");

            var cookietoSet = response.getResponseHeader("Auth-Token");
            if (cookietoSet) {
                setCookie("Auth-Token", cookietoSet);
            }
            cookietoSet = null;
            connect();
            sendStatus("ONLINE");
            showUsersStatus();
        },
        error: function (e) {
            $("#uploaderror").text("incorrect login or password");
          }
    });
}
$("#sigUpButtom").click(function () {
    login();
});
function logout() {
    sendStatus("OFFLINE");
}
function isLogin() {
    var username = getCookie("Auth-Token");
    if (username != "") {
        login();
    }
}
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function setCookie(cname, cvalue) {
    var d = new Date();
    d.setTime(d.getTime() + (60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function deleteAllCookies() {
    var cookies = document.cookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
}
function showAllMessages() {
    $.ajax({
        url: "/OnlineChat/messages",
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success: function (data, textStatus, response) {
            console.log("DONE");
            console.log(data);
            $("#ExampleMessage").empty();
            for (var i = 0; i < data.length; i++) {
                var text = "<p><span class='author'> "+data[i].login +" " + data[i].login + "</span> say: " + data[i].text + "</p>";
                $("#ExampleMessage").append(text);
            }
            document.getElementById('ExampleMessage').scrollTop = 9999;
        },
        error: function (e) {
            console.log("ERROR");
        }
    });
}
function clearMessages() {
    $("#ExampleMessage").empty();
}
$("#regform").submit(function (event) {
    if (validate()) {
        var form = $('#regform')[0];
        var oMyForm = new FormData(form);
        oMyForm.append("myfile", $('input[type=file]')[0].files[0]);
        $.ajax({
            url: '/OnlineChat/registration',
            data: oMyForm,
            dataType: 'text',
            processData: false,
            contentType: false,
            type: 'POST',
            success: function (data, textStatus, response) {
                modal3.style.display = 'none';
                console.log("done create user");
                alert(data);
            },
            error: function (data, textStatus, response) {
                $("#createErrorMessage").text(data.responseText);
            }
        });
    }
});
function validate() {
    var email = $('#id03').find('input[name="email"]').val();
    var phone = $('#id03').find('input[name="phone"]').val();
    var emailFilter = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/;
    var phoneFilter = /^\d{10}$/;

    if (!emailFilter.test(email)) {
        alert("email incorrect = " + email);
        return false;
    }
    if (!phoneFilter.test(phone)) {
        alert("phone incorrect = " + phone);
        return false;
    }
    return true;
}
$("input[type=file]").change(function () {
    var FileUploadPath = $(this).val();
    var ext = FileUploadPath.split('.').pop().toLowerCase();
    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
        alert('invalid extension!');
        document.getElementById("myfile").value = "";
    }
});