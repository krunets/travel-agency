<div class="col-md-12">
    <div class="modal" id="show-tour-hotels" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

        </div>
         <#list hotels as hotel>
             <div class="target"
                     data-hotel-id="${hotel.id}"
                     data-hotel-name="${hotel.name}"
                     data-hotel-latitude="${hotel.latitude}"
                     data-hotel-longitude="${hotel.longitude}"
                     data-hotel-stars="${hotel.stars}"
                     data-hotel-phone="${hotel.phone}">
             </div>
         </#list>
        <div id="map" style="width: 100%; height: 75%;"></div>
    </div>
</div>
