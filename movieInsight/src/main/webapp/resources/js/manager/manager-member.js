// 1) 검색 결과 조회 
document.getElementById("searchMember").addEventListener("keyup", function (event) {
    if (event.key === "Enter") {
        searchMember();
    }
});

function searchMember() {
    // 사용자가 입력한 검색어 가져오기
    var searchInput = document.getElementById("searchMember").value;
    console.log(searchInput);
    // 모든 항목 가져오기
    var memberList = document.querySelectorAll(".member-list");
    
    console.log(memberList);

    for (var i = 0; i < memberList.length; i++) {
        // 각 회원의 닉네임 가져오기
        var memberName = memberList[i].querySelector(".member-name").textContent;

        // 각 회원의 이메일 가져오기
        var memberEmail = memberList[i].querySelector(".member-email").textContent;
        
        if (memberName.toLowerCase().includes(searchInput.toLowerCase()) || memberEmail.toLowerCase().includes(searchInput.toLowerCase())) {
            memberList[i].style.display = "flex";
        } else {
            memberList[i].style.display = "none";
        }
        
        
    }
}

// 2) 조회 눌렀을 때 팝업 띄우기

const memberInfoModal = document.getElementById("manager-member-info-box");
const memberInfoContent = document.getElementById("manager-member-info-content");
const memberInfoBack = document.getElementById("member-info-back");
const memberInfoBtn = document.querySelector(".edit-button");
const closeModalBtn = document.querySelector("#closeBtn");


// 회원 조회 팝업 열기
memberInfoBtn.addEventListener("click", e => {

	console.log("나 누름");
	console.log(e);
	/*
	memberInfoModal.classList.toggle("toggle-popup");
	*/
	
	memberInfoModal.style.display = "flex";
});

// 회원 조회 팝업 닫기
closeModalBtn.addEventListener("click", e => {
	/*memberInfoModal.classList.toggle("toggle-popup");*/
	
	memberInfoModal.style.display = "none";
});

memberInfoBack.addEventListener("click", e=>{
    memberInfoModal.style.display = "none";
})

/*
function modalOpen(){
    modal.style.display ="flex";
}

function modalClose(){

  console.log("닫기");
    modal.style.display = "none";
}

*/


// 3) 조회를 눌렀을 때 값 전달
/*
const memberList = document.getElementsByClassName("edit-button");
var memberNo = null; 

function movieInfo(memberInfo) {

	console.log("memberNo" + memberNo);
    memberNo  = memberInfo;
}

*/

// 4) 회원 클릭하면 어떤 회원인지 정보 얻어오기
function getMemberInfo(){
	const memberInfoEdit = document.getElementsByClassName("edit-button");
	console.log(memberInfoEdit);
	for(let memNo of memberInfoEdit){
		memNo.addEventListener("click", e => {
				console.log("회원 클릭됨 ");
			selectMemberNo = memNo.getAttribute("member-no");
			
			
			console.log("selectMemberNo"+ selectMemberNo);
		});
	}
}














