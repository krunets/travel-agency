<div class="modal fade" id="add-tour-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Tour adding</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="edit-comment-form" action="/tour/add" method="post" enctype="multipart/form-data">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                    <div class="form-group">
                        <input type="file" class="form-control" name="photo" id="photo"/>
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">Date:</label>
                        <div id="datepicker" class="date uui-datepicker date-button">
                            <input autocomplete="off" type="text" id="date" name="date" class="uui-form-element" placeholder="Date" required/>
                            <span class="input-group-addon uui-button"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="cost" class="col-form-label">Cost:</label>
                        <input type="number" class="form-control"
                               name="cost" id="cost" min="1"/>
                    </div>
                    <div class="form-group">
                        <label for="duration" class="col-form-label">Duration:</label>
                        <input class="form-control" type="number"
                               name="duration" id="duration" min="1" required>
                    </div>
                    <div class="form-group">
                        <label for="tourType" class="col-form-label">Tour type:</label>
                        <select id="tourType" name="tourType" required>
                            <option disabled selected>Select tourtype:</option>
                            <#if tourTypeEnum?has_content>
                                <#list tourTypeEnum as tourType>
                                    <option value="${tourType.id}">${tourType.type}</option>
                                </#list>
                            </#if>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-form-label">Description:</label>
                        <textarea class="form-control" name="description" id="description"
                                  placeholder="Describe this tour:" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary float-right">Add</button>
                </form>
            </div>
        </div>
    </div>
</div>