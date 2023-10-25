<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<%-- ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	1) CSS 왜 안되는지 모르겠음. 
★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ --%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Search-Movie</title>
		<link rel="stylesheet" href="/movieInsight/resources/css/movie/search-movie.css">
	</head>
	<body>
	
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
	
        <main>
            <section class = "search-list">
                <div class = "search-title-container">
                    검색결과 : ${movieQuery}
                </div>

                <c:choose>
                	<%-- 1) 검색 결과가 비어있는 경우  --%>
                	<c:when test="${empty movieList}">
                		<%-- ★★★★★★★★★★★★★★★★★★★ 아니 이거 왜 CSS 안먹는지 모르겠음.★★★★★★★★★★★★★★★★★★★★★★★ --%>
                		<div class = "movie-search-list-none-container"  id = "none-list">
                			검색결과가 존재하지 않습니다.
                		</div>
                	</c:when>
                
                	<%-- 2) 검색 결과가 존재하는 경우 --%>
                	<c:otherwise> 
                		<%-- 2-1) 반복해서 보여주기 --%>

                		<c:forEach items = "${movieList}" var="movie" >

                			<a href="/movieInsight/movie/${movie.movieNo}">
				                 <div class = "movie-search-list-container" onclick="movieInfo('${movie.movieNo}')"> <%-- value 값으로 전달가능? --%>
				                    <div class = "movie-search-img-wrapper">
				                        <img src = "/movieInsight/resources/images/movie/${movie.movieImg}" class = "movie-image"> <%-- ???????????? --%>
				                        <div class="movie-search-title">${movie.movieTitle}</div>
				                    </div>
				                    
				                    <div class = "movie-search-content-container">
				                        <div class = "movie-search-content">
				                            <h1>줄거리</h1>
				                            <div class = "content-box">
				                                <p>
				                                    ${movie.movieSummary}			                                
				                                 </p>
				                            </div>
				                        </div>
				                        <div class = "movie-search-content">
				                            <h1>등장인물</h1>
				                            <div class = "content-box">
				                                <p>
				                                    ${movie.actorNames}
				                                </p>
				                            </div>
				                        </div>
				                        <div class = "movie-search-content">
				                            <h1>영화정보</h1>
				                            <div class = "content-box">
				                            	<table>
				                            		<tr>
				                            			<td>
				                            				감독 : ${movie.directorNames}  
				                            			</td>
				                            			<td>
				                            				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;장르 : ${movie.movieGenre}
				                            			</td>
				                            			<td>
				                            				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;개봉일 : ${movie.movieReleaseDate}
				                            			</td>
				                            			<td>
				                            				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상영등급 : ${movie.movieAge} 
				                            			</td>
				                            			<td>
				                            				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;러닝타임 : ${movie.movieRunningTime}분
				                            			</td>
				                            						                            			
				                            		</tr>
				                            	</table>
	
				                            </div>
				                        </div>
				                    </div>
				                </div>
			                </a>

                		</c:forEach>

                	</c:otherwise>
                
                </c:choose>
                
            </section>

        </main>
        
		<script src="/movieInsight/resources/js/movie/search-movie.js"></script>
	
        
	</body>
</html>