<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">EpamTravelAgency</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link</a>
            </li>
        </ul>
        <div class="btn-group">

            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                    Locale
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <a class="dropdown-item" href="?lang=en_US">EN</a>
                    <a class="dropdown-item" href="?lang=ru_RU">RU</a>
                </div>
            </div>
        <#if springMacroRequestContext.requestUri?contains("/admin/home") || springMacroRequestContext.requestUri?contains("/user/home")>
            <form action="/logout">
                <button type="submit" class="btn btn-success">Logout</button>
            </form>
        </#if>
        <#if springMacroRequestContext.requestUri?contains("/")
        && !springMacroRequestContext.requestUri?contains("/login")
        && !springMacroRequestContext.requestUri?contains("/admin/home")
        && !springMacroRequestContext.requestUri?contains("/user/home")>
            <form action="/login">
                <button type="submit" class="btn btn-warning">Sign In</button>
            </form>
        </#if>
        </div>
    </div>
</nav>