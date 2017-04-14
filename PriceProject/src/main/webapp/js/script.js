$(document).ready(function() {
$("#requestForm").submit(function (event) {
    var data = {};
    data["category"] = $("#category").val();
    data["product"] = $("#product").val();
    data["min_price"] = $("#min_price").val();
    data["max_price"] = $("#max_price").val();
    if(formValid()) {
        $("#errorMessage").empty();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/price",
            data: JSON.stringify(data),
            dataType: 'json',
            xhrFields: {withCredentials: true},
            timeout: 600000,
            success: function (data, textStatus, response) {
                $("#table").empty();
                $("#table").append("<tr><th>Product name</th><th>Category name</th><th>Price</th>"+
                    "</tr><tr><td>"+data[0].name+"</td><td>"+data[0].categoryName+"</td><td>"+data[0].price+" rub </td></tr>");
                for (var i = 1; i<data.length; i++){
                    $("#table").append("<tr><td>"+data[i].name+"</td><td>"+data[i].categoryName+"</td><td>"+data[i].price+" rub </td></tr>");
                 }
            },
            error: function (data) {
                $("#table").empty();
                $("#errorMessage").text(data.responseText);
            }
        });
    } else {
        $("#table").empty();
        $("#errorMessage").text("form is incorrect");
    }
});
$("#min_price").on('input', function() {
    if (inputValid($("#min_price").val())) {
        $("#min_price").css('color','green');
    } else {
        $("#min_price").css('color','red');
    }
});
$("#max_price").on('input', function() {
    if (inputValid($("#max_price").val())) {
        $("#max_price").css('color','green');
    } else {
        $("#max_price").css('color','red');
    }
});
function inputValid(value) {
    var priceFilter =/^[+-]?\d+(\.\d+)?$/;
    if (priceFilter.test(value) || ($.trim(value) == '')) {
        return true;
    } else {
        return false;
    }
}
function formValid() {
    if (!inputValid($("#min_price").val()) || !inputValid($("#max_price").val())) {
        return false;
    } else {
        if (($.trim($("#min_price").val()) != '') && ($.trim($("#max_price").val()) != '') && $("#min_price").val() >= $("#max_price").val()) {
            return false;
        }
    }
    if ($("#category").val()!="" || $("#product").val()!="" || $("#min_price").val()!="" || $("#max_price").val()!="") {
        return true;
    } else {
        return false
    }
}
});