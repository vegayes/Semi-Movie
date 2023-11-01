<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mypage</title>
		
		<!-- 아이콘 -->
        <script src="https://kit.fontawesome.com/ac58eafae7.js" crossorigin="anonymous"></script>

         <!-- Swiper 라이브러리를 CDN을 통해 로드 -->
         <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css" />

        <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
		
		
		<link rel="stylesheet" href="/movieInsight/resources/css/mypage/mypage.css">

		<link rel="stylesheet" href="/movieInsight/resources/css/mypage/favorite-list-popup.css">
		<link rel="stylesheet" href="/movieInsight/resources/css/mypage/comment-update-popup.css">  
	</head>
	<body>
	
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

        <main>

            <aside class = "my-page-tag">
                <a href="#">즐겨찾기</a>
                <a href="#mypage-visit-history">방문기록</a>
                <a href="#mypage-comment-list">내가 작성한 댓글</a>
                <a href="#mypage-modify-member-info">회원정보 수정</a>
            </aside>

            <section class ="mypage-content">
                <!--  마이페이지 첫 번째 구간 -->
                <section class = "mypage"  id = "mypage-favorite">

                    <div class = "mypage-title">


                       	<p>My Page</p>
                       
                        <p>${loginMember.memberNickname}님, 환영합니다.</p>
<%-- ①User는 로그인한 사람 JSP에서 변경해야함  --%>
                    </div>

                    <div class = "favorite-setting" >
					
                        <a class = "movie-favorite-btn" id = "movie-favorite-btn">
                            <i class="fa-solid fa-star"></i>
                            영화 즐겨찾기
                        </a>

                        <a class = "cinema-favorite-btn" id = "cinema-favorite-btn">
                            <i class="fa-solid fa-star"></i>
                            영화관 즐겨찾기
                        </a>


<%-- js로 빼지 말고 해야하나??:: 영화를 누르면 회원이 저장한 영화 즐겨찾기에 대한 데이터 가져오기
                         :: 영화관을 누르면 회원이 저장한 영화관 즐겨찾게 대한 데이터 가져오기  --%>



                        <div class = "edit-favorite-btn" id = "favorite-popup-open">
                            <i class="fa-solid fa-pen-to-square"></i>
                        </div>
                    </div>
                    
                    
                    
                    <!-- --------------------------------------------------------------------------------- -->
                 
 <%-- =====================================================================================================================================       
                    								 즐겨찾기 리스트  팝업    --%> 
                     <div id = "favorite-list-modal-box" style="display : none;">
                         <div id = "favorite-list-modal-content">
                             <div id = "fv-modal-close" >&times;</div>
                             
                             <div class = "popup-title">
                                 MovieInSight
                             </div>
                 
                             <div class = "favorite-deletAll-btn-container">
                                 <label>
                                     <input type="checkbox" name = "favorite-check-All" id = "favorite-check-All"></input>
                                     전체선택
                                 </label>
                             </div>
                 
                             <div class = "favorite-list-container" >
                                 <table class = "favorite-list-table" id = "favorite-list-table">
                                     <thead>
                                         <tr>
                                             <th class = "favorite-list-img">list</th>
                                             <th class = "favorite-list-title">Title</th>
                                             <th class = "favorite-list-date">Date of registration</th>
                                             <th class = "favorite-list-check">check</th>
                                         </tr>
                                     </thead>
                                     
                                     <c:forEach items = "${movieList}" var="movie">
	                                     <tr class = "favorite-list fvMovie" style = "border : 2px solid blue" id = "fvMovie">
	                                         <td class = "favorite-list-img">
	                                             <div class = "favorite-list-img-wrapper">
	                                                 <img src="/movieInsight/resources/images/movie/${movie.movieImg}">
	                                             </div>  
	                 
	                                         </td>
	                                         <td class = "favorite-list-title">${movie.movieTitle}</td>
	                                         <td class = "favorite-list-date">${movie.movieLikeEnrollDate}</td>
	                                         <td class = "favorite-list-check">
	                                             <input type="checkbox" name ="favorite-check" id="check_btn" data-movieNo="${movie.movieNo}"/>
	                                             <label for="check_btn"></label>
	                                         </td>
	                                     </tr> 
                                     </c:forEach>
                           
                                     <c:forEach items = "${cinemaList}" var="cinema">
	                                     <tr class = "favorite-list fvCinema" style = "border : 2px solid red"  id = "fvCinema">
	                                         <td class = "favorite-list-img">
	                                             <div class = "favorite-list-img-wrapper">
	                                                 <img src="/movieInsight/resources/images/cinema/${cinema.cinemaImg}">
	                                             </div>  
	                 
	                                         </td>
	                                         <td class = "favorite-list-title">${cinema.cinemaName}</td>
	                                         <td class = "favorite-list-date">${cinema.cinemaLikeEnrollDate}</td>
	                                         <td class = "favorite-list-check">
	                                             <input type="checkbox" name ="favorite-check" id="check_btn" data-cinemaNo="${cinema.cinemaNo}"/>
	                                             <label for="check_btn"></label>
	                                         </td>
	                                     </tr> 
                                     </c:forEach>
                                 </table>
                            </div>
                             
                             <a class = "favorite-delet-btn-container">
                                <div class = "red favorite-delet-btn">삭제</div>
                             </a>                               

                         </div>
                         <label  id = "fv-modal-back"></label>
                     </div>    
<%-- ============================================================ 즐겨찾기 리스트 (팝업) 끝 ============================================================ --%>
					
					<%--1) 즐겨찾기 리스트가 없는 경우  --%>
					
					<%--<c:if test="${empty }">--%>
					<div class = "favorite-list" id = "movie-favorite-container"  >
						<%--1) 즐겨찾기 리스트가 없는 경우  --%>
						<c:if test = "${empty movieList}">
							
							<div class = "favorite-not-content">
								 현재 저장된 즐겨찾기가 없습니다. 
								 <!-- ★★★★★★★★★★★★★★★★★★★★★★★★ 추가하러 가기 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  -->
							</div>
						
						</c:if>
			
						<%-- 1-1) 즐겨 찾기 리스트가 있는 경우 --%>
                    	<c:if test = "${not empty movieList}">
				            <div class="likeContainer">
				                <div class="likeSwiper">
				                    <div class="gallery-container">
				                      <div class="gallery">
				                      	<c:forEach items = "${movieList}" var = "movie">
					                        <div class="recommend-container">
					                          <a href="/movieInsight/movie/${movie.movieNo}">
					                    		<div class = "recommendImg-wrapper">	                    		
						                            <img src="/movieInsight/resources/images/movie/${movie.movieImg}" alt="movieTitle : ${movie.movieTitle}">
						                            <div class = "recommendImg-hover">${movie.movieTitle}</div>
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
                    	</c:if>	
                    	
                    </div>
                    
                    
					<div class = "favorite-list" id = "cinema-favorite-container" style = "display: none;">
						<%--1) 즐겨찾기 리스트가 없는 경우  --%>
						<c:if test = "${empty cinemaList}">
							
							<div class = "favorite-not-content">
								 현재 저장된 즐겨찾기가 없습니다. 
								 <!-- ★★★★★★★★★★★★★★★★★★★★★★★★ 추가하러 가기 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  -->
							</div>
						
						</c:if>
			
						<%-- 1-1) 즐겨 찾기 리스트가 있는 경우 --%>
                    	<c:if test = "${not empty cinemaList}">
				            <div class="likeContainer">
				                <div class="likeSwiper">
				                    <div class="gallery-container">
				                      <div class="galleryCinema">
				                      	<c:forEach items = "${cinemaList}" var = "cinema">
					                        <div class="recommend-container-cinema">
					                          <a href="/movieInsight/cinemaDetail/${cinema.cinemaName}">
					                    		<div class = "recommendImg-wrapper-cinema">	                    		
						                            <img src="/movieInsight/resources/images/cinema/${cinema.cinemaImg}" alt="movieTitle : ${cinema.cinemaName}">
						                            <div class = "recommendImg-hover">${cinema.cinemaName}</div>
					                    		</div>
					                          </a>
					                        </div>
				                      	</c:forEach>
				                      	
				                      </div>
				                    </div>
				                    <button class="cinema-prev-button">&lt;</button>
				                    <button class="cinema-next-button">&gt;</button>
				                  </div>
				            </div>
                    	</c:if>	
                    	
                    </div>                    
                </section>


                <!-- 마이페이지 두 번째 구간 -->
                <section class = "mypage"  id = "mypage-visit-history">

                    <div class = "visit-history-container">
                        <div class = "visit-history-title">
                            방문기록
                        </div>
                    </div>

                    <!-- ②방문기록 리스트!! (근데,, 리스트가 없으면?? ) -->
                    <div class = "visit-history-content-container" >
                        <!-- ① 스크롤 해서 가져오기! -->
                         <!-- ⑥ ②방문기록 없는 경우 jsp로 loop 값. -->

                         <div class = "visit-history-content">
                            <table>     

                                <tr class = "visit-history-list">
                                    <td class = "history-img-container">
                                        <img src = "">
                                    </td>
                                    <td class = "history-content-container">
                                        <h1> 인사이드 아웃</h1>
                                        <!-- ④ 줄거리 더 많이 쓰이게되면, ....으로 표시-->
                                        <p>그러나 부모님과 함께 샌프란시스코로 이사하면서 그녀의 삶은 완전히 뒤바뀐다. 기쁨은 이런 큰 변화 속에서 라일리의 감정을 잘 다스리려고 노력한다. 그러나, 이사로 인한 스트레스로 슬픔이 나타나고 만다. 라일리의 기분을 위해 고군분투하던 기쁨과 슬픔은 먼 곳에 떨어져 버리고 결국 라일리의 감정 컨트롤 본부에는 악감정만이 남게 된다.</p>
                                    </td>
                                    <td class = "history-del-btn">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>


                                <tr class = "visit-history-list">
                                    <td class = "history-img-container">
                                        <img src = "">
                                    </td>
                                    <td class = "history-content-container">
                                        <h1> 미니언즈</h1>
                                        <!-- ④ 줄거리 더 많이 쓰이게되면, ....으로 표시-->
                                        <p>각자 색다른 매력을 지닌 미니언 케빈, 스튜어트와 밥은 갑작스럽게 새로운 보스를 찾아야 하는 상황에 처 하니 그들은 함께 슈퍼배드 원정대를 결성 한다. 그리고 머지 않아 여성 슈퍼 악당 스칼렛을 만나 그녀의 미션을 수행하고자 나선다.</p>
                                    </td>
                                    <td class = "history-del-btn">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>


                                <tr class = "visit-history-list">
                                    <td class = "history-img-container">
                                        <img src = "">
                                    </td>
                                    <td class = "history-content-container">
                                        <h1> 주토피아</h1>
                                        <!-- ④ 줄거리 더 많이 쓰이게되면, ....으로 표시-->
                                        <p>토끼 종족 최초로 경찰이 된 열정 넘치는 주디는 덩치가 큰 육식 동물들 사이에서 경찰로 활약하기 위해 애쓴다. 그러던 어느 날, 주토피아에 의문의 연쇄 실종 사건이 발생하고 능글맞은 사기꾼 여우 닉과 함께 48시간 안에 사건을 해결하기 위해 이곳저곳을 돌아다니며 고군분투한다.</p>
                                    </td>
                                    <td class = "history-del-btn">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>
    

                                <tr class = "visit-history-list">
                                    <td class = "history-img-container">
                                        <img src = "">
                                    </td>
                                    <td class = "history-content-container">
                                        <h1> 엘리멘탈</h1>
                                        <!-- ④ 줄거리 더 많이 쓰이게되면, ....으로 표시-->
                                        <p>각양각색의 원소들이 살아가는 엘리멘트 시티, 지하철에서 우연히 마주친 앰버와 웨이드는 정반대 종족인 서로에게 끌린다.</p>
                                     </td>
                                    <td class = "history-del-btn">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>


                                <tr class = "visit-history-list">
                                    <td class = "history-img-container">
                                        <img src = "">
                                    </td>
                                    <td class = "history-content-container">
                                        <h1> 미니언즈</h1>
                                        <!-- ④ 줄거리 더 많이 쓰이게되면, ....으로 표시-->
                                        <p>각자 색다른 매력을 지닌 미니언 케빈, 스튜어트와 밥은 갑작스럽게 새로운 보스를 찾아야 하는 상황에 처 하니 그들은 함께 슈퍼배드 원정대를 결성 한다. 그리고 머지 않아 여성 슈퍼 악당 스칼렛을 만나 그녀의 미션을 수행하고자 나선다.</p>
                                    </td>
                                    <td class = "history-del-btn">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>


                                <tr class = "visit-history-list">
                                    <td class = "history-img-container">
                                        <img src = "">
                                    </td>
                                    <td class = "history-content-container">
                                        <h1> 주토피아</h1>
                                        <!-- ④ 줄거리 더 많이 쓰이게되면, ....으로 표시-->
                                        <p>토끼 종족 최초로 경찰이 된 열정 넘치는 주디는 덩치가 큰 육식 동물들 사이에서 경찰로 활약하기 위해 애쓴다. 그러던 어느 날, 주토피아에 의문의 연쇄 실종 사건이 발생하고 능글맞은 사기꾼 여우 닉과 함께 48시간 안에 사건을 해결하기 위해 이곳저곳을 돌아다니며 고군분투한다.</p>
                                    </td>
                                    <td class = "history-del-btn">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>
    

                                <tr class = "visit-history-list">
                                    <td class = "history-img-container">
                                        <img src = "">
                                    </td>
                                    <td class = "history-content-container">
                                        <h1> 엘리멘탈</h1>
                                        <!-- ④ 줄거리 더 많이 쓰이게되면, ....으로 표시-->
                                        <p>각양각색의 원소들이 살아가는 엘리멘트 시티, 지하철에서 우연히 마주친 앰버와 웨이드는 정반대 종족인 서로에게 끌린다.</p>
                                     </td>
                                    <td class = "history-del-btn">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>
                               


                               
                            </table>
                        </div>



                        </div>
                    </div>
                </section>


                <!-- 마이페이지 세 번째 구간 -->
                <section class = "mypage"  id = "mypage-comment-list">

                    <div class = "comment-list-container">
                        <div class = "comment-list-title">
                            내가 작성한 댓글
                        </div>
                    </div>
                    
                    <div class = "comment-setting" >
					
                        <a class = "movie-comment-btn" id = "movie-comment-btn">
                            <i class="fa-solid fa-star"></i>
                            영화 
                        </a>
						<div class="dropdown">
	                        <a class = "cinema-comment-btn" id = "cinema-comment-btn">
	                            <i class="fa-solid fa-star"></i>
	                            영화관 
	                        </a>
						  <div class="dropdown-content">
							  <a class = "dropdownBtn">시설 만족도</a>
							  <a class = "dropdownBtn">직원 친절도</a>
						  </div>
					   </div>


                    </div>

                    <div class = "comment-list-content-container" id = "movie-comment-container">
                        <table class = "comment-list-table" >

							<c:if test= "${empty commentMovie}">
								<tr class = "comment-not-content">
								 	현재 작성된 영화 댓글이 없습니다. 
								 <!-- ★★★★★★★★★★★★★★★★★★★★★★★★ 추가하러 가기 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  -->
								</tr>
							</c:if>
		 					
		 					<c:if test= "${not empty commentMovie}">
	 							<c:forEach items = "${commentMovie}" var = "commentMovie" varStatus="status">
		                             <tr class = "comment-list-col" >
		                                <td class = "comment-list-check">
		                                    <input type="checkbox" name = "comment-check" id = "check">
		
		                                    <label for="check"></label> 
		                                </td>
		                                <td class = "comment-list-content-no">${status.count}</td>
		                                <td class = "comment-list-board">${commentMovie.movieTitle}</td>
		                                <td class = "comment-list-content">${commentMovie.movieCommentContent}</td>
		                                <td class = "comment-list-date"> ${commentMovie.movieCommentDate} ${commentMovie.movieCommentNo}</td>
		                                <td class = "comment-list-edit" id = "cmPopup">
		                                    <button onclick="updateCommentModal(${commentMovie.movieCommentNo})">
		                                        수정 
		                                    </button>
		                                </td>
		                               
		                            </tr>	
 								</c:forEach>
		 					</c:if>
		 				</table>		
					</div>
					<div class = "comment-list-content-container" id = "cinema-comment-container" style = "display : none">
 						<table class = "comment-list-table">
 							
 							<c:if test= "${empty commentCinema}">
								<tr class = "comment-not-content">
								 	현재 작성된 영화관 댓글이 없습니다. 
								 <!-- ★★★★★★★★★★★★★★★★★★★★★★★★ 추가하러 가기 ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★  -->
								</tr>
							</c:if>
 							<c:if test= "${not empty commentCinema}">	
	 							<c:forEach items = "${commentCinema}" var = "commentCinema" varStatus="status">
		                             <tr class = "comment-list-col">
		                                <td class = "comment-list-check">
		                                    <input type="checkbox" name = "comment-check" id = "check">
		                                    <label for="check"></label> 
		                                </td>
		                                <td class = "comment-list-content-no">${status.count}</td>
		                                <td class = "comment-list-board">${commentCinema.cinemaName}</td>
		                                <td class = "comment-list-content">${commentCinema.cinemaCommentContent}</td>
		                                <td class = "comment-list-date"> ${commentCinema.cinemaCommentDate}</td>
		                                 <td class = "comment-list-type" style="display:none">${commentCinema.cinemaCommentType}</td>
		                                <td class = "comment-list-edit" id = "cmPopup">
		                                    <button onclick="updateCommentModalCinema(${commentCinema.cinemaCommentNo})">
		                                        수정 
		                                    </button>
		                                </td>
		                            </tr>	
	 							</c:forEach>
	 						</c:if>
                        </table>
                    </div>
                    <div class ="comment-list-del-container">
                        <table>
                            <tr class = "comment-list-col">
                                <td class = "comment-list-check">
                                    <input type="checkbox" name = "comment-All" id = "comment-del-All">
                                </td>
                   		        <td class = "comment-list-all-content-">전체선택</td>
                                <td class = "comment-list-board">
                                   <button class = "comment-list-del-btn">
                                        삭제
                                    </button>
                                </td>

                            </tr>
                        </table>
                    </div>
                </section>

 <%--  ===========================================  댓글 수정 팝업  시작  ==================================== --%>
                <div id = "comment-update-modal-box">
                    <div id = "comment-update-content">

                        <div id = "cm-modal-close" >&times;</div>
                        <section class ="title-container">
                            댓글 수정
                        </section>
                        <!-- 추가도 동일 -->
            
                        <section class = "update-container">
                            <div class = "update-info-container">
                                <table class = "cm-update-info-table">
            
                                    <tr>
                                        <th> 게시물 명 </th>
                                        <td>
                                        	<%-- 
                                            <input type="text" readonly id = "cm-update-input-title">
                                            --%>
                                            <!-- readonly :: 글 수정 불가하게 하기  -->
                                             <p id = "comment-title"></p>
                                        </td>
                                    </tr>
            
                                    <tr>
                                        <th> 댓글 내용</th>
                                        <td>
                                            <textarea type="text"  autocomplete="off"  id = "cm-update-input-comment">
                                            </textarea>
                                        </td>
                                    </tr>
            
                                    <tr>
                                        <th> 영화평점 </th>
                                        <!-- 별점으로 표시하면 좋을 거 같음. -->
                                        <td>
                                            <input type="number" step="0.1"  max=5 min = 0 id= "cm-update-grade" >
                                        </td>
                                    </tr>
            
                                    <tr>
                                        <th> 작성 및 수정일 </th>
                                        <td>
                                        <%-- 
                                        <input type="text" readonly> 
                                        --%>
                                           <p id = "comment-enroll-date"></p>
                                        </td>
                                    </tr>
            
                                </table>
            
            
                            </div>
                        </section>
            
                        <section class = "update-btn-container">
                            <div class ="btn" id= "comment-update-btn">
                                수정하기
                            </div>
                            <div class ="btn" id = "comment-del-btn">
                                취소하기
                            </div>
                        </section>

                    </div>
                    <label  id = "cm-modal-back" style = "background-color: red;"></label>
                </div>   

<%-- ========================================   댓글 수정 팝업 종료 =========================================--%>



                <!-- 마이페이지 네 번째 구간 -->
                <!-- 클래스 원래 mypage 근데 이거 크기 정해져있는거라서 내가보기엔 크기 안정한거로 만들어야 되지 않을까.. 라는 생각? -->
                <section class = "mypage-info"  id = "mypage-modify-member-info">

                    <div class = "modify-member-info-title">
                        회원정보 수정
                    </div>

                    <div class = "modify-member-info-container">
	                        
						<div class = "modify-member-info-content">
	                       <form action ="/movieInsight/mypage/profile" method="POST" name ="info-update" id = "profileUpdate" enctype="multipart/form-data">
	                         <!--  name 아직 안넣음 -->
		                         
		                         <!-- 프로필 -->
		                         <div class = "member-info-profile-container">
		
									<div class = "member-profile-wrapper">
										<%-- 프로필 이미지가 없으면  --%>
										<c:if test = "${empty loginMember.memberProfile}">
											<img src="/movieInsight/resources/images/member/기본이미지.png" id = "profileImage">
										</c:if>
										
										<c:if test = "${not empty loginMember.memberProfile}">
											<img src="/movieInsight/resources/images/member/${loginMember.memberProfile}" id = "profileImage">
										</c:if>
	                                </div>
	                                
	 								<span id="deleteImage">x</span>
									
									<div class = "member-change-btn-container">
			                             <input type="file" id="file-input" name="profileImage" accept="image/*"/>
			                             <label for="file-input" class="member-profile-modify-btn">
			                                 <i class="fa-solid fa-camera-retro"></i>
			                                 <span>프로필 사진 변경</span>
			                             </label>
			
			                             <button class = "member-profile-modify-btn changeBtn">변경하기</button>
	                               </div>	
		                         </div>
	                       </form>
	                       
	                       <div class = "info-container">

		                       <form action ="/movieInsight/mypage/update" name ="info-update" method = "POST">
		                            <!-- 변경 -->
		                            <div class = "member-info-container">
		                                <div class = "modify-content-container" id = "change-pw">
		                                    <div class = "modify-title">
		                                        비밀번호 변경
		                                    </div>
		
		                                    <div class = "modify-content">
		                                        <div class = "modify-content-input">
		                                            <i class="fa-solid fa-lock"></i>
		
		                                            <input type="password" placeholder="User Pw" name = "memberPw" id = "newPw">
		                                        </div>
		                                    </div>
		
		                                    <!-- 유효성 검사에서 사용할 것 -->
		                                     <div class = "validation-content" id = "pwMessage">
		                                    </div>
		                                </div>
		
		                                <div class = "modify-content-container" id = "confirm-pw">
		                                    <div class = "modify-title">
		                                        비밀번호 재입력
		                                    </div>
		
		                                    <div class = "modify-content">
		                                        <div class = "modify-content-input">
		                                            <i class="fa-solid fa-user-lock"></i>
		
		                                            <input type="password" placeholder="Confirm Pw" name = "newCheckPw" id = "newCheckPw">
		                                        </div>
		                                    </div>
		
		                                    <!-- 유효성 검사에서 사용할 것 -->
		                                    <div class = "validation-content" id = "pwCheckMessage">
		                                    </div>
		                                </div>
		
		
		                                <div class = "modify-content-container" id = "ninck-pw">
		                                    <div class = "modify-title">
		                                        닉네임 변경
		                                    </div>
		
		                                    <div class = "modify-content">
		                                        <div class = "modify-content-input">
		                                            <i class="fa-regular fa-circle-user"></i>
		
		                                             <input type="text" placeholder="NickName" name = "memberNickname" id = "newNickName" value= "${loginMember.memberNickname}">
		                                        </div>
		                                    </div>
		
		                                    <!-- 유효성 검사에서 사용할 것 -->
		                                    <div class = "validation-content" id = "nickNameMessage">
		                                    </div>
		                                </div>
		
		
		                                <div class = "modify-content-container" id = "change-pw">
		                                    <div class = "modify-title">
		                                        성별 변경
		                                    </div>
		
		                                    <div class = "modify-content">
		                                        <div class = "modify-content-btn">
		                                            <div class = "modify-gender" id="man" >
		                                                <label>
		                                                    <input type="checkbox"  name='memberGender' value='M'>
		                                                    남자
		                                                </label>
		                                            </div>
		                                            <div class = "modify-gender" id = "woman">
		                                                <label>
		                                                    <input type="checkbox"   name='memberGender' value='F'>
		                                                    여자
		                                                </label>
		                                            </div>
		
		                                        </div>
		                                    </div>
		
		                                    <div class = "validation-content">
		                                    </div>
		                                </div>
		                            </div>						
									                            <!-- 버튼 -->
		                            <div class = "member-info-option-container">
		                                <button class = "member-info-option-btn ">
		                                    수정하기
		                                </button>
		
		                                <button class = "member-info-option-btn red">
		                                    회원탈퇴 
		                                </button>
		                            </div>
	                            
							   </form>
		                   </div>    
	                       
						</div>
                    </div>
                </section>

            </section>
        </main>  

	<!-- footer 추가 -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />

        <!-- Swiper 스크립트 추가 -->
        <!-- <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script> -->
      	<script>
			// 로그인한 회원의 성별
			const loginMemberGender = "${loginMember.memberGender}";
			var memberNo = ${loginMember.memberNo};
			
		</script>

        <script src = "/movieInsight/resources/js/mypage/mypage.js"></script>		
		
	</body>
</html>