// 현재 페이지가 어디와 연관있는지 확인
document.getElementById("logo").addEventListener("click", () => {

    console.log("수정 버튼 눌림");

    console.log(location.pathname);

});



// 로그인 아닐때 창
function callFunction() {
	alert("로그인 후 이용해주세요");
	location.href="/movieInsight/member/loginPage";
	return true;
}