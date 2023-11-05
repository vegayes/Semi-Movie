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


// 즐겨찾기 팝업 조회 
/*
function selectFvMovie(){
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
function  selectFvMovie(){
  fetch("/movieInsight/mypage/like/select?memberNo=" + memberNo)
  .then(response => response.json()) 
  .then(list => {
      console.log(list);
      console.log("이건가?");

      // 기존의 favorite-list 엘리먼트들을 숨김
    //   movieListRows.forEach(function(row) {
    //     row.style.display = 'none';
    //   });




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

              selectFvMovie();

              if(result > 0){
                alert("즐겨찾기 삭제 완료하였습니다.");
                refreshFavoriteList();
                refreshFavoriteCinemaList();

                modalClose();

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