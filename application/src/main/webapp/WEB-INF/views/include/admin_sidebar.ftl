<ul class="admin-sidebar bg-dark navbar-nav">
    <form id="getAllUser" action="/user/all" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
    </form>
    <li onclick="getAllUser.submit();" class="nav-item">
        <a class="nav-link"><span class="text-white">Users</span></a>
    </li>
    <form id="getAllTours" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
    </form>
    <li onClick="getAllTours.submit();" class="nav-item">
        <a class="nav-link"><span class="text-white">Tours</span></a>
    </li>
    <form id="getAllReviews" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
    </form>
    <li onClick="getAllReviews.submit();" class="nav-item">
        <a class="nav-link"><span class="text-white">Reviews</span></a>
    </li>
    <form id="getAllHotels" method="post">
        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
    </form>
    <li onClick="getAllHotels.submit();" class="nav-item ">
        <a class="nav-link"><span class="text-white">Hotels</span></a>
    </li>
</ul>
