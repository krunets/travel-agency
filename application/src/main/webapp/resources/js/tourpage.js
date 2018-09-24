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

$('.edit-modal').click(function () {
    var tourPhoto = '';
    var tourId = '';
    var tourDuration = '';
    var tourDescription = '';
    var tourCost = '';
    var tourDate = '';
    var tourCountry = '';
    var tourType = '';

    if (typeof $(this).data('tour-id') !== 'undefined') {
        tourPhoto = $(this).data('tour-photo');
        tourId = $(this).data('tour-id');
        tourDuration = $(this).data('tour-duration');
        tourDescription = $(this).data('tour-description');
        tourCost = $(this).data('tour-cost');
        tourDate = $(this).data('tour-date');
        tourCountry = $(this).data('tour-country');
        tourType = $(this).data('tour-type');
    }

    var split = tourDate.split("-");
    var newDate = split[1] + '/' + split[2] + '/' + split[0];

    $('#edit-id').val(tourId);
    $('#edit-photo').val(tourPhoto);
    $('#edit-duration').val(tourDuration);
    $('#edit-description').val(tourDescription);
    $('#edit-cost').val(tourCost);
    $('#edit-date').val(newDate);
    $('#edit-country').val(tourCountry);
    $('#edit-tourType').val(tourType).change();
});

