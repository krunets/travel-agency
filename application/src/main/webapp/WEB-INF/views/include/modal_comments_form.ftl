<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Edit comment</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="edit-comment-form" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">Username:</label>
                        <input type="text" class="form-control" id="username" value="${review.user.login}" disabled/>
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="col-form-label">Message:</label>
                        <textarea class="form-control" name="content" id="content">${review.content}</textarea>
                    </div>
                    <button type="submit" class="btn btn-primary float-right">Edit</button>
                </form>
            </div>
        </div>
    </div>
</div>