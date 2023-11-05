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

// 평점 색칠하기
/*
function colorStarsBasedOnRating(rating) {
    // var starIcons = document.querySelectorAll('.fa-clapperboard'); 

   
    starIcons.forEach(function(star, index) {
        var decimalPart = rating - index;
        
        if (decimalPart >= 1) {
            star.classList.add('filled');
        } else if (decimalPart > 0) {
            star.style.width = (decimalPart * 100) + '%';
            star.classList.add('filled');
        }
    });

    
 //______________________________________________________________________________________________
    var iconElements = document.querySelectorAll('.fa-clapperboard'); // 아이콘을 나타내는 클래스명으로 변경해주세요
    var integerPart = Math.floor(rating); // 평점의 정수 부분을 얻습니다.
    var decimalPart = rating - integerPart; // 평점의 소수 부분을 얻습니다.

    // 정수 부분까지의 아이콘을 순차적으로 색칠합니다.
    for (var i = 0; i < integerPart; i++) {
        iconElements[i].style.width = '100%';
    }

    // 소수 부분이 있는 경우 해당 아이콘의 배경 색상을 변경합니다.
    if (decimalPart > 0) {
        var nextIndex = integerPart; // 다음 아이콘의 인덱스는 정수 부분의 끝입니다.
        iconElements[nextIndex].style.color = 'gold'; // 색상을 노란색으로 설정
    }
}
*/

console.log("평점 : " + movieRating);


document.addEventListener('DOMContentLoaded', function() {
    var grade = movieRating; // 평점
    var fullIcons = Math.floor(grade); //정수
    var partialIconPercentage = Math.floor(grade % 1 * 100);  // 소수

    console.log(fullIcons);
    console.log(partialIconPercentage);

    // 정수
    for (var i = 0; i < fullIcons; i++) {
        var icon = document.createElement('i');
        icon.classList.add('fa-solid', 'fa-clapperboard', 'filled' , 'grade-container-i');
        document.querySelector('.grade-color').appendChild(icon);
    }

    // 소수점
    if (partialIconPercentage > 0) {
        var partialIcon = document.createElement('i');
        partialIcon.classList.add('fa-solid', 'fa-clapperboard', 'filled' , 'grade-container-i');
        partialIcon.style.clipPath = 'polygon(0% 0%, ' + partialIconPercentage + '% 0%, ' + partialIconPercentage + '% 100%, 0% 100%)';
        document.querySelector('.grade-color').appendChild(partialIcon);
    }
});

// 영화 아이콘
function updateGradeIcons(grade) {
    var fullIcons = Math.floor(grade); //정수
    var partialIconPercentage = Math.floor(grade % 1 * 100);  // 소수
    var padding = 20;

    var gradeContainer = document.querySelector('.grade-color');
    gradeContainer.innerHTML = ''; // 기존의 아이콘을 모두 제거

    // 정수 
    for (var i = 0; i < fullIcons; i++) {
        var icon = document.createElement('i');
        icon.classList.add('fa-solid', 'fa-clapperboard', 'filled', 'grade-container-i');
        gradeContainer.appendChild(icon);
    }

    // 소수
    if (partialIconPercentage > 0) {
        var partialIcon = document.createElement('i');
        partialIcon.classList.add('fa-solid', 'fa-clapperboard', 'filled', 'grade-container-i');
        partialIcon.style.clipPath = 'polygon(0% 0%, ' + (partialIconPercentage - (padding / window.innerWidth) * 100) + '% 0%, ' + (partialIconPercentage - (padding / window.innerWidth) * 100) + '% 100%, 0% 100%)';
        gradeContainer.appendChild(partialIcon);
    }
}

// 영화 평점 ajax
function selectMovieGradeUpdate() {
    fetch("/movieInsight/movie/update/grade?movieNo=" + movieNo)
    .then(response => response.json()) 
    .then(movie => {
        console.log(movie);
        console.log(movie.sumMovieGrade);

        const gradeElement = document.getElementById('grade');

        if (gradeElement) {
            gradeElement.textContent = `평점 : ${movie.sumMovieGrade}`;
            updateGradeIcons(movie.sumMovieGrade); // 아이콘 색 업데이트
        } else {
            console.error("Element with id 'grade' not found.");
        }
    })
    .catch(err => console.error(err));
}

/*
function updateComment(commentNo) {
    console.log("수정하기");
    console.log(commentNo);

    // const commentContentTd = document.querySelector(".comment-list-content");
    const commentContentTd = document.querySelectorAll(`.comment-content-tr .comment-list-content`);
    console.log("수정 할 td" + commentContentTd);

    for(let i = 0; i < commentContentTd.length ; i++){
        commentContentTd[i].getAttribute('data-commentNo');
        console.log("수정 No : " +commentContentTd[i].getAttribute('data-commentNo') );
        if(commentContentTd[i].getAttribute('data-commentNo') === commentNo){
            console.log("수정할 수 있음.");
            const commentContent = commentContentTd[i].innerText;
            console.log("댓글 수정 내용 : "+ commentContent);
        }


    }

    const saveButton = document.createElement("button");
    saveButton.classList.add('saveBtn');
    saveButton.innerText = "제출";
    saveButton.onclick = function() {
        const updatedContent = inputElement.value;
        const updatedGrade = gradeInput.value;

        console.log("수정된 내용 : " + updatedContent);
        console.log("수정된 평점 : " + updatedGrade);

        fetch("/movieInsight/movie/comment/update?updatedContent=" + updatedContent + "&commentGrade=" + commentGrade + "&commentNo=" + commentNo)
        .then(response => response.json()) 
        .then(update => {

            console.log(update);

            if(update >0 ){
                alert("댓글이 수정되었습니다.");
            }else{
                alert("댓글 수정에 오류가 발생하였습니다.");
            }


        })
        .catch(err => console.error(err));
    };

    // const editButton = document.querySelector(`.editBtn[data-commentNo="${commentNo}"]`);
    // editButton.replaceWith(saveButton);

}*/


function selectMovieCommentList() {
    fetch("/movieInsight/movie/comment/select?movieNo=" + movieNo)
        .then(response => response.json()) 
        .then(list => {
            console.log(list);

            const commentListTable = document.getElementById('comment-list-table');
            commentListTable.innerHTML = ''; // 기존 테이블 내용 초기화

            for (let comment of list) {

                const gradeTr = document.createElement('tr');
                gradeTr.classList.add('comment-grade-tr');
                const gradeTd = document.createElement('td');
                gradeTd.innerText = `평점`;

                const gradeSpan = document.createElement('span');
                gradeSpan.setAttribute('data-movieGrade', `${comment.movieGrade}`);
                gradeSpan.classList.add('commentMovieGrade');
                gradeSpan.innerText = comment.movieGrade;
                gradeTr.appendChild(gradeTd);
                gradeTr.appendChild(gradeSpan);

                const contentTr = document.createElement('tr');
                contentTr.classList.add('comment-content-tr');
                // contentTr.style.border = '2px solid blue';

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
                idTd.innerText = `${comment.commentMovieWriter} : `;

                const contentTd = document.createElement('td');
                contentTd.classList.add('comment-list-content');
                // contentTd.classList.add('comment-content');
                contentTd.setAttribute('data-commentNo', `${comment.movieCommentNo}`);
                contentTd.innerText = comment.movieCommentContent;

                const dateTd = document.createElement('td');
                dateTd.classList.add('comment-list-date');
                dateTd.innerText = comment.movieCommentDate;

                const editTd = document.createElement('td');
                editTd.classList.add('comment-list-edit');
                
                console.log("memberid :" + memberId);
                console.log("commentWriter :" + comment.commentMovieWriter);
                if (comment.commentMovieWriter === memberId) {
                    const editBtn = document.createElement('button');
                    editBtn.classList.add('editBtn');
                    editBtn.innerText = '수정';
                    editBtn.onclick = function() {
                        updateComment(comment.movieCommentNo);
                    };

                    const deleteBtn = document.createElement('button');
                    deleteBtn.classList.add('deletBtn');
                    deleteBtn.innerText = '삭제';
                    deleteBtn.onclick = function() {
                        // 삭제 버튼 클릭 시 동작하는 함수 호출
                        deleteComment(comment.movieCommentNo);
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
        })
        .catch(err => console.log(err));
}

let isInputOpen = false;

function updateComment(commentNo) {

    if (isInputOpen) {
        alert("현재 댓글 수정 진행 중인 영역이 있습니다.");

        return;
    }

    console.log("수정하기");
    console.log(commentNo);

    const commentContentTdList = document.querySelectorAll('.comment-content-tr .comment-list-content');
    const gradeElementList = document.querySelectorAll(`.comment-grade-tr .commentMovieGrade`);
    const editButton = document.querySelectorAll(".editBtn");

    var commentListIdElement = document.querySelectorAll('.comment-list-id'); // 해당 요소를 가져옵니다.
    var commentIds = [];
    commentListIdElement.forEach(function(comment) {
        var id = comment.innerText.replace(" :",""); // 각 댓글의 data-id 값을 가져옵니다.
        commentIds.push(id); // 배열에 추가합니다.
      });


    console.log("댓글 작성자 : " + commentIds);
    console.log("개수 : " + editButton.length);
    console.log("grade요소 확인 : " + gradeElementList);

    for (let i = 0 , k = 0; i < commentContentTdList.length; i++) {
        const currentCommentNo = commentContentTdList[i].getAttribute('data-commentNo');
        console.log("수정 No : " + typeof currentCommentNo);

        console.log("내가 누른 인덱스 번호 : " + i);

        if (parseInt(currentCommentNo) === commentNo) {
            isInputOpen = true; 
            const commentContent = commentContentTdList[i].innerText;
            const grade = gradeElementList[i].innerText;
            const gradeContent = parseFloat(grade);

            console.log("수정전 내용 : " + commentContent);
            console.log("수정전 평점  : " + gradeContent);

            // 입력창 교체
            const inputElement = document.createElement("input"); 
            inputElement.classList.add("inputUpdate"); 
            inputElement.type = "text";
            inputElement.value = commentContent.trimLeft();
            
            console.log("교체된 값 : " + inputElement.value);
            console.log("교체된 값 : " + inputElement.value.trimLeft());

            const gradeInput = document.createElement("input");
            gradeInput.classList.add("inputGrade");
            gradeInput.type = "number";
            gradeInput.value = gradeContent;

            gradeElementList[i].textContent  = "";
            gradeElementList[i].appendChild(gradeInput);
        
            commentContentTdList[i].textContent  = "";
            commentContentTdList[i].appendChild(inputElement);

            inputElement.focus();

            const saveButton = document.createElement("button");
            saveButton.classList.add('saveBtn');
            saveButton.innerText = "제출";
            saveButton.onclick = function() {
                const updatedContent = inputElement.value;
                const updatedGrade = gradeInput.value;
        
                console.log("수정된 내용 : " + updatedContent);
                console.log("수정된 평점 : " + updatedGrade);
        
                fetch("/movieInsight/movie/comment/update?updatedContent=" + updatedContent + "&commentGrade=" + updatedGrade + "&commentNo=" + commentNo)
                .then(response => response.json()) 
                .then(update => {
                    console.log(update);
        
                    if(update >0 ){
                        alert("댓글이 수정되었습니다.");
                        selectMovieCommentList();
                        selectMovieGradeUpdate();
                        isInputOpen = false; 
                    }else{
                        alert("댓글 수정에 오류가 발생하였습니다.");
                    }
        
        
                })
                .catch(err => console.error(err));
            };
            console.log("안녕 " + i);
            editButton[k].replaceWith(saveButton);
        }
        console.log(commentIds[i]);
        console.log(memberId);
        console.log(commentIds[i] === memberId);
        if(commentIds[i] === memberId){
            k++;
        }
        console.log("k :" + k);
    }
}



// 영화 각 평점  아이콘
function memberGrade(grade) {
    var fullIcons = Math.floor(grade); //정수
    var partialIconPercentage = Math.floor(grade % 1 * 100);  // 소수
    var padding = 20;

    var gradeContainer = document.querySelector('.memberGrade');
    gradeContainer.innerHTML = ''; // 기존의 아이콘을 모두 제거

    // 정수 
    for (var i = 0; i < fullIcons; i++) {
        var icon = document.createElement('i');
        icon.classList.add('fa-solid', 'fa-clapperboard', 'filled', 'grade-container-member');
        gradeContainer.appendChild(icon);
    }

    // 소수
    if (partialIconPercentage > 0) {
        var partialIcon = document.createElement('i');
        partialIcon.classList.add('fa-solid', 'fa-clapperboard', 'filled', 'grade-container-member');
        partialIcon.style.clipPath = 'polygon(0% 0%, ' + (partialIconPercentage - (padding / window.innerWidth) * 100) + '% 0%, ' + (partialIconPercentage - (padding / window.innerWidth) * 100) + '% 100%, 0% 100%)';
        gradeContainer.appendChild(partialIcon);
    }
}

document.addEventListener('DOMContentLoaded', function() {
    var memberGrades = document.querySelectorAll('.memberGrade');

    memberGrades.forEach(function(gradeElement) {
        var grade = gradeElement.getAttribute('data-movieGrade');
        memberGrade(grade, gradeElement);
    });
});

  



    

function resetGrade() {

    movieGrade = 0;

    // 등급 아이콘 초기화
    const thumbs = document.querySelectorAll(`.fa-thumbs-up`);
    thumbs.forEach(thumb => {
        thumb.classList.remove('fas');
        thumb.classList.add('far');
        thumb.style.color = '';
    });
}




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
    }else if (movieGrade == 0){
        alert("1점 이상 체크한 후 버튼을 클릭해주세요.");
        return;
    }




    // 3) AJAX를 이용해서 댓글 내용 DB에 저장(INSERT)
    fetch("/movieInsight/movie/comment/insert?commentContent="+commentContent.value + "&movieNo="+movieNo + "&movieGrade=" + movieGrade)
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){ // 등록 성공
            alert("댓글이 등록되었습니다.");

            commentContent.value = ""; // 작성했던 댓글 삭제

            selectMovieCommentList();
            selectMovieGradeUpdate();
            resetGrade();

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
                selectMovieCommentList();
                selectMovieGradeUpdate();
            }else{
                alert("삭제 실패");
            }
        })
        .catch(err => console.log(err));

    }
}

/*
// 댓글 수정 (아직 안함) btn이 왜 있었지?
function updateComment(commentNo){

    // 새로 작성된 댓글 내용 얻어오기
    const commentContent = btn.parentElement.previousElementSibling.value;
    fetch("/commnet/update?commentContent="+commentContent + "&commentNo="+commentNo)
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){
            alert("댓글이 수정되었습니다.");
            selectMovieCommentList();
            selectMovieGradeUpdate();
        }else{
            alert("댓글 수정 실패");
        }
    })
    .catch(err => console.log(err));

}
*/

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



const star = document.getElementById("star");
let clicked = false;

if(document.getElementById("star")) {

    star.addEventListener("click", function () {
        if (clicked) {
            star.style.color = 'white'; // 클릭 후 다시 하얀색으로 변경
        } else {
            star.style.color = '#ffee32'; // 클릭 시 색상을 보라색으로 변경
        }
        clicked = !clicked;
    });
    
    star.addEventListener("mouseenter", function () {
        if (!clicked) {
            star.style.color = 'white'; // 마우스를 올렸을 때 파란색으로 변경
        } else {
            star.style.color = 'white'; // 클릭한 상태에서 마우스를 올렸을 때 보라색으로 변경
        }
    });
    
    star.addEventListener("mouseleave", function () {
        if (!clicked) {
            star.style.color = 'white'; // 마우스를 내렸을 때 다시 하얀색으로 변경
        }
    });

    
}



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