<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Search-Cinema</title>
		
		<link rel="stylesheet" href="/movieInsight/resources/css/cinema/search-cinema.css">
		
	</head>
	<body>
	
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		
        <main>
            <section class = "search-list">
                <div class = "search-title-container">
                    검색결과 : ${cinemaQuery}
                </div>
                
                <c:choose>
                	<%-- 1) 검색결과가 비어있는 경우 --%>
                	<c:when test="${empty cinemaList}">
    					<div class = "cinema-search-list-none-container" > 
    						검색결과가 존재하지 않습니다.
    					</div>            		
                	</c:when>
                
					<c:otherwise>
						<c:forEach items = "${cinemaList}" var="cinema" >
							<a href="/movieInsight/cinemaDetail/${cinema.cinemaName}">
				                <div class = "cinema-search-list-container" onclick="movieInfo('${cinema.cinemaNo}')"> <%-- value 값으로 전달가능? --%>
				                    <div class = "cinema-search-img-wapper">
				
				                        <img src = "/movieInsight/resources/images/cinema/${cinema.cinemaImg}">
								
				                    </div>
				                    
				                    <div class = "cinema-search-content-container">
				                        <div class = "cinema-search-content">
				                            <h1>${cinema.cinemaName} 정보</h1>
				                            <div class = "content-box">
		                                		<table>
				                            		<tr>
				                            			<td>
				                            				주소 : ${cinema.cinemaAddress}    
				                            			</td>
				                            			
				                            			<c:if test = "${ not empty cinema.cinemaSpecialHall }">
					                            			<td>
					                            				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 특별관 : ${cinema.cinemaSpecialHall}
					                            			</td>	  				                            			
				                            			</c:if>
				                            		</tr>
				                            		
				                            		<tr>
				                            			<td>
				                            				수용인원 : ${cinema.cinemaMaxInclude}석   
				                            			</td>
				                            			<td>
				                            				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 연락처 : ${cinema.cinemaContact}
				                            			</td>	
				                            		
				                            		</tr>
				                            	</table>
				                                
				                            </div>
				                        </div>
				                        <div class = "cinema-search-content">
				                            <h1>영화관 평점</h1>
				                            <div class = "content-box">
				                                <p>
				                                    평점 : ${cinema.sumCinemaGrade}
				                                </p>
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
	
	</body>
</html>