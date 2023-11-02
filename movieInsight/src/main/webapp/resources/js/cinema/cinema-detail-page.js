var cinemaGrade = 0;
var facilityGrade = 0;
var commentGrade = 0;
var menuGrade = 0;

const grades = {
    cinema: 0,
    facility: 0,
    comment: 0,
    menu: 0
};

function handleGrade(thumbs, gradeType) {
    thumbs.forEach((thumb, index) => {
        thumb.addEventListener('click', () => {
            grades[gradeType] = index + 1;

            console.log(grades[gradeType]);

            if (gradeType === 'facility') {
                facilityGrade = grades[gradeType]; 
            }else if (gradeType === 'comment'){
                commentGrade = grades[gradeType];
            }else if(gradeType === 'menu'){
                menuGrade = grades[gradeType];
            }

            if (thumb.classList.contains('far')) {
                thumb.classList.remove('far');
                thumb.classList.add('fas');
                thumb.style.color = 'blue';

                for (let i = 0; i < index; i++) {
                    thumbs[i].classList.remove('far');
                    thumbs[i].classList.add('fas');
                    thumbs[i].style.color = 'blue';
                }
            } else {
                thumb.classList.remove('fas');
                thumb.classList.add('far');
                thumb.style.color = '';

                for (let i = index + 1; i < thumbs.length; i++) {
                    thumbs[i].classList.remove('fas');
                    thumbs[i].classList.add('far');
                    thumbs[i].style.color = '';
                }
            }
        });
    });
}

document.addEventListener("DOMContentLoaded", function () {
    handleGrade(document.querySelectorAll('.cinemaGrade'), 'cinema');
    handleGrade(document.querySelectorAll('.facilityGrade'), 'facility');
    handleGrade(document.querySelectorAll('.commentGrade'), 'comment');
    handleGrade(document.querySelectorAll('.menuGrade'), 'menu');
});

console.log("cinemaGrade : " + cinemaGrade);





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

        console.log(typeof cinemaName);

        const data =   {"cinemaName" : cinemaName, 
                        "memberNo" : memberNo,
                        "check" : check };

        fetch("/movieInsight/cinemaDetail/favorite", {
            method : "POST",
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(data)
        })
        .then(response => response.text()) 

        .then(count => { 

            if(count == -1){ 
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



  let clicked = false;
  
  favoriteStar.addEventListener("click", function () {
      if (clicked) {
        favoriteStar.style.color = 'white'; // 클릭 후 다시 하얀색으로 변경
      } else {
        favoriteStar.style.color = 'yellow'; // 클릭 시 색상을 보라색으로 변경
      }
      clicked = !clicked;
  });
  
  favoriteStar.addEventListener("mouseenter", function () {
      if (!clicked) {
        favoriteStar.style.color = 'white'; // 마우스를 올렸을 때 파란색으로 변경
      } else {
        favoriteStar.style.color = 'yellow'; // 클릭한 상태에서 마우스를 올렸을 때 보라색으로 변경
      }
  });
  
  favoriteStar.addEventListener("mouseleave", function () {
      if (!clicked) {
        favoriteStar.style.color = 'white'; // 마우스를 내렸을 때 다시 하얀색으로 변경
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


// 댓글 AJAX 조회 
/*
function selectCinemaCommentList() {
    fetch("/movieInsight/cinemaDetail/comment/select?cinemaName=" + cinemaName)
        .then(response => response.json()) 
        .then(list => {
        console.log(list);
    
        const commentListTable = document.getElementById('comment-list-table');
        commentListTable.innerHTML = ''; // 기존 테이블 내용 초기화
    
        for(let [index, fv] of list.entries()){
            const fvRow = document.createElement("tr");
            fvRow.classList.add("comment-list-col", "movieComment"); // 기존에 있던 클래스 추가
    
            const checkTd = document.createElement("td");
            checkTd.classList.add("comment-list-check");
            const checkbox = document.createElement("input");
            checkbox.setAttribute("type", "checkbox");
            checkbox.setAttribute("name", "comment-check");
            checkbox.id = `check_${index}`; // 고유한 ID 부여
            const label = document.createElement("label");
            label.setAttribute("for", `check_${index}`);
    
            checkTd.appendChild(checkbox);
            checkTd.appendChild(label);
    
            const contentNoTd = document.createElement("td");
            contentNoTd.classList.add("comment-list-content-no");
            contentNoTd.innerText = index + 1;
    
            const titleTd = document.createElement("td");
            titleTd.classList.add("comment-list-board");
            titleTd.innerText = fv.movieTitle;
    
            const contentTd = document.createElement("td");
            contentTd.classList.add("comment-list-content");
            contentTd.innerText = fv.movieCommentContent;
    
            const dateTd = document.createElement("td");
            dateTd.classList.add("comment-list-date");
            dateTd.innerText = fv.movieCommentDate;
    
            const typeTd = document.createElement("td");
            typeTd.classList.add("comment-list-type");
            typeTd.style.display = 'none';
            typeTd.innerText = fv.movieCommentNo;
    
            const editTd = document.createElement("td");
            editTd.classList.add("comment-list-edit");
            const editButton = document.createElement("button");
            editButton.innerText = "수정";
            editButton.onclick = function() {
            updateCommentModal(fv.movieCommentNo);
            };
    
            editTd.appendChild(editButton);
    
            fvRow.appendChild(checkTd);
            fvRow.appendChild(contentNoTd);
            fvRow.appendChild(titleTd);
            fvRow.appendChild(contentTd);
            fvRow.appendChild(dateTd);
            fvRow.appendChild(typeTd);
            fvRow.appendChild(editTd);
    
            commentListTable.appendChild(fvRow);
        }
    
        })
        .catch(err => console.log(err));
    }
      */

    // 안된다구..
function selectCinemaCommentList() {
    fetch("/movieInsight/cinemaDetail/comment/select?cinemaName=" + cinemaName)
        .then(response => response.json()) 
        .then(list => {
            console.log(list);

            const commentListTable = document.getElementById('comment-list-table');
            commentListTable.innerHTML = ''; // 기존 테이블 내용 초기화
            console.log("왜 없애지도 못하는거지??");
            for (let comment of list) {
                if (comment.cinemaCommentType === '친절도') {
                    const gradeTr = document.createElement('tr');
                    gradeTr.classList.add('comment-grade-tr');
                    const gradeTd = document.createElement('td');
                    gradeTd.innerText = `평점 ${comment.cinemaGrade}`;
                    gradeTr.appendChild(gradeTd);

                    const contentTr = document.createElement('tr');
                    contentTr.classList.add('comment-content-tr');
                    contentTr.style.border = '2px solid blue';

                    const imgTd = document.createElement('td');
                    imgTd.classList.add('comment-img');
                    const writerImgWrapper = document.createElement('div');
                    writerImgWrapper.classList.add('comment-writer-img-wrapper');
                    const imgSrc = comment.writerProfile ? `/movieInsight/resources/images/member/${comment.writerProfile}` : '/movieInsight/resources/images/member/기본이미지.png';
                    const writerImg = document.createElement('img');
                    writerImg.setAttribute('src', imgSrc);
                    writerImgWrapper.appendChild(writerImg);
                    imgTd.appendChild(writerImgWrapper);

                    const idTd = document.createElement('td');
                    idTd.classList.add('comment-list-id');
                    idTd.innerText = `${comment.commentCinemaWriter} : `;

                    const contentTd = document.createElement('td');
                    contentTd.classList.add('comment-list-content', 'comment-content');
                    contentTd.innerText = comment.cinemaCommentContent;

                    const dateTd = document.createElement('td');
                    dateTd.classList.add('comment-list-date');
                    dateTd.innerText = comment.cinemaCommentDate;

                    const editTd = document.createElement('td');
                    editTd.classList.add('comment-list-edit');

                    if (comment.commentCinemaWriter === memberNo) {
                        consoel.log("멤버 같은지 확인 했음. 너 작성자구나?");
                        const editBtn = document.createElement('button');
                        editBtn.classList.add('editBtn');
                        editBtn.innerText = '수정';
                        editBtn.onclick = function() {
                            // 수정 버튼 클릭 시 동작하는 함수 호출
                            updateCommentModal(comment.cinemaCommentNo);
                        };

                        const deleteBtn = document.createElement('button');
                        deleteBtn.classList.add('deleteBtn');
                        deleteBtn.innerText = '삭제';
                        deleteBtn.onclick = function() {
                            // 삭제 버튼 클릭 시 동작하는 함수 호출
                            deleteComment(comment.cinemaCommentNo);
                        };

                        editTd.appendChild(editBtn);
                        editTd.appendChild(deleteBtn);
                    }

                    contentTr.appendChild(imgTd);
                    contentTr.appendChild(idTd);
                    contentTr.appendChild(contentTd);
                    contentTr.appendChild(dateTd);
                    contentTr.appendChild(editTd);

                    commentListTable.appendChild(gradeTr);
                    commentListTable.appendChild(contentTr);
                }
            }
        })
        .catch(err => console.log(err));
}
    

      





/* 댓글  (시설, 친절도) */ 

var addComment = document.querySelectorAll("#commentSubmit");
var commentContent = document.querySelectorAll("#commentContent");


console.log(addComment);
var cinemaCommentType = 9; // 임의의 숫자

for(let i = 0; i < addComment.length; i++){
    addComment[i].addEventListener("click", e => { 
        console.log("i의 값 " + i);
        cinemaCommentType = i;
        console.log("영화관 No : " + cinemaName);
        console.log("회원 No : " + memberNo);
   
        commentValue = commentContent[i].value;

        console.log("내용보기 :" + commentValue ) 
    
        // 1) 로그인이 되어있나? -> 전역변수 memberNo 이용
        if(memberNo == ""){ // 로그인 X
            alert("로그인 후 이용해주세요~");
            return;
        }

        // 3) 무슨 댓글인지 확인 및 값 전달
          if(cinemaCommentType  == 0){
            console.log("시설 만족도 ")
            cinemaGrade = facilityGrade;
            cinemaCommentType = "시설";
        }else if (cinemaCommentType == 1){
            cinemaGrade = commentGrade;
            cinemaCommentType = "친절도";
        }else{
            cinemaCommentType = "";
            alert("버튼을 확인해주세요");
        }  

        // 2) 댓글 내용이 작성되어있나?
        if(commentContent[i].value.trim().length == 0 ){ // 미작성인 경우
            alert("댓글을 작성한 후 버튼을 클릭해주세요.");
    
            commentContent[i].value = ""; // 띄어쓰기, 개행문자 제거
            commentContent[i].focus();

            console.log("작성된 인덱스 번호 : " + i);
            return;
        }else if(cinemaGrade == 0){
            alert("1점 이상 체크한 후 버튼을 클릭해주세요.");
            return;
        }

        console.log(cinemaCommentType );
        console.log(typeof commentContent);
        console.log(typeof commentValue);
        console.log(commentValue);

        console.log(typeof cinemaName + cinemaName);
        console.log(typeof cinemaGrade);
        console.log(typeof cinemaCommentType);

    
        // 4) AJAX를 이용해서 댓글 내용 DB에 저장(INSERT)
        fetch("/movieInsight/cinemaDetail/comment/insert?commentValue="+commentValue + "&cinemaName="+cinemaName + "&cinemaGrade=" + cinemaGrade + "&cinemaCommentType=" + cinemaCommentType)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){ // 등록 성공
                alert("댓글이 등록되었습니다.");

    
                commentContent[i].value = ""; // 작성했던 댓글 삭제
                
                console.log("요");
                selectCinemaCommentList();
    
            } else { // 실패
                alert("댓글 등록에 실패했습니다...");
            }
        })
        .catch(err => console.log(err));
    });
}

    
// 댓글 삭제
function deleteComment(cinemaCommentNo){
                    
    console.log("삭제 버튼 누름 " + cinemaCommentNo);

    if( confirm("정말로 삭제 하시겠습니까?") ){

        console.log("정말로 삭제 응답")
        fetch("/movieInsight/cinemaDetail/comment/delete?cinemaCommentNo="+ cinemaCommentNo)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
               
                alert("삭제되었습니다");
                selectCinemaCommentList();

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
            selectCinemaCommentList();
        }else{
            alert("댓글 수정 실패");
        }
    })
    .catch(err => console.log(err));

}


// 메뉴 댓글(댓글 내용은 없음 )
const menuComment  = document.getElementById("menuCommentSubmit");












function updateComment(commentNo){
    document.addEventListener("DOMContentLoaded", function () {
        const editButtons = document.querySelectorAll('.editBtn');

        editButtons.forEach((button) => {
            button.addEventListener('click', function () {
                const commentContentTd = this.parentElement.parentElement.querySelector('.comment-list-content');
                const commentContent = commentContentTd.innerText;

                // Create an input element
                const inputElement = document.createElement('input');
                inputElement.type = 'text';
                inputElement.value = commentContent;

                // Replace the td content with the input element
                commentContentTd.innerHTML = '';
                commentContentTd.appendChild(inputElement);

                // Add event listener to save changes when Enter is pressed
                inputElement.addEventListener('keyup', function (event) {
                    if (event.key === 'Enter') {
                        // Save changes and update the server with AJAX or form submission
                        const newCommentContent = this.value;

                        console.log("수정할 댓글내용 : ", newCommentContent)
                        // Restore td content
                        
                    }
                });

                this.addEventListener('click', function () {
                    const newCommentContent = inputElement.value;
                    console.log("수정할 댓글내용 : ", newCommentContent)
                
                    console.log(commentNo);



                });
            });
        });
    });
}