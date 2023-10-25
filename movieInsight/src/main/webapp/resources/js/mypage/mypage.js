// ** smooth 이동
document.querySelectorAll('a[href^="#mypage"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
        e.preventDefault();
        
        const headerHeight = 88; // 헤더의 높이
        const first = 0;
        const second = 840;
        const thrid = 1680;
        const forth = 2520;
        
        const targetId = this.getAttribute('href').substring(1);
        const targetElement = document.getElementById(targetId);

        if (targetElement) {
          var offsetPosition = first;
        	if(targetId == "mypage-visit-history") offsetPosition = second;
          else if(targetId == "mypage-comment-list") offsetPosition = thrid;
          else if(targetId == "mypage-modify-member-info") offsetPosition = forth;
          
            /*
            const targetPosition = targetElement.getBoundingClientRect().top;
            const offsetPosition = targetPosition - headerHeight;
            */
            window.scrollTo({
                top: offsetPosition,
                behavior: 'smooth'
            });
        }
    });
});


// 1) 모달창 띄우기

const modal = document.getElementById("favorite-list-modal-box");
const modalContent = document.getElementById("favorite-list-modal-content");
const openModalBtn = document.getElementById("favorite-popup-open");
const closeModalBtn = document.getElementById("fv-modal-close");
const closeModalBack = document.getElementById("fv-modal-back");

function modalOpen(){
    modal.style.display ="flex";
}

function modalClose(){

  console.log("닫기");
    modal.style.display = "none";
}


// 모달창 열기
openModalBtn.addEventListener("click", modalOpen);
// 모달창 닫기
closeModalBtn.addEventListener("click", modalClose);
closeModalBack.addEventListener("click", modalClose);







// 1-1)모달 안에서의 체크박스
// 1) 체크박스 확인
function checkAllList(e) {

    let checkCount = 0;
    document.getElementsByName("favorite-check").forEach(function(v, i) {
      if(v.checked === false){
        checkCount++;
      }
    });

    if(checkCount>0) {
      document.getElementById("favorite-check-All").checked = false;
    } else if(checkCount === 0) {
      document.getElementById("favorite-check-All").checked = true;
    }
}


//2-1)전체선택시 전체선택/ 다시 누르면 전체 해제
document.getElementById("favorite-check-All").addEventListener("click" ,function(){

    var commentAll = document.getElementById("favorite-check-All");
    var commentChecks = document.getElementsByName("favorite-check");

    for(var i = 0; i<commentChecks.length; i++){
        commentChecks[i].checked = commentAll.checked;
    }

});


//2-2)선택이 하나라도 없으면 전체 선택 취소
document.getElementsByName("favorite-check").forEach(function(v) {
    v.addEventListener('click', checkAllList);
});







// 3) 즐겨찾기 -> 영화관, 영화 구분하기
const movieBtn = document.getElementById("movie-favorite-btn");
const cinemaBtn = document.getElementById("cinema-favorite-btn");

const favoriteMovieContainer =document.getElementById("movie-favorite-container");
const favoriteCinemaContainer =document.getElementById("cinema-favorite-container");



cinemaBtn.addEventListener("click" ,function(){
    
  console.log("영화 누름");

  cinemaBtn.style.opacity = "1";
  cinemaBtn.style.color = "black";

  movieBtn.style.opacity = "0.5";

  favoriteMovieContainer.style.display = "none";
  favoriteCinemaContainer.style.display = "";
});


movieBtn.addEventListener("click" ,function(){
    
  console.log("영화관 누름");

  cinemaBtn.style.opacity = "0.5";


  movieBtn.style.opacity = "1";
  movieBtn.style.color = "black";

  favoriteMovieContainer.style.display = "";
  favoriteCinemaContainer.style.display = "none";
});







// 5) 체크박스 하나만 선택하게하기 ( 성별 )
// const divCheckboxes = document.querySelectorAll('.modify-gender');

// // 클릭 이벤트를 처리하는 함수
// function handleCheckboxInteraction() {
//   const checkbox = this.querySelector('input[type="checkbox"]');
//   const label = this.querySelector('label');

//   // 모든 체크박스의 배경색과 레이블 색상을 초기화
//   divCheckboxes.forEach((otherDiv) => {
//     otherDiv.style.backgroundColor = '';
//     otherDiv.querySelector('label').style.color = ''; 
//   });

//   // 선택된 체크박스의 배경색과 레이블 색상을 변경
//   this.style.backgroundColor = 'blue';
//   label.style.color = 'white';
//   checkbox.checked = !checkbox.checked; // 체크박스 상태 변경
// }

// // 각 div 요소에 클릭 이벤트 리스너를 추가합니다.
// divCheckboxes.forEach((divCheckbox) => {
//   divCheckbox.addEventListener('click', handleCheckboxInteraction);
// });
// 4) 파일 열기

// document.addEventListener("DOMContentLoaded", function() {
//   const fileTrigger = document.getElementById("file-trigger");
//   const fileInput = document.getElementById("file-input");

//   fileTrigger.addEventListener("click", function() {
//       fileInput.click(); // 파일 업로드 input 열기
//   });
// });



// 5) 체크박스 하나만 선택하게하기 ( 성별 )
const divCheckboxes = document.querySelectorAll('.modify-gender');

// 클릭 이벤트를 처리하는 함수
function handleCheckboxInteraction() {
  const checkbox = this.querySelector('input[type="checkbox"]');
  const label = this.querySelector('label');

  // 모든 체크박스의 배경색과 레이블 색상을 초기화
  divCheckboxes.forEach((otherDiv) => {
    otherDiv.style.backgroundColor = '';
    otherDiv.querySelector('label').style.color = ''; 
  });

  // 선택된 체크박스의 배경색과 레이블 색상을 변경
  this.style.backgroundColor = 'blue';
  label.style.color = 'white';
  checkbox.checked = !checkbox.checked; // 체크박스 상태 변경
}

// 각 div 요소에 클릭 이벤트 리스너를 추가합니다.
divCheckboxes.forEach((divCheckbox) => {
  divCheckbox.addEventListener('click', handleCheckboxInteraction);
});







//6) 
// Swiper 초기화 및 설정
// const swiper = new Swiper('.swiper-container', {
//   // 슬라이드 모드를 horizontal로 설정
//   direction: 'horizontal',

//   // 한 번에 보여지는 슬라이드 개수 (1로 설정하면 1개씩 슬라이딩)
//   slidesPerView: 1,

//   // 슬라이더의 루프를 설정
//   loop: true,

//   // 다음 버튼을 사용하여 다음 슬라이드로 이동
//   navigation: {
//     nextEl: '.next-btn',
//   },
// });

// // "next-btn" 클릭 시 다음 슬라이드로 이동
// document.querySelector('.next-btn').addEventListener('click', () => {
//   swiper.slideNext();
// });


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


// 모달창 열기
openModalCMBtn.addEventListener("click", modalCMOpen);
// 모달창 닫기
closeModalCMBtn.addEventListener("click", modalCMClose);
closeModalCMBack.addEventListener("click", modalCMClose);




// ------------------------------------------------------------------------------------------------------


