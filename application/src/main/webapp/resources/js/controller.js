var userDTO = {
    login: "",
    password: ""
};

var logIn = function () {
    userDTO.login = $('#login').val();
    userDTO.password = $('#password').val();
    $.ajax({
        type: 'POST',
        url: '/login',
        accept: "application/json",
        contentType: "application/json",
        data: JSON.stringify(userDTO),
        dataType: "json",
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            console.log(error);
        }
    });

};

var registration = function () {

};