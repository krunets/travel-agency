<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta charset="utf-8">
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/css/comments.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="/resources/uui/css/lib/components/datepicker3.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<#include "include/header.ftl">
<#include "include/searchform.ftl">

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="page-header">
                <h1><small class="pull-right">${tour.reviews?size} comments</small> Comments </h1>
            </div>
            <#list tour.reviews as review>
                <div class="comments-list">
                    <div class="media">
                        <p class="float-right"><small>5 days ago</small></p>
                        <a class="media-left" href="#"><img src="http://lorempixel.com/40/40/people/1/"></a>
                        <div class="media-body">
                            <h4 class="media-heading user_name">${review.user.login}</h4>
                            ${review.content}
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>


<#--
<div class="container">
    <div class="row">
        <div class="margin-bottom">
            <div class="card">
                <p><img class="image-size-card" src="${tour.photo}"></p>
                <div class="card-body">
                    <h5 class="card-title">Card title</h5>
                    <p class="card-text"><@spring.message "date.message"/> ${tour.date}</p>
                    <p class="card-text"><@spring.message "cost.message"/> ${tour.cost}$</p>
                    <p class="card-text"><@spring.message "description.message"/> ${tour.description}</p>
                    <p class="card-text"><@spring.message "duration.message"/> ${tour.duration.toDays()}</p>
                    <p class="card-text"><@spring.message "tourtype.message"/> ${tour.tourType}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <h3>User Comment Example</h3>
        </div><
    </div>
    <#list tour.reviews as review>
        <div class="row">
            <div class="col-sm-1">
                <div class="thumbnail">
                    <img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
                </div>
            </div>

            <div class="col-sm-5">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <strong>${review.user.login}</strong>&lt;#&ndash; <span class="text-muted">commented 5 days ago</span>&ndash;&gt;
                    </div>
                    <div class="panel-body">${review.content}</div>
                </div>
            </div>
        </div>
    </#list>
</div>
-->


<#list tour.reviews as review>
<p>${review.content}</p>
<p>${review.user.login}</p>
</#list>
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

