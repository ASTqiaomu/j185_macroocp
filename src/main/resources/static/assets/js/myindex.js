var map = null;
$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "getloginuser",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: null,
        async: false,
        success: function (msg) {
            map = msg;
            $('#login-truename')[0].innerHTML=map["login_truename"];
            $('#login_truename')[0].innerHTML=map["login_truename"];
            $('#login_rName')[0].innerHTML=map["login_rname"];
        }
    });
});

function logout() {
    $.ajax({
        type: "POST",
        url: "logout",
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        data: null,
        async: false,
        success: function (msg) {
            if (msg === 0){
                window.location.href="login.html";
            } else {
                alert("退出时遇到问题，请查看后台");
            }
        }
    });
}