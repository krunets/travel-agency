<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta charset="utf-8">
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/uui/css/lib/components/datepicker3.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<#include "include/header.ftl">
<#include "include/searchform.ftl">
<div class="custom-container">
    <div class="margin-bottom">
    <#if !checkTours>
        <table id="example" class="table table-striped">
            <div class="custom-margin">
                <div class="dropdown show">
                    <a class="btn btn-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Show
                    </a>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="/tour?limit=5">5</a>
                        <a class="dropdown-item" href="/tour?limit=10">10</a>
                        <a class="dropdown-item" href="/tour?limit=15">15</a>
                    </div>
                </div>
                <form action="/tour/pagination" name="pagination" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                    <input name="size" type="hidden" value="">
                    <button type="button" name="pagination_button"
                            class="btn btn-primary float-right"><@spring.message "paginate.message"/></button>
                </form>
            </div>
            <thead>
            <tr>
                <@security.authorize access="hasRole('ROLE_ADMIN')">
                    <button class="btn btn-info btn-xs" data-target="#add-tour-modal" data-toggle="modal">
                        Add
                        <span class="fa fa-plus-circle"></span>
                    </button>
                </@security.authorize>
                <th scope="col"><@spring.message "photo.message"/></th>
                <th scope="col"><@spring.message "date.message"/></th>
                <th scope="col"><@spring.message "cost.message"/></th>
                <th scope="col"><@spring.message "description.message"/></th>
                <th scope="col"><@spring.message "duration.message"/></th>
                <th scope="col"><@spring.message "tourtype.message"/></th>
                <th scope="col"><@spring.message "country.message"/></th>
                <th scope="col"><@spring.message "hotel.message"/></th>
                <@security.authorize access="hasRole('ROLE_ADMIN')">
                    <th scope="col">Edit</th>
                    <th scope="col">Delete</th>
                </@security.authorize>
            </tr>
            </thead>
            <tbody>
                <#list tours as tour>
                <tr>
                    <td><a style="cursor: pointer;" href="/tour/${tour.id}/info"><img class="image-size"
                                                                                      src="${tour.photo}"></a></td>
                    <td>${tour.date}</td>
                    <td>${tour.cost}$</td>
                    <td>${tour.description}</td>
                    <td>${tour.duration.toDays()}</td>
                    <td>${tour.tourType}</td>
                    <td>
                        <#list tour.countries as country>
                            ${country.name}
                        </#list>
                    </td>
                    <td>
                        <#list tour.hotels as hotel>
                            <p><@spring.message "hotel.name.message"/>: ${hotel.name}</p>
                            <p><@spring.message "hotel.phone.message"/>: ${hotel.phone}</p>
                            <#list 1..hotel.stars as index>
                                <span class="fa fa-star checked"></span>
                            </#list>
                        </#list>
                    </td>
                    <@security.authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <p data-placement="top" data-toggle="tooltip" title="Edit">
                                <button class="edit-modal btn btn-primary btn-xs" data-title="Edit"
                                        data-toggle="modal"
                                        data-target="#edit-tour-modal"
                                        data-tour-photo="${tour.photo}"
                                        data-tour-id="${tour.id}"
                                        data-tour-date="${tour.date}"
                                        data-tour-cost="${tour.cost}"
                                        data-tour-description="${tour.description}"
                                        data-tour-duration="${tour.duration.toDays()}"
                                        data-tour-type="${tour.tourType.type}">
                                    <span class="fa fa-pencil-square-o"></span>
                                </button>
                            </p>
                        </td>
                        <td>
                            <form action="/tour/${tour.id}/delete" method="post">
                                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                                <button type="submit" class="btn btn-danger btn-xs">
                                    <span class="fa fa-times"></span>
                                </button>
                            </form>
                        </td>
                        <#include "include/tour_add_modal.ftl">
                        <#include "include/tour_edit_modal.ftl">
                    </@security.authorize>
                </tr>
                </#list>
            </tbody>
        </table>
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <#list 1..tourPaginationDTO.pageAmount as i>
                    <li class="page-item"><a class="page-link" href="/tour?page=${i}">${i}</a></li>
                </#list>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>
    </#if>
    </div>
</div>
<#if checkTours>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col"><@spring.message "photo.message"/></th>
        <th scope="col"><@spring.message "date.message"/></th>
        <th scope="col"><@spring.message "cost.message"/></th>
        <th scope="col"><@spring.message "description.message"/></th>
        <th scope="col"><@spring.message "duration.message"/></th>
        <th scope="col"><@spring.message "tourtype.message"/></th>
    </tr>
    </thead>
    <tbody>
        <#list criteriaTour as tour>
        <tr>
            <td><img class="image-size" src="${tour.photo}"></td>
            <td>${tour.date}</td>
            <td>${tour.cost}$</td>
            <td>${tour.description}</td>
            <td>${tour.duration.toDays()}</td>
            <td>${tour.tourType}</td>
        </tr>
        </#list>
    </tbody>
</table>
</#if>
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
<script>


</script>
</body>
</html>





