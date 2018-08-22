<html>
<head><title>Registration page</title></head>
<body>
<#include "include/header.ftl">
<h1>Login</h1>
<form id="loginForm" action="/login" method="post">
    <div>
        <label for="login">Input your login</label>
        <input type="text" name="login" id="login" required autofocus/>
    </div>
    <div>
        <label for="password">Input your password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <button type="button" onclick="registration()">Sign in</button>
</form>
<#include "include/footer.ftl">
</body>
</html>