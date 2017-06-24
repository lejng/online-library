window.onload = function(){
    var buttonSend = document.querySelector('#button_send');
    buttonSend.onclick = function(){
        var form = document.forms.form_edit_user;
        if(form.checkValidity() == false) {
            form.reportValidity();
            return;
        }
        var formData = new FormData(form);
        var req = new XMLHttpRequest();
        var url = form.action;
        req.open("POST",url);
        req.send(formData);
        req.onreadystatechange = function () {
            if(this.readyState != 4) return;
            if(this.status == 200){
                var userCard = document.querySelector('#user_card');
                $('#addEditModal').modal('hide');
                userCard.innerHTML = '';
                userCard.innerHTML = this.responseText;
            }
            if(this.status != 200) {
                $('#addEditModal').modal('hide');
                alert("can not update profile,check your internet connection");
                console.log("error on url " + url);
            }

        }
    }
}
