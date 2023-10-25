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
            	<form action="/Reservation" method="POST" name="movieReservation">
		            <div class="mmcon">
		                <div class="line1">
		                    <div class="l1-1">
		                        <a href="">
		                            <img src="/movieInsight/resources/images/movie/movieT/Rectangle 3.png">
		                        </a>
		                    </div>
		                    <div class="l1-2">
		                        <h3 id="ms">메가박스 코엑스몰 <br>
		                            서울 강남구 봉은사로 524 코엑스몰 B1</h3>
		                    </div>
		                    <div class="l1-3">
		                        <h3>
		                            <a id="my" href="https://www.megabox.co.kr/booking">예매 바로가기</a>
		                        </h3>
		                    </div>
		                </div>
		                <div class="line2">
		                    <div class="l2-1">
		                        <a href="">
		                            <img src="/movieInsight/resources/images/movie/movieT/Rectangle 4.png">
		                        </a>
		                    </div>
		                    <div class="l2-2">
		                        <h3 id="ms">CGV 용산아이파크몰 <br>
		                            서울특별시 용산구 한강대로23길 55</h3>
		                    </div>
		                    <div class="l2-3">
		                        <h3>
		                            <a id="my" href="http://www.cgv.co.kr/theaters/?theaterCode=0013">예매 바로가기</a>
		                        </h3>
		                    </div>
		                </div>
		                <div class="line3">
		                    <div class="l3-1">
		                        <a href="">
		                            <img src="/movieInsight/resources/images/movie/movieT/Rectangle 2.png">
		                        </a>
		                    </div>
		                    <div class="l3-2">
		                        <h3 id="ms">
		                            CGV 왕십리 <br>
		                            서울특별시 성동구 행당동 왕십리광장로 17
		                        </h3>
		                    </div>
		                    <div class="l3-3">
		                        <h3>
		                            <a id="my" href="http://www.cgv.co.kr/theaters/?areacode=01&theaterCode=0074&date=20230919">예매 바로가기</a>
		                        </h3>
		                    </div>
		                </div>
		            </div>
			   </form>
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
                <h1 id="hi">사용자가 종아할 만한 영화</h1>
            </div>
            <div class="ftMain">
                <div class="ftmain">
                    <div class="gallery-container">
                      <div class="gallery">
                        <div class="ft1">
                          <a href="/favorite1">
                            <img src="/movieInsight/resources/images/movie/movieT/youtube-logo 1.png" alt="Image 1">
                          </a>
                        </div>
                        <div class="ft2">
                          <a href="/favorite2">
                            <img src="/movieInsight/resources/images/movie/movieT/logo 2.png" alt="Image 2">
                          </a>
                        </div>
                        <div class="ft3">
                          <a href="/favorite3">
                            <img src="/movieInsight/resources/images/movie/movieT/cat2 3.png" alt="Image 3">
                          </a>
                        </div>
                        <div class="ft4">
                          <a href="/favorite4">
                            <img src="/movieInsight/resources/images/movie/movieT/cat2 4.png" alt="Image 4">
                          </a>
                        </div>
                        <div class="ft5">
                          <a href="/favorite5">
                            <img src="/movieInsight/resources/images/movie/movieT/DR.CHEON.jpg" alt="Image 5">
                          </a>
                        </div>
                        <!-- 추가 이미지들을 이곳에 추가 -->
                      </div>
                    </div>
                    <button class="prev-button">&lt;</button>
                    <button class="next-button">&gt;</button>
                  </div>
            </div>
        </div>
    </main>


    <script src="/movieInsight/resources/js/movie/movie-detail-page.js"></script>
</body>
</html>