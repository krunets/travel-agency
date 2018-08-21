var login = function () {
    console.log($("#loginForm").serialize());
    $.ajax({
        type: 'POST',
        url: '/login',
        contentType: "application/json",
        data: $("#loginForm").serialize(),
        dataType: "json",
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            console.log(error);
        }
    });

};