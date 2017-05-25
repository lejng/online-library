/**
 * Created by Lenovo on 25.05.2017.
 */
var urlForm;
var urlRate;
var offImage;
var onImage;
function setImage(on,off){
    onImage = on;
    offImage = off;
}
function setUrl(urlF,urlR){
    urlForm = urlF;
    urlRate = urlR;
}
window.onload = function(){
    var buttonSendComment = document.querySelector('#button_send_comment');
    buttonSendComment.onclick = function () {sendForm();}
    function sendForm(){
        var formData = new FormData(document.forms.form_comment);
        var xhr = new XMLHttpRequest();
        var url = urlForm;
        xhr.open("POST", url);
        xhr.send(formData);
        xhr.onreadystatechange = function() {
            if (this.readyState != 4) return;
            if (this.status == 200) {
                var element = document.querySelector('#comments');
                var comments = element.innerHTML + this.responseText;
                element.innerHTML = comments;
                document.querySelector('#text_area_comment').value='';
            }
            if (this.status != 200) {
                // обработать ошибку
                console.log('error: ' + (this.status ? this.statusText : 'error request'))
                return;
            }
        }
    }
    function Send(){
        this.send = function(value){
            //alert(value);
            var url = urlRate;
            var req = new XMLHttpRequest();
            req.open('POST',url,true);
            req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            var body = 'rate='+encodeURIComponent(value);
            req.send(body);
            req.onreadystatechange = function() {
                if (this.readyState != 4) return;
                if (this.status == 200) {
                    var element = document.querySelector('#book_rating');
                    element.innerHTML = this.responseText;
                }
                if (this.status != 200) {
                    // обработать ошибку
                    console.log('error: ' + (this.status ? this.statusText : 'error request'))
                    return;
                }
            }
        };
    }
    setImage(onImage,offImage);
    rating(true,'#user_rate',new Send());
}
