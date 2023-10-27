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
</head>

<body>
    

    <div class="admin-menu">
        <ul>
            <li class="admin-list"><a href="/movieInsight/manager/member">회원 관리</a></li> 
            <li class="admin-list"><a href="#">영화</a></li>               
            <li class="admin-list"><a href="#">영화관</a></li>
            <li class="admin-list"><a href="#">메뉴</a></li>
            <li class="admin-list"><a href="/moviInsight/manager/promotion">이벤트</a></li>
        </ul>
    </div>



    <div class="member-search">
        <input type="text" placeholder="회원 검색" id = "searchMember">
        <br><br><br>
        <span>총 회원 수 : ${memberCount}명</span> <span>현재 접속자 수 : ###명</span><br>
      
    </div>
        
        
    <div class="member-container">      

       <c:forEach items="${allMemberList}" var="member">
        	<div class="member-list">
        	
            <span class="member-name">${member.memberNickname}</span>
            <span class="member-email">${member.memberEmail}</span>
            <span class="member-date">${member.memberEnrollDate}</span>
            <c:if test = "${member.memberGender eq 'M'}">
            	<span class="member-gender">남자</span>
            </c:if>
            <c:if test = "${member.memberGender eq 'F'}">
            	<span class="member-gender">여자</span>
            </c:if>
          
            <div class="member-buttons">
                <button class="edit-button">조회</button>
                <button class="delete-button">탈퇴</button>
            </div>
        </div>
       		
       </c:forEach>

    </div>
    
  

</body>
</html>