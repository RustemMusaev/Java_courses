$("input[id='checkTitle']").change(function() {
    if(this.checked) {
        $(".td_title").show();
    } else {
        $(".td_title").hide();
    }
});
$("input[id='checkDate']").change(function() {
    if(this.checked) {
        $(".td_date").show();
    } else {
        $(".td_date").hide();
    }
});
$("input[id='checkMessage']").change(function() {
    if(this.checked) {
        $(".td_message").show();
    } else {
        $(".td_message").hide();
    }
});
$("input[id='checkImage']").change(function() {
    if(this.checked) {
        $(".td_image").show();
    } else {
        $(".td_image").hide();
    }
});