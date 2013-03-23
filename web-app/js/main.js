

/**
 * Uses a Bootstrap modal window to present an OK/Cancel confirm.
 *  Requires:  bootstrap-modal.js, bootstrap.css
 * @param message
 * @param true_func
 * @param false_func
 */
function customConfirm(message, true_func, false_func){
            var container = document.createElement('div');
    //template for modal window
    container.innerHTML += '<div class="modal custom-confirm">'+
                            '<div class="modal-body">' +
                                '<div>' + message + '</div>' +
                                '<div class="controls">'+ 
                                    '<button type="button" class="btn primary">OK</button>' +
                                    '<button type="button" class="btn">Cancel</button>' +
                                '</div>' +
                            '</div>' +
                        '</div>';
    //modal window
    var modal = container.firstChild;
    container = document.createElement('div');
    container.innerHTML = '<div class="modal-backdrop  in"></div>';
    //dark background
    var background = container.firstChild;
    //get click OK button
    var ok = modal.getElementsByTagName('button')[0];
    ok.onclick = function() {
        modal.parentNode.removeChild(modal);
        document.body.removeChild(background);
        true_func();
    }
    //get click Cancel button
    var cancel = modal.getElementsByTagName('button')[1];
    cancel.onclick = function() {
        modal.parentNode.removeChild(modal);
        document.body.removeChild(background);
        false_func();
    }
    document.body.appendChild(background);
    document.body.appendChild(modal);
}
