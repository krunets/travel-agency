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

$("input[placeholder]").each(function () {
    $(this).attr('size', $(this).attr('placeholder').length);
});
$('.custom-datepicker').uui_datepicker({todayHighlight: true});

$(document).ready(function () {
    $('#example').DataTable();
    $('#example_filter').hide();
    $('#example_length').css("width", "100px");
    $('example_length').css("width", "100px");
    $("button[name = 'pagination_button']").click(function () {
        $("input[name='size']").val($("select[name='example_length']").val());
        $("form[name='pagination']").submit();
    })
});