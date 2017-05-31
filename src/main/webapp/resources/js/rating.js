	var onImage = 'stars/star-on.svg';
 	var offImage = 'stars/star-off.svg';
 	function setImage(on,off){
 		onImage = on;
 		offImage = off;
 	}
 	//вызываеться для изменения рейтинга вручную например пришло квкое-то значение с сервера
 	function changeRating(value,selectorId){
 		var rate = document.querySelector(selectorId);
 		clickStar(value,rate.childNodes);
 	}
//вызываеться при клике мышкой
 	function clickStar(value,array,afterClick){
 		count = value;
 		for(var i = 0; i < count; i++)
 			array[i].src = onImage;
 		for(j = i; j < 5; j++)
 			array[j].src = offImage;
 		if(afterClick != null){
 			try{
 				afterClick.send(value);
 			}catch(e){
 				console.log(e);
 			}
 		}
 	}
//первоначальная установка и создание рейтинга 
 	function rating(onclick,selectorId,afterClick){
 		var rate = document.querySelector(selectorId);
 		if(rate != null) {
            this.onStar = parseInt(rate.getAttribute('value'));
            for (var i = 0; i < 5; i++) {
                var input = document.createElement('input');
                input.type = 'image';
                input.value = i + 1;
                input.readOnly = true;
                if (onclick) {
                    input.onclick = function () {
                        clickStar(this.value, rate.childNodes, afterClick);
                        onStar = this.value;
                    };
                    input.onmouseenter = function () {
                        clickStar(this.value, rate.childNodes);
                    };
                    input.onmouseleave = function () {
                        clickStar(onStar, rate.childNodes);
                    }
                }
                if (i < onStar)
                    input.src = onImage;
                else
                    input.src = offImage;
                rate.append(input);
            }
        }

 	}