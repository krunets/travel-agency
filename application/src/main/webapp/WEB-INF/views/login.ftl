<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<#--
    <link rel="stylesheet" type="text/css" href="/resources/uui/bootstrap/css/bootstrap.min.css"/>
-->
</head>
<body>
<#include "include/header.ftl">
<#--<form method="post">
<#if error>
    <p>Login or Password is incorrect!</p>
</#if>
    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
    <div>
        <label for="username">Login</label>
        <input type="text" name="username" id="username" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>

    <button type="submit">Submit</button>
</form>-->
<div class="container">
    <div class="row">
        <div class="padding-top-fourth align-self-center col-md-12">
            <form method="post">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
            <#if error>
                <div class=" alert alert-danger" role="alert">
                    <p>Login or password is incorrect!</p>
                </div>
            </#if>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control" id="username" placeholder="Enter login" name="username">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" placeholder="Enter password"
                           name="password">
                </div>
                <button type="submit" class="btn btn-primary pull-right">Sign In</button>
                <button type="button" class="btn btn-secondary pull-right">Sign Up</button>
            </form>
        </div>
    </div>
</div>
<#include "include/footer.ftl">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<#--<script src="/resources/uui/jquery/jquery-2.2.4.min.js"></script>
<script src="/resources/uui/jquery/jquery-3.3.1.min.js"></script>
<script src="/resources/uui/jquery-ui/jquery-ui.min.js"></script>
<script src="/resources/uui/bootstrap/js/bootstrap.min.js"></script>&ndash;&gt;
<script src="/resources/js/controller.js" type="text/javascript"></script>-->
</body>
</html>