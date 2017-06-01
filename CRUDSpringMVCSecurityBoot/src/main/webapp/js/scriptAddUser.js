$(document).ready(function () {
    $("#nameIsCorrect").on('input', function () {
        if (inputNameValid($("#nameIsCorrect").val())) {
            $("#nameIsCorrect").css('color', 'green');

        }
    });
       function inputNameValid(value) {
         if ((value.length > 1)) {
            return true;
        } else {
            return false;
        }
    }

    function inputPhoneValid(value) {
        var priceFilter = /^([a-zA-Z0-9])+$/;
        if (priceFilter.test(value) && (value.length < 21)) {
            return true;
        } else {
            return false;
        }
    }

    function inputEmailValid(value) {
        var priceFilter = /^([a-zA-Z0-9])+$/;
        if (priceFilter.test(value) && (value.length < 21)) {
            return true;
        } else {
            return false;
        }
    }

 });