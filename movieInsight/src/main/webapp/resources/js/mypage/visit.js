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
  