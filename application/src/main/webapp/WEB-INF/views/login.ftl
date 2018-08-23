<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
</head>
<body>
<#include "include/header.ftl">
<h1>Login</h1>
<form method="post">
    <#if error>
        <p>Login or Password is incorrect!</p>
    </#if>
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"><div>
        <label for="username">Login</label>
        <input type="text" name="username" id="username" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
<#--
    <button type="button" onclick="logIn()">Sign in</button>
-->
    <button type="submit">Submit</button>
</form>
<#include "include/footer.ftl">
<#--
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
-->
<#--
<script src="/resources/js/controller.js" type="text/javascript"></script>
-->
</body>
</html>