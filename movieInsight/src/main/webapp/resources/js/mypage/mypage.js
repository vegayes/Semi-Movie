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

// 1-2) 스크롤 시, 위치에 따라 색 변경
// document.addEventListener("DOMContentLoaded", function() {
//   window.addEventListener("scroll", function() {

//     console.log("스크롤중");
//     var scroll = window.scrollY;

//     var navbar = document.querySelector(".my-page-tag");
//     if (scroll > 1) {
//       navbar.style.background = "#4c6ef5";
//     } else {
//       navbar.style.background = "#343a40";
//     }
//   });
// });



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
    
  console.log("영화관 누름");

  cinemaBtn.style.opacity = "1";
  cinemaBtn.style.color = "black";

  movieBtn.style.opacity = "0.5";

  favoriteMovieContainer.style.display = "none";
  favoriteCinemaContainer.style.display = "";
});


movieBtn.addEventListener("click" ,function(){
    
  console.log("영화 누름");

  cinemaBtn.style.opacity = "0.5";


  movieBtn.style.opacity = "1";
  movieBtn.style.color = "black";

  favoriteMovieContainer.style.display = "";
  favoriteCinemaContainer.style.display = "none";
});


const commentMovieBtn = document.getElementById("movie-comment-btn");
const commentCinemaBtn = document.getElementById("cinema-comment-btn");

const commentMovieContainer =document.getElementById("movie-comment-container");
const commentCinemaContainer =document.getElementById("cinema-comment-container");

commentMovieBtn.addEventListener("click" ,function(){
    
  console.log("영화 누름");

  commentCinemaBtn.style.opacity = "0.5";


  commentMovieBtn.style.opacity = "1";
  commentMovieBtn.style.color = "black";

  commentMovieContainer.style.display = "";
  commentCinemaContainer.style.display = "none";
});

commentCinemaBtn.addEventListener("click" ,function(){
    
  console.log("영화관 누름");

  commentCinemaBtn.style.opacity = "1";
  commentCinemaBtn.style.color = "black";

  commentMovieBtn.style.opacity = "0.5";

  commentMovieContainer.style.display = "none";
  commentCinemaContainer.style.display = "";
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











//6) 
// 영화 Swiper
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

// 영화관 Swiper

const galleryCinema = document.querySelector('.galleryCinema');
const prevButtonCinema = document.querySelector('.cinema-prev-button');
const nextButtonCinema = document.querySelector('.cinema-next-button');

nextButtonCinema.addEventListener('click', () => {
  scrollPosition += galleryCinema.clientWidth;
  if (scrollPosition > galleryCinema.scrollWidth - galleryCinema.clientWidth) {
    scrollPosition = galleryCinema.scrollWidth - galleryCinema.clientWidth;
  }
  galleryCinema.style.transform = `translateX(-${scrollPosition}px)`;
});

prevButtonCinema.addEventListener('click', () => {
  scrollPosition -= galleryCinema.clientWidth;
  if (scrollPosition < 0) {
    scrollPosition = 0;
  }
  galleryCinema.style.transform = `translateX(-${scrollPosition}px)`;
});

/*
const galleryCinema = document.querySelector('.galleryCinema');
const prevButtonCinema = document.querySelector('.cinema-prev-button');
const nextButtonCinema = document.querySelector('.cinema-next-button');

//let scrollPosition = 0;

nextButtonCinema.addEventListener('click', () => {
  scrollPosition += galleryCinema.clientWidth;
  if (scrollPosition > galleryCinema.scrollWidth - galleryCinema.clientWidth) {
    scrollPosition = galleryCinema.scrollWidth - galleryCinema.clientWidth;
  }
  galleryCinema.style.transform = `translateX(-${scrollPosition}px)`;
});

prevButtonCinema.addEventListener('click', () => {
  scrollPosition -= galleryCinema.clientWidth;
  if (scrollPosition < 0) {
    scrollPosition = 0;
  }
  galleryCinema.style.transform = `translateX(-${scrollPosition}px)`;
});
*/



// ***********************************************************
// 프로필 사진 변경하기
// **********************************************************

//----------------------------------------------------

// 프로필 이미지 추가/변경/삭제
const profileImage = document.getElementById("profileImage"); // img 태그
const imageInput = document.getElementById("file-input"); // input 태그
const deleteImage = document.getElementById("deleteImage"); // x버튼


let initCheck; // 초기 프로필 이미지 상태를 저장하는 변수
                // false == 기본 이미지,  true == 이전 업로드 이미지

let deleteCheck = -1; 
// 프로필 이미지가 새로 업로드 되거나 삭제 되었음을 나타내는 변수
// -1 == 초기값 ,  0 == 프로필 삭제(x버튼),  1 == 새 이미지 업로드


let originalImage; // 초기 프로필 이미지 파일 경로 저장

if(imageInput != null){ // 화면에 imageInput이 있을 경우 ( if 굳이 안해도 되긴 함 ) 

    // 프로필 이미지가 출력되는 img태그의 src 속성을 저장
    originalImage = profileImage.getAttribute("src"); 

    console.log(originalImage);

    // 회원 프로필 화면 진입 시 
    // 현재 회원의 프로필 이미지 상태를 확인
    if(profileImage.getAttribute("src") == "/movieInsight/resources/images/member/기본이미지.png"){
        // 기본 이미지인 경우
        initCheck = false;
    }else{
        initCheck = true;
    }
    



    // change 이벤트 : 값이 변했을 때
    // - input type="file", "checkbox", "radio" 에서 많이 사용
    // - text/number 형식 사용 가능
    //   -> 이 때 input값 입력 후 포커스를 잃었을 때 
    //      이전 값과 다르면 change 이벤트 발생

    imageInput.addEventListener("change", e => {

        // 2MB로 최대 크기 제한 
        const maxSize = 1 * 1024 * 1024 * 2; // 파일 최대 크기 지정(바이트 단위)

        console.log(e.target); // input
        console.log(e.target.value); // 업로드된 파일 경로
        console.log(e.target.files); // 업로드된 파일의 정보가 담긴 배열

        const file = e.target.files[0]; // 업로드한 파일의 정보가 담긴 객체


        // 파일을 한번 선택한 후 취소했을 때 ( file이 undefined가 된다 ) 
        if(file == undefined){ 
            console.log("파일 선택이 취소됨");
            deleteCheck = -1; // 취소 == 파일 없음 == 초기상태

            // 취소 시 기존 프로필 이미지로 변경 ( 기존 이미지에서 변경되는게 없게 하겠다는거죠 ) 
            profileImage.setAttribute("src", originalImage);

            return;
        }

        if( file.size > maxSize){ // 선택된 파일의 크기가 최대 크기를 초과한 경우
            alert("2MB 이하의 이미지를 선택해주세요.");
            imageInput.value = ""; 
            // input type="file" 태그에 대입할 수 있는 value는 "" (빈칸) 뿐이다!
            deleteCheck = -1; // 취소 == 파일 없음 == 초기상태

            // 기존 프로필 이미지로 변경
            profileImage.setAttribute("src", originalImage);

            return;
        }

	
        // JS에서 파일을 읽는 객체
        // - 파일을 읽고 클라이언트 컴퓨터에 파일을 저장할 수 있음 
        const reader = new FileReader();

        reader.readAsDataURL(file);
        // 매개변수에 작성된 파일을 읽어서 저장 후
        // 파일을 나타내는 URL을 result 속성으로 얻어올 수 있게 함.

        // 다 읽었을 때
        reader.onload = e => {
            //console.log(e.target);
            console.log(e.target.result); // 읽은 파일의 URL 
            /* 개발자도구에서 Application탭에서 Frames > top > images 안에서 확인가능 */

            const url = e.target.result;

            // 프로필이미지(img) 태그에 src 속성으로 추가
            profileImage.setAttribute("src", url);

            deleteCheck = 1;
        }
    });


    // x버튼 클릭 시
    deleteImage.addEventListener('click', () => {
        imageInput.value = ""; // input type="file"의 value 삭제

        profileImage.setAttribute("src", "/movieInsight/resources/images/member/기본이미지.png");
        // 프로필 이미지를 기본 이미지로 변경

        deleteCheck = 0;
    });


    // #profileFrm이 제출 되었을 때
    document.getElementById("profileUpdate").addEventListener("submit", e => {

        // initCheck
        // 초기 프로필 이미지 상태를 저장하는 변수
        // false == 기본 이미지,  true == 이전 업로드 이미지

        // deleteCheck
        // 프로필 이미지가 새로 업로드 되거나 삭제 되었음을 나타내는 변수
        // -1 == 초기값 ,  0 == 프로필 삭제(x버튼),  1 == 새 이미지 업로드

        let flag = true; // 제출하면 안되는 경우의 초기값 플래그 true로 지정

        // 이전 프로필 이미지가 없으면서, 새 이미지 업로드를 했다 -> 처음으로 이미지 추가
        if(!initCheck && deleteCheck == 1)  flag = false;

        // 이전 프로필 이미지가 있으면서, 새 이미지 업로드를 했다 -> 새 이미지로 변경
        if(initCheck && deleteCheck == 1)   flag = false;
        
        // 이전 프로필 이미지가 있으면서, 프로필 삭제 버튼을 눌렀다 -> 삭제
        if(initCheck && deleteCheck == 0)   flag = false;

        if(flag){ // flag == true -> 제출하면 안되는 경우
            e.preventDefault(); // form 기본 이벤트 제거
            alert("이미지 변경 후 클릭하세요");
        }
        
      
	    return true;
    });
}


// 드롭다운 값 가져오기

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



// 정보 수정


const updateForm = document.getElementById("updateForm");

const newPw = document.getElementById("newPw");
const newCheckPw = document.getElementById("newCheckPw");
const pwMessage = document.getElementById("pwMessage");
const pwCheckMessage = document.getElementById("pwCheckMessage");

// 비밀번호 확인 유효성 검사
newCheckPw.addEventListener('input', ()=>{  

  if(newPw.value.trim() !== ''){ // 비밀번호가 유효하게 작성된 경우에

    // 비밀번호 == 비밀번호 확인  (같을 경우)
    if(newPw.value == newCheckPw.value){
      console.log("비밀번호 일치");
      pwCheckMessage.innerText = "비밀번호가 \n 일치합니다";
      pwCheckMessage.classList.add("confirm");
      pwCheckMessage.classList.remove("error");
        
    } else{ // 다를 경우
      pwCheckMessage.innerText = "비밀번호가 \n 일치하지 않습니다";
      pwCheckMessage.classList.add("error");
      pwCheckMessage.classList.remove("confirm");
    }

  } else { // 비밀번호가 유효하지 않은 경우
    alert("비밀번호 먼저 입력해주세요");
    newPw.focus();
    newPw.value = "";
    newCheckPw.value ="";
  }
});


newPw.addEventListener("input", () => {

  // 비밀번호가 입력되지 않은 경우
  if(newPw.value.trim().length == 0){
      newPw.value = ""; // 띄어쓰지 못넣게 하기

      pwMessage.innerText = "영어,숫자,특수문자(!,@,#,-,_)\n 6~20글자 사이로 입력해주세요.";
      pwMessage.classList.remove("confirm", "error"); // 검정 글씨

      return;
  }


  // 정규 표현식을 이용한 비밀번호 유효성 검사

  // 영어,숫자,특수문자(!,@,#,-,_) 6~20글자 사이
  const regEx = /^[a-zA-Z0-9\!\@\#\-\_]{6,20}$/;

  // 입력한 비밀번호가 유효한 경우
  if(regEx.test(newPw.value)){
      
      // 비밀번호가 유효하게 작성된 상태에서
      // 비밀번호 확인이 입력되지 않았을 때
      if(newCheckPw.value.trim().length == 0){

          pwMessage.innerText = "유효한 비밀번호 \n형식입니다";
          pwMessage.classList.add("confirm");
          pwMessage.classList.remove("error");
      
      }else{
          // 비밀번호가 유효하게 작성된 상태에서
          // 비밀번호 확인이 입력되어 있을 때

          // 비밀번호 == 비밀번호 확인  (같을 경우)
          if(newPw.value == newCheckPw.value){
            pwCheckMessage.innerText = "비밀번호가 일치합니다";
            pwCheckMessage.classList.add("confirm");
            pwCheckMessage.classList.remove("error");
              
          } else{ // 다를 경우
            pwCheckMessage.innerText = "비밀번호가 \n일치하지 않습니다";
            pwCheckMessage.classList.add("error");
            pwCheckMessage.classList.remove("confirm");
          }
      }

      
  } else{ // 유효하지 않은 경우
      
      pwMessage.innerText = "비밀번호 형식이 \n유효하지 않습니다";
      pwMessage.classList.add("error");
      pwMessage.classList.remove("confirm");
  }
});


// 닉네임 일치하는지 확인 ajax
const newNickname = document.getElementById("newNickName");
const nickNameMessage = document.getElementById("nickNameMessage");


newNickname.addEventListener("input",function() {
    // 닉네임 입력이 되지 않은 경우
    if(newNickname.value.trim() == ''){
      nickNameMessage.innerText = "한글,영어,숫자로만 2~10글자";
      nickNameMessage.classList.remove("confirm", "error");
      newNickname.value = ""; 
      return;
  }

  // 정규표현식으로 유효성 검사
  const regEx = /^[가-힣\w\d]{2,10}$/;

  if(regEx.test(newNickname.value)){// 유효

      fetch("/movieInsight/mypage/nickname?nickname="+newNickname.value)
      .then(resp => resp.text()) // 응답 객체를 text로 파싱(변환)
      .then(count => {

          if(count == 0){ // 중복 아닌 경우
            nickNameMessage.innerText = "사용 가능한 닉네임 입니다";
            nickNameMessage.classList.add("confirm");
            nickNameMessage.classList.remove("error");
              
          }else{ // 중복인 경우
            nickNameMessage.innerText = "이미 사용중인 닉네임 입니다";
            nickNameMessage.classList.add("error");
            nickNameMessage.classList.remove("confirm");
          }
      })
      .catch(err => console.log(err));

      


  } else{ // 무효
    nickNameMessage.innerText = "닉네임 형식이 유효하지 않습니다";
    nickNameMessage.classList.add("error");
    nickNameMessage.classList.remove("confirm");
  }

});



// "남자" 체크박스 요소
var manCheckbox = document.getElementById('man');
// "여자" 체크박스 요소
var womanCheckbox = document.getElementById('woman');

// 로그인 정보의 성별 값에 따라 체크박스를 설정합니다.
if (loginMemberGender === 'M') {

    console.log("남자 선택됨");

    manCheckbox.querySelector('input[type="checkbox"]').checked = true;
    manCheckbox.style.backgroundColor = 'blue';
    manCheckbox.querySelector('label').style.color = 'white';


} else if (loginMemberGender === 'F') {
    womanCheckbox.querySelector('input[type="checkbox"]').checked = true;
    womanCheckbox.style.backgroundColor = 'blue';
    womanCheckbox.querySelector('label').style.color = 'white';
    
}



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

