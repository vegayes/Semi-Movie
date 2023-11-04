<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<%-- ★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★

★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ --%>

<%-- ◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎

◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎◎ --%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Footer</title>
		
		<link rel="stylesheet" href ="/movieInsight/resources/css/common/footer.css">

        <script src="https://kit.fontawesome.com/ac58eafae7.js" crossorigin="anonymous"></script>
		
	</head>

		<footer>
		    <div class="footer-image">
		        <img src="/movieInsight/resources/images/common/footer.png">
		    </div>
		    <div class="footer-content">
		        <p>Copyright &copy; 2023 MovieInsight Institute E-Class</p>
		        <p><a href="movieinsightsemiproject@gmail.com" style = "color : #fdffb6">movieinsightsemiproject@gmail.com</a></p>
				<a href="#">프로젝트 소개</a>
		        <span>|</span>
		        <a href="/movieInsight/policy#tos">이용약관</a>
		        <span>|</span>
		        <a href="/movieInsight/policy#policy">개인정보처리방침</a>
		    </div>
		</footer>
		
		<%-- request에 message가 존재할 경우 --%>
		<c:if test="${not empty message}">
			
			<script>
				// EL/JSTL 구문이 먼저 해석
				// 문자열의 경우 따옴표가 없는 상태이니 옆에 붙여줘야함.
				alert('${message}') // ${message}
			</script>
			
			<%--
				session에 message를 추가하면
				브라우저 종료 또는 만료 전까지 계속 메시지가 출력된다
				
				-> 1회 출력 후 session에서 message 삭제 
		
			 
			 <c:remove var="message" scope="session"/>
			 
		 	 --%>
			 
		</c:if>
</html>