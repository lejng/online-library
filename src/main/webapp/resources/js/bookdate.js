
var urlForm;
var urlRate;
var offImage;
var onImage;
var urlReadStatus;
var urlNotReadStatus;
var urlGoingToReadStatus;
function setImage(on,off){
    onImage = on;
    offImage = off;
}
function setUrl(urlF,urlR){
    urlForm = urlF;
    urlRate = urlR;
}

function setUrlForBookStatus(urlRead,urlNotRead,urlGoingToRead){
    urlReadStatus = urlRead;
    urlNotReadStatus = urlNotRead;
    urlGoingToReadStatus = urlGoingToRead;
}

window.onload = function (){
    setImage(onImage,offImage);
    rating(true,'#user_rate',new sendRating());
    var buttonSendComment = document.querySelector('#button_send_comment');
    if(buttonSendComment != null) {
        buttonSendComment.onclick = function () {
            sendFormWithComment();
        };
    }
    var buttonGoRead = document.querySelector('#button_go_read');
    var buttonRead = document.querySelector('#button_read');
    var buttonNotRead = document.querySelector('#button_not_read');
    if(buttonGoRead != null && buttonRead != null && buttonNotRead != null){
        buttonGoRead.onclick = function(){
            changeBookStatus(this,urlGoingToReadStatus);
        }

        buttonRead.onclick = function(){
            changeBookStatus(this,urlReadStatus);
        }

        buttonNotRead.onclick = function(){
            changeBookStatus(this,urlNotReadStatus);
        }
    }

    function changeBookStatus(button,url) {
        var req = new XMLHttpRequest();
        req.open('GET',url,true);
        req.send();
        req.onreadystatechange = function () {
            if (req.status != 200) {
                console.log("error send data by url " + url);
            } else {
                setActiveButton(button);
            }
        }
    }


    function setActiveButton(activeButton){
        var unactive = "btn btn-secondary ";
        buttonGoRead.className = unactive;
        buttonNotRead.className = unactive;
        buttonRead.className = unactive;
        activeButton.className = unactive + "active";

    }

    function sendFormWithComment(){
        var formData = new FormData(document.forms.form_comment);
        var req = new XMLHttpRequest();
        var url = urlForm;
        req.open("POST", url);
        req.send(formData);
        req.onreadystatechange = function() {
            if (this.readyState != 4) return;
            if (this.status == 200) {
                var elementComments = document.querySelector('#comments');
                var comments = elementComments.innerHTML + this.responseText;
                elementComments.innerHTML = comments;
                document.querySelector('#text_area_comment').value='';
            }
            if (this.status != 200) {
                console.log('error: ' + (this.status ? this.statusText : 'error request'))
                return;
            }
        }
    }

    function sendRating(){
        this.send = function(value){
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
                    console.log('error: ' + (this.status ? this.statusText : 'error request'))
                    return;
                }
            }
        };
    }


};
