// 현재 페이지가 어디와 연관있는지 확인
document.getElementById("logo").addEventListener("click", () => {

    console.log("수정 버튼 눌림");

    console.log(location.pathname);

});

