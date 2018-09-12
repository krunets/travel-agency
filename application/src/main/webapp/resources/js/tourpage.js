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

$('button[data-toggle=edit-modal]').click(function () {
    var tourDuration = '';
    var tourDescription = '';
    var tourCost = '';
    var tourDate = '';
    var tourCountry = '';
    var tourType = '';

    var tourId = '';
    var reviewId = '';
    var reviewContent = '';
    var username = '';
    var userid = '';

    if (typeof $(this).data('id') !== 'undefined') {
        tourDuration = $(this).data('edit-duration');
        tourDescription = $(this).data('edit-description');
        tourCost = $(this).data('edit-cost');
        tourDate = $(this).data('edit-date');
        tourCountry = $(this).data('edit-country');
        tourType = $(this).data('edit-tourType');
    };
    $('#edit-duration').val(tourDuration);
    $('#edit-description').val(tourDescription);
    $('#edit-cost').val(tourCost);
    $('#edit-date').val(tourDate);
    $('#edit-country').val(tourCountry);
    $('#edit-tourType').val(tourType);

/*    var action = '/review/' + reviewId + '/edit/tour/' + tourId + '/user/' + userid;
    $('#edit-comment-form').attr('action', action);*/
});

