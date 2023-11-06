<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MOVIEINSIGHT, 영화관 상세페이지</title>


 <script src="https://kit.fontawesome.com/69a462bb6c.js" crossorigin="anonymous"></script>
 <link rel="stylesheet" href="/movieInsight/resources/css/cinema/cinema-detail-page.css">
</head>
<body>
	
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
		
	<main>

       
        <form method="POST" encType="multipart/form-data" id="cinema-form">
            <section class="cinema">

                <div>       
                    <c:choose>
                        <c:when test="${loginMember.memberNo == 12}">
                            <img src="/movieInsight/resources/images/cinema/${cinemaInfo.cinemaImg}" id="cinemaImg">
                            <input type="file" name="cinemaImage"  id="fileChange" accept="image/*"> 
                        </c:when>
                        <c:when test="${empty cinemaInfo}">
                            <img src="" id="cinemaImg">
                            <input type="file" name="cinemaImage"  id="fileInput" accept="image/*">
                        </c:when>
                        <c:otherwise>
                            <img src="/movieInsight/resources/images/cinema/${cinemaInfo.cinemaImg}" id="cinemaImg">
                        </c:otherwise>
                    </c:choose>

                </div>
                
                <div>
                    <div class="cinema_title">
                         <div class="star">
                                  
	                    	<c:if test="${empty favorite}">             
		                        <i class="fa-regular fa-star" id = "favoriteStar"></i>
	                    	</c:if>
							                       
	                    	<%-- 누르적이 있는 경우 --%>
	                    	<c:if test="${not empty favorite}" >             
		                        <i class="fa-solid fa-star" id="favoriteStar" style = "color : #ffee32"></i>  
	                    	</c:if>
                                    
                        </div>                
                        <div>
                            <c:choose>
                                <c:when test="${empty cinemaInfo}">
                                    <input type="text" name="cinemaName" style="font-size: 30px;" placeholder="영화관 이름">
                                </c:when>
                                <c:when test="${loginMember.memberNo == 12}">
                                    <input type="text" name="cinemaName" style="font-size: 30px;" value="${cinemaInfo.cinemaName}" >
                                </c:when>
                                <c:otherwise>
                                    ${cinemaInfo.cinemaName}  
                                </c:otherwise>
                            </c:choose>
                        </div>
                        
                        <div class = "grade-container"> <%-- 평점 --%>
                            <p id="grade">평점 : ${cinemaInfo.sumCinemaGrade}</p>
                            <div class = "grade-color">
                            </div>
                        </div>
                    </div>
                    <div class="cinema_description">
                        <div>
                            <div>
                                <c:choose>

                                    <c:when test="${empty cinemaInfo}">
                                        <input type="text" name="cinemaAddress" style="font-size: 22px; width: 500px" placeholder="영화관 주소">
                                    </c:when>
                                    <c:when test="${loginMember.memberNo == 12}">
                                        <input type="text" name="cinemaAddress" style="font-size: 22px; width: 500px" value="${cinemaInfo.cinemaAddress}">
                                    </c:when>
                                    <c:otherwise>
                                        주소 : ${cinemaInfo.cinemaAddress}  
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div>    
                                <c:choose>

                                    <c:when test="${empty cinemaInfo}">
                                        <input type="text" name="cinemaContact" style="font-size: 22px; width: 500px" placeholder="영화관 연락처">
                                    </c:when>
                                    <c:when test="${loginMember.memberNo == 12}">
                                        <input type="text" name="cinemaContact" style="font-size: 22px; width: 500px" value="${cinemaInfo.cinemaContact}">
                                    </c:when>
                                    <c:otherwise>
                                        연락처 : ${cinemaInfo.cinemaContact} 
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div>
                                <c:choose>

                                    <c:when test="${empty cinemaInfo}">
                                        <input type="text" name="cinemaMaxInclude" style="font-size: 22px; width: 500px" placeholder="최대 수용인원">
                                    </c:when>
                                    <c:when test="${loginMember.memberNo == 12}">
                                        <input type="text" name="cinemaMaxInclude" style="font-size: 22px; width: 500px" value="${cinemaInfo.cinemaMaxInclude}">
                                    </c:when>
                                    <c:otherwise>
                                        수용인원 : ${cinemaInfo.cinemaMaxInclude} 명 
                                    </c:otherwise>
                                </c:choose>   
                            </div>
                            <div>   
                                <c:choose>

                                    <c:when test="${empty cinemaInfo}">
                                        <input type="text" name="cinemaSpecialHall" style="font-size: 22px; width: 500px" placeholder="특별관 정보(/구분)">
                                    </c:when>
                                    <c:when test="${loginMember.memberNo == 12}">
                                        <input type="text" name="cinemaSpecialHall" style="font-size: 22px ; width: 500px" value="${cinemaInfo.cinemaSpecialHall}">
                                    </c:when>
                                    <c:otherwise>
                                        특별관 : ${cinemaInfo.cinemaSpecialHall} 
                                    </c:otherwise>
                                </c:choose>   
                            </div>
                            <div>
                                <c:choose>

                                    <c:when test="${empty cinemaInfo}">
                                        <input type="text" name="cinemaLink" style="font-size: 17px; width: 500px" placeholder="바로가기 링크">
                                    </c:when>
                                    <c:when test="${loginMember.memberNo == 12}">
                                        <input type="text" name="cinemaLink" style="font-size: 17px; width: 500px;" value="${cinemaInfo.cinemaLink}">
                                    </c:when>
                                    <c:otherwise>
                                        <a href=${cinemaInfo.cinemaLink} target="_blank">바로가기</a> 
                                    </c:otherwise>
                                </c:choose>   
                            </div>

                            <c:if test="${loginMember.memberNo == 12 && not empty cinemaInfo}">
                                <div>
                                    <button type="submit" id="updateButton" name="update" style="color: black;">수정하기</button>
                                </div>
                            </c:if>
                            <c:if test="${empty cinemaInfo}">
                                <div>
                                    <button type="submit"  id="newButton"  name="new" style="color: black;">등록하기</button>
                                </div>
                            </c:if>
                            <script>

                                if(document.getElementById("updateButton")) {
                                    document.getElementById("updateButton").addEventListener("click", function() {
                                        // "수정하기" 버튼 클릭 시 "등록하기" 폼의 action 속성 설정
                                        console.log("수정버튼 눌림");
                                        document.getElementById("cinema-form").action = "/movieInsight/managerDetail/updateCinema/${cinemaInfo.cinemaNo}";
                                        document.getElementById("cinema-form").submit();
                                    });
                                }   
                                if( document.getElementById("newButton")) {
                                    document.getElementById("newButton").addEventListener("click", function() {
                                        // "등록하기" 버튼 클릭 시 "수정하기" 폼의 action 속성 설정
                                        console.log("등록버튼 눌림");
                                        document.getElementById("cinema-form").action = "/movieInsight/managerDetail/updateCinema/0";
                                        document.getElementById("cinema-form").submit();
                                    });
                                }
                            
                            </script>
                            

                        </div>
                    </div>
                </div>
            </section>
        </form>

    <section class="screening_movie">
            <div>상영중인 영화</div>
            

            <c:choose>
                	<%-- 1) 검색 결과가 비어있는 경우  --%>
                <c:when test="${empty movieList}">

                    <div class = "screening_movie_detail"  id = "none-list">
                        검색결과가 존재하지 않습니다.
                    </div>

                </c:when>
                    
                        <%-- 2) 검색 결과가 존재하는 경우 --%>
                <c:otherwise> 
                    <div class="screening_movie_detail">
                        <c:forEach items = "${movieList}"  var="movie" >
                            <a href="/movieInsight/movie/${movie.movieNo}">
                                <div>
                                    
                                    <div>			
                                        <img src="/movieInsight/resources/images/movie/${movie.movieImg}">
                                    </div>
                                    <div>
                                        <div>${movie.movieTitle}</div>
                                        <div>${movie.movieAge} / ${movie.movieGenre} / ${movie.movieRunningTime}분</div>
                                        <div>${movie.movieReleaseDate} 개봉</div>
                                    </div>
                                    <div>
                                        <div>출연진</div>
                                        <div>${movie.actorNames}</div>
                                    </div>

                                </div>
                            </a>
                        </c:forEach>
                    </div>
                       
                </c:otherwise>
                    
            </c:choose>
            
    </section>
    <%-- ======================= 영화관 설명 끝 ================================---%>
        <section class="facility" id="cinemaScroll">
            <section class="block">
                <section class="block_name">                    
                      시설만족도
                </section>
               <section class="block_body">
               	 <div class="combody">
                	
                    <table class = "comment-list-table"  id = "comment-list-table-facility">
	                	<c:forEach items = "${commentCinemaList}" var="facility">
	                		<c:if test ="${facility.cinemaCommentType eq '시설'}">
	                			<tr class = "comment-grade-tr">
		                			<td>평점 <span data-cinemaGrade="${facility.cinemaGrade}" class = "commentCinemaGrade">${facility.cinemaGrade}</span></td>
		                		</tr>
		                		<tr class = "comment-content-tr" >
		                			 <td class = "comment-img">
	                                      <div class = "comment-writer-img-wrapper">
	                                      	<c:if test = "${empty facility.writerProfile}">
	                                     		<img src="/movieInsight/resources/images/member/기본이미지.png">
	                                      	</c:if>
	                                      	<c:if test = "${not empty facility.writerProfile}">
		                                          <img src="/movieInsight/resources/images/member/${facility.writerProfile}">                                      	
	                                      	</c:if>
	                                      </div>  
	                                      
	                                 </td>
	                                 <td class = "comment-list-id">
	                                 	${facility.commentCinemaWriter} : 
	                                 </td>
	                                  <td class = "comment-list-content comment-content" data-commentNo="${facility.cinemaCommentNo}">${facility.cinemaCommentContent}</td>
	                                  <td class = "comment-list-date">${facility.cinemaCommentDate}</td>
	                                  <td class = "comment-list-edit">
	                                  	<c:if test = "${facility.commentCinemaWriter eq sessionScope.loginMember.memberId}">
	    	                            	<button class="editBtn" onclick="updateComment(${facility.cinemaCommentNo})" >수정</button>
			                            	<button class="deletBtn" onclick="deleteComment(${facility.cinemaCommentNo})">삭제</button>                              	
	                                  	</c:if>
	                                  </td>
		                		</tr>
	                		</c:if>

	                		
	                	</c:forEach> 
                	</table>
            	</div>
            </section>
          </section>
            <fieldset class="commentForm">
                <form id="facilityForm">
                    <div>
                        <input placeholder= "시설 평가내용 입력...." id = "commentContent">
                    </div>
                    <div>
                        평점 : 
                    </div>
                    <div class="CF2"> 
                        <!-- 따봉 -->
                        <i class="far fa-thumbs-up facilityGrade"></i>
                        <i class="far fa-thumbs-up facilityGrade"></i>
                        <i class="far fa-thumbs-up facilityGrade"></i>
                        <i class="far fa-thumbs-up facilityGrade"></i>
                        <i class="far fa-thumbs-up facilityGrade"></i>
                    </div>
                </form>
                    <div id="commentSubmit">
                        <img src="/movieInsight/resources/images/movie/movieT/list.png">
                    </div>
            </fieldset>

        </section>  
    
         
        <section class="facility">
            <section class="block">
                <section class="block_name">                    
                      직원 친절도
                </section>
               <section class="block_body">
                <div class="combody">
                    <table class = "comment-list-table"  id = "comment-list-table-good">
	                	<c:forEach items = "${commentCinemaList}" var="comment">
	                		<c:if test ="${comment.cinemaCommentType eq '친절도'}">
	                			<tr class = "comment-grade-tr">
	                				<td>평점 <span data-cinemaGrade="${comment.cinemaGrade}" class = "commentCinemaGrade">${comment.cinemaGrade}</span></td>
		                		</tr>
		                		<tr class = "comment-content-tr" >
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
	                                 	${comment.commentCinemaWriter} : 
	                                 </td>
	                                  <td class = "comment-list-content comment-content" data-commentNo="${comment.cinemaCommentNo}">${comment.cinemaCommentContent}</td>
	                                  <td class = "comment-list-date">${comment.cinemaCommentDate}</td>
	                                  <td class = "comment-list-edit">
	                                  	<c:if test = "${comment.commentCinemaWriter eq sessionScope.loginMember.memberId}">
	    	                            	<button class="editBtn" onclick="updateComment(${comment.cinemaCommentNo})">수정</button>
			                            	<button class="deletBtn" onclick="deleteComment(${comment.cinemaCommentNo})">삭제</button>                              	
	                                  	</c:if>
	                                  </td>
		                		</tr>
	                		</c:if>
	                		
	                	</c:forEach> 
                	</table>
                <!-- 
                    <div class="comb">
                        <img id="pop1" src="resources/images/cinema/popcon.png"><img id="pop2" src="resources/images/cinema/popcon.png">
                    </div>
                    <div class="comb2">
                        <div class="cb1"><h2 id="idd">id :</h2></div><div class="cb1-1"><h3 id="dd1">댓글내용 .......................................................................................................</h3></div><div class="cb1-2"><button class="editBtn">수정</button><button class="deletBtn">삭제</button></div>
                    </div>
                    <div class="comb">
                        <img id="pop1" src="resources/images/cinema/popcon.png"><img id="pop2" src="resources/images/cinema/popcon.png">
                    </div>
                    <div class="comb4">
                        <div class="cb2"><h2 id="idd">id :</h2></div><div class="cb2-1"><h3 id="dd1">댓글내용 .......................................................................................................</h3></div><div class="cb2-2"><button class="editBtn">수정</button><button class="deletBtn">삭제</button></div>
                    </div>
                    <div class="comb">
                        <img id="pop1" src="resources/images/cinema/popcon.png"><img id="pop2" src="resources/images/cinema/popcon.png">
                    </div>
                    <div class="comb6">
                        <div class="cb3"><h2 id="idd">id :</h2></div><div class="cb3-1"><h3 id="dd1">댓글내용 .......................................................................................................</h3></div><div class="cb3-2"><button class="editBtn">수정</button><button class="deletBtn">삭제</button></div>
                    </div>
                    <div class="comb">
                        <img id="pop1" src="resources/images/cinema/popcon.png"><img id="pop2" src="resources/images/cinema/popcon.png">
                    </div>
                    <div class="comb6">
                        <div class="cb3"><h2 id="idd">id :</h2></div><div class="cb3-1"><h3 id="dd1">댓글내용 .......................................................................................................</h3></div><div class="cb3-2"><button class="editBtn">수정</button><button class="deletBtn">삭제</button></div>
                    </div>
                     -->
                 </div>
            </section>
          </section>
            <fieldset class="commentForm">
                <form id="facilityForm">
                    <div>
                        <input type="text" placeholder= "직원 친절도 평가 입력...." id = "commentContent">
                    </div>
                    <div>
                        평점 : 
                    </div>
                    <div class="CF2"> 
                        <!-- 따봉 -->
                        <i class="far fa-thumbs-up commentGrade"></i>
                        <i class="far fa-thumbs-up commentGrade"></i>
                        <i class="far fa-thumbs-up commentGrade"></i>
                        <i class="far fa-thumbs-up commentGrade"></i>
                        <i class="far fa-thumbs-up commentGrade"></i>
                    </div>
                </form>
                <div id="commentSubmit">
                    <img src="/movieInsight/resources/images/movie/movieT/list.png">
                </div>
            </fieldset>
        </section>  

        
        <section class="recommended_menu">
            <div>메뉴 추천</div>
            <div>
                <div class="popcorn">
                    <div>팝콘 1등</div>
                    <c:if test = "${empty bestMenu.bestPopCorn}">
                    	<div>
	                    	<h1>
	                    		아직 등록된 팝콘 평점이 존재하지 않습니다.
	                    	</h1> 
                    	</div>
                    	<div>
                 	         <h1>
                    			팝콘에 대한 평점을 남겨주세요!!
                    		</h1>
                    	</div>

                    </c:if>
                    
                   <c:if test = "${not empty bestMenu.bestPopCorn}">
	                    <div>
		                    <div class = "menu-wrapper" >
	    	                    <img src="/movieInsight/resources/images/menu/${bestMenu.bestPopCorn.menuCategory}/${bestMenu.bestPopCorn.menuImg}" id="img_popcorn">
	    	                </div>
	       	             </div>
	                    <div>
	                        이름 : ${bestMenu.bestPopCorn.menuName}
	                    </div>
	                    <div>
	                        가격 : ${bestMenu.bestPopCorn.menuPrice}원
	                    </div>
	                    <div class="score_popcorn">
	                    	${bestMenu.bestPopCorn.menuGrade}
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                    </div>
                    </c:if>

                </div>


                <div class="beverage">
                    <div>음료 1등</div>
                    
                    <c:if test = "${empty bestMenu.bestDrink}">
                    	<div>
	                    	<h1>
	                    		아직 등록된 음료 평점이 존재하지 않습니다.
	                    	</h1> 
                    	</div>
                    	<div>
                 	         <h1>
                    			음료에 대한 평점을 남겨주세요!!
                    		</h1>
                    	</div>

                    </c:if>
                    <c:if test = "${not empty bestMenu.bestDrink}">
	                    <div  >
	                    	<div class = "menu-wrapper" >
		                        <img src="/movieInsight/resources/images/menu/${bestMenu.bestDrink.menuCategory}/${bestMenu.bestDrink.menuImg}" id="img_beverage">
	                    	</div>
	                    </div>
	                    <div>
	                        이름 : ${bestMenu.bestDrink.menuName}
	                    </div>
	                    <div>
	                        가격 : ${bestMenu.bestDrink.menuPrice}원
	                    </div>
	                    <div class="score_beverage">
	                    	${bestMenu.bestDrink.menuGrade}
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                    </div>
	                </c:if>
                </div>


                <div>
                    <div>사이드 1등</div>
                    <c:if test = "${empty bestMenu.bestSnack}">
                    	<div>
	                    	<h1>
	                    		아직 등록된 스낵 평점이 존재하지 않습니다.
	                    	</h1> 
                    	</div>
                    	<div>
                 	         <h1>
                    			스낵에 대한 평점을 남겨주세요!!
                    		</h1>
                    	</div>

                    </c:if>
                   <c:if test = "${not empty bestMenu.bestSnack}">
	                    <div>
	                    	<div class = "menu-wrapper" >
		                        <img src="/movieInsight/resources/images/menu/${bestMenu.bestSnack.menuCategory}/${bestMenu.bestSnack.menuImg}" id="img_side">
		                    </div>
	                    </div>
	                    <div>
	                        이름 : ${bestMenu.bestSnack.menuName}
	                    </div>
	                    <div>
	                        가격 : ${bestMenu.bestSnack.menuPrice}원
	                    </div>
	                    <div class="score_side">
	                    	${bestMenu.bestSnack.menuGrade}
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                    </div>
	                </c:if>
                </div>
            </div>



            <div class="comment" >
                <div class = "menuFromContainer">
                    <form action="/movieInsight/cinemaDetail/menu/insert" method="POST" encType="multipart/form-data" id="menuForm">
                        <label for="menuSelect">해당 극장 메뉴</label>
                        <select id="menuSelect" name="menu">
                            <option value="" disabled selected>목록</option>
                            <optgroup label="팝콘">
 	                           <c:forEach items = "${menuList}" var="menuName">
	                            	<c:if test ="${menuName.menuCategory eq '팝콘' }">
		                            	 	<option name = "menuSelect" value = "${menuName.menuName}">${menuName.menuName}</option>
	                            	</c:if>
                            	</c:forEach> 
                            </optgroup>
                            <optgroup label="음료">
 	                           <c:forEach items = "${menuList}" var="menuName">
	                            	<c:if test ="${menuName.menuCategory eq '음료' }">
		                            	 	<option name = "menuSelect" value = "${menuName.menuName}">${menuName.menuName}</option>
	                            	</c:if>
                            	</c:forEach> 
                            </optgroup>
                            <optgroup label="사이드">
 	                           <c:forEach items = "${menuList}" var="menuName">
	                            	<c:if test ="${menuName.menuCategory eq '스낵' }">
		                            	 	<option name = "menuSelect" value = "${menuName.menuName}">${menuName.menuName}</option>
	                            	</c:if>
                            	</c:forEach>
                            </optgroup>
                        </select>

                            <div>
                                평점 :  
                            </div>
                            <div class="CF2"> 
                                <!-- 따봉 -->
                                <i class="far fa-thumbs-up menuGrade"></i>
                                <i class="far fa-thumbs-up menuGrade"></i>
                                <i class="far fa-thumbs-up menuGrade"></i>
                                <i class="far fa-thumbs-up menuGrade"></i>
                                <i class="far fa-thumbs-up menuGrade"></i>
                            </div>
                    </form>
	                <div id="menuCommentSubmit">
	                    <img src="/movieInsight/resources/images/movie/movieT/list.png">
	                </div>
                 </div>
            </div>
            
            

        </section>
    </main>


    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>

    <script>
        const cinemaName = "${cinemaInfo.cinemaName}"
        const memberNo = "${sessionScope.loginMember.memberNo}";
        const memberId = "${sessionScope.loginMember.memberId}";

        var cinemaRating = "${cinemaInfo.sumCinemaGrade}"
    </script>

    <script src="/movieInsight/resources/js/cinema/cinema-detail-page.js"></script>
    
</html>