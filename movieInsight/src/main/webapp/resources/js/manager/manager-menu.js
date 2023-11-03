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
if(document.getElementById("event-modal-close")){
  closeModalCMBtn.addEventListener("click", modalCMClose);
}

if(document.getElementById("event-modal-back")) {
  closeModalCMBack.addEventListener("click", modalCMClose);

}




// + 누르면 모달 창 열기
document.getElementById("event-add").addEventListener("click", function(){
  modalCMOpen();
  
  
    document.getElementById("update-del-button").addEventListener("click", e=> {

    if(confirm("댓글 변경을 수정을 취소하시겠습니까?")){    
      modalCMClose();
    }

  });
});    

    

   
// 메뉴 선택에 따라 
// document.addEventListener("DOMContentLoaded", function () {
//   var updateButton = document.getElementById("updateButton");
//   var menuSelect = document.getElementById("menuSelect");
//   var updateForm = document.getElementById("updateform");
  
//   if(updateButton) {
//     // 메뉴 선택에 따라 동작 변경
//     menuSelect.addEventListener("change", function(){
//       // 이벤트 리스너 내부에서 변수를 선언해야 합니다.
//       var value = this.value;
//       console.log(value);
//     });

//     updateButton.addEventListener("click", function(event) {
//       event.preventDefault(); // 폼 기본 제출 동작을 방지
//       console.log("수정버튼 눌림");
//       // 메뉴 선택 값에 접근하여 해당 값에 따라 action 설정
//       var selectedValue = menuSelect.value;

//       if (selectedValue === "promotion-cinema") {
//         console.log("영화관 홍보");
//         updateForm.action = "/movieInsight/managerDetail/insertPromotion";
//       } else if (selectedValue === "event") {
//         console.log("이벤트 홍보");
//         updateForm.action = "/movieInsight/managerDetail/insertEvent";
//       }

//       // 폼 제출
//       updateForm.submit();
//     });
//   }   
// });
            
    
            