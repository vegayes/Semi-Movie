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
const memberInfoBtn = document.querySelectorAll(".edit-button");
const closeModalBtn = document.querySelector("#closeBtn");


// 회원 조회 팝업 열기
memberInfoBtn.forEach(button => {

    button.addEventListener("click", e => {
        // 각 버튼에 대한 처리 코드
        console.log("나 누름");
        
        memberInfoModal.style.display = "flex";
    });

});


// 회원 조회 팝업 닫기
closeModalBtn.addEventListener("click", e => {
	memberInfoModal.style.display = "none";
    const tbody = document.getElementById("commentTableBody");
    tbody.innerHTML = "";
});

memberInfoBack.addEventListener("click", e=>{
    memberInfoModal.style.display = "none";
})




function getMemberInfo(memberNo) {
    fetch("/movieInsight/managerDetail/selectMemberInfo/select?memberNo=" + memberNo)
        .then(response => response.json())
        .then(commentList => {

            console.log("member: ",commentList.member );

            const idElement = document.getElementById("idElement");
            const emailElement = document.getElementById("emailElement");
            const enrollDateElement = document.getElementById("enrollDateElement");
            const delYNElement = document.getElementById("delYNElement");
            const titleElement = document.getElementById("titleElement");

            titleElement.textContent = commentList.member.memberNickname + " 님의 회원정보";
            idElement.textContent = commentList.member.memberNickname;
            emailElement.textContent = commentList.member.memberEmail;
            enrollDateElement.textContent = commentList.member.memberEnrollDate;

            if(commentList.member.memberDelYN == "Y") {
                delYNElement.textContent = "O";
            }else {
                delYNElement.textContent = "X";
            }
            
            const movieComments = commentList.movieComment;
            const cinemaComments = commentList.cinemaComment;
            
            // movieComment 배열의 각 원소를 comment 객체로 변환
            const movieCommentObjects = movieComments.map(comment => {
                return {
                    commentType: "영화",
                    commentContent: comment.movieCommentContent,
                    commentDate: comment.movieCommentDate,
                    commentDelYN: comment.movieCommentDelYN
                };
            });
            
            // cinemaComment 배열의 각 원소를 comment 객체로 변환
            const cinemaCommentObjects = cinemaComments.map(comment => {
                return {
                    commentType: comment.cinemaCommentType,
                    commentContent: comment.cinemaCommentContent,
                    commentDate: comment.cinemaCommentDate,
                    commentDelYN: comment.cinemaCommentDelYN
                };
            });
            
            // 두 배열을 통합
            const combinedComments = [...movieCommentObjects, ...cinemaCommentObjects];

            combinedComments.sort((a, b) => {
                const dateA = new Date(a.commentDate);
                const dateB = new Date(b.commentDate);
            
                // 내림차순 정렬 (최신 날짜가 먼저 오도록)
                return dateB - dateA;
            });
            
            const tbody = document.getElementById("commentTableBody");

            // 통합된 배열을 가지고 테이블에 댓글내용 최신순으로 출력
            combinedComments.forEach(comment => {
                const row = document.createElement("tr");
            
                // 댓글 종류
                const typeCell = document.createElement("td");
                typeCell.textContent = comment.commentType;
                typeCell.classList.add("popup-member-comment-type"); // 클래스 추가
                row.appendChild(typeCell);
            
                // 댓글 내용
                const contentCell = document.createElement("td");
                contentCell.textContent = comment.commentContent;
                contentCell.classList.add("popup-member-comment-content");
                row.appendChild(contentCell);
            
                // 댓글 작성일
                const dateCell = document.createElement("td");
                dateCell.textContent = comment.commentDate;
                dateCell.classList.add("popup-member-comment-date");
                row.appendChild(dateCell);
            
                // 댓글 삭제 여부
                const delYNCell = document.createElement("td");
                if (comment.commentDelYN == "Y") {
                    delYNCell.textContent = "O";
                } else {
                    delYNCell.textContent = "X";
                }
            
                delYNCell.classList.add("popup-member-comment-del-YN");
                row.appendChild(delYNCell);
            
                tbody.appendChild(row);
            });
            
         })
         .catch(err => console.log(err));
}




// 회원 탈퇴
function deleteMember(memberNo) {
    const confirmation = confirm("정말 탈퇴시키겠습니까?");
    
    if (confirmation) {
        window.location.href = "/movieInsight/managerDetail/deleteMember/"+memberNo;
    } 
 
}







