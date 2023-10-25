// 1) 이벤트 수정 버튼 누르면
const eventUpdate =document.getElementsByClassName("event-update");

var click = null;
for(let i = 0; i <eventUpdate.length; i++){
    eventUpdate[i].addEventListener("click", function(){
            window.open("http://127.0.0.1:5556/admin-popup-event-update.html",
             "팝업 수정","width=900, height=570, top=200, left=540");

    });
}


// 2) 특별관 추가 버튼 누르면
document.getElementById("special-add").addEventListener("click", function(){
    window.open("http://192.168.50.200:5501/my-page-popup-favorite.html",
    "특별관 추가",
    "width=900, height=570, top=200, left=540");
});


// 3) 이벤트 추가 버튼 누르면
document.getElementById("event-add").addEventListener("click", function(){
    window.open("http://192.168.50.200:5501/my-page-popup-favorite.html",
    "특별관 추가",
    "width=900, height=570, top=200, left=540");
});