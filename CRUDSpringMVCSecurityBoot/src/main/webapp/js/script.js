function logout() {
    $.ajax({
        url: "/logout",
        type: "GET",
        headers: {
            "Auth-Token": localStorage.getItem("token"),
        },
        success: function (data) {
            console.log("data = " + data);
            deleteAllCookies();
            location.href = "/";
        },
        error: function (e) {
            console.log("data error = " + data);
        }
    });
}
function login() {
    var data = {};
    data["login"] = $("#login").val();
    data["password"] = $("#password").val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/login",
        data: JSON.stringify(data),
        dataType: 'json',
        xhrFields: {withCredentials: true},
        timeout: 600000,
        success: function (data, textStatus, response) {
            var cookietoSet = response.getResponseHeader("Auth-Token");
            if (cookietoSet) {
                setCookie("Auth-Token", cookietoSet);
            }
            cookietoSet = null;
            location.href = "/users";
        },
        error: function (e) {
            $("#uploaderror").text("incorrect login or password");
            deleteAllCookies();
        }
    });
}
$("#signIn").click(function () {
    login();
});
$("#reset").click(function () {
    $("#uploaderror").empty();
});
function isLogin() {
    var token = getCookie("Auth-Token");
    if (token != "") {
        console.log("send cockie")
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
