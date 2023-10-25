document.addEventListener("DOMContentLoaded", function () {
  const thumbs = document.querySelectorAll('.fa-thumbs-up');

  thumbs.forEach((thumb, index) => {
      thumb.addEventListener('click', () => {
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