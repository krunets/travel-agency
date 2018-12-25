<div class="search-form-background ">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="col-md-12">
                    <h1><@spring.message "search.form.tittle"/></h1>
                    <p><@spring.message "search.form.description"/></p>
                </div>
                <form action="/tour/search">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                    <div class="form-group">
                        <label for="country-search-field">Country</label>
                        <input type="text"
                               class="form-control autocomplete"
                               id="country-search-field"
                               name="countryName"
                               placeholder="<@spring.message "search.form.country"/>"/>
                    </div>
                    <div class="form-group">
                        <div id="datepicker" class="custom-datepicker date uui-datepicker date-button">
                            <label for="startTourDate">Start date</label>
                            <input autocomplete="off" type="text" id="startTourDate"
                                   name="startTourDate"
                                   findTourByCountryAndDateAndDuration        class="input-select uui-form-element"
                                   placeholder="<@spring.message "search.form.startDate"/>"
                                   required/>
                            <span class="input-group-addon uui-button"></span>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <input class="form-control durations"
                                   type="number"
                                   placeholder="<@spring.message "search.form.duration.from"/>"
                                   name="tourDuration"
                                   id="tourDuration"
                                   min="1"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <input class="form-control durations"
                                   type="number"
                                   placeholder="<@spring.message "search.form.duration.to"/>"
                                   name="tourDuration"
                                   id="tourDuration"
                                   min="1"
                                   required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <input class="form-control costs"
                                   type="number"
                                   placeholder="<@spring.message "search.form.cost.from"/>"
                                   name="costs"
                                   id="cost"
                                   min="1"
                                   required>
                        </div>
                        <div class="form-group col-md-6">
                            <input class="form-control costs"
                                   type="number"
                                   placeholder="<@spring.message "search.form.cost.to"/>"
                                   name="costs"
                                   id="cost"
                                   min="1"
                                   required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="transfertTypeId"><@spring.message "search.form.transferType"/></label>
                            <select class="custom-select transfers" name="transferTypeCodes" id="transfertTypeId"
                                    required
                                    multiple>
                                <option value="AP"><@spring.message "transfertype.ap"/></option>
                                <option value="GT"><@spring.message "transfertype.gt"/></option>
                                <option value="GB"><@spring.message "transfertype.gb"/></option>
                                <option value="WS"><@spring.message "transfertype.ws"/></option>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="tourTypeId"><@spring.message "search.form.tourType"/></label>
                            <select class="custom-select tourtypes" name="tourTypeIds" id="tourTypeId" required
                                    multiple>
                                <option value="1"><@spring.message "tourtype.at"/></option>
                                <option value="2"><@spring.message "tourtype.wt"/></option>
                                <option value="3"><@spring.message "tourtype.ctt"/></option>
                                <option value="4"><@spring.message "tourtype.ct"/></option>
                                <option value="5"><@spring.message "tourtype.et"/></option>
                                <option value="6"><@spring.message "tourtype.gt"/></option>
                                <option value="7"><@spring.message "tourtype.it"/></option>
                            </select>
                        </div>
                    </div>
                    <button type="submit"
                            class="btn btn-primary mb-2 float-right"
                            <#--onclick="tourSearch()"-->><@spring.message "search.from.search"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
