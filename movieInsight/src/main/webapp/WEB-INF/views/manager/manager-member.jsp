<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<c:set var="allMemberList" value="${memberMap.memberList}"/>
<c:set var="memberCount" value="${memberMap.memberCount}"/>

    
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <title>관리자 페이지-회원</title>
	     <link rel="stylesheet" href="/movieInsight/resources/css/manager/manager_member.css">
	     <link rel="stylesheet" href="/movieInsight/resources/css/manager/member_info_popup.css">
	     <!--  
	     <link rel="stylesheet" href="/movieInsight/resources/css/mypage/favorite-list-popup.css">
	     -->
	     
	</head>
	
	<body>
	    
	
	    <div class="admin-menu">
	        <ul>
	            <li class="admin-list"><a href="/movieInsight/manager/member">회원 관리</a></li> 
	            <li class="admin-list"><a href="#">영화</a></li>               
	            <li class="admin-list"><a href="#">영화관</a></li>
	            <li class="admin-list"><a href="#">메뉴</a></li>
	            <li class="admin-list"><a href="/movieInsight/manager/promotion">이벤트</a></li>
	        </ul>
	    </div>
	    
	    
	    
	    
	    <%--------------------------------------------------------------------------------------- --%>
         <div id = "manager-member-info-box" class = "toggle-popup" style = "flex">
             <div id = "manager-member-info-content">
             	<div id = "closeBtn" >&times;</div>
	             <section class = "popup-member-info-container">
	                <p class = "popup-member-info-title">
	                    회원1님의 회원 정보
	                </p>
	
	                <table class = "table-info">
	                    <tr>
	                        <th>아이디</th>
	                        <th>이메일</th>
	                        <th>가입일</th>
	                        <th>탈퇴여부</th>
	                    </tr>
	                    
	                    <tr>
	                        <td>user01</td>
	                        <td>user01@naver.com</td>
	                        <td>2023-10-01</td>
	                        <td>X</td>
	                    </tr>
	                </table>
	                
	            </section>
	
	            <section class = "popup-member-list-container">
	                <div class = "popup-member-write-list-container">
	                    <table class = "popup-member-write-list-table">
	                        <thead>
	                            <tr>
	                                <th class = "popup-member-comment-no">
	                                    댓글 번호
	                                </th>
	    
	                                <th class = "popup-member-comment-content">
	                                    댓글 내용
	                                </th>
	    
	                                <th class = "popup-member-comment-date">
	                                    댓글 작성일
	                                </th>
	    
	                                <th class = "popup-member-comment-del-YN">
	                                    댓글 삭제 여부
	                                </th>
	
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <tr>
	                                <td class = "popup-member-comment-no">
	                                    1
	                                </td>
	    
	                                <td class = "popup-member-comment-content">
	                                    댓글 내용1
	                                </td>
	    
	                                <td class = "popup-member-comment-date">
	                                    댓글 작성일1
	                                </td>
	    
	                                <td class = "popup-member-comment-del-YN">
	                                    X
	                                </td>
	    
	    
	    
	                            </tr>
	
	                            <tr>
	                                <td class = "popup-member-comment-no">
	                                    2
	                                </td>
	    
	                                <td class = "popup-member-comment-content">
	                                    댓글 내용1
	                                </td>
	    
	                                <td class = "popup-member-comment-date">
	                                    댓글 작성일1
	                                </td>
	    
	                                <td class = "popup-member-comment-del-YN">
	                                    X
	                                </td>
	    
	    
	    
	                            </tr>
	
	
	                            <tr>
	                                <td class = "popup-member-comment-no">
	                                    3
	                                </td>
	    
	                                <td class = "popup-member-comment-content">
	                                    댓글 내용1
	                                </td>
	    
	                                <td class = "popup-member-comment-date">
	                                    댓글 작성일1
	                                </td>
	    
	                                <td class = "popup-member-comment-del-YN">
	                                    X
	                                </td>
	    
	    
	    
	                            </tr>
	
	
	                            <tr>
	                                <td class = "popup-member-comment-no">
	                                    4
	                                </td>
	    
	                                <td class = "popup-member-comment-content">
	                                    댓글 내용1
	                                </td>
	    
	                                <td class = "popup-member-comment-date">
	                                    댓글 작성일1
	                                </td>
	    
	                                <td class = "popup-member-comment-del-YN">
	                                    X
	                                </td>
	    
	    
	    
	                            </tr>
	
	                            <tr>
	                                <td class = "popup-member-comment-no">
	                                    5
	                                </td>
	    
	                                <td class = "popup-member-comment-content">
	                                    댓글 내용1
	                                </td>
	    
	                                <td class = "popup-member-comment-date">
	                                    댓글 작성일1
	                                </td>
	    
	                                <td class = "popup-member-comment-del-YN">
	                                    X
	                                </td>
	    
	    
	    
	                            </tr>
	
	
	                            <tr>
	                                <td class = "popup-member-comment-no">
	                                    6
	                                </td>
	    
	                                <td class = "popup-member-comment-content">
	                                    댓글 내용1
	                                </td>
	    
	                                <td class = "popup-member-comment-date">
	                                    댓글 작성일1
	                                </td>
	    
	                                <td class = "popup-member-comment-del-YN">
	                                    X
	                                </td>
	    
	    
	    
	                            </tr>
	
	                            <tr>
	                                <td class = "popup-member-comment-no">
	                                    7
	                                </td>
	    
	                                <td class = "popup-member-comment-content">
	                                    댓글 내용1
	                                </td>
	    
	                                <td class = "popup-member-comment-date">
	                                    댓글 작성일1
	                                </td>
	    
	                                <td class = "popup-member-comment-del-YN">
	                                    X
	                                </td>
	    
	    
	    
	                            </tr>
	
	
	                            <tr>
	                                <td class = "popup-member-comment-no">
	                                    8
	                                </td>
	    
	                                <td class = "popup-member-comment-content">
	                                    댓글 내용1
	                                </td>
	    
	                                <td class = "popup-member-comment-date">
	                                    댓글 작성일1
	                                </td>
	    
	                                <td class = "popup-member-comment-del-YN">
	                                    X
	                                </td>
	    
	    
	    
	                            </tr>
	                            
	    
	                           
	                        </tbody>
	                     
	                    </table>
	
	                </div>
	                
	
	            </section> 
             </div>
             <label  id = "member-info-back"></label>
         </div>                        
                     
	    
	    
	    
	    <%--------------------------------------------------------------------------------------- --%>
	
	
	
	    <div class="member-search">
	        <input type="text" placeholder="회원 검색" id = "searchMember">
	        <br><br><br>
	        <span>총 회원 수 : ${memberCount}명</span> <span>현재 접속자 수 : ###명</span><br>
	      
	    </div>
	        
	        
	    <div class="member-container">      
	
	       <c:forEach items="${allMemberList}" var="member" varStatus="status">
	       		<div class="member-list">
	        		
	        		<span class ="member-index">${status.count}</span>
		            <span class="member-name">${member.memberNickname}</span>
		            <span class="member-email">${member.memberEmail}</span>
		            <span class="member-date">${member.memberEnrollDate}</span>
		            <c:if test = "${member.memberGender eq 'M'}">
		            	<span class="member-gender">남자</span>
		            </c:if>
		            <c:if test = "${member.memberGender eq 'F'}">
		            	<span class="member-gender">여자</span>
		            </c:if>
		            
		            <c:if test = "${member.memberDelYN eq 'N'}">
			            <span class = "member-del-YN">회원유지</span>								            
		            </c:if>
		            <c:if test = "${member.memberDelYN eq 'Y'}">
			            <span class = "member-del-YN">탈퇴</span>								            
		            </c:if>
		            
		            
		          
		            <div class="member-buttons">
		                <div class="edit-button" member-no="${member.memberNo}" onclick ="getMemberInfo()")>
		                		조회
		                </div>
		                <div class="delete-button">
		                		탈퇴
		                </div>
		            </div>
	        	</div>
	       		
	       </c:forEach>
	
	    </div>
	    
	    


	    
		<script src="/movieInsight/resources/js/manager/manager-member.js"></script> 
	</body>



</html>