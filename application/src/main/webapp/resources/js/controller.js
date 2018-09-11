var getUsers = function () {
    var token = $("meta[name='_csrf']").attr("content");
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

var addTour = function () {
        var tourDTO = {
            photo: $('#photo').val(),
            date: $('#date').val(),
            cost: $('#cost').val(),
            tourType: $('#tourType').val(),
            description: $('#description').val(),
            duration: $('#duration').val()
        };
        console.log(tourDTO);
        var token = $("meta[name='_csrf']").attr("content");
        $.ajax({
            type: 'POST',
            url: '/tour/add',
            accept: "application/json",
            contentType: "application/json",
            data: tourDTO,
            dataType: "json",
            headers: {'X-CSRF-Token': token},
            success: function (data) {
                console.log(data);
            },
            error: function (error) {
                console.log(error);
            }
        });
    }
    ;