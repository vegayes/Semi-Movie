<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<c:set var="promotion" value="${promotionMap.promotion}"/>
<c:set var="event" value="${promotionMap.event}"/>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Admin-Event</title>
		
		
        <link rel="stylesheet" href = "/movieInsight/resources/css/manager/manager-event.css">
        <!-- 아이콘 -->
        <script src="https://kit.fontawesome.com/ac58eafae7.js" crossorigin="anonymous"></script>
		
	</head>
	<body>
        <nav>
            <div class="admin-menu">
                <ul>

                    <li class="admin-list"><a href="/movieInsight/manager/member">회원 관리</a></li>                  
                    <li class="admin-list"><a href="/movieInsight/mypage/manager/movie">영화</a></li>                
                    <li class="admin-list"><a href="#">영화관</a></li>
                    <li class="admin-list"><a href="/movieInsight/mypage/manager/menu">메뉴</a></li>
                    <li class="admin-list"><a href="/moviInsight/manager/promotion">이벤트</a></li>
                    
                </ul>
               	 <li class="admin-back"><a href = "/movieInsight/movie" >뒤로가기</a></li>
            </div>
        </nav>

        <main>
            <section class = "title-container">
                <div>
                    이벤트 관리 
                </div>
            </section>


            <section class = "event-list-container">
                <div class = "event-container">
                    <div class = "event-list">
                        <div class = "event-container-title" >
                           <p>특별관</p>
                           <a class="fa-regular fa-square-plus" id = "special-add"></a>
                        </div>

                        <div class = "event-info-container">
                            <table class = "event-info-table">

                            	<c:forEach items="${promotion}" var = "promotion" varStatus="status">
	                            	<tr>
	                            		<td class = "event-no">
	                            			${status.count}
	                            		</td>
	                                    <td class = "event-title"> 
	                                    	
	                                        ${promotion.promotionType}
	                                    </td>
	                                    <td class = "event-update">
	                                        <div>
	                                            수정
	                                        </div>
	                                    </td>
	                                    <td class = "event-del">
	                                        <div>
	                                            삭제
	                                        </div>
	                                    </td>
	                                </tr>
                            	</c:forEach>
                            	
                            </table>
                        </div>


                    </div>
                </div>

                <div class = "event-container">
                    <div class = event-list>
                        <div class = "event-container-title" >
                            <p>이벤트</p>
                           
                            <a class="fa-regular fa-square-plus" id = "event-add"></a>
                        </div>

                        <div class = "event-info-container">
                            
                            <table class = "event-info-table">
                            
                            	<c:forEach items="${event}" var = "event" varStatus="status">
	                                <tr>
                                		<td class = "event-no">
	                            			${status.count}
	                            		</td>
	                                
	                                    <td class = "event-title">
	                                        ${event.eventTitle}
	                                    </td>
	                                    <td class = "event-update">
	                                        <div>
	                                            수정
	                                        </div>
	                                    </td>
	                                    <td class = "event-del">
	                                        <div>
	                                            삭제
	                                        </div>
	                                    </td>
	                                </tr>
	                            </c:forEach>
                            </table>

                        </div>
                    </div>
                </div>
            </section>   





            <script src="/movieInsight/resources/js/manager/manager-event.js"></script>
        </main>	
	
	</body>
</html>