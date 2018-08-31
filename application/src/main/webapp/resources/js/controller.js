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

};