<#import "/spring.ftl" as spring />
<html>
<head><title>Registration page</title></head>
<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
      integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<body>
<#include "include/header.ftl">
<div class="container">
    <div class="row">
        <div class="padding-top-fourth align-self-center col-md-12">
       <#-- <@spring.bind "userDTO"/>

            <form action="/registration" method="post">
                First name:<br>
            <@spring.formInput "userDTO.login"/>
            <@spring.showErrors "<br>"/>
                <br><br>
                Last name:<br>
            <@spring.formPasswordInput "userDTO.password"/>
            <@spring.showErrors "<br>"/>
                <br><br>
                Email:<br>
            <@spring.formPasswordInput "user.confirmedPassword"/>
            <@spring.showErrors "<br>"/>
                <br><br>
                <input type="submit" value="Submit">
            </form>-->
        <form name="registrationForm" action="/registration" method="post">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
            <#if login_error>
                <div class=" alert alert-danger" role="alert">
                    <p>Login is already in use!</p>
                </div>
            </#if>
                <div class="form-group">
                    <label for="login">Input login</label>
                    <input type="text" name="login" id="login" required autofocus/>
                </div>
                <div class="form-group">
                    <label for="password">Input password</label>
                    <input type="password" name="password" id="password" required/>
                </div>
                <div class="form-group">
                    <label for="password">Confirm password</label>
                    <input type="password" name="confirmedPassword" id="password" required/>
                </div>
                <button type="submit" class="btn btn-primary float-right">Sign up</button>
            </form>
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
</body>
</html>