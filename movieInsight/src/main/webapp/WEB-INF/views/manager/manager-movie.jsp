<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지-영화</title>
    <link rel="stylesheet" href="/movieInsight/resources/css/manager/manager_movie.css">
</head>
<body>
    

        <nav>
            <div class="admin-menu">
                <ul>
                    <li class="admin-list"><a href="#">회원 관리</a></li> 
                    <li class="admin-list"><a href="/movieInsight/mypage/manager/movie">영화</a></li>               
                    <li class="admin-list"><a href="#">영화관</a></li>
                    <li class="admin-list"><a href="/movieInsight/mypage/manager/menu">메뉴</a></li>
                    <li class="admin-list"><a href="/moviInsight/manager/promotion">이벤트</a></li>
                </ul>
            </div>
        </nav>

        
            <div class="movie-search">
                <input type="text" placeholder="영화 검색" id="searchInput">  <br><br>
                <span>총 영화 : ${movieList.size()}편</span>
            </div>
            

            <div class="movie-item-list">

                <c:forEach items = "${movieList}" var="movieList" >
                    <div class="movie-item">
                        <span class="movie-title">${movieList.movieTitle}</span>
                        <span class="movie-title">${movieList.movieGenre}</span>
                        <span class="movie-title">${movieList.movieGrade}</span>
                        <span class="movie-title">${movieList.movieRunningTime}분</span>
                    
                        <div class="movie-buttons">
                            <a href="/movieInsight/movie/${movieList.movieNo}"  class="comment-button">댓글</a>
                            <a href="#" class="edit-button">수정</a>
                            <a href="#" class="delete-button">삭제</a>
                        </div>
                    </div>
                </c:forEach>
               

            </div>
            
        
            <script>
                // 'Enter' 키를 누를 때 영화 검색 기능 실행
                document.getElementById("searchInput").addEventListener("keyup", function (event) {
                    if (event.key === "Enter") {
                        searchMovies();
                    }
                });
            
                function searchMovies() {
                    // 사용자가 입력한 검색어 가져오기
                    var searchInput = document.getElementById("searchInput").value;
                    
                    // 모든 영화 항목 가져오기
                    var movieItems = document.querySelectorAll(".movie-item");
            
                    for (var i = 0; i < movieItems.length; i++) {
                        // 각 영화 항목의 제목 가져오기
                        var movieTitle = movieItems[i].querySelector(".movie-title").textContent;
            
                        // 영화 제목에 검색어가 포함되어 있으면 항목을 보이게 하고, 포함되어 있지 않으면 항목을 숨김
                        if (movieTitle.toLowerCase().includes(searchInput.toLowerCase())) {
                            movieItems[i].style.display = "flex";
                        } else {
                            movieItems[i].style.display = "none";
                        }
                    }
                }
            </script>
            
    

   


</body>
</html>
