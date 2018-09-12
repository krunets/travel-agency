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

/*var addTour = function () {
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
 multipart: true,
 data: tourDTO,
 headers: {'X-CSRF-Token': token},
 success: function (data) {
 console.log(data);
 },
 error: function (error) {
 console.log(error);
 }
 });
 };*/

var getExtension = function (filename) {
    var parts = filename.split('.');
    return parts[parts.length - 1];
};

var isImage = function (filename) {
    var ext = getExtension(filename);
    switch (ext.toLowerCase()) {
        case 'jpg':
        case 'png':
            return true;
    }
    return false;
};

var addTour = function () {
    var file = $('#photo');
    if (!isImage(file.val())) {
        $('#photoExtensionError').removeClass('none');
        return false;
    }
    return true;
};

$('button[data-toggle=modal]').click(function () {
    var tourId = '';
    var reviewId = '';
    var reviewContent = '';
    var username = '';
    var userid = '';

    if (typeof $(this).data('id') !== 'undefined') {
        tourId = $(this).data('tour-id');
        reviewId = $(this).data('id');
        reviewContent = $(this).data('content');
        username = $(this).data('username');
        userid = $(this).data('user-id');
    }

    $('#username').val(username);
    $('#content').val(reviewContent);

    var action = '/review/' + reviewId + '/edit/tour/' + tourId + '/user/' + userid;
    $('#edit-comment-form').attr('action', action);
});