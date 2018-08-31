
var getUsers = function () {
    $.ajax({
        type: 'POST',
        url: '/user/all',
        headers: {'X-CSRF-Token': token},
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            console.log(error);
        }
    });
};