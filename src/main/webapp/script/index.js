"use strict";

$(document).ready(() => {
    $("#login_form").submit((e) => {
        e.preventDefault();

        let userName = $("#login_form input[type=text]").val();
        let password = $("#login_form input[type=password]").val();

        $.post("/login", {userName, password}, (response) => {
            if (response === "Success") {
                $("#login_form").hide();
                $("#confirm_form").show();
            } else {
                alert("WTF?")
            }
        });
    });

    $("#confirm_login_form").submit((e) => {
        e.preventDefault();

        let code = $("#confirm_login_form input").val();

        $.post("/login-confirm", {code}, (response) => {
            alert(JSON.stringify(response));
        });
    });
});
