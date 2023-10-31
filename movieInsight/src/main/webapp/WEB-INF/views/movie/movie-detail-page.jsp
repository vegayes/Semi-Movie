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

        <c:choose>
                <%-- *************** 관리자 페이지에서 등록 버튼 눌렀을 때 ************************ --%>
                <c:when test="${empty movieInfo}">
                    <form action="/movieInsight/managerDetail/insertMovie" method="POST" encType="multipart/form-data" class="movie">
                        
                            <div class="movie_img">
                                <img src="" id="movieImg">
                                <input type="file" name="movieImage"  id="fileInput" accept="image/*">
                            </div>
                            <div id="movie-content">
                            
                                <div class="movie_title">
                                    <div class="star">
                                        <a href="#">
                                            <i class="fa-solid fa-star" id="star"></i>         
                                        </a>
                                    </div>
                                    <input type="text" name="movieTitle" style="font-size: 30px;" placeholder="제목">
                                    <div> <%-- 평점 --%>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                        <div></div>
                                    </div>
                                </div>
                                <h2 class = "movie_summary_title">줄거리</h2>  
                                <textarea  name="movieSummary" ></textarea>

                                <div class="movie_description">
                                    
                                    <span>
                                        <input type="text" name="movieGenre" placeholder="장르">
                                        <input type="text" name="movieReleaseDate" placeholder="출시일(2020-01-01)">
                                    </span>
                                    <div>

                                        <input type="text" name="directorNames" placeholder="감독(/구분)">
                                        <input type="text" name="actorNames" placeholder="출연진(/구분)">
                                        <input type="text" name="movieAge" placeholder="관람나이(세)">
                                        <input type="text" name="movieRunningTime" placeholder="상영시간(숫자만)">

                                    </div>
                                    <button type="submit" style="color: black;" id="insertButton" name="insert">등록하기</button>
                                </div>
                               
                            </div>
                    </div>
                </form>
            </c:when>
            
            <%-- *************** 관리자 페이지에서 수정 버튼 눌렀을 때 ************************--%>
            <c:when test="trun">
                <form action="/movieInsight/managerDetail/updateMovie" method="POST" encType="multipart/form-data" class="movie">
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
                </form>
            </c:when>
            <%-- *************** 일반 회원이 영화 조회할 때 ************************--%>
            <c:otherwise>
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
            </c:otherwise>
        </c:choose>





        <div class="movieP2">
            <div class="mp2Head">
                <h1 id="mm">영화 예매</h1>
            </div>
     <!--       	<form action="/Reservation" method="POST" name="movieReservation">  --> 
   				<div class = "selectCinemaList-Container">
    				<c:forEach items = "${selectCinemaList}" var="cinema" >
    					<c:if test = "${cinema.cinemaType eq 'C' }"> <%-- 영화관인경우 --%>
	 						<div class = "cinemaList">
								<div class="cinemaList-img">
			                        <a href="/movieInsight/cinemaDetail/${cinema.cinemaName}">
			                        	<div class = "cinemaImg-wrapper">
				                            <img src="/movieInsight/resources/images/cinema/${cinema.cinemaImg}">
				                        	<div class="cinema-hover"><p>영화관 정보 보러가기</p></div>
			                        	</div>
			                        </a>
			                    </div>
			                    <div class="cinemaList-info">
			                        <div class = "cinemaList-info-content">${cinema.cinemaName}</div>
			                        <div class = "cinemaList-info-content">${cinema.cinemaAddress}</div>
			                    </div>
			                    <div class="cinemaList-url">
		                            <a href="${cinema.cinemaLink}" target="_blank"><p>예매 바로가기</p></a>
			                    </div>
							</div>   
    					</c:if>
    					
    					<c:if test = "${cinema.cinemaType eq 'O' }"> <%-- OTT의 경우 --%>
 	 						<div class = "cinemaList">
								<div class="cinemaList-img">
			                        <a href="${cinema.cinemaLink}" target="_blank">
			                        	<div class = "cinemaImg-wrapper">
				                            <img src="/movieInsight/resources/images/cinema/${cinema.cinemaImg}" >
				                        	<div class="cinema-hover"><p>영화관 정보 보러가기</p></div>
			                        	</div>
			                        </a>
			                    </div>
			                    <div class="cinemaList-info">
			                        <div class = "cinemaList-info-content">${cinema.cinemaName}</div>
			                    </div>
			                    <div class="cinemaList-url">
		                            <a href="${cinema.cinemaLink}" target="_blank"><p>사이트 바로가기</p></a>
			                    </div>
							</div>     					
    					</c:if>
    					
    					
    					
    					

    				</c:forEach>    				
   				</div>

			   <!-- </form> -->
          </div>

       
        <div class="comment" id="commentScroll">
            <div class="comhead"><h1 id="comment">comment</h1></div>
                <div class="combody" style = "border:2px solid red">
                	<table class = "comment-list-table">
	                	<c:forEach items = "${commentMovieList}" var="comment">
	                		
	                		<tr class = "comment-grade-tr">
	                			<td>평점 ${comment.movieGrade}</td>
	                		</tr>
	                		<tr class = "comment-content-tr" style = "border : 2px solid blue">
	                			 <td class = "comment-img">
                                      <div class = "comment-writer-img-wrapper">
                                      	<c:if test = "${empty comment.writerProfile}">
                                     		<img src="/movieInsight/resources/images/member/기본이미지.png">
                                      	</c:if>
                                      	<c:if test = "${not empty comment.writerProfile}">
	                                          <img src="/movieInsight/resources/images/member/${comment.writerProfile}">                                      	
                                      	</c:if>
                                      </div>  
                                      
                                 </td>
                                 <td class = "comment-list-id">
                                 	${comment.commentMovieWriter} : 
                                 </td>
                                  <td class = "comment-list-content comment-content">${comment.movieCommentContent}</td>
                                  <td class = "comment-list-date">${comment.movieCommentDate}</td>
                                  <td class = "comment-list-edit">
                                  	<c:if test = "${comment.commentMovieWriter eq sessionScope.loginMember.memberId}">
    	                            	<button class="editBtn">수정</button>
		                            	<button class="deletBtn">삭제</button>                              	
                                  	</c:if>
                                  </td>
	                		</tr>
	                		
	                	</c:forEach> 
                	</table>

                </div>
               <!--   <fieldset class="commentForm">
                    <form action="/movieInsight/movie/profile" id="facilityForm">-->
                <div class = "commentForm">
                    <div class = "commnetForm-white">

                        <div>
                            <textarea id="commentContent" placeholder= "영화 감상내용 입력...."></textarea>
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
                <!--      </form> -->
            <!-- </fieldset>  -->
                    </div>
                    <div id="commentSubmit">
                        <img src="/movieInsight/resources/images/movie/movieT/list.png">
                    </div>
                </div>
            </div>
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
	                          <a href="/movieInsight/movie/${recommend.movieNo}">
	                    		<div class = "recommendImg-wrapper">	                    		
		                            <img src="/movieInsight/resources/images/movie/${recommend.movieImg}" alt="movieTitle : ${recommend.movieTitle}">
		                            <div class = "recommendImg-hover">${recommend.movieTitle}</div>
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

    <script>
        const movieNo = "${movieInfo.movieNo}"
        const memberNo = "${sessionScope.loginMember.memberNo}";
    </script>

    <script src="/movieInsight/resources/js/movie/movie-detail-page.js"></script>
</body>
</html>