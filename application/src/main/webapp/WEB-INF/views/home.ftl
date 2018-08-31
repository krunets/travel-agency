<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/uui/css/lib/components/datepicker3.css"/>
</head>
<body>
<#include "include/header.ftl">
<a href="?lang=en_US">EN</a>
<a href="?lang=ru_RU">RU</a>
<div class="search-form-background ">
    <div class="container">
        <div class="row">
            <div class="align">
                <div class="col-md-12">
                    <div class="col-md-12">
                        <h1>Find Deals for Any Season</h1>
                        <p>From cozy country homes to funky city apartments</p>
                    </div>
                    <form name="searchFormDTO" action="/tour/search" class="form-inline" method="post">
                        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                        <div class="form-group group mx-sm-3 mb-2">
                        <#--<input type="text" name="countryName" id="countryName" class="form-control"
                               placeholder="Where are you going?">-->
                            <select name="countryName">
                                <option disabled selected>Where are you going?</option>
                            <#list countriesDTO as country>
                                <option value="${country.code}">${country.name}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="form-group mx-sm-3 mb-2">
                            <div id="datepicker" class="date uui-datepicker date-button">
                                <input autocomplete="off" type="text" id="startTourDate" name="startTourDate"
                                       class="uui-form-element" placeholder="When are going to start tour?"/>
                                <span class="input-group-addon uui-button">
                            <i class="fa fa-calendar white"></i>
                         </span>
                            </div>
                        </div>
                        <div class="form-group mb-2 group mx-sm-3">
                            <input class="form-control" type="number" placeholder="How many days are going?"
                                   name="tourDuration" id="tourDuration" min="1">
                        </div>
                        <button type="submit" class="btn btn-primary mb-2">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="margin-bottom">
    <#if !checkTours>
        <table id="example" class="table table-striped">
            <div class="custom-margin">
                <form action="/tour/pagination" name="pagination" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                    <input name="size" type="hidden" value="">
                    <button type="button" name="pagination_button" class="btn btn-primary float-right">Paginate</button>
                </form>
            </div>
            <thead>
            <tr>
                <th scope="col"><@spring.message "photo.message"/></th>
                <th scope="col">Date</th>
                <th scope="col">Cost</th>
                <th scope="col">Description</th>
                <th scope="col">Duration/Days</th>
                <th scope="col">Tourtype</th>
            </tr>
            </thead>
            <tbody>
                <#list tours as tour>
                <tr>
                    <td><img class="image-size" src="${tour.photo}"></td>
                    <td>${tour.date}</td>
                    <td>${tour.cost}$</td>
                    <td>${tour.description}</td>
                    <td>${tour.duration.toDays()}</td>
                    <td>${tour.tourType}</td>
                <#-- <td>
                     <#list tour.countries as country>
                         <p>Country: ${country.name}</p>
                     </#list>
                 </td>-->
                </tr>
                </#list>
            </tbody>
        </table>
    </#if>
    </div>
</div>
<#if checkTours>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Photo</th>
        <th scope="col">Date</th>
        <th scope="col">Cost</th>
        <th scope="col">Description</th>
        <th scope="col">Duration/Days</th>
        <th scope="col">Tourtype</th>
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
        <#-- <td>
             <#list tour.countries as country>
                 <p>Country: ${country.name}</p>
             </#list>
         </td>-->
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
</body>
</html>