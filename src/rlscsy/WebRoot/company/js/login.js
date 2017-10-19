$(document).ready(function() {
    $(".validation-summary-errors").hide();
});

function loginReturn(url) {
    location = url;
}

function loginFailure(err) {
    var i = decodeURIComponent(err.getResponseHeader("X-Error-Message"));
    $("#vcode").val("");
    $("#password").val("");
    $(".code_img").click();
    $(".validation-summary-errors").html("<ul><li>" + i + "</li></ul>");
    $(".validation-summary-errors").show();
}