$("input[placeholder]").each(function () {
    $(this).attr('size', $(this).attr('placeholder').length);
});
$('#datepicker').uui_datepicker({todayHighlight: true});
$('.uui-carousel').carousel({
    interval: 2000
});
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