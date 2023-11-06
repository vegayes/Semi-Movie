
// 영화를 선택했을 때 실행되는 함수
function showMovieTable() {
    document.getElementById('fvMovieContainer').style.display = 'block';
    document.getElementById('fvCinemaContainer').style.display = 'none';
  }
  
  // 영화관을 선택했을 때 실행되는 함수
  function showCinemaTable() {
    document.getElementById('fvCinemaContainer').style.display = 'block';
    document.getElementById('fvMovieContainer').style.display = 'none';
  }
  
  
  var commentFlag = true;
  // 4) 댓글 -><영화 영화관 구분하기
  const commentMovieBtn = document.getElementById("movie-comment-btn");
  const commentCinemaBtn = document.getElementById("cinema-comment-btn");
  
  const commentMovieContainer =document.getElementById("movie-comment-container");
  const commentCinemaContainer =document.getElementById("cinema-comment-container");
  
  commentMovieBtn.addEventListener("click" ,function(){
    commentFlag = true;
    console.log("영화 누름");
  
    commentCinemaBtn.style.opacity = "0.5";
  
  
    commentMovieBtn.style.opacity = "1";
    commentMovieBtn.style.color = "black";
  
    commentMovieContainer.style.display = "";
    commentCinemaContainer.style.display = "none";
  });
  
  commentCinemaBtn.addEventListener("click" ,function(){
    commentFlag = false;
    console.log("영화관 누름");
  
    commentCinemaBtn.style.opacity = "1";
    commentCinemaBtn.style.color = "black";
  
    commentMovieBtn.style.opacity = "0.5";
  
    commentMovieContainer.style.display = "none";
    commentCinemaContainer.style.display = "";
  });
  
  
  
  
  // 4-1) 드롭다운 값 가져오기
  
  var dropdownName = document.getElementsByClassName("dropdownBtn");
  
  for (var i = 0; i < dropdownName.length; i++) {
    dropdownName[i].addEventListener("click", function(event){
      viewCinema(event.target.textContent);
    });
  }
  
  function viewCinema(type) {
    var cinemaList = document.querySelectorAll(".comment-list-col");
  
    console.log("가져온 값 " + type);
  
    for (var i = 0; i < cinemaList.length; i++) {
      var cinemaTypeElement = cinemaList[i].querySelector(".comment-list-type");
  
      if (cinemaTypeElement) { // 요소가 존재하는지 확인
        var cinemaType = cinemaTypeElement.textContent.trim().toLowerCase();
        console.log("현재 리스트의 값 :" + cinemaType);
        if (cinemaType.toLowerCase().includes(type.toLowerCase())) {
          console.log("일치함");
          cinemaList[i].style.display = "flex";
        } else {
          console.log("불일치함");
          cinemaList[i].style.display = "none";
        }
      }
    }
  }
  
  
  document.addEventListener('DOMContentLoaded', function() {
    const dropdownBtns = document.querySelectorAll('.dropdownBtn');
    
    dropdownBtns.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const selectedType = this.innerText;
            const commentListType = document.querySelectorAll('.comment-list-type');
            
            commentListType.forEach(function(type) {
                if (type.innerText === selectedType) {
                    type.parentElement.style.display = 'table-row';
                } else {
                    type.parentElement.style.display = 'none';
                }
            });
        });
    });
  });
  

// 4-2)댓글 체크박스
// 1) 체크박스 확인
function checkAllcomment(e) {

    let commentCheckCount = 0;
    document.getElementsByName("comment-check").forEach(function(v, i) {
      if(v.checked === false){
        commentCheckCount++;
      }
    });
  
    if(commentCheckCount>0) {
      document.getElementById("comment-del-All").checked = false;
    } else if(commentCheckCount === 0) {
      document.getElementById("comment-del-All").checked = true;
    }
  }
  
  
  //2-1)전체선택시 전체선택/ 다시 누르면 전체 해제
  document.getElementById("comment-del-All").addEventListener("click" ,function(){
  
    var commentAll = document.getElementById("comment-del-All");
    var commentChecks = document.getElementsByName("comment-check");
  
    for(var i = 0; i<commentChecks.length; i++){
        commentChecks[i].checked = commentAll.checked;
    }
  
  });
  
  
  //2-2)선택이 하나라도 없으면 전체 선택 취소
  document.getElementsByName("check").forEach(function(v) {
    v.addEventListener('click', checkAllList);
  });


//3) 삭제 
document.querySelector(".comment-list-del-btn").addEventListener("click", function() {

  var checkedItems = document.querySelectorAll('input[name="comment-check"]:checked');
  var a = Array.from(checkedItems);
  var selectedDelMovie = a.map(function(checkbox) {
      if(commentFlag) {
        return checkbox.getAttribute('data-commentmovieNo');
      }  else{
        return checkbox.getAttribute('data-commentCinemaNo');
      }
  });

  if (selectedDelMovie.length > 0) {
      var confirmed = confirm("선택된 댓글을 삭제하시겠습니까?");
      if (confirmed) {

          console.log(selectedDelMovie);

          if(commentFlag) {
            fetch("/movieInsight/mypage/comement/del", {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify({ delMovie: selectedDelMovie })
            })
            .then(response => response.json()) 
            .then(result => {
                console.log(result);


                if(result > 0){
                  alert("댓글 삭제 완료하였습니다.");


                }else{
                  alert("즐겨찾기 삭제 실패했습니다.")
                }
            })
            .catch(err => console.log(err));
          }else{
            fetch("/movieInsight/mypage/comement2/del", {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              },
              body: JSON.stringify({ delCinemaNo: selectedDelMovie })
            })
            .then(response => response.json()) 
            .then(result => {
                console.log(result);


                if(result > 0){
                  alert("댓글 삭제 완료하였습니다.");


                }else{
                  alert("즐겨찾기 삭제 실패했습니다.")
                }
            })
            .catch(err => console.log(err));
          }
          location.reload();
      }
    } else {
      alert("삭제할 항목을 선택해주세요."); 
  }
});

// --------------------------------------------------------------
//  댓글 수정 팝업 

// 1) 팝업창 띄우기
const modalCM = document.getElementById("comment-update-modal-box");
const modalCMContent = document.getElementById("comment-update-content");
const openModalCMBtn = document.getElementById("cmPopup");
const closeModalCMBtn = document.getElementById("cm-modal-close");
const closeModalCMBack = document.getElementById("cm-modal-back");

function modalCMOpen(){
    modalCM.style.display ="flex";
}

function modalCMClose(){
  console.log("닫기");
    modalCM.style.display = "none";
}


// // 모달창 닫기
closeModalCMBtn.addEventListener("click", modalCMClose);
closeModalCMBack.addEventListener("click", modalCMClose);


myMovieCommentRows = document.querySelectorAll(".movieComment");

// 수정 완료 후 마이페이지 댓글 비동기 조회  (영화)
function selectMypageComment(){
  fetch("/movieInsight/mypage/comment/select?memberNo=" + memberNo)
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
      // editButton.id = `updateCommentBtn_${fv.movieCommentNo}`; 
      editButton.id =  "updateCommentBtn"; 
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
};


// 수정 완료 후 마이페이지 댓글 비동기 조회  (영화관)
function selectMypageCommentCinema(){
  fetch("/movieInsight/mypage/comment2/select?memberNo=" + memberNo)
  .then(response => response.json()) 
  .then(list => {
    console.log(list);

    const commentListTable = document.getElementById('cinema-comment-list-table');
    const commentContainer = document.getElementById('cinema-comment-container');
    commentListTable.innerHTML = ''; // 기존 테이블 내용 초기화

    if (list.length === 0) {
      const emptyCommentRow = document.createElement("tr");
      emptyCommentRow.classList.add("comment-not-content");

      const emptyCommentData = document.createElement("td");
      emptyCommentData.setAttribute("colspan", "7");
      emptyCommentData.innerText = "현재 작성된 영화관 댓글이 없습니다.";

      emptyCommentRow.appendChild(emptyCommentData);
      commentListTable.appendChild(emptyCommentRow);
    } else {
      commentContainer.style.display = 'block';

      for(let [index, commentCinema] of list.entries()){
        const fvRow = document.createElement("tr");
        fvRow.classList.add("comment-list-col");

        const checkTd = document.createElement("td");
        checkTd.classList.add("comment-list-check");
        const checkbox = document.createElement("input");
        checkbox.setAttribute("type", "checkbox");
        checkbox.setAttribute("name", "comment-check");
        checkbox.id = `check_${index}`;
        const label = document.createElement("label");
        label.setAttribute("for", `check_${index}`);

        checkTd.appendChild(checkbox);
        checkTd.appendChild(label);

        const contentNoTd = document.createElement("td");
        contentNoTd.classList.add("comment-list-content-no");
        contentNoTd.innerText = index + 1;

        const titleTd = document.createElement("td");
        titleTd.classList.add("comment-list-board");
        titleTd.innerText = commentCinema.cinemaName;

        const contentTd = document.createElement("td");
        contentTd.classList.add("comment-list-content");
        contentTd.innerText = commentCinema.cinemaCommentContent;

        const dateTd = document.createElement("td");
        dateTd.classList.add("comment-list-date");
        dateTd.innerText = commentCinema.cinemaCommentDate;

        const typeTd = document.createElement("td");
        typeTd.classList.add("comment-list-type");
        typeTd.style.display = 'none';
        typeTd.innerText = commentCinema.cinemaCommentType;

        const editTd = document.createElement("td");
        editTd.classList.add("comment-list-edit");
        const editButton = document.createElement("button");
        // editButton.id = `updateCommentBtn_${fv.movieCommentNo}`; 
        editButton.id =  "updateCommentBtn"; 
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
    }

  })
  .catch(err => console.log(err));
};
















// 1-2)수정 팝업 띄우기  (영화 댓글)
function updateCommentModal(commentNo) {

  /*
  해당 요소에 등록된 이벤트 리스너를 모두 제거 (* 불안정 *)
  ?? 근데 영화 수정 -> 영화관 수정 -> 영화 수정문제도 해결?
  얘도 중복된 이벤트 리스너에 영향이 있지 않았나.. 추정 ?
  */
  const element = document.getElementById("comment-del-btn");
  const clonedElement = element.cloneNode(true);
  element.parentNode.replaceChild(clonedElement, element);

  const element2 = document.getElementById("comment-update-btn");
  const clonedElement2 = element2.cloneNode(true);
  element2.parentNode.replaceChild(clonedElement2, element2);

  modalCMOpen();
  console.log("모달창 띄우기");
  console.log("commentNo : " + commentNo);
  movieCommentNo = commentNo;

  fetch("/movieInsight/mypage/comment?commentNo=" + commentNo)
  .then(response => response.json()) 
  .then(commentInfo => {
      console.log(commentInfo);

      document.getElementById('comment-title').innerText = commentInfo.movieTitle; 
      document.getElementById('cm-update-input-comment').value = commentInfo.movieCommentContent;
      document.getElementById('cm-update-grade').value = commentInfo.movieGrade;
      document.getElementById('comment-enroll-date').innerText = commentInfo.movieCommentDate; 
       
      
  })
  .catch(err => console.log(err));

  document.getElementById("comment-del-btn").addEventListener("click", e=> {

    if(confirm("댓글 변경을 수정을 취소하시겠습니까?")){  
      selectMypageComment();  
      modalCMClose();
    }

  });

  document.getElementById("comment-update-btn").addEventListener("click", e=> {

    // 새로 작성된 댓글 내용 얻어오기
    const movieCommentContent = document.getElementById('cm-update-input-comment').value;
    const movieGrade = document.getElementById('cm-update-grade').value;


    fetch("/movieInsight/mypage/comment/update?movieCommentContent="+movieCommentContent + "&movieCommentNo=" + movieCommentNo + "&movieGrade="+movieGrade )
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){
            alert("댓글이 수정되었습니다.");
            modalCMClose();
            selectMypageComment();
        }else{
            alert("댓글 수정 실패");
        }
    })
    .catch(err => console.log(err));
  });

}

// 1-3수정 팝업 띄우기  (영화관 댓글)
function updateCommentModalCinema(cinemaCommentNo) {

  /*
  해당 요소에 등록된 이벤트 리스너를 모두 제거 (* 불안정 *)
  */
  const element = document.getElementById("comment-del-btn");
  const clonedElement = element.cloneNode(true);
  element.parentNode.replaceChild(clonedElement, element);

  const element2 = document.getElementById("comment-update-btn");
  const clonedElement2 = element2.cloneNode(true);
  element2.parentNode.replaceChild(clonedElement2, element2);

  modalCMOpen();
  console.log("모달창 띄우기");
  console.log("cinemaCommentNo : " + cinemaCommentNo);

  fetch("/movieInsight/mypage/comment2?cinemaCommentNo=" + cinemaCommentNo)
  .then(response => response.json()) 
  .then(commentInfo => {
      console.log(commentInfo);

      // document.getElementById('comment-title').innerText =  commentInfo.cinemaName  + " / " + commentInfo.cinemaCommentType; 
      document.getElementById('comment-title').innerHTML =  commentInfo.cinemaName  + " <span style='color:#eaeaea;'>&nbsp; / &nbsp;</span>  <span style='color:#fff07c;'>" 
                                                          + commentInfo.cinemaCommentType + "</span>";
      document.getElementById('cm-update-input-comment').value = commentInfo.cinemaCommentContent;
      document.getElementById('cm-update-grade').value = commentInfo.cinemaGrade;
      document.getElementById('comment-enroll-date').innerText = commentInfo.cinemaCommentDate;
       
  })
  .catch(err => console.log(err));

  document.getElementById("comment-del-btn").addEventListener("click", e=> {
    if(confirm("댓글 변경을 수정을 취소하시겠습니까?")){    
      modalCMClose();
    }

  });

  document.getElementById("comment-update-btn").addEventListener("click", e=> {

    // 새로 작성된 댓글 내용 얻어오기
    const cinemaCommentContent = document.getElementById('cm-update-input-comment').value;
    const cinemaGrade = document.getElementById('cm-update-grade').value;


    fetch("/movieInsight/mypage/comment2/update?cinemaCommentContent="+cinemaCommentContent + "&cinemaCommentNo=" + cinemaCommentNo + "&cinemaGrade="+cinemaGrade )
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){
            alert("댓글이 수정되었습니다.");
            modalCMClose();
            selectMypageCommentCinema();
        }else{
            alert("댓글 수정 실패");
        }
    })
    .catch(err => console.log(err));


  });

}