<html>
<head><title>Login page</title></head>
<body>
<h1>Login</h1>
<form id="loginForm" action="/login" method="post">
    <div>
        <label for="login">Login</label>
        <input type="text" name="login" id="login" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <button type="button" onclick="login()">Sign in</button>
</form>
<script src="webjars/jquery/3.3.1-1/jquery.js" type="text/javascript"></script>
<script src="../js/controller.js"></script>
</body>
</html>