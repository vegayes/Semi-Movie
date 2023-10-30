<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지-영화관</title>
     <link rel="stylesheet" href="/movieInsight/resources/css/manager/manager_cinema.css">
</head>
<body>
    

        <div class="admin-menu">
            <ul>
                <li class="admin-list"><a href="#">회원 관리</a></li> 
                <li class="admin-list"><a href="/movieInsight/manager/movie">영화</a></li>               
                <li class="admin-list"><a href="/movieInsight/manager/cinema">영화관</a></li>
                <li class="admin-list"><a href="/movieInsight/manager/menu">메뉴</a></li>
                <li class="admin-list"><a href="/movieInsight/manager/promotion">이벤트</a></li>
            </ul>
        </div>

        
            <div class="cinema-search">
                <input type="text" placeholder="영화관 검색" id="searchInput"><br><br>
                <span>총 영화관 : ${cinemaList.size()}개</span> 

                <span id="cinemaAdd"><a href="/movieInsight/cinemaDetail/insert">추가</a></span>

            </div>
            

            <div class="cinema-item-list">

                <c:forEach items = "${cinemaList}" var = "cinema" > 
                  
                    <div class="cinema-item">
                        <span class="cinema-title">${cinema.cinemaName}</span>
                        <span class="cinema-title">${cinema.cinemaAddress}</span>
                        <span class="cinema-title">평점 : ${cinema.cinemaGrade}</span>
                        <span class="cinema-title">수용인원 : ${cinema.cinemaMaxInclude}명</span>
                    
                        <div class="cinema-buttons">
                            <a href="/movieInsight/cinemaDetail/${cinema.cinemaName}#cinemaScroll" class="comment-button">댓글</a>
                            <a href="/movieInsight/cinemaDetail/${cinema.cinemaName}" class="edit-button">수정</a>
                            <a href="/movieInsight/managerDetail/deleteCinema/${cinema.cinemaNo}" class="delete-button">삭제</a>
                        </div>
                    </div>

                </c:forEach>
               

            </div>
            
        
        
            <script>
                // 'Enter' 키를 누를 때 영화 검색 기능 실행
                document.getElementById("searchInput").addEventListener("keyup", function (event) {
                    if (event.key === "Enter") {
                        searchCinema();
                    }
                });
            
                function searchCinema() {
                    // 사용자가 입력한 검색어 가져오기
                    var searchInput = document.getElementById("searchInput").value;
                    
                    // 모든 영화관 항목 가져오기
                    var cinemaItems = document.querySelectorAll(".cinema-item");
                    

                    for (var i = 0; i < cinemaItems.length; i++) {
                        // 각 영화 항목의 제목 가져오기
                        var cinemaTitle = cinemaItems[i].querySelector(".cinema-title").textContent;
            
                        // 영화 제목에 검색어가 포함되어 있으면 항목을 보이게 하고, 포함되어 있지 않으면 항목을 숨김
                        if (cinemaTitle.toLowerCase().includes(searchInput.toLowerCase())) {
                            cinemaItems[i].style.display = "flex";
                        } else {
                            cinemaItems[i].style.display = "none";
                        }
                    }
                }




                
            </script>

            <c:if test="${not empty message}">
         
                <script>
                    // EL/JSTL 구문이 먼저 해석
                    // 문자열의 경우 따옴표가 없는 상태이니 옆에 붙여줘야함.
                    alert('${message}') // ${message}
                </script>

            </c:if>


</body>
</html>
