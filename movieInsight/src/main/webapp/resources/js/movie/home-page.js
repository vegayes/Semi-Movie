
    new Swiper(".main-1", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-1-n", // 다음 버튼 클래스명
        prevEl: ".main-1-p", // 이번 버튼 클래스명
      },
    });
   new Swiper(".main-2", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-2-n", // 다음 버튼 클래스명
        prevEl: ".main-2-p", // 이번 버튼 클래스명
      },
    });
    new Swiper(".main-3", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-3-n", // 다음 버튼 클래스명
        prevEl: ".main-3-p", // 이번 버튼 클래스명
      },
    });
    new Swiper(".main-4", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-4-n", // 다음 버튼 클래스명
        prevEl: ".main-4-p", // 이번 버튼 클래스명
      },
    });
    new Swiper(".main-5", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-5-n", // 다음 버튼 클래스명
        prevEl: ".main-5-p", // 이번 버튼 클래스명
      },
    });
    new Swiper(".main-6", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-6-n", // 다음 버튼 클래스명
        prevEl: ".main-6-p", // 이번 버튼 클래스명
      },
    });
    new Swiper(".main-7", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-7-n", // 다음 버튼 클래스명
        prevEl: ".main-7-p", // 이번 버튼 클래스명
      },
    });
    new Swiper(".main-8", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-8-n", // 다음 버튼 클래스명
        prevEl: ".main-8-p", // 이번 버튼 클래스명
      },
    });
    new Swiper(".main-9", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-9-n", // 다음 버튼 클래스명
        prevEl: ".main-9-p", // 이번 버튼 클래스명
      },
    });
    
     new Swiper(".main-10", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      loopedSlidesLimit: false,
      slideToClickedSlide: true,
      loopedSlides: 1,
      navigation: {
        nextEl: ".main-10-n", // 다음 버튼 클래스명
        prevEl: ".main-10-p", // 이번 버튼 클래스명
      },
    });





    new Swiper(".swiper-ls-1", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      navigation: {
        nextEl: ".ls-1-n", // 다음 버튼 클래스명
        prevEl: ".ls-1-p", // 이번 버튼 클래스명
      },
      pagination: {
        el: ".ls-1-pa",
        type: "bullets",
      },
    });

    new Swiper(".swiper-ls-2", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      // navigation: {
      //   nextEl: ".ls-2-n", // 다음 버튼 클래스명
      //   prevEl: ".ls-2-p", // 이번 버튼 클래스명
      // },
    });

    new Swiper(".swiper-ls-3", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      navigation: {
        nextEl: ".ls-3-n", // 다음 버튼 클래스명
        prevEl: ".ls-3-p", // 이번 버튼 클래스명
      },
      pagination: {
        el: ".ls-3-pa",
        type: "bullets",
      },
    });

    new Swiper(".swiper-center", {
      slidesPerView: "auto",
      spaceBetween: 24,
      loop: true,
      navigation: {
        nextEl: ".center-n", // 다음 버튼 클래스명
        prevEl: ".center-p", // 이번 버튼 클래스명
      },
    });






    //  포인터 올리면 화면 검게 변하는 것  ///
    document.addEventListener("DOMContentLoaded", function() {
  const movieItems = document.querySelectorAll(".movie_item");

  movieItems.forEach(item => {
    item.addEventListener("mouseover", function() {
      item.classList.add("hovered");
    });

    item.addEventListener("mouseout", function() {
      item.classList.remove("hovered");
    });
  });
});


            // 양 사이드바 초마다 넘기기  //

document.addEventListener("DOMContentLoaded", function() {
  // Swiper 초기화 (메뉴추천)
  const swiperMenu = new Swiper(".swiper-ls-1", {
    slidesPerView: "auto",
    spaceBetween: 24,
    loop: true,
    navigation: {
      nextEl: ".ls-1-n", // 다음 버튼 클래스명
      prevEl: ".ls-1-p", // 이전 버튼 클래스명
    },
    pagination: {
      el: ".ls-2-pa",
      type: "bullets",
    },
  });

  // 2초마다 슬라이드 넘기기 (메뉴추천)
  setInterval(function() {
    swiperMenu.slideNext();
  }, 2000);
});



document.addEventListener("DOMContentLoaded", function() {
  // Swiper 초기화 (메뉴추천)
  const swiperMenu = new Swiper(".swiper-ls-2", {
    slidesPerView: "auto",
    spaceBetween: 24,
    loop: true,
    navigation: {
      nextEl: ".ls-2-n", // 다음 버튼 클래스명
      prevEl: ".ls-2-p", // 이전 버튼 클래스명
    },
    pagination: {
      el: ".ls-2-pa",
      type: "bullets",
    },
  });

  // 2초마다 슬라이드 넘기기 (메뉴추천)
  setInterval(function() {
    swiperMenu.slideNext();
  }, 3500);
});



document.addEventListener("DOMContentLoaded", function() {
  // Swiper 초기화 (메뉴추천)
  const swiperMenu = new Swiper(".swiper-ls-3", {
    slidesPerView: "auto",
    spaceBetween: 24,
    loop: true,
    navigation: {
      nextEl: ".ls-3-n", // 다음 버튼 클래스명
      prevEl: ".ls-3-p", // 이전 버튼 클래스명
    },
    pagination: {
      el: ".ls-3-pa",
      type: "bullets",
    },
  });

  // 4.5초마다 슬라이드 넘기기 (메뉴추천)
  setInterval(function() {
    swiperMenu.slideNext();
  }, 4500);
});