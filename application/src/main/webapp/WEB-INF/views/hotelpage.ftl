<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Hotel</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/comments.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/uui/css/lib/components/datepicker3.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<#include "include/header.ftl">
<div class="col-md-12">
    <#include "include/modal_map.ftl">
</div>
<div class="row">
    <div class="col-md-12">
        <div class="margin-bottom">
            <table class="table">
                <div class="custom-margin">
                    <div class="dropdown show">
                        <a class="btn btn-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Show
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <a class="dropdown-item" onclick="insertParam('limit', 5)">5</a>
                            <a class="dropdown-item" onclick="insertParam('limit', 10)">10</a>
                            <a class="dropdown-item" onclick="insertParam('limit', 15)">15</a>
                        </div>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="horizontal-center-container pagination">
                        <#if hotelPaginationDTO.page gt 1>
                            <li class="page-item"><a class="page-link" onclick="insertParam('page', ${hotelPaginationDTO.page-1})">Previous</a></li>
                        </#if>
                        <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount>
                         <li class="page-item"><a class="page-link" onclick="insertParam('page', ${hotelPaginationDTO.page+1})">Next</a></li>
                        </#if>
                        </ul>
                    </nav>
                    <thead>
                    <tr>
                        <th scope="col"><@spring.message "hotel.name.message"/></th>
                        <th scope="col"><@spring.message "hotel.phone.message"/></th>
                        <th scope="col"><@spring.message "hotel.stars.message"/></th>
                    </tr>
                    </thead>
                    <tbody>
                <#list hotelPaginationDTO.data as hotel>
                <tr class="cursor-pointer"
                    data-target="#add-tour-modal"
                    data-toggle="modal"

                    data-hotel-latitude="${hotel.latitude}"
                    data-hotel-longitude="${hotel.longitude}">

                    <td>${hotel.name}</td>
                    <td>${hotel.phone}</td>
                    <td>
                        <#list 1..hotel.stars as index>
                            <span class="fa fa-star checked"></span>
                        </#list>
                    </td>
                </tr>
                </#list>
                    </tbody>
            </table>
            <nav aria-label="Page navigation example">
                <ul class="horizontal-center-container pagination">
                <#if hotelPaginationDTO.page gt 1>
                            <li class="page-item"><a class="page-link"
                                                     onclick="insertParam('page', ${hotelPaginationDTO.page-1})">Previous</a>
                            </li>
                </#if>
                    <li class=" page-item"><a class="bg-primary page-link"
                                              onclick="insertParam('page', ${hotelPaginationDTO.page})">${hotelPaginationDTO.page}</a>
                    </li>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount>
                        <li class=" page-item"><a class="page-link"
                                                  onclick="insertParam('page', ${hotelPaginationDTO.page+1})">${hotelPaginationDTO.page+1}</a>
                        </li>
                    </#if>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount - 2>
                        <li class=" page-item"><a class="page-link"
                                                  onclick="insertParam('page', ${hotelPaginationDTO.page+2})">${hotelPaginationDTO.page+2}</a>
                        </li>
                    </#if>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount - 3>
                     <li class=" page-item"><a class="page-link"
                                               onclick="insertParam('page', ${hotelPaginationDTO.page+3})">${hotelPaginationDTO.page+3}</a>
                     </li>
                    </#if>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount - 4>
                     <li class=" page-item"><a class="page-link"
                                               onclick="insertParam('page', ${hotelPaginationDTO.page+4})">${hotelPaginationDTO.page+4}</a>
                     </li>
                    </#if>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount - 5>
                         <li class=" page-item"><a class="page-link"
                                                   onclick="insertParam('page', ${hotelPaginationDTO.page+5})">${hotelPaginationDTO.page+5}</a>
                         </li>
                    </#if>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount - 6>
                        <li class=" page-item"><a class="page-link"
                                                  onclick="insertParam('page', ${hotelPaginationDTO.page+6})">${hotelPaginationDTO.page+6}</a>
                        </li>
                    </#if>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount - 7>
                         <li class=" page-item"><a class="page-link"
                                                   onclick="insertParam('page', ${hotelPaginationDTO.page+7})">${hotelPaginationDTO.page+7}</a>
                         </li>
                    </#if>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount - 8>
                        <li class=" page-item"><a class="page-link"
                                                  onclick="insertParam('page', ${hotelPaginationDTO.page+8})">${hotelPaginationDTO.page+8}</a>
                        </li>
                    </#if>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount - 9>
                         <li class=" page-item"><a class="page-link"
                                                   onclick="insertParam('page', ${hotelPaginationDTO.page+9})">${hotelPaginationDTO.page+9}</a>
                         </li>
                    </#if>
                    <#if hotelPaginationDTO.page < hotelPaginationDTO.pageAmount>
                        <li class="page-item"><a class="page-link"
                                                 onclick="insertParam('page', ${hotelPaginationDTO.page+1})">Next</a>
                        </li>
                    </#if>
                </ul>
            </nav>
        </div>
    </div>
</div>
<#include "include/footer.ftl">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
<script src="/resources/uui/js/lib/components/bootstrap-datepicker.js"></script>
<script src="/resources/uui/js/uui-datepicker.min.js"></script>
<script src="/resources/uui/bootstrap/js/jquery.dataTables.min.js"></script>
<script src="/resources/uui/bootstrap/js/dataTables.bootstrap4.min.js"></script>
<script src="/resources/js/controller.js"></script>
<script src="/resources/js/tourpage.js"></script>
<script src="/resources/uui/js/uui-rating.min.js"></script>
<script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript">
</script>
<script type="text/javascript">
    $('tr[data-toggle=modal]').click(function () {
        var latitude = $(this).data('hotel-latitude');
        var longitude = $(this).data('hotel-longitude');

        ymaps.ready(function () {

            var map = new ymaps.Map("map", {
                center: [latitude, longitude],
                zoom: 10
            });

            var myGeoObject = new ymaps.GeoObject({
                geometry: {
                    type: "Point", // тип геометрии - точка
                    coordinates: [latitude, longitude],
                    zoom: 25
                }
            });
            map.geoObjects.add(myGeoObject);
        });
    });
</script>
</body>
</html>

