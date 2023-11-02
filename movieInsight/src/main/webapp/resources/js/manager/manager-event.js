// 1) 팝업창 띄우기
const modalCM = document.getElementById("manager-event-update-box");
const modalCMContent = document.getElementById("manager-event-update-content");

const closeModalCMBtn = document.getElementById("event-modal-close");
const closeModalCMBack = document.getElementById("event-modal-back");

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


// 1-2)수정 팝업 띄우기  (특별관)
// function updatePromotion(promotionNo) {

//   modalCMOpen();
//   console.log("모달창 띄우기");
//   console.log("promotionNo : " + promotionNo);
//   promotionNo = promotionNo;

//   fetch("/movieInsight/managerDetail/updatePromotion?promotionNo=" + promotionNo)
//   .then(response => response.json()) 
//   .then(commentInfo => {
//       console.log(commentInfo);

//   })
//   .catch(err => console.log(err));

//   document.getElementById("comment-del-btn").addEventListener("click", e=> {

//     if(confirm("댓글 변경을 수정을 취소하시겠습니까?")){    
//       modalCMClose();
//     }

//   });
// }


// // 1-3수정 팝업 띄우기  (이벤트)
// function updateEvent(eventNo) {

//   modalCMOpen();
//   promotionNo = promotionNo;

//   document.getElementById("comment-del-btn").addEventListener("click", e=> {

//     if(confirm("댓글 변경을 수정을 취소하시겠습니까?")){    
//       modalCMClose();
//     }

//   });
// }



// 2) 특별관 추가 버튼 누르면 (수정 내용 따로 바꿔야 할 듯.) 
document.getElementById("special-add").addEventListener("click", function(){
  modalCMOpen();
  
  
    document.getElementById("update-del-button").addEventListener("click", e=> {

    if(confirm("댓글 변경을 수정을 취소하시겠습니까?")){    
      modalCMClose();
    }

  });
});


// 3) 이벤트 추가 버튼 누르면 (수정 내용 따로 바꿔야 할 듯. ) 
document.getElementById("event-add").addEventListener("click", function(){
  modalCMOpen();
  
  
    document.getElementById("comment-del-btn").addEventListener("click", e=> {

    if(confirm("댓글 변경을 수정을 취소하시겠습니까?")){    
      modalCMClose();
    }

  });
});







