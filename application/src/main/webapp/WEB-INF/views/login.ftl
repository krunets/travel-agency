<html>
<head>
    <title>Login page</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
</head>
<body>
<#include "include/header.ftl">
<h1>Login</h1>
<form action="/login" method="post">
    <div>
        <label for="login">Login</label>
        <input type="text" name="login" id="login" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <button type="button" onclick="logIn()">Sign in</button>
    <button type="submit">Submit</button>
</form>
<#include "include/footer.ftl">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="/resources/js/controller.js" type="text/javascript"></script>
</body>
</html>