<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Hotel page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/comments.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/uui/css/lib/components/datepicker3.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<#include "include/header.ftl">
<div class="row">
    <div class="col-md-12">
        <div class="margin-bottom">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><@spring.message "hotel.name.message"/></th>
                    <th scope="col"><@spring.message "hotel.phone.message"/></th>
                    <th scope="col"><@spring.message "hotel.stars.message"/></th>
                </tr>
                </thead>
                <tbody>
                <#list hotelPaginationDTO.data as hotel>
                <tr>
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
                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                <#list 1..hotelPaginationDTO.pageAmount as i>
                    <#if hotelPaginationDTO.page == i>
                        <li class=" page-item"><a class="bg-primary page-link"
                                                  onclick="insertParam('page', ${i})">${i}</a>
                        </li>
                    <#else>
                        <li class="page-item"><a class="page-link"
                                                 onclick="insertParam('page', ${i})">${i}</a>
                        </li>
                    </#if>
                </#list>
                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
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
</body>
</html>

