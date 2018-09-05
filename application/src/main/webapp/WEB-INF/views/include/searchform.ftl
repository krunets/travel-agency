<div class="search-form-background ">
    <div class="container">
        <div class="row">
            <div class="align">
                <div class="col-md-12">
                    <div class="col-md-12">
                        <h1><@spring.message "search.form.tittle"/></h1>
                        <p><@spring.message "search.form.description"/></p>
                    </div>
                    <form name="searchFormDTO" action="/tour/search" class="form-inline" method="post">
                        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                        <div class="form-group group mx-sm-3 mb-2">
                            <select name="countryName" required>
                                <option disabled selected><@spring.message "search.form.country"/></option>
                            <#list countriesDTO as country>
                                <option value="${country.code}">${country.name}</option>
                            </#list>
                            </select>
                        </div>
                        <div class="form-group mx-sm-3 mb-2">
                            <div id="datepicker" class="date uui-datepicker date-button">
                                <input autocomplete="off" type="text" id="startTourDate" name="startTourDate"
                                       class="uui-form-element" placeholder="<@spring.message "search.form.startDate"/>"
                                       required/>
                                <span class="input-group-addon uui-button">
                         </span>
                            </div>
                        </div>
                        <div class="form-group mb-2 group mx-sm-3">
                            <input class="form-control" type="number"
                                   placeholder="<@spring.message "search.form.duration"/>"
                                   name="tourDuration" id="tourDuration" min="1" required>
                        </div>
                        <button type="submit"
                                class="btn btn-primary mb-2"><@spring.message "search.from.search"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
