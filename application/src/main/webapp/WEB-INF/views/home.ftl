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
</head>
<body>
<#include "include/header.ftl">
<#include "include/searchform.ftl">
<div class="custom-container">
    <div class="margin-bottom">
    <#if !checkTours>
        <table id="example" class="table table-striped">
            <div class="custom-margin">
                <form action="/tour/pagination" name="pagination" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                    <input name="size" type="hidden" value="">
                    <button type="button" name="pagination_button"
                            class="btn btn-primary float-right"><@spring.message "paginate.message"/></button>
                </form>
            </div>
            <thead>
            <tr>
                <th scope="col"><@spring.message "photo.message"/></th>
                <th scope="col"><@spring.message "date.message"/></th>
                <th scope="col"><@spring.message "cost.message"/></th>
                <th scope="col"><@spring.message "description.message"/></th>
                <th scope="col"><@spring.message "duration.message"/></th>
                <th scope="col"><@spring.message "tourtype.message"/></th>
                <th scope="col"><@spring.message "country.message"/></th>
                <th scope="col"><@spring.message "hotel.message"/></th>
            </tr>
            </thead>
            <tbody>
                <#list tours as tour>
                <tr>
                    <td><a style="cursor: pointer;" href="/tour/${tour.id}/info"><img class="image-size" src="${tour.photo}"></a></td>
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
                </tr>
                </#list>
            </tbody>
        </table>
       <#-- <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">Next</a></li>
            </ul>
        </nav>-->
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
<script src="/resources/uui/js/uui-rating.min.js"></script>
<script>
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
</script>
</body>
</html>

