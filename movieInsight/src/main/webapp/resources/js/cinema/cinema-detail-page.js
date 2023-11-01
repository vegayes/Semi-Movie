var cinemaGrade = 0;

document.addEventListener("DOMContentLoaded", function () {
    const thumbs = document.querySelectorAll('.fa-thumbs-up');
  
    thumbs.forEach((thumb, index) => {
        thumb.addEventListener('click', () => {
            cinemaGrade = index+1;

            if (thumb.classList.contains('far')) {
                thumb.classList.remove('far');
                thumb.classList.add('fas');
                thumb.style.color = 'blue'; // 따봉 색 변경 (예: 파란색)
  
                // 왼쪽 따봉을 클릭하면, 왼쪽에 있는 따봉만 채움
                for (let i = 0; i < index; i++) {
                    thumbs[i].classList.remove('far');
                    thumbs[i].classList.add('fas');
                    thumbs[i].style.color = 'blue'; // 왼쪽 따봉 색을 채움 (예: 파란색)
                }
            } else {
                thumb.classList.remove('fas');
                thumb.classList.add('far');
                thumb.style.color = ''; // 따봉 색 제거
  
                // 오른쪽 따봉을 클릭하면, 해당 따봉 위치부터 끝까지 리셋
                for (let i = index + 1; i < thumbs.length; i++) {
                    thumbs[i].classList.remove('fas');
                    thumbs[i].classList.add('far');
                    thumbs[i].style.color = ''; // 오른쪽 따봉 색을 제거
                }
            }
        });
    });
  });
  
  const star = document.getElementById("star");
  let clicked = false;
  
  star.addEventListener("click", function () {
      if (clicked) {
          star.style.color = 'white'; // 클릭 후 다시 하얀색으로 변경
      } else {
          star.style.color = 'purple'; // 클릭 시 색상을 보라색으로 변경
      }
      clicked = !clicked;
  });
  
  star.addEventListener("mouseenter", function () {
      if (!clicked) {
          star.style.color = 'blue'; // 마우스를 올렸을 때 파란색으로 변경
      } else {
          star.style.color = 'purple'; // 클릭한 상태에서 마우스를 올렸을 때 보라색으로 변경
      }
  });
  
  star.addEventListener("mouseleave", function () {
      if (!clicked) {
          star.style.color = 'white'; // 마우스를 내렸을 때 다시 하얀색으로 변경
      }
  });

  



if (document.getElementById("fileChange")) {
    // 파일 입력란의 이벤트 리스너 추가
    document.getElementById("fileChange").addEventListener("change", function(event) {
        // 선택한 파일 가져오기
        const selectedFile = event.target.files[0];

        // 이미지 요소에 미리보기 이미지 표시
        if (selectedFile) {
            const reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById("cinemaImg").src = e.target.result;
            };
            reader.readAsDataURL(selectedFile);
        }
    });
}

if (document.getElementById("fileInput")) {
    // 파일 입력란의 이벤트 리스너 추가
    document.getElementById("fileInput").addEventListener("input", function(event) {
        // 선택한 파일 가져오기
        const selectedFile = event.target.files[0];

        // 이미지 요소에 미리보기 이미지 표시
        if (selectedFile) {
            const reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById("cinemaImg").src = e.target.result;
            };
            reader.readAsDataURL(selectedFile);
        }
    });
}





/* 댓글  (시설, 친절도) */ 

var addComment = document.querySelectorAll("#commentSubmit");
var commentContent = document.querySelectorAll("#commentContent");


console.log(addComment);

for(let i = 0; i < addComment.length; i++){
    addComment[i].addEventListener("click", e => { 
        var cinemaCommentType = "";
        console.log("영화관 No : " + cinemaNo);
        console.log("회원 No : " + memberNo);
   
        commentValue = commentContent[i].value;

        console.log("내용보기 :" + commentValue ) 
    
        // 1) 로그인이 되어있나? -> 전역변수 memberNo 이용
        if(memberNo == ""){ // 로그인 X
            alert("로그인 후 이용해주세요~");
            return;
        }
    
        // 2) 댓글 내용이 작성되어있나?
        if(commentContent[i].value.trim().length == 0 ){ // 미작성인 경우
            alert("댓글을 작성한 후 버튼을 클릭해주세요.");
    
            commentContent[i].value = ""; // 띄어쓰기, 개행문자 제거
            commentContent[i].focus();

            console.log("작성된 인덱스 번호 : " + i);

            
            cinemaCommentType = i;

            return;
        }else if(cinemaGrade == 0){
            alert("1점 이상 체크한 후 버튼을 클릭해주세요.");
        }

        // 3) 무슨 댓글인지 확인 및 값 전달
        if(cinemaCommentType  == 0){
            console.log("시설 만족도 ")
            cinemaCommentType = "시설";
        }else if (cinemaCommentType == 1){
            cinemaCommentType = "직원";
        }else{
            cinemaCommentType = "";
            alert("버튼을 확인해주세요");
        }

        console.log(cinemaCommentType);

    
        // 4) AJAX를 이용해서 댓글 내용 DB에 저장(INSERT)
        fetch("/movieInsight/cinemaDetail/comment/insert?commentContent="+commentContent + "&cinemaNo="+cinemaNo + "&cinemaGrade=" + cinemaGrade + "&cinemaCommentType=" + cinemaCommentType)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){ // 등록 성공
                alert("댓글이 등록되었습니다.");
    
                commentContent[i].value = ""; // 작성했던 댓글 삭제
    
                // selectCommentList(); // 비동기 댓글 목록 조회 함수 호출
    
            } else { // 실패
                alert("댓글 등록에 실패했습니다...");
            }
        })
        .catch(err => console.log(err));
    });
}

    
// 댓글 삭제
function deleteComment(movieCommentNo){
                    
    console.log("삭제 버튼 누름 " + movieCommentNo);

    if( confirm("정말로 삭제 하시겠습니까?") ){

        console.log("정말로 삭제 응답")
        fetch("/movieInsight/cinema/comment/delete?cinemaCommentNo="+ cinemaCommentNo)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                alert("삭제되었습니다");
                // selectCommentList(); // 목록을 다시 조회해서 삭제된 글을 제거
            }else{
                alert("삭제 실패");
            }
        })
        .catch(err => console.log(err));

    }
}

  
    
    // 댓글 수정 (아직 안함) 
    function updateComment(commentNo, btn){
    
        // 새로 작성된 댓글 내용 얻어오기
        const commentContent = btn.parentElement.previousElementSibling.value;
    
    
        fetch("/commnet/update?commentContent="+commentContent + "&commentNo="+commentNo)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                alert("댓글이 수정되었습니다.");
                selectCommentList();
            }else{
                alert("댓글 수정 실패");
            }
        })
        .catch(err => console.log(err));
    
    }


// 메뉴 댓글(댓글 내용은 없음 )
const menuComment  = document.getElementById("menuCommentSubmit");



















// 즐겨찾기 버튼이 클릭 되었을 때
const favoriteStar = document.getElementById("favoriteStar");

if(document.getElementById("favoriteStar")) {

    favoriteStar.addEventListener("click", e => {

        // 로그인 여부 검사  빈문자열은 "" 임.
        if(memberNo == ""){
            alert("로그인 후 이용해주세요")
            return;
        }

        let check; // 버튼  X(빈 별) : 0  
                //      O(꽉찬 별) : 1

        // contains("클래스명") : 클래스가 있으면 true, 없으면 false
        if(e.target.classList.contains("fa-regular")){ // 빈 별
            check = 0;
        }else{ // 꽉찬 별
            check = 1;
        }

        const data =   {"movieNo" : movieNo , 
                        "memberNo" : memberNo,
                        "check" : check };

        fetch("/movieInsight/movie/favorite", {
            method : "POST",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(data)
        })
        .then(response => response.text()) 

        .then(count => { 

            if(count == -1){ // INSERT, DELETE 실패 시
                console.log("즐겨찾기 추가 오류");
                return;
            }

            e.target.classList.toggle("fa-regular");
            e.target.classList.toggle("fa-solid");

        }) 

        .catch(err => {
            console.log("예외 발생");
            console.log(err);
        }) 


    });

}
