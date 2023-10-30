<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<%-- ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
	1) CSS 변경 ( hove 시 디자인 ) 
	2) 마이페이지 눌렀을 때 로그인이 되어있지 않으면 로그인 창으로 넘어가거나 경고창으로 알려주기 
★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ --%>

<%-- ◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎
	1) 어느 페이지에 속해있는지 파악해야 함. (영화, 영화관 메인)  ==> + ) 검색도 영화 , 영화관 페이지마다 다르게 설정해야 할 듯. 
	2) 로그인 파악
	3) 프로필 파악
◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎ --%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Header</title>
		
		<link rel="stylesheet" href ="/movieInsight/resources/css/common/header.css">

        <script src="https://kit.fontawesome.com/ac58eafae7.js" crossorigin="anonymous"></script>
		
	</head>
	<body>
        <nav>
            <section class = "logo" id = "logo">
            <!-- movie , cinema 확인하고 logo 눌렀을 때 이동 시, 제대로 이동하기. -->
            	<c:choose>

            		<c:when test="${pageType eq 'cinema'}"> <%-- 영화, 마이페이지 페이지인 경우 --%>  
            		 	<a href="/movieInsight/cinema">  <%-- 영화 메인페이지 컨트롤러- --%>              
		                    <img src="/movieInsight/resources/images/common/logo.png">
		                </a>            	

            		</c:when>
            		
            		<c:otherwise> <%-- 영화관 페이지인 경우 --%>
		                <a href="/movieInsight/movie">   <%-- 영화관 메인페이지 컨트롤러- --%>               
		                    <img src="/movieInsight/resources/images/common/logo.png">
		                </a>
            		</c:otherwise>
            		
            	</c:choose>

            </section>
            
            <section class = "movie-search-area">
               		<%--1) 페이지에 따라  --%>
                	<%-- 1-1) 영화 메인 페이지의 경우 --%>
                	<c:choose>
	                    <c:when test="${pageType eq 'movie'}">

			              	<form action="/movieInsight/movie/search" name="movieSearch"> <!-- ② action 값 변경-->

			              	

			                  <fieldset>
			                      <input type="search" id="query" name="movieQuery"
			                      autocomplete="on" placeholder="검색">
			                      <button id="search-btn" class="fa-solid fa-magnifying-glass"></button>
			                  </fieldset>
			              	</form>	                    	
	                   		 <div class = "main-sort">
		                   	 	<%-- ③ a 태그 입력하기 진행하기! main페이지로 이동할 수 있게 --%>
			                    <a href ="#">#인기순</a>
			                    <a href ="#">#최신순</a>
			                    <a href ="#">#장르별</a>
			                 </div>
		                </c:when>
		                <%-- 1-2)영화관 메인 페이지의 경우 --%>
		                <c:when test="${pageType eq 'cinema'}">
			              	<form action="/movieInsight/cinema/search" name="cinemaSearch"> <!-- ② action 값 변경-->
			                  <fieldset>
			                      <input type="search" id="query" name="cinemaQuery"
			                      autocomplete="on" placeholder="검색">
			                      <button id="search-btn" class="fa-solid fa-magnifying-glass"></button>
			                  </fieldset>
			              	</form>			                
	                   		<div class = "main-sort">
			                	<%-- ③ a 태그 입력하기 진행하기! main페이지로 이동할 수 있게 --%>
			                	<a href ="#">#극장 평점순</a>
			                    <a href ="#">#직원 친절도순</a>
			                    <a href ="#">#메뉴 만족도순</a>
			                </div>
		                </c:when>
		                <%-- 1-3)마이페이지 혹은 정렬이 필요 없지만, 헤더는 필요한 페이지의 경우 --%>
		                <c:otherwise>
		                	<form action="/movieInsight/movie/search" name="movieSearch"> <!-- ② action 값 변경-->
			                  <fieldset>
			                      <input type="search" id="query" name="movieQuery"
			                      autocomplete="on" placeholder="검색">
			                      <button id="search-btn" class="fa-solid fa-magnifying-glass"></button>
			                  </fieldset>
			              	</form>	                    	
		                </c:otherwise>
                	</c:choose>
            </section>

            <section class = "user-function">
     
     			<%-- 2) 로그인 유무  --%>
                <c:choose>
               		<%-- 2-1) 로그인 X 경우 --%>
                	<c:when test="${empty sessionScope.loginMember}">
		                <a class = "user-login"  href= "/movieInsight/member/loginPage">
		                    <i class="fa-solid fa-right-to-bracket"></i>
		                    <p>LOGIN</p>
		                    
	                        <span></span>
						    <span></span>
						    <span></span>
						    <span></span>
		                </a>
		                <a class = "user-mypage"  href= "/movieInsight/manager/promotion">
		                	<div class = "mypage-wrapper">
		                	 	<i class="fa-solid fa-user"></i>    
		                	</div>
		                </a>
		                                	
                	</c:when>
                	<%-- 2-2) 로그인  O 경우 --%>
                	<c:otherwise>				
		               <a class = "user-logout"  href="/">
		                   <i class="fa-solid fa-right-from-bracket"></i>
		                   <span>LOGOUT</span>
		               </a>
		               
		               <!-- ⑤ 버튼으로 만들어야 하나? 필터로 만들어버리기 -->
		                <a class = "user-mypage" href="/movieInsight/manager/promotion">
		                    <div class = "mypage-wrapper">
		                    	<%-- 3) 프로필 유무 ( 로그인부터 파악해야 하나..? ) --%>
				                <c:choose>
					                <%-- 3-2) 프로필이 없는 경우 --%>
				                	<c:when test="true">
						                 <i class="fa-solid fa-user"></i>          		
				                	</c:when>
				               		<%-- 3-1) 프로필이 있는 경우 --%>			                	
				                	<c:otherwise>				
						               <!-- ⑥이거 나중에 유저 사진으로 변경하기 -->
						               <img src="resources/images/member/profile1.jpg">                  	
				                	</c:otherwise>                	
				                </c:choose>  
		                    </div>
		                </a>	                	
                	</c:otherwise>                	
                </c:choose>
            </section>
        </nav>
        
        
    <script src="/movieInsight/resources/js/common/header.js"></script>
        
	</body>
	
</html>