var movieGrade = 0;

document.addEventListener("DOMContentLoaded", function () {
  const thumbs = document.querySelectorAll('.fa-thumbs-up');

  thumbs.forEach((thumb, index) => {
      thumb.addEventListener('click', () => {
        console.log("인덱스: " + index);
        movieGrade = index+1;

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


const addComment = document.getElementById("commentSubmit");
const commentContent = document.getElementById("commentContent");

addComment.addEventListener("click", e => { // 댓글 등록 버튼이 클릭이 되었을 때


    console.log("영화 No : " + movieNo);
    console.log("회원 No : " + memberNo);
    console.log("movieGrade : " + movieGrade);


    // 1) 로그인이 되어있나? -> 전역변수 memberNo 이용
    if(memberNo == ""){ // 로그인 X
        alert("로그인 후 이용해주세요~");
        return;
    }

    // 2) 댓글 내용이 작성되어있나?
    if(commentContent.value.trim().length == 0){ // 미작성인 경우
        alert("댓글을 작성한 후 버튼을 클릭해주세요.");

        commentContent.value = ""; // 띄어쓰기, 개행문자 제거
        commentContent.focus();
        return;
    }

    // 3) AJAX를 이용해서 댓글 내용 DB에 저장(INSERT)
    fetch("/movieInsight/movie/comment/insert?commentContent="+commentContent.value + "&movieNo="+movieNo + "&movieGrade=" + movieGrade)
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){ // 등록 성공
            alert("댓글이 등록되었습니다.");

            commentContent.value = ""; // 작성했던 댓글 삭제

            // selectCommentList(); // 비동기 댓글 목록 조회 함수 호출

        } else { // 실패
            alert("댓글 등록에 실패했습니다...");
        }
    })
    .catch(err => console.log(err));
});


// 댓글 삭제
function deleteComment(movieCommentNo){
                    
    console.log("삭제 버튼 누름 " + movieCommentNo);

    if( confirm("정말로 삭제 하시겠습니까?") ){

        console.log("정말로 삭제 응답")
        fetch("/movieInsight/movie/comment/delete?movieCommentNo="+ movieCommentNo)
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


// 댓글 수정
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

const gallery = document.querySelector('.gallery');
const prevButton = document.querySelector('.prev-button');
const nextButton = document.querySelector('.next-button');

let scrollPosition = 0;

nextButton.addEventListener('click', () => {
  scrollPosition += gallery.clientWidth;
  if (scrollPosition > gallery.scrollWidth - gallery.clientWidth) {
    scrollPosition = gallery.scrollWidth - gallery.clientWidth;
  }
  gallery.style.transform = `translateX(-${scrollPosition}px)`;
});

prevButton.addEventListener('click', () => {
  scrollPosition -= gallery.clientWidth;
  if (scrollPosition < 0) {
    scrollPosition = 0;
  }
  gallery.style.transform = `translateX(-${scrollPosition}px)`;
});




// 수정할 때 이미지 변경하면 미리보기 출력되게
if (document.getElementById("fileInput")) {
    // 파일 입력란의 이벤트 리스너 추가
    document.getElementById("fileInput").addEventListener("input", function(event) {
        // 선택한 파일 가져오기
        const selectedFile = event.target.files[0];

        // 이미지 요소에 미리보기 이미지 표시
        if (selectedFile) {
            const reader = new FileReader();
            reader.onload = function(e) {
                document.getElementById("movieImg").src = e.target.result;
            };
            reader.readAsDataURL(selectedFile);
        }
    });
}