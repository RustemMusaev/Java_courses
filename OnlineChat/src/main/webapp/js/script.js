// Get the modal
var modal1 = document.getElementById('id01');
var modal2 = document.getElementById('id02');
var stompClient;
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal1) {
        modal1.style.display = "none";
    }
    if (event.target == modal2) {
        modal2.style.display = "none";
    }
}
function connect() {
    stompClient = null;
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({"Auth-Token": localStorage.getItem("token")}, function(frame) {
        console.log('Connected: /topic/messages ' + frame);
        stompClient.subscribe("/topic/messages", function(messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body));
        });
        stompClient.subscribe("/topic/users", function(messageOutput) {
            console.log("user ---- "+messageOutput);
            showUsersStatus(JSON.parse(messageOutput.body));
        });
    });
    console.log("connect done");
}
function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
function sendMessage() {
    var textMessage = document.getElementById("textMessages").value;
    console.log(textMessage);
    $.ajax({
        url: "/chats/messages",
        type: "POST",
        headers : {
            "Auth-Token": localStorage.getItem("token"),
            'Accept': 'application/json',
            'Content-Type': 'application/json' },
        dataType: "json",
        data: JSON.stringify({'message':textMessage}),
    });
}
function sendStatus(status) {
    console.log("status " + status);
    $.ajax({
        url: "/chats/users",
        type: "POST",
        headers : {
            "Auth-Token": localStorage.getItem("token"),
            'Accept': 'application/json',
            'Content-Type': 'application/json' },
        dataType: "json",
        data: JSON.stringify({'status':status}),
    });
    console.log("send status done");
}
function showMessageOutput(messageOutput) {
    console.log(messageOutput);
    var text="\n "+messageOutput.login+" say: "+messageOutput.text;
    $("textarea#ExampleMessage").append(text);
    document.getElementById('ExampleMessage').scrollTop = 9999;
}
function showUsersStatus(userStatus) {
    console.log(userStatus);
    $("#usersStatus").empty();
    if(userStatus != undefined) {
        for (var i = 0; i < userStatus.length; i++) {
            var text = "\n " + userStatus[i].login + " say: " + userStatus[i].name;
            console.log(text);
            var login = userStatus[i].login;
            $("#usersStatus").append("<div style='border: double' id='user'> user "+login +" online "+
                "<div class='span'>" +
                "<p>"+userStatus[i].name + " name</p>" +
                "<p>"+userStatus[i].surname + " surname</p>" +
                "<p>"+userStatus[i].email + " email</p>" +
                "<p>"+userStatus[i].photo + " photo</p>" +
                "<p>"+userStatus[i].phone + " phone</p>"+
                "</div></div><br>");
            $(".span").hide();
        }
    }
}
function login() {
    var data = {};
  //  checkCookie();
    data["username"] = $("#username").val();
    data["pwd"] = $("#pwd").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/login1",
        data: JSON.stringify(data),
        dataType: 'json',
        xhrFields: { withCredentials: true },
        timeout: 600000,
        success: function (data, textStatus, response) {
            console.log("DONE");
            console.log(data);
            console.log(textStatus);
            console.log(response);
            document.getElementById('id01').style.display='none';
            $("#singUpButton").hide();
            $("#createUserButton").hide();
            $("#buttons-hbox").append("<button id='signOut' onclick='logout()'>Sign out</button>");
            $("#chat").append("<div><textarea rows='10' cols='50' id='ExampleMessage' >"+data["username"]+" insert chat id = </textarea>" +
                "<br /><button onclick='showAllMessages()'>Show last Messages</button>" +
                "<br /><button onclick='clearMessages()'>Clear Messages</button>"+
                "<br /><input type='text' id='textMessages' placeholder='Write a message.....'/>"+
                "<button onclick='sendMessage()'>Send message</button></div>");

            var cookietoSet=response.getResponseHeader("Auth-Token");
            console.log("1");
            console.log(cookietoSet);
            console.log("1");
            if(cookietoSet) {
                setCookie("Auth-Token",cookietoSet);
            }
            cookietoSet = null;
            console.log("login done");
            connect();
            sendStatus("online");
            showUsersStatus();
        },
        error: function (e) {
            //   document.getElementById('id01').style.display='none';
            console.log("ERROR");
        }
    });
}
$("#sigUpButtom").click(function() {
    login();
});
function logout() {
    sendStatus("offline");
    console.log("bye");
    deleteAllCookies();
    $("#singUpButton").show();
    $("#createUserButton").show();
    $("#signOut").remove();
    $("#chat").empty();
    disconnect();
    $("#usersStatus").empty();
}
$("#userRegistration").submit(function()  {
    document.getElementById('id02').style.display='none';
});
function isLogin() {
    var username = getCookie("Auth-Token");
    if (username != "") {
        login();
    }
}
function checkCookie() {
    var username = getCookie("Auth-Token");
    if (username != "") {
        alert("Welcome again " + username);
    } else {
        username = prompt("Please enter your name:", "");
        if (username != "" && username != null) {
            setCookie("username", username, 365);
        }
    }
}
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
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
    d.setTime(d.getTime() + (60*60*1000));
    var expires = "expires="+ d.toUTCString();
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
        url: "/messages",
        type: "GET",
        headers : {
            'Accept': 'application/json',
            'Content-Type': 'application/json' },
        dataType: "json",
        success: function (data, textStatus, response) {
            console.log("DONE");
            console.log(data);
            $("#ExampleMessage").empty();
            for(var i = 0; i < data.length; i++){
                var text="\n "+data[i].login+" say: "+data[i].text;
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
$('#usersStatus').on('mouseover', '#user', function() {
    $('.span').show();
});
$('#usersStatus').on('mouseout', '#user', function() {
    $('.span').hide();
});