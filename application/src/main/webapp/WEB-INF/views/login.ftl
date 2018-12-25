<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<#--
    <link rel="stylesheet" type="text/css" href="/resources/uui/bootstrap/css/bootstrap.min.css"/>
-->
</head>
<body>
<#include "include/header.ftl">
<div class="container">
    <div class="row">
        <div class="padding-top-fourth col-md-12">
            <div class="margin-bottom">
                <form id="signInForm" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                <#if error>
                    <div class=" alert alert-danger" role="alert">
                        <p>Login or password is incorrect!</p>
                    </div>
                </#if>
                    <div class="form-group">
                        <label for="email">Login:</label>
                        <input type="text" class="form-control" id="username" placeholder="Enter login" name="username">
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" class="form-control" id="password" placeholder="Enter password"
                               name="password">
                    </div>

                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" name="remember-me"  class="custom-control-input" id="exampleCheck1">
                        <label class="custom-control-label" for="exampleCheck1">Remember me</label>
                    </div>

                    <button type="button" onclick="signIn()" class="btn btn-primary float-right">Sign In</button>
                    <button type="button" onclick="signUp()" class="btn btn-secondary float-right">Sign Up</button>
                </form>
                <form id="signUpForm" action="/registration">
                </form>
            </div>
        </div>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
<script>
    var signIn = function () {
        $('#signInForm').submit();
    };
    var signUp = function () {
        $('#signUpForm').submit();
    }
</script>
</body>
</html>