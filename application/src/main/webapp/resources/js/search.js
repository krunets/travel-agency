var searchTourDTO = {
    countryName: '',
    startTourDate: '',
    tourDuration: [],
    transferTypeCodes: []
};


$(document).ready(function () {
    $('#country-search-field').devbridgeAutocomplete({
        serviceUrl: '/country/read/all',
        paramName: 'countryName',
        minChars: 1,
        autoSelectFirst: true,
        select: function (event, ui) {
            console.log(ui.item.label);
            $("#country-search-field").val(ui.item.label);
            return false;
        }
    });
});


var tourSearch = function () {
    var csrfToken = $("meta[name='_csrf']").attr("content");
    searchTourDTO.countryName = $('#country-search-field').val();
    searchTourDTO.startTourDate = $('#startTourDate').val();
    searchTourDTO.tourDuration = $('.durations').val();
    searchTourDTO.transferTypeCodes = $('.transfers').val();

    console.log(searchTourDTO);

    $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: '/tour/search',
        headers: {"X-CSRF-TOKEN": csrfToken},
        data: JSON.stringify(searchTourDTO),
        dataType: 'json',
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            alert("Something went wrong! Please try again!");
        }
    });
};