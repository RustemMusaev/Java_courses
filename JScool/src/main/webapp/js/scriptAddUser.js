$(document).ready(function () {
    $("#addUser").submit(function (event) {
        var data = {};
        data["name"] = $("#name").val();
        data["surname"] = $("#surname").val();
        data["born"] = new Date($("#born").val());
        data["login"] = $("#login").val();
        data["password"] = $("#password").val();
        data["aboutMe"] = $("#aboutMe").val();
        data["address"] = $("#address").val();
        console.log("before send");
        if (formValid()) {
            $("#errorMessage").empty();
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/addUser",
                data: JSON.stringify(data),
                dataType: 'json',
                xhrFields: {withCredentials: true},
                timeout: 600000,
                success: function (data, textStatus, response) {
                    location.href = "/users";
                },
                error: function (data) {
                    $("#errorMessage").text(data.responseText);
                }
            });
        } else {
            $("#errorMessage").text("form is incorrect");
        }
    });
    $("#name").on('input', function () {
        if (inputCharValid($("#name").val())) {
            $("#name").css('color', 'green');
        } else {
            $("#name").css('color', 'red');
        }
    });
    $("#surname").on('input', function () {
        if (inputCharValid($("#surname").val())) {
            $("#surname").css('color', 'green');
        } else {
            $("#surname").css('color', 'red');
        }
    });
    $("#login").on('input', function () {
        if (inputLoginValid($("#login").val())) {
            $("#login").css('color', 'green');
        } else {
            $("#login").css('color', 'red');
        }
    });
    $("#aboutMe").on('input', function () {
        if ($("#aboutMe").val().length < 101) {
            $("#aboutMe").css('color', 'green');
        } else {
            $("#aboutMe").css('color', 'red');
        }
    });
    $("#address").on('input', function () {
        if ($("#address").val().length < 51) {
            $("#address").css('color', 'green');
        } else {
            $("#address").css('color', 'red');
        }
    });
    $("#born").on('input', function () {
        var date = new Date($("#born").val());
        console.log(date)
    });
    function inputCharValid(value) {
        var priceFilter = /^([a-zA-Z])+$/;
        if (priceFilter.test(value) && (value.length < 21)) {
            return true;
        } else {
            return false;
        }
    }

    function inputLoginValid(value) {
        var priceFilter = /^([a-zA-Z0-9])+$/;
        if (priceFilter.test(value) && (value.length < 21)) {
            return true;
        } else {
            return false;
        }
    }

    function formValid() {
        if (inputCharValid($("#name").val()) && inputCharValid($("#surname").val())
            && inputLoginValid($("#login").val())) {
            console.log("true");
            return true;
        } else {
            console.log("false");
            return false;
        }
    }
});