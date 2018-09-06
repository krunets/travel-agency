<#import "/spring.ftl" as spring/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Tour page</title>
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
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="${tour.photo}" alt="First slide">
                        <div class="carousel-caption d-none d-md-block">
                            <h5><@spring.message "cost.message"/> ${tour.cost}$</h5>
                            <p><@spring.message "date.message"/> ${tour.date}</p>
                            <p><@spring.message "duration.message"/> ${tour.duration.toDays()}</p>
                            <p>${tour.tourType}</p>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="margin-bottom">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><@spring.message "description.message"/></h5>
                        <p class="card-text"> ${tour.description}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="margin-bottom">

                <div class="page-header">
                    <h1>
                        <small class="pull-right">${tour.reviews?size} comments</small>
                        Comments
                    </h1>
                </div>

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="margin-bottom">
                <form action="/user/review/${tour.id}/tour" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                    <textarea name="content" class="resize-none margin-bottom form-control input-sm chat-input"
                              placeholder="Write your message here..." required></textarea>
                    <input type="button" id="show-comments-button" class="auto-width btn btn-outline-info float-right"
                           value="Show comments"/>
                    <button type="submit" class="auto-width btn btn-outline-info float-right">Add comment</button>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="margin-bottom">
            <#list tour.reviews as review>
                <div class="comments-list none">
                    <div class="media">
                        <a class="media-left" href="#"><img class="comments-img" src="${review.user.photo}"></a>
                        <div class="media-body">
                            <h4 class="media-heading user_name">${review.user.login}</h4>
                            <p>${review.content}</p>
                        </div>
                    </div>
                </div>
            </#list>
            </div>
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
    $('#show-comments-button').click(function () {
        $('.comments-list').toggleClass('none');
        if ($('#show-comments-button').val() == "Show comments") {
            $('#show-comments-button').val("Hide comments");
        } else {
            $('#show-comments-button').val("Show comments");
        }
    });
</script>
</body>
</html>

