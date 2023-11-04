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

// 2) 즐겨찾기 -> 영화관, 영화 구분하기
const movieBtn = document.getElementById("movie-favorite-btn");
const cinemaBtn = document.getElementById("cinema-favorite-btn");

const favoriteMovieContainer =document.getElementById("movie-favorite-container");
const favoriteCinemaContainer =document.getElementById("cinema-favorite-container");
/*
var favoriteFlag = true;

cinemaBtn.addEventListener("click" ,function(){
    
  console.log("영화관 누름");

  cinemaBtn.style.opacity = "1";
  cinemaBtn.style.color = "black";

  movieBtn.style.opacity = "0.5";

  favoriteMovieContainer.style.display = "none";
  favoriteCinemaContainer.style.display = "";

  favoriteFlag = true;
});


movieBtn.addEventListener("click" ,function(){
    
  console.log("영화 누름");

  cinemaBtn.style.opacity = "0.5";


  movieBtn.style.opacity = "1";
  movieBtn.style.color = "black";

  favoriteMovieContainer.style.display = "";
  favoriteCinemaContainer.style.display = "none";

  favoriteFlag = false;
});


*/

var favoriteFlag = true; // 영화를 기본값으로 선택하도록 설정

cinemaBtn.addEventListener("click" ,function(){
    console.log("영화관 누름");

    cinemaBtn.style.opacity = "1";
    cinemaBtn.style.color = "black";

    movieBtn.style.opacity = "0.5";

    favoriteMovieContainer.style.display = "none";
    favoriteCinemaContainer.style.display = "";

    favoriteFlag = false; // 영화관을 선택할 때 favoriteFlag 값을 false로 설정
});


movieBtn.addEventListener("click" ,function(){
    console.log("영화 누름");

    cinemaBtn.style.opacity = "0.5";

    movieBtn.style.opacity = "1";
    movieBtn.style.color = "black";

    favoriteMovieContainer.style.display = "";
    favoriteCinemaContainer.style.display = "none";

    favoriteFlag = true; // 영화를 선택할 때 favoriteFlag 값을 true로 설정
});


document.addEventListener('DOMContentLoaded', function() {


  movieListRows.forEach(function(row) {
      row.style.display = 'none'; // 해당 <tr> 요소를 숨김
  });
});


// 2-2) 모달창 띄우기

const modal = document.getElementById("favorite-list-modal-box");
const modalContent = document.getElementById("favorite-list-modal-content");
const openModalBtn = document.getElementById("favorite-popup-open");
const closeModalBtn = document.getElementById("fv-modal-close");
const closeModalBack = document.getElementById("fv-modal-back");

/*
var movieListRows = document.querySelectorAll('#fvMovie'); // movieList의 모든 <tr> 요소 선택
var cinemaListRows = document.querySelectorAll('#fvCinema'); // movieList의 모든 <tr> 요소 선택
*/
var movieListRows = document.querySelectorAll('.fvMovie'); // movieList의 모든 <tr> 요소 선택
var cinemaListRows = document.querySelectorAll('.fvCinema'); // movieList의 모든 <tr> 요소 선택


function modalOpen(){

    modal.style.display ="flex";

    if (favoriteFlag) {

      console.log("안녕하세요");

      movieListRows.forEach(function(row) {
        row.style.display = 'flex'; // 해당 <tr> 요소를 숨김
    });

    cinemaListRows.forEach(function(row) {
      row.style.display = 'none'; // 해당 <tr> 요소를 다시 보여줌
   });
  }else{

    console.log("영화관을 클릭한 상태에서 출력")

    movieListRows.forEach(function(row) {
      row.style.display = 'none'; // 해당 <tr> 요소를 숨김
    });

   cinemaListRows.forEach(function(row) {
      row.style.display = 'flex'; // 해당 <tr> 요소를 다시 보여줌
    });

  }

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



// 2-3) 선택이 된 값들 출력
/*
document.querySelector(".favorite-delet-btn").addEventListener("click", function() {

  var checkedItems = document.querySelectorAll('input[name="favorite-check"]:checked');
  console.log(checkedItems);
  var a = Array.from(checkedItems);
  console.log("a : "+ a);
  console.log("a[0] " + a[0]);
  var selectedMovieNos = a.map(function(checkbox) {
      console.log(checkbox);
      return checkbox.getAttribute('data-movieno');
  });

  if (selectedMovieNos.length > 0) {
      var confirmed = confirm("선택된 항목을 삭제하시겠습니까?");
      if (confirmed) {
          // TODO: AJAX 요청을 보내서 서버에서 삭제 작업 수행
          console.log(selectedMovieNos); // 선택된 movieNo 확인용 로그


      }
  } else {
      alert("삭제할 항목을 선택해주세요."); 
  }
});
*/

// 즐겨찾기 팝업 조회 
/*
function selectFvMovie(){
  /*
  fetch("/movieInsight/mypage/like/select?memberNo=" + memberNo)
  .then(response => response.text()) // 응답객체를 parsing 하겠다 json으로 
  .then(list => {
      console.log(list);

      movieListRows.forEach(function(row) {
        row.style.display = 'none'; // 해당 <tr> 요소를 숨김
      });

      for(let fv of list){
        const fvRow = document.createElement("tr");
        fvRow.classList.add("favorite-list");

        const fvTd = document.createElement("td");
        fvTd.classList.add("favorite-list-img");

        const fvImgDiv = document.createElement("div");
        fvImgDiv.classList.add("favorite-list-img-wrapper");

        const fvImg = document.createAttribute("img");
          fvImg.setAttribute("src", "/movieInsight/resources/images/movie/" + fv.movieImg);

        
        const title = fvTd.classList.add("favorite-list-title");
        title.innerText = fv.movieTitle;

        const date = fvTd.classList.add("favorite-list-date");
        date.innerText = fv.movieLikeEnrollDate;

        const check = fvTd.classList.add("favorite-list-check");

        console.log(check);
      }

  })
  .catch(err => console.log(err));
};
*/

// 마이페이지 ( 수정된 이후 ) 즐겨찾기 리스트를 다시 조회하는 함수
function refreshFavoriteList() {
  fetch("/movieInsight/mypage/favorite/select?memberNo=" + memberNo )
    .then(response => response.json())
    .then(movieList => {

      console.log(movieList);

      const favoriteContainer = document.getElementById('movie-favorite-container');
      favoriteContainer.innerHTML = '';

      if (movieList.length === 0) {
        const notContentDiv = document.createElement('div');
        notContentDiv.classList.add('favorite-not-content');
        notContentDiv.innerText = '현재 저장된 즐겨찾기가 없습니다.';
        favoriteContainer.appendChild(notContentDiv);
      } else {
        const likeContainer = document.createElement('div');
        likeContainer.classList.add('likeContainer');

        const likeSwiper = document.createElement('div');
        likeSwiper.classList.add('likeSwiper');

        const galleryContainer = document.createElement('div');
        galleryContainer.classList.add('gallery-container');

        const gallery = document.createElement('div');
        gallery.classList.add('gallery');

        for (let movie of movieList) {
          const recommendContainer = document.createElement('div');
          recommendContainer.classList.add('recommend-container');

          const link = document.createElement('a');
          link.href = `/movieInsight/movie/${movie.movieNo}`;

          const recommendImgWrapper = document.createElement('div');
          recommendImgWrapper.classList.add('recommendImg-wrapper');

          const img = document.createElement('img');
          img.src = `/movieInsight/resources/images/movie/${movie.movieImg}`;
          img.alt = `movieTitle : ${movie.movieTitle}`;

          const recommendImgHover = document.createElement('div');
          recommendImgHover.classList.add('recommendImg-hover');
          recommendImgHover.innerText = movie.movieTitle;

          recommendImgWrapper.appendChild(img);
          recommendImgWrapper.appendChild(recommendImgHover);

          link.appendChild(recommendImgWrapper);
          recommendContainer.appendChild(link);
          gallery.appendChild(recommendContainer);
        }

        galleryContainer.appendChild(gallery);
        likeSwiper.appendChild(galleryContainer);
        likeContainer.appendChild(likeSwiper);

        const prevButton = document.createElement('button');
        prevButton.classList.add('prev-button');
        prevButton.innerHTML = '&lt;';

        const nextButton = document.createElement('button');
        nextButton.classList.add('next-button');
        nextButton.innerHTML = '&gt;';

        likeSwiper.appendChild(prevButton);
        likeSwiper.appendChild(nextButton);

        favoriteContainer.appendChild(likeContainer);
      }
    })
    .catch(err => console.error(err));
}



// 영화관 즐겨찾기 ajax 조회
function refreshFavoriteCinemaList() {
  fetch("/movieInsight/mypage/favorite/select/cinema?memberNo=" + memberNo)
      .then(response => response.json())
      .then(cinemaList => {
          const galleryContainer = document.querySelector('.galleryCinema');
          galleryContainer.innerHTML = '';

          if (cinemaList.length === 0) {
              const notContentDiv = document.createElement('div');
              notContentDiv.classList.add('favorite-not-content');
              notContentDiv.innerText = '현재 저장된 즐겨찾기가 없습니다.';
              galleryContainer.appendChild(notContentDiv);
          } else {
              for (let cinema of cinemaList) {
                  const recommendContainer = document.createElement('div');
                  recommendContainer.classList.add('recommend-container-cinema');

                  const link = document.createElement('a');
                  link.href = `/movieInsight/cinemaDetail/${cinema.cinemaName}`;

                  const recommendImgWrapper = document.createElement('div');
                  recommendImgWrapper.classList.add('recommendImg-wrapper-cinema');

                  const img = document.createElement('img');
                  img.src = `/movieInsight/resources/images/cinema/${cinema.cinemaImg}`;
                  img.alt = `movieTitle : ${cinema.cinemaName}`;

                  const recommendImgHover = document.createElement('div');
                  recommendImgHover.classList.add('recommendImg-hover');
                  recommendImgHover.innerText = cinema.cinemaName;

                  recommendImgWrapper.appendChild(img);
                  recommendImgWrapper.appendChild(recommendImgHover);

                  link.appendChild(recommendImgWrapper);
                  recommendContainer.appendChild(link);
                  galleryContainer.appendChild(recommendContainer);
              }
          }
      })
      .catch(err => console.error(err));
}




// 영화 즐겨찾기 팝업 조회 (ajax) 
function selectFvMovie(){
  fetch("/movieInsight/mypage/like/select?memberNo=" + memberNo)
  .then(response => response.json()) 
  .then(list => {
      console.log(list);

      // 기존의 favorite-list 엘리먼트들을 숨김
      movieListRows.forEach(function(row) {
        row.style.display = 'none';
      });

      // var fvMovie = document.querySelectorAll('.fvMovie');

      // fvMovie.forEach(funciton())

      console.log("엘리먼트들 숨김 처리 했음.")

      // list에서 가져온 데이터로 새로운 엘리먼트를 생성하고 추가
      for(let fv of list){
        const fvRow = document.createElement("tr");
        fvRow.classList.add("favorite-list");
        fvRow.classList.add("fvMovie");
        fvRow.setAttribute("style", " 2px solid blue");

        const fvTd = document.createElement("td");
        fvTd.classList.add("favorite-list-img");

        const fvImgDiv = document.createElement("div");
        fvImgDiv.classList.add("favorite-list-img-wrapper");

        const fvImg = document.createElement("img");
        fvImg.src = "/movieInsight/resources/images/movie/" + fv.movieImg; // 이미지 소스 설정

        const title = document.createElement("td");
        title.classList.add("favorite-list-title");
        title.innerText = fv.movieTitle;

        const date = document.createElement("td");
        date.classList.add("favorite-list-date");
        date.innerText = fv.movieLikeEnrollDate;

        const check = document.createElement("td");
        check.classList.add("favorite-list-check");

        const checkbox = document.createElement("input");
        checkbox.setAttribute("type", "checkbox");
        checkbox.setAttribute("name", "favorite-check");
        checkbox.setAttribute("id", "check_btn");
        checkbox.setAttribute("data-movieNo", fv.movieNo);

        const label = document.createElement("label");
        label.setAttribute("for", "check_btn");

        // 생성된 엘리먼트들을 추가
        fvImgDiv.appendChild(fvImg);
        fvTd.appendChild(fvImgDiv);
        fvRow.appendChild(fvTd);
        fvRow.appendChild(title);
        fvRow.appendChild(date);
        check.appendChild(checkbox);
        check.appendChild(label);
        fvRow.appendChild(check);

        // 새로운 엘리먼트를 기존의 테이블에 추가
        document.getElementById('favorite-list-table').appendChild(fvRow);
      }

  })
  .catch(err => console.log(err));

};


document.querySelector(".favorite-delet-btn").addEventListener("click", function() {

  var checkedItems = document.querySelectorAll('input[name="favorite-check"]:checked');
  var a = Array.from(checkedItems);
  var selectedDelMovie = a.map(function(checkbox) {
      return checkbox.getAttribute('data-movieno');
  });

  if (selectedDelMovie.length > 0) {
      var confirmed = confirm("선택된 즐겨찾기를 삭제하시겠습니까?");
      if (confirmed) {

          console.log(selectedDelMovie);

          fetch("/movieInsight/mypage/like/del", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ delMovie: selectedDelMovie })
          })
          .then(response => response.json()) 
          .then(result => {
              console.log(result);

              // selectFvMovie();

              if(result > 0){
                alert("즐겨찾기 삭제 완료하였습니다.");
                refreshFavoriteList();
                refreshFavoriteCinemaList();

                // modalClose();

                var movieModal = document.querySelector('.movieModal');
                movieModal.style.display = "none";

              }else{
                alert("즐겨찾기 삭제 실패했습니다.")
              }
          })
          .catch(err => console.log(err));
      }
    } else {
      alert("삭제할 항목을 선택해주세요."); 
  }
});



/*
    document.addEventListener("DOMContentLoaded", function() {
        var checkboxes = document.querySelectorAll('input[type="checkbox"][name="favorite-check"]');
        
        checkboxes.forEach(function(checkbox) {
            checkbox.addEventListener('change', function() {
                if (this.checked) {
                    var movieNo = this.parentElement.querySelector('p').textContent;
                    console.log('선택된 영화의 movieNo:', movieNo);
                    // 여기에서 movieNo를 활용하여 원하는 작업을 수행할 수 있습니다.
                }
            });
        });
    });

*/





/************************************************************************************************************************************************************* */




/*
const fvMovie = document.getElementById("#fvMovie");
const fvCinema = document.getElementById("#fvCinema");

if (favoriteFlag == "true") {
  console.log("나는 영화 누름");

  fvCinema.forEach(element => {
    element.style.display = "none";
  });

  fvMovie.forEach(element => {
    element.style.display = "";
  });

} else {
  console.log("나는 영화관 누름");

  fvMovie.forEach(element => {
    element.style.display = "none";
  });

  fvCinema.forEach(element => {
    element.style.display = "";
  });
}

*/

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



// 4) 댓글 -><영화 영화관 구분하기
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
document.getElementsByName("comment-check").forEach(function(v) {
  v.addEventListener('click', checkAllList);
});




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


// 로그인 한 정보 체크 

// "남자" 체크박스 요소
var manCheckbox = document.getElementById('man');
// "여자" 체크박스 요소
var womanCheckbox = document.getElementById('woman');

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

divCheckboxes.forEach((divCheckbox) => {
  divCheckbox.addEventListener('click', handleCheckboxInteraction);
});



// 6) 회원 탈퇴
const SecessionBtn = document.getElementById("memberSecession");

SecessionBtn.addEventListener("click", function(event) {
  event.preventDefault(); // 폼태그 동작 X 
  console.log("memberNo : " + memberNo)

  fetch("/movieInsight/mypage/secession?memberNo="+memberNo)
  .then(resp => resp.json()) 
  .then(count => {

      if(count == 0){ // 실패
        alert("탈퇴 진행 중 오류가 발생하였습니다.");
      }else{ 
        alert("탈퇴가 완료 되었습니다.\n 다음에 또 방문해주세요~!");
        location.href =  location.origin + "/movieInsight/movie";
      }
  })
  .catch(err => console.log(err));
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
      editButton.id = `updateCommentBtn_${fv.movieCommentNo}`; 
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
        editButton.id = `updateCommentBtn_${commentCinema.cinemaCommentNo}`;
        editButton.innerText = "수정";
        editButton.onclick = function() {
          updateCommentModalCinema(commentCinema.cinemaCommentNo);
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




// ------------------------------------------------------------------------------------------------------


//6) 
// 영화 Swiper

const gallery = document.querySelector('.gallery');
const prevButton = document.querySelector('.prev-button');
const nextButton = document.querySelector('.next-button');

let scrollPosition = 0;
if(gallery && prevButton && nextButton){
  nextButton.addEventListener('click', () => {
    console.log("클릭됨");
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

}


const galleryCinema = document.querySelector('.galleryCinema');
const prevButtonCinema = document.querySelector('.cinema-prev-button');
const nextButtonCinema = document.querySelector('.cinema-next-button');

let scrollPositionCinema = 0;

if (galleryCinema && prevButtonCinema && nextButtonCinema) {
  // 요소들이 모두 존재하는 경우에만 이벤트를 추가합니다.
  nextButtonCinema.addEventListener('click', () => {
    console.log("클릭됨");
    scrollPositionCinema += galleryCinema.clientWidth;
    if (scrollPositionCinema > galleryCinema.scrollWidth - galleryCinema.clientWidth) {
      scrollPositionCinema = galleryCinema.scrollWidth - galleryCinema.clientWidth;
    }
    galleryCinema.style.transform = `translateX(-${scrollPositionCinema}px)`;
  });

  prevButtonCinema.addEventListener('click', () => {
    scrollPositionCinema -= galleryCinema.clientWidth;
    if (scrollPositionCinema < 0) {
      scrollPositionCinema = 0;
    }
    galleryCinema.style.transform = `translateX(-${scrollPositionCinema}px)`;
  });
}


/******************************************************************************************* */
// 방문기록 삭제 ajax 조회
function selectVisit(){
  fetch("/movieInsight/mypage/visit/select?memberNo=" + memberNo)
  .then(response => response.json()) 
  .then(list => {
      console.log(list);

      // 기존의 visit-history-content 엘리먼트를 선택
      const visitHistoryContent = document.querySelector('.visit-history-content');

      // visit-history-content 엘리먼트의 내용을 지움
      visitHistoryContent.innerHTML = '';

      // list에서 가져온 데이터로 새로운 엘리먼트를 생성하고 추가
      for(let visit of list){
          const visitHistoryList = document.createElement("tr");
          visitHistoryList.classList.add("visit-history-list");

          const historyImgContainer = document.createElement("td");
          historyImgContainer.classList.add("history-img-container");

          const aTag = document.createElement("a");
          console.log(visit.movieNo + " 영화 넘버 ");
          aTag.href = "/movieInsight/movie/" + visit.movieNo;

          const img = document.createElement("img");
          img.src = "/movieInsight/resources/images/movie/" + visit.movieImg;

          aTag.appendChild(img);


          const historyContentContainer = document.createElement("td");
          historyContentContainer.classList.add("history-content-container");

          const h1 = document.createElement("h1");
          h1.innerText = visit.movieTitle;

          const p = document.createElement("p");
          p.innerText = visit.movieSummary;

          const historyDelBtn = document.createElement("td");
          historyDelBtn.classList.add("history-del-btn");

          const delBtnDiv = document.createElement("div");
          delBtnDiv.onclick = function() {
              delVisit(visit.visitNo);
          }
          delBtnDiv.innerText = "삭제";

          // 엘리먼트들을 추가
          historyImgContainer.appendChild(aTag);
          historyContentContainer.appendChild(h1);
          historyContentContainer.appendChild(p);
          historyDelBtn.appendChild(delBtnDiv);

          visitHistoryList.appendChild(historyImgContainer);
          visitHistoryList.appendChild(historyContentContainer);
          visitHistoryList.appendChild(historyDelBtn);

          // 새로운 엘리먼트를 visit-history-content에 추가
          visitHistoryContent.appendChild(visitHistoryList);
      }

  })
  .catch(err => console.log(err));
};


// 방문기록 삭제
function delVisit(visitNo){
  fetch("/movieInsight/mypage/visit/del?visitNo=" + visitNo)
  .then(response => response.json()) 
  .then(check => {
      console.log(check);
      
      if(check){
        alert("방문기록이 삭제되었습니다.");
        selectVisit();
      }else{
        alert("방문기록 삭제 중 오류가 발생하였습니다.")
      }
  })
  .catch(err => console.log(err));
};






















