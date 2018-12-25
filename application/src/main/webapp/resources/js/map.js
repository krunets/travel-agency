var bookHotelDTO = {
    hotelId: "",
    beds: ""
};

$('button[data-toggle=modal]').click(function () {
    ymaps.ready(function () {
        var map = new ymaps.Map("map", {
            center: ['55.10', '28.15'],
            zoom: 4
        });

        var bookHotel =
            new ymaps.control.Button({
                data: {
                    content: "Book hotel"
                },
                options: {
                    maxWidth: 150
                }
            });

        bookHotel.events
            .add(
                'press',
                function () {
                    $("#getCodeModal").modal('show');
                }
            );

        var clusterer = new ymaps.Clusterer({});
        $(".target").each(function () {
            var id = $(this).data('hotel-id');
            var name = $(this).data('hotel-name');
            var latitude = $(this).data('hotel-latitude');
            var longitude = $(this).data('hotel-longitude');
            var stars = $(this).data('hotel-stars');
            var phone = $(this).data('hotel-phone');

            var placemark = new ymaps.Placemark([latitude, longitude], {
                balloonContentHeader: name,
                balloonContentBody:
                'Phone: ' + phone + '\n' +
                'Rating: ' + stars + '<span class="fa fa-star checked"></span>',
                id: id
            });

            placemark.events.add('click', function (e) {
                bookHotelDTO.hotelId = e.get('target').properties.get('id');
            });

            clusterer.add(placemark);
        });
        map.controls.add(bookHotel);
        map.geoObjects.add(clusterer);

    });
});

$('#book-button').click(function () {
    bookHotelDTO.beds = $('#rooms-select').val();
    bookHotelRequest();
});

var loadActiveRoomsRequest = function () {
    var csrfToken = $("meta[name='_csrf']").attr("content");
    $.ajax({
        type: 'GET',
        url: '/room/active/by/hotel/' + bookHotelDTO.hotelId,
        headers: {"X-CSRF-TOKEN": csrfToken},
        success: function (data) {
        },
        error: function (error) {
            alert("Something went wrong! Please try again!");
        }
    });
};

var bookHotelRequest = function () {
    var csrfToken = $("meta[name='_csrf']").attr("content");
    $.ajax({
        type: 'POST',
        url: '/hotel/' + bookHotelDTO.hotelId + '/book/beds/' + bookHotelDTO.beds,
        headers: {"X-CSRF-TOKEN": csrfToken},
        success: function (data) {
            alert(data);
        },
        error: function (error) {
            alert("Something went wrong! Please try again!");
        }
    });
};

