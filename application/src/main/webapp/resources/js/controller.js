var searchTourDTO = {
    countryName: "",
    startTourDate: "",
    tourDuration: ""
};

var registration = function () {

};

var searchTour = function () {
    searchTourDTO.countryName = $('#countryName').val();
    searchTourDTO.startTourDate = $('#startTourDate').val();
    searchTourDTO.tourDuration = $('#tourDuration').val();
    var token = $("meta[name='_csrf']").attr("content");
    $.ajax({
        type: 'POST',
        url: '/tour/search',
        accept: "application/json",
        contentType: "application/json",
        data: JSON.stringify(searchTourDTO),
        dataType: "json",
        headers: {'X-CSRF-Token': token},
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            console.log(error);
        }
    });
};

$("input[placeholder]").each(function () {
    $(this).attr('size', $(this).attr('placeholder').length);
});
$('#datepicker').uui_datepicker({todayHighlight: true});