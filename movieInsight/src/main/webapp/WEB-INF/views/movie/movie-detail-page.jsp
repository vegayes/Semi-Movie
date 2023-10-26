<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!--  
<c:forEach items="${boardTypeList}" var="boardType">
    <c:if test="${boardType.BOARD_CODE == boardCode}" >
        <c:set var="boardName" value="${boardType.BOARD_NAME}"/>
    </c:if>
</c:forEach>
-->

<%-- map에 저장된 값들을 각각 변수에 저장 --%>
<%-- 
<c:set var="pagination" value="${map.pagination}"/>
<c:set var="boardList" value="${map.boardList}"/>
<c:set var="boardName" value ="${boardTypeList[boardCode-1].BOARD_NAME}"/>
--%>
<%-- ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	1) 

	2) 
★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ --%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>movie-Detail</title>
    
    <link rel="stylesheet" href="/movieInsight/resources/css/movie/movie-detail-page.css">
    <script src="https://kit.fontawesome.com/69a462bb6c.js" crossorigin="anonymous"></script>
    
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />

    <main>
        <div class="movie">
            <div class="movie_img">
           	 	<img src="/movieInsight/resources/images/movie/${movieInfo.movieImg}">
            </div>
            <div>
                <div class="movie_title">
                    <div class="star">
                        <a href="#">
                            <i class="fa-solid fa-star" id="star"></i>         
                        </a>
                    </div>
                    <div>${movieInfo.movieTitle}</div>
                    <div> <%-- 평점 --%>
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                        <div></div>
                    </div>
                </div>
 	            <h2 class = "movie_summary_title">줄거리</h2>  
                <div class="movie_summary">
                     ${movieInfo.movieSummary} 
                </div>
                <div class="movie_description">
                    <div>
                        <div>
                            <div>장르 :
                            	${movieInfo.movieGenre}
                            </div> 
								
                        </div>
                        <div>
                            <div>감독 :</div>
                            
							<c:forEach items = "${directorInfoList}" var="directorInfo" varStatus="status">
							
									<c:if test="${status.index >= 0}">
										<a href="${directorInfo.CASTING_URL}" target="_blank">
											${directorInfo.CASTING_NAME}
										</a>
									
										<c:if test="${!status.last}">&nbsp; / </c:if>
									
									</c:if>

							</c:forEach>                             
                            
                            
                            <%-- 감독 나무위키 링크 걸어두기 (inline 으로 잡아서?? 구분자를 기준으로 많이 존재하면 그만큼 받아서 링크 걸어두기--%>
                            <%-- <c:choose>></c:choose>--%>
                            <a> ${movieInfo.directorNames}</a>
                        </div>
                        <div>
                            <div>출연진 :</div>
                            	
							<c:forEach items = "${actorInfoList}" var="actorInfo" varStatus="status">
							
									<c:if test="${status.index >= 0}">
										<a href="${actorInfo.CASTING_URL}" target="_blank">
											${actorInfo.CASTING_NAME}
										</a>
									
										<c:if test="${!status.last}">&nbsp; / </c:if>
									
									</c:if>

							</c:forEach>                            
                           	<%-- 
                            <a> ${movieInfo.actorNames}</a>
                            
                            --%>
                            <%-- 
                            <a>케네스 브래너,</a>
                            <a>카밀 코탄</a>
                            --%>
                        </div>
                    </div>
                    <div>
                        <div>개봉일 : ${movieInfo.movieReleaseDate}</div>
                        <div>등급 : 
                        <%-- 
                        	<c:choose>
                        		<c:when test="${movie.movieAge}.length() > 2">
                        			${movie.movieAge}
                        		</c:when>
                        		<c:otherwise>
                        			${movie.movieRunningTime}이상 관람가
                        		</c:otherwise>
                        	</c:choose>
                        --%>
                        	${movieInfo.movieAge}
                        </div>
                        <div>러닝타임 : ${movieInfo.movieRunningTime }분</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="movieP2">
            <div class="mp2Head">
                <h1 id="mm">영화 예매</h1>
            </div>
     <!--       	<form action="/Reservation" method="POST" name="movieReservation">  --> 
   				<div class = "selectCinemaList-Container">
    				<c:forEach items = "${selectCinemaList}" var="cinema" >
						<div class = "cinemaList">
							<div class="cinemaList-img">
		                        <a href="/movieInsight/cinemaDetail/${cinema.cinemaName}">
		                        	<div class = "cinemaImg-wrapper">
			                            <img src="/movieInsight/resources/images/cinema/${cinema.cinemaImg}">
		                        	</div>
		                        	<div class="cinema-hover">영화관 정보 보러가기</div>
		                        </a>
		                    </div>
		                    <div class="cinemaList-info">
		                        <div class = "cinemaList-info-content">${cinema.cinemaName}</div>
		                        <div class = "cinemaList-info-content">${cinema.cinemaAddress}</div>
		                    </div>
		                    <div class="cinemaList-url">
	                            <a href="${cinema.cinemaLink}" target="_blank">예매 바로가기</a>
		                    </div>
						</div>
    				</c:forEach>    				
   				</div>

			   <!-- </form> -->
          </div>


        <div class="comment">
            <div class="comhead"><h1 id="comment">comment</h1></div>
                <div class="combody">
                        <div class="comb1">
                            <img id="pop1" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png"><img id="pop2" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png">
                        </div>
                        <div class="comb2">
                            <div class="cb1"><h2 id="idd">id :</h2></div><div class="cb1-1"><h3 id="dd1">댓글내용 .......................................................................................................</h3></div><div class="cb1-2"><button class="editBtn">수정</button><button class="deletBtn">삭제</button></div>
                        </div>
                        <div class="comb3">
                            <img id="pop1" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png"><img id="pop2" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png">
                        </div>
                        <div class="comb4">
                            <div class="cb2"><h2 id="idd">id :</h2></div><div class="cb2-1"><h3 id="dd1">댓글내용 .......................................................................................................</h3></div><div class="cb2-2"><button class="editBtn">수정</button><button class="deletBtn">삭제</button></div>
                        </div>
                        <div class="comb5">
                            <img id="pop1" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png"><img id="pop2" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png"><img id="pop2" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png">
                        </div>
                        <div class="comb6">
                            <div class="cb3"><h2 id="idd">id :</h2></div><div class="cb3-1"><h3 id="dd1">댓글내용 .......................................................................................................</h3></div><div class="cb3-2"><button class="editBtn">수정</button><button class="deletBtn">삭제</button></div>
                        </div>
                        <div class="comb5">
                            <img id="pop1" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png"><img id="pop2" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png"><img id="pop2" src="/movieInsight/resources/images/movie/movieT/팝콘 1.png">
                        </div>
                        <div class="comb6">
                            <div class="cb3"><h2 id="idd">id :</h2></div><div class="cb3-1"><h3 id="dd1">댓글내용 .......................................................................................................</h3></div><div class="cb3-2"><button class="editBtn">수정</button><button class="deletBtn">삭제</button></div>
                        </div>
                </div>
                <fieldset class="commentForm">
                    <form id="facilityForm">
                        <div>
                            <input placeholder= "영화 감상내용 입력....">
                        </div>
                        <div>
                            <h3>평점 : </h3>
                        </div>
                        <div class="CF2"> 
                            <!-- 따봉 -->
                            <i class="far fa-thumbs-up"></i>
                            <i class="far fa-thumbs-up"></i>
                            <i class="far fa-thumbs-up"></i>
                            <i class="far fa-thumbs-up"></i>
                            <i class="far fa-thumbs-up"></i>
                        </div>
                    </form>
                    <button form="facilityForm" id="submit">
                    	<img src="/movieInsight/resources/images/movie/movieT/list.png">
                    </button>
                </fieldset>
        </div>

        <div class="foot">
            <div class="ftHead">
                <h1 id="hi">'<span style = "color : #74bbe8">${movieInfo.movieTitle}</span>'와 비슷한 영화 추천</h1>
            </div>
            <div class="ftMain">
                <div class="ftmain">
                    <div class="gallery-container">
                      <div class="gallery">
                      	<c:forEach items = "${recommendMovie}" var = "recommend">
	                        <div class="recommend-container">
	                          <a href="/${recommend.movieNo}">
	                    		<div class = "recommendImg-wrapper">	                    		
		                            <img src="/movieInsight/resources/images/movie/${recommend.movieImg}" alt="movieTitle : ${recommend.movieTitle}">
	                    		</div>
	                          </a>
	                        </div>
                      	</c:forEach>
                      </div>
                    </div>
                    <button class="prev-button">&lt;</button>
                    <button class="next-button">&gt;</button>
                  </div>
            </div>
        </div>
    </main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

    <script src="/movieInsight/resources/js/movie/movie-detail-page.js"></script>
</body>
</html>