// 1) 팝업창 띄우기
const modalCM = document.getElementById("manager-menu-update-box");
const modalCMContent = document.getElementById("manager-menu-update-content");

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
function updateMenu(menuNo) {

  modalCMOpen();
  console.log("모달창 띄우기");
  console.log("menuNo : " + menuNo);
  menuNo = menuNo;

  fetch("/movieInsight/mypage/comment?menuNo=" + menuNo)
  .then(response => response.json()) 
  .then(commentInfo => {
      console.log(commentInfo);

  })
  .catch(err => console.log(err));

  document.getElementById("comment-del-btn").addEventListener("click", e=> {

    if(confirm("댓글 변경을 수정을 취소하시겠습니까?")){    
      modalCMClose();
    }

  });
}

    
    

    

   


            
    
            