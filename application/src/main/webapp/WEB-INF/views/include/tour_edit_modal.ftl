<div class="modal fade" id="edit-tour-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Tour edditing</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/tour/edit" id="edit-tour-form" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                <#-- <div id="photoExtensionError" class="alert alert-danger none" role="alert">
                     <p>File must have PNG or JPG extension!</p>
                 </div>-->
                    <div class="form-group">
                        <input type="hidden" name="photo" id="edit-photo"/>
                        <input type="hidden" name="id" id="edit-id"/>
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">Date:</label>
                        <div id="datepicker" class="custom-datepicker date uui-datepicker date-button">
                            <input autocomplete="off" type="text" id="edit-date" name="date" class="uui-form-element"
                                   value="${tour.date}" placeholder="Date" required/>
                            <span class="input-group-addon uui-button"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="edit-cost" class="col-form-label">Cost:</label>
                        <input type="number" class="form-control" value="${tour.cost}" name="cost" id="edit-cost"
                               min="1"/>
                    </div>
                    <div class="form-group">
                        <label for="edit-duration" class="col-form-label">Duration:</label>
                        <input class="form-control" type="number" value="${tour.duration.toDays()}" name="duration"
                               id="edit-duration" min="1" required>
                    </div>
                    <div class="form-group">
                        <label for="tourType" class="col-form-label">Tour type:</label>
                        <select id="edit-tourType" name="tourType" required>
                            <option disabled selected>Select tourtype:</option>
                            <#if tourTypeEnum?has_content>
                                <#list tourTypeEnum as tourType>
                                    <option value="${tourType.type}">${tourType.type}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-form-label">Description:</label>
                        <textarea class="form-control" name="description" id="edit-description"
                                  placeholder="Describe this tour:" required>${tour.description}</textarea>
                    </div>
                    <button type="submit" class="btn btn-primary float-right">Edit</button>
                </form>
            </div>
        </div>
    </div>
</div>