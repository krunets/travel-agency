$(document).ready(function () {
    $("input[placeholder]").each(function () {
        $(this).attr('size', $(this).attr('placeholder').length);
    });
    $('#datepicker').uui_datepicker({todayHighlight: true});
    $('.uui-carousel').carousel({
        interval: 2000
    });

    $('#example').DataTable();
    $('#example_filter').hide();
    $('#example_length').css("width", "100px");
    $('example_length').css("width", "100px");
    $("button[name = 'pagination_button']").click(function () {
        $("input[name='size']").val($("select[name='example_length']").val());
        $("form[name='pagination']").submit();
    })
});
$('#show-comments-button').click(function () {
    $('.comments-list').toggleClass('none');
    if ($('#show-comments-button').val() == "Show comments") {
        $('#show-comments-button').val("Hide comments");
    } else {
        $('#show-comments-button').val("Show comments");
    }
});

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
})