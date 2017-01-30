
const host="http://localhost:8080/";



function logIn() {
    var login = $("#exampleInputLogin").val();
    var password = $("#exampleInputPassword").val();

    $.ajax({
        type: 'POST',
        url: host + 'login',
        headers:{"login" : login,
                 "password":password},
        contentType:"application/json",
        
    });
}

function signUp() {
    var login = $("#registerLogin").val();
    var password = $("#registerPassword").val();
    var age = $("#registerAge").val();
    var name = $("#registerName").val();
    var person = {
        "login" : login,
        "password" : password,
        "age" : age,
        "name" : name
    }
    $.ajax({
        type: 'POST',
        url: host + 'register',
        contentType:"application/json",
        data:JSON.stringify(person),
    });
}

