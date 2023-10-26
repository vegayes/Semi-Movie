
const policy = document.getElementsByName("policy");

// policy 요소 배열에 대해 이벤트 리스너
policy.forEach(element => {
  element.addEventListener("click", () => {
    location.href = "#policy";
  });


  element.addEventListener("click", () => {
    location.href = "#tos";
  });
  
   element.addEventListener("click", () => {
    location.href = "#location";
  });
  
});




