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
        favoriteStar.style.color = '#ffee32'; // 클릭 시 색상을 보라색으로 변경
      }
      clicked = !clicked;
  });
  
  favoriteStar.addEventListener("mouseenter", function () {
      if (!clicked) {
        favoriteStar.style.color = 'white'; // 마우스를 올렸을 때 파란색으로 변경
      } else {
        favoriteStar.style.color = 'white'; // 클릭한 상태에서 마우스를 올렸을 때 보라색으로 변경
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
function selectFacilityCommentList() {
    fetch("/movieInsight/cinemaDetail/comment/select?cinemaName=" + cinemaName)
        .then(response => response.json()) 
        .then(list => {
            console.log(list);
            
            const commentListTable = document.getElementById('comment-list-table-facility');
            commentListTable.innerHTML = ''; // 기존 테이블 내용 초기화

            for (let comment of list) {
                if(comment.cinemaCommentType === '시설'){
                    const gradeTr = document.createElement('tr');
                    gradeTr.classList.add('comment-grade-tr');
                    const gradeTd = document.createElement('td');
                    // gradeTd.innerText = `평점 ${comment.cinemaGrade}`;
                    // gradeTr.appendChild(gradeTd);
                    gradeTd.innerText = `평점`;

                    const gradeSpan = document.createElement('span');
                    gradeSpan.setAttribute('data-cinemaGrade', `${comment.cinemaGrade}`);
                    gradeSpan.classList.add('commentCinemaGrade');
                    gradeSpan.innerText = comment.cinemaGrade;
                    gradeTr.appendChild(gradeTd);
                    gradeTr.appendChild(gradeSpan);   
    
                    const contentTr = document.createElement('tr');
                    contentTr.classList.add('comment-content-tr');
                    // contentTr.style.border = '2px solid red';
    
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
                    contentTd.classList.add('comment-list-content');
                    contentTd.classList.add('comment-content');
                    contentTd.innerText = comment.cinemaCommentContent;
    
                    const dateTd = document.createElement('td');
                    dateTd.classList.add('comment-list-date');
                    dateTd.innerText = comment.cinemaCommentDate;
    
                    const editTd = document.createElement('td');
                    editTd.classList.add('comment-list-edit');
                    
                    console.log("memberid :" + memberId);
                    console.log("commentWriter :" + comment.commentCinemaWriter);
                    if (comment.commentCinemaWriter === memberId) {
                        const editBtn = document.createElement('button');
                        editBtn.classList.add('editBtn');
                        editBtn.innerText = '수정';
                        editBtn.onclick = function() {
                            updateComment(comment.cinemaCommentNo);
                        };
    
                        const deleteBtn = document.createElement('button');
                        deleteBtn.classList.add('deletBtn');
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

// 몰라 똑같은 코드 반복적이게 써.. 
function selectGoodCommentList() {
    fetch("/movieInsight/cinemaDetail/comment/select?cinemaName=" + cinemaName)
        .then(response => response.json()) 
        .then(list => {
            console.log(list);
            const commentListTable = document.getElementById('comment-list-table-good');
            commentListTable.innerHTML = ''; // 기존 테이블 내용 초기화

            for (let comment of list) {
                if(comment.cinemaCommentType === '친절도'){
                    const gradeTr = document.createElement('tr');
                    gradeTr.classList.add('comment-grade-tr');
                    const gradeTd = document.createElement('td');
                    // gradeTd.innerText = `평점 ${comment.cinemaGrade}`;
                    // gradeTr.appendChild(gradeTd);
                    gradeTd.innerText = `평점`;

                    const gradeSpan = document.createElement('span');
                    gradeSpan.setAttribute('data-cinemaGrade', `${comment.cinemaGrade}`);
                    gradeSpan.classList.add('commentCinemaGrade');
                    gradeSpan.innerText = comment.cinemaGrade;
                    gradeTr.appendChild(gradeTd);
                    gradeTr.appendChild(gradeSpan);                


                    const contentTr = document.createElement('tr');
                    contentTr.classList.add('comment-content-tr');
                    // contentTr.style.border = '2px solid red';
                
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
                    contentTd.classList.add('comment-list-content');
                    contentTd.classList.add('comment-content');
                    contentTd.innerText = comment.cinemaCommentContent;
                
                    const dateTd = document.createElement('td');
                    dateTd.classList.add('comment-list-date');
                    dateTd.innerText = comment.cinemaCommentDate;
                
                    const editTd = document.createElement('td');
                    editTd.classList.add('comment-list-edit');
                    
                    console.log("memberid :" + memberId);
                    console.log("commentWriter :" + comment.commentCinemaWriter);
                    if (comment.commentCinemaWriter === memberId) {
                        const editBtn = document.createElement('button');
                        editBtn.classList.add('editBtn');
                        editBtn.innerText = '수정';
                        editBtn.onclick = function() {
                            updateComment(comment.cinemaCommentNo);
                        };
                
                        const deleteBtn = document.createElement('button');
                        deleteBtn.classList.add('deletBtn');
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

console.log("평점 : " + cinemaRating);

/*
document.addEventListener('DOMContentLoaded', function() {
    var grade = cinemaRating; // 평점
    var fullIcons = Math.floor(grade); //정수
    var partialIconPercentage = Math.floor(grade % 1 * 100);  // 소수
    var padding = 15;

    console.log(fullIcons);
    console.log(partialIconPercentage);

    // 정수
    for (var i = 0; i < fullIcons; i++) {
        var icon = document.createElement('i');
        icon.classList.add('fa-solid', 'fa-crown', 'filled' , 'grade-container-i');
        document.querySelector('.grade-color').appendChild(icon);
    }

    // 소수점
    if (partialIconPercentage > 0) {
        var partialIcon = document.createElement('i');
        partialIcon.classList.add('fa-solid', 'fa-crown', 'filled' , 'grade-container-i');
        partialIcon.style.clipPath = 'polygon(0% 0%, ' + (partialIconPercentage - (padding / window.innerWidth) * 100) + '% 0%, ' + (partialIconPercentage - (padding / window.innerWidth) * 100) + '% 100%, 0% 100%)';        document.querySelector('.grade-container').appendChild(partialIcon);
        document.querySelector('.grade-color').appendChild(partialIcon);
    }
});

*/

document.addEventListener('DOMContentLoaded', function() {
    var grade = cinemaRating; // 평점
    var fullIcons = Math.floor(grade); //정수
    var partialIconPercentage = Math.floor(grade % 1 * 100);  // 소수

    // 정수
    for (var i = 0; i < fullIcons; i++) {
        var icon = document.createElement('i');
        icon.classList.add('fa-solid', 'fa-crown', 'filled' , 'grade-container-i');
        document.querySelector('.grade-color').appendChild(icon);
    }

    // 소수점
    if (partialIconPercentage > 0) {
        var partialIcon = document.createElement('i');
        partialIcon.classList.add('fa-solid', 'fa-crown', 'filled' , 'grade-container-i');
        partialIcon.style.clipPath = 'polygon(0% 0%, ' + partialIconPercentage + '% 0%, ' + partialIconPercentage + '% 100%, 0% 100%)';
        document.querySelector('.grade-color').appendChild(partialIcon);
    }
});


// 영화관 아이콘
function updateGradeIcons(grade) {
    var fullIcons = Math.floor(grade); //정수
    var partialIconPercentage = Math.floor(grade % 1 * 100);  // 소수
    var padding = 15;

    var gradeContainer = document.querySelector('.grade-color');
    gradeContainer.innerHTML = ''; // 기존의 아이콘을 모두 제거

    // 정수 
    for (var i = 0; i < fullIcons; i++) {
        var icon = document.createElement('i');
        icon.classList.add('fa-solid', 'fa-crown', 'filled', 'grade-container-i');
        gradeContainer.appendChild(icon);
    }

    // 소수
    if (partialIconPercentage > 0) {
        var partialIcon = document.createElement('i');
        partialIcon.classList.add('fa-solid', 'fa-crown', 'filled', 'grade-container-i');
        partialIcon.style.clipPath = 'polygon(0% 0%, ' + (partialIconPercentage - (padding / window.innerWidth) * 100) + '% 0%, ' + (partialIconPercentage - (padding / window.innerWidth) * 100) + '% 100%, 0% 100%)';
        gradeContainer.appendChild(partialIcon);
    }
}


// 영화관 평점 ajax
function selectCinemaGradeUpdate() {
    fetch("/movieInsight/cinemaDetail/update/grade?cinemaName=" + cinemaName)
    .then(response => response.json()) 
    .then(cinema => {
        console.log(cinema);
        console.log(cinema.sumCinemaGrade);

        const gradeElement = document.getElementById('grade');

        if (gradeElement) {
            gradeElement.textContent = `평점 : ${cinema.sumCinemaGrade}`;
            updateGradeIcons(cinema.sumCinemaGrade); // 아이콘 색 업데이트
        } else {
            console.error("Element with id 'grade' not found.");
        }
    })
    .catch(err => console.error(err));
}



// ajax 진행 후 등급 초기화 
function resetGrade(gradeType) {
    grades[gradeType] = 0;

    if (gradeType === 'facility') {
        facilityGrade = 0;
    } else if (gradeType === 'comment') {
        commentGrade = 0;
    } else if (gradeType === 'menu') {
        menuGrade = 0;
    }

    // 등급 아이콘 초기화
    const thumbs = document.querySelectorAll(`.${gradeType}Grade`);
    thumbs.forEach(thumb => {
        thumb.classList.remove('fas');
        thumb.classList.add('far');
        thumb.style.color = '';
    });
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

        // 4) AJAX를 이용해서 댓글 내용 DB에 저장(INSERT)
        fetch("/movieInsight/cinemaDetail/comment/insert?commentValue="+commentValue + "&cinemaName="+cinemaName + "&cinemaGrade=" + cinemaGrade + "&cinemaCommentType=" + cinemaCommentType)
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){ // 등록 성공
                alert("댓글이 등록되었습니다.");

                commentContent[i].value = ""; // 작성했던 댓글 삭제

                selectFacilityCommentList();
                selectGoodCommentList();
                selectCinemaGradeUpdate();

                // .facilityGrade 클래스를 가진 요소들을 초기화
                const facilityGradeIcons = document.querySelectorAll('.facilityGrade');
                facilityGradeIcons.forEach(icon => icon.classList.remove('fas', 'far'));
                facilityGradeIcons.forEach(icon => icon.classList.add('far'));

                const commentGradeIcons = document.querySelectorAll('.commentGrade');
                commentGradeIcons.forEach(icon => icon.classList.remove('fas', 'far'));
                commentGradeIcons.forEach(icon => icon.classList.add('far'));

                // 시설 등급 초기화
                resetGrade('facility');
                resetGrade('comment');
                resetGrade('menu');

                
                
    
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
                selectFacilityCommentList();
                selectGoodCommentList();

            }else{
                alert("삭제 실패");
            }
        })
        .catch(err => console.log(err));

    }
}

 

/*
// 베스트 메뉴 다시 조회 (ajax) 
document.addEventListener('DOMContentLoaded', function() {
    function selectBestMenuList() {
        fetch("/movieInsight/cinemaDetail/menu/select?cinemaName=" + cinemaName)
            .then(response => response.json()) 
            .then(bestMenu => {
                const popcornDiv = document.querySelector('.popcorn');
                const beverageDiv = document.querySelector('.beverage');
                const snackDiv = document.querySelector('.snack');

                const createMenuDiv = (menu, menuCategory) => {
                    const menuWrapper = document.createElement('div');
                    menuWrapper.classList.add('menu-wrapper');
                    const img = document.createElement('img');
                    img.src = `/movieInsight/resources/images/menu/${menu.menuCategory}/${menu.menuImg}`;
                    img.id = `img_${menuCategory}`;
                    menuWrapper.appendChild(img);

                    const nameDiv = document.createElement('div');
                    nameDiv.innerText = `이름 : ${menu.menuName}`;

                    const priceDiv = document.createElement('div');
                    priceDiv.innerText = `가격 : ${menu.menuPrice}원`;

                    const scoreDiv = document.createElement('div');
                    scoreDiv.classList.add(`score_${menuCategory}`);
                    scoreDiv.innerHTML = `${menu.menuGrade} 
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>`;

                    const menuDiv = document.createElement('div');
                    menuDiv.classList.add('menu');
                    menuDiv.appendChild(menuWrapper);
                    menuDiv.appendChild(nameDiv);
                    menuDiv.appendChild(priceDiv);
                    menuDiv.appendChild(scoreDiv);

                    return menuDiv;
                };

                popcornDiv.innerHTML = '';
                beverageDiv.innerHTML = '';
                snackDiv.innerHTML = '';

                if (bestMenu.bestPopCorn === null) {
                    const noPopCornDiv = document.createElement('div');
                    noPopCornDiv.innerHTML = '<h1>아직 등록된 팝콘 평점이 존재하지 않습니다.</h1><h1>팝콘에 대한 평점을 남겨주세요!!</h1>';
                    popcornDiv.appendChild(noPopCornDiv);
                } else {
                    const menuDiv = createMenuDiv(bestMenu.bestPopCorn, 'popcorn');
                    popcornDiv.appendChild(menuDiv);
                }

                if (bestMenu.bestDrink === null) {
                    const noDrinkDiv = document.createElement('div');
                    noDrinkDiv.innerHTML = '<h1>아직 등록된 음료 평점이 존재하지 않습니다.</h1><h1>음료에 대한 평점을 남겨주세요!!</h1>';
                    beverageDiv.appendChild(noDrinkDiv);
                } else {
                    const menuDiv = createMenuDiv(bestMenu.bestDrink, 'beverage');
                    beverageDiv.appendChild(menuDiv);
                }

                if (bestMenu.bestSnack === null) {
                    const noSnackDiv = document.createElement('div');
                    noSnackDiv.innerHTML = '<h1>아직 등록된 스낵 평점이 존재하지 않습니다.</h1><h1>스낵에 대한 평점을 남겨주세요!!</h1>';
                    snackDiv.appendChild(noSnackDiv);
                } else {
                    const menuDiv = createMenuDiv(bestMenu.bestSnack, 'snack');
                    snackDiv.appendChild(menuDiv);
                }
            })
            .catch(err => console.log(err));
    }

    const button = document.getElementById('menuCommentSubmit'); // 버튼의 ID를 여기에 넣으세요
    button.addEventListener('click', selectBestMenuList);
});
*/


/*
// 메뉴 ajax 갱신 시간 남으면 할게요;. 
function selectBestMenuList() {
    fetch("/movieInsight/cinemaDetail/menu/select?cinemaName=" + cinemaName)
        .then(response => response.json()) 
        .then(bestMenu => {
            const recommendedMenuDiv = document.querySelector('.recommended_menu');
            recommendedMenuDiv.innerHTML = '';

            const popcornDiv = document.querySelector('.popcorn');
            const beverageDiv = document.querySelector('.beverage');
            const snackDiv = document.querySelector('.snack');

            const createMenuDiv = (menu, menuCategory) => {
                const menuWrapper = document.createElement('div');
                menuWrapper.classList.add('menu-wrapper');
                const img = document.createElement('img');
                img.src = `/movieInsight/resources/images/menu/${menu.menuCategory}/${menu.menuImg}`;
                img.id = `img_${menuCategory}`;
                menuWrapper.appendChild(img);
            
                const nameDiv = document.createElement('div');
                nameDiv.innerText = `이름 : ${menu.menuName}`;
            
                const priceDiv = document.createElement('div');
                priceDiv.innerText = `가격 : ${menu.menuPrice}원`;
            
                const scoreDiv = document.createElement('div');
                scoreDiv.classList.add(`score_${menuCategory}`);
                scoreDiv.innerHTML = `${menu.menuGrade} 
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>`;
            
                const menuDiv = document.createElement('div');
                menuDiv.classList.add('menu');
                menuDiv.appendChild(menuWrapper);
                menuDiv.appendChild(nameDiv);
                menuDiv.appendChild(priceDiv);
                menuDiv.appendChild(scoreDiv);
            
                return menuDiv;
            };
            

            popcornDiv.innerHTML = '';
            beverageDiv.innerHTML = '';
            snackDiv.innerHTML = '';

            if (bestMenu.bestPopCorn === null) {
                const noPopCornDiv = document.createElement('div');
                noPopCornDiv.innerHTML = '<h1>아직 등록된 팝콘 평점이 존재하지 않습니다.</h1><h1>팝콘에 대한 평점을 남겨주세요!!</h1>';
                popcornDiv.appendChild(noPopCornDiv);
            } else {
                const menuDiv = createMenuDiv(bestMenu.bestPopCorn, 'popcorn');
                popcornDiv.appendChild(menuDiv);
            }

            if (bestMenu.bestDrink === null) {
                const noDrinkDiv = document.createElement('div');
                noDrinkDiv.innerHTML = '<h1>아직 등록된 음료 평점이 존재하지 않습니다.</h1><h1>음료에 대한 평점을 남겨주세요!!</h1>';
                beverageDiv.appendChild(noDrinkDiv);
            } else {
                const menuDiv = createMenuDiv(bestMenu.bestDrink, 'beverage');
                beverageDiv.appendChild(menuDiv);
            }

            if (bestMenu.bestSnack === null) {
                const noSnackDiv = document.createElement('div');
                noSnackDiv.innerHTML = '<h1>아직 등록된 스낵 평점이 존재하지 않습니다.</h1><h1>스낵에 대한 평점을 남겨주세요!!</h1>';
                snackDiv.appendChild(noSnackDiv);
            } else {
                const menuDiv = createMenuDiv(bestMenu.bestSnack, 'snack');
                snackDiv.appendChild(menuDiv);
            }
        })
        .catch(err => console.log(err));
}

*/


// 메뉴 댓글(댓글 내용은 없음 )
const menuComment  = document.getElementById("menuCommentSubmit");
console.log(menuComment);

menuComment.addEventListener("click", e => { 

    console.log("누름");

    const selectMenu = document.getElementById("menuSelect").value;

    console.log("내용보기 :" + selectMenu);
    console.log("메뉴 :" + menuGrade);

    var menuSelect = document.getElementById('menuSelect');

    menuSelect = menuSelect.options[menuSelect.selectedIndex].value;

    console.log(menuSelect);

    if(memberNo == ""){ // 로그인 X
        alert("로그인 후 이용해주세요~");
        return;
    }


    // 2) 댓글 내용이 작성되어있나?
    if(menuSelect === '' ){ // 미작성인 경우
        alert("메뉴를 선택한 후  버튼을 클릭해주세요.");
        selectMenu.focus();
        return;
    }else if(menuGrade == 0){
        alert("1점 이상 체크한 후 버튼을 클릭해주세요.");
        return;
    }

    // 4) AJAX를 이용해서 댓글 내용 DB에 저장(INSERT)
    fetch("/movieInsight/cinemaDetail/menu/insert?menuSelect="+menuSelect + "&cinemaName="+cinemaName + "&menuGrade=" + menuGrade)
    .then(resp => resp.text())
    .then(result => {
        if(result > 0){ // 등록 성공
            alert("메뉴 평점이 등록되었습니다.");

            const selectMenu = document.getElementById('menuSelect');
            selectMenu.value = ''; 

            // selectBestMenuList();

            selectCinemaGradeUpdate();

            const commentGradeIcons = document.querySelectorAll('.menuGrade');
            commentGradeIcons.forEach(icon => icon.classList.remove('fas', 'far'));
            commentGradeIcons.forEach(icon => icon.classList.add('far'));
            
            // menuGrade를 0으로 초기화
            menuGrade = 0;
        
            // 시설 등급 초기화
            resetGrade('menu');

            
        } else { // 실패
            alert("메뉴 평점에 실패했습니다...");
        }
    })
    .catch(err => console.log(err));
});


// 수정하기 (영화관)
function updateComment(commentNo) {
    console.log("수정하기");
    console.log(commentNo);

    const commentContentTdList = document.querySelectorAll('.comment-content-tr .comment-list-content');
    const gradeElementList = document.querySelectorAll(`.comment-grade-tr .commentCinemaGrade`);
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

    for (let i = 0, k = 0; i < commentContentTdList.length; i++) {

        const currentCommentNo = commentContentTdList[i].getAttribute('data-commentNo');
        console.log("수정 No : " + currentCommentNo);
        console.log("시작 인덱스 " + i);

        if (parseInt(currentCommentNo) === commentNo) {
            const commentContent = commentContentTdList[i].innerText;
            const grade = gradeElementList[i].innerText;
            const gradeContent = parseFloat(grade);

            console.log("수정전 내용 : " + commentContent);
            console.log("수정전 평점  : " + gradeContent);

            // 입력창 교체
            const inputElement = document.createElement("input"); 
            inputElement.type = "text";
            inputElement.value = commentContent.trimLeft();
            
            console.log("교체된 값 : " + inputElement.value);
            console.log("교체된 값 : " + inputElement.value.trimLeft());

            const gradeInput = document.createElement("input");
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
        
                fetch("/movieInsight/cinemaDetail/comment/update?updatedContent=" + updatedContent + "&commentGrade=" + updatedGrade + "&commentNo=" + commentNo)
                .then(response => response.json()) 
                .then(update => {
                    console.log(update);
                    console.log("뭐 ; " + commentNo);
        
                    if(update >0 ){
                        alert("댓글이 수정되었습니다.");

                        selectFacilityCommentList();
                        selectGoodCommentList();
                        selectCinemaGradeUpdate();
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




