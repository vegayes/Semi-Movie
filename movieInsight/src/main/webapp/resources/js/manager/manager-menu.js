// 1) 메뉴 수정 버튼 누르면
const menuUpdate =document.getElementsByClassName("menu-update");

var click = null;
for(let i = 0; i <menuUpdate.length; i++){
    menuUpdate[i].addEventListener("click", function(){
            window.open("http://127.0.0.1:5556/admin-popup-menu-update.html",
             "팝업 수정","width=900, height=570, top=200, left=540");

    });
}
