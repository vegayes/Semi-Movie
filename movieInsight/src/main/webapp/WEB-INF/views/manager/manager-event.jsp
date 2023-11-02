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
        <link rel="stylesheet" href = "/movieInsight/resources/css/manager/event_update_popup.css">
        <!-- 아이콘 -->
        <script src="https://kit.fontawesome.com/ac58eafae7.js" crossorigin="anonymous"></script>
		
	</head>
	<body>
        <nav>
            <div class="admin-menu">
                <ul>


              
                    <li class="admin-list"><a href="/movieInsight/manager/member">회원 관리</a></li>     
                    <li class="admin-list"><a href="/movieInsight/manager/movie">영화</a></li>               
                    <li class="admin-list"><a href="/movieInsight/manager/cinema">영화관</a></li>
                    <li class="admin-list"><a href="/movieInsight/manager/menu">메뉴</a></li>
                    <li class="admin-list"><a href="/movieInsight/manager/promotion">이벤트</a></li>

                </ul>
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
	                                    <td class = "event-del">
	                                        <div>
	                                            <a href="/movieInsight/managerDetail/deletePromotion/${promotion.promotionNo}" class="delete-button">삭제</a>
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
	                                   
	                                    <td class = "event-del">
	                                        <div>
	                                            <a href="/movieInsight/managerDetail/deleteEvent/${event.eventPRNo}" class="delete-button">삭제</a>
	                                        </div>
	                                    </td>
	                                </tr>
	                            </c:forEach>
                            </table>

                        </div>
                    </div>
                </div>
            </section>   

 <%-- =====================================================================================================================================       
                    								 이벤트/특별관 수정  팝업    --%> 
				
			<div id = "manager-event-update-box" style="display : none;">
					<div id = "manager-event-update-content">
						<div id = "event-modal-close" >&times;</div>
						<form   method="POST" encType="multipart/form-data" id="updateform">   
							<section class ="title-container">
								이벤트 수정
							</section>
				
							<section class = "event-container">
								<div class = "event-info-container">
									<table>
										<tr>
											<th> 홍보 카테고리</th>
											<td>
												<select name="menu" id="menuSelect">
													<option value="none">=== 카테고리 선택 ===</option>
													<option value="promotion-cinema">특별관</option>
													<option value="event">이벤트</option>
												</select>
											</td>
										</tr>
				
										<tr>
											<th> 제목</th>
											<td>
												<input type="text"  autocomplete="off" name="title">
											</td>
										</tr>
				
										<tr>
											<th> 내용</th>
											<td>
												
												<textarea name="content">
														
												</textarea>
												
											</td>
										</tr>
										
										
										<tr class = "cinema-content">
											<th>해당 영화관</th>
											
											<td>
												<div id="cinemaCheckBox">
													<c:forEach items="${cinemaList}" var = "cinema">
														<div class = "checkBox-container">
															<p>${cinema.cinemaName}</p>
															<input type="checkbox"  value="${cinema.cinemaNo}" class = "checkBox" name="cinemaNoList">
														</div>

													</c:forEach>
												</div>
											</td>

										</tr>

										<tr>
											<th> 사진 </th>
											<td>
												<input type="file"  accept="image/*" name="img">
											</td>
										</tr>
				
										<tr>
											<th> URL </th>
											<td>
												<input type="text" value="https://" name="url">
											</td>
										</tr>
									</table>
				
				
								</div>
							</section>
						
							<section class = "event-btn-container">
								<div class ="btn">
									<button type="submit" id="updateButton">추가하기</button>
								</div>
								
								<div class ="btn">
									<button type="submit" id="update-del-button">취소하기</button>
								</div>
								
							</section>                             
						</form>  
					<label  id = "event-modal-back"></label>
				</div>	
			</div>    
				
<%-- ============================================================  이벤트/특별관 수정 (팝업) 끝 ============================================================ --%>
	
	<script>
		// 이벤트 리스너가 설정되는 지점을 확인하세요.
		document.addEventListener("DOMContentLoaded", function () {
			var updateButton = document.getElementById("updateButton");
			var menuSelect = document.getElementById("menuSelect");
			var updateForm = document.getElementById("updateform");
			
			if(updateButton) {
				// 메뉴 선택에 따라 동작을 변경합니다.
				menuSelect.addEventListener("change", function(){
					// 이벤트 리스너 내부에서 변수를 선언해야 합니다.
					var value = this.value;
					console.log(value);
				});

				updateButton.addEventListener("click", function(event) {
					event.preventDefault(); // 폼의 기본 제출 동작을 방지합니다.
					console.log("수정버튼 눌림");
					// 메뉴 선택 값에 접근하여 해당 값에 따라 action 설정
					var selectedValue = menuSelect.value;

					if (selectedValue === "promotion-cinema") {
						console.log("영화관 홍보");
						updateForm.action = "/movieInsight/managerDetail/insertPromotion";
					} else if (selectedValue === "event") {
						console.log("이벤트 홍보");
						updateForm.action = "/movieInsight/managerDetail/insertEvent";
					}

					// 변경된 action을 가진 폼을 제출합니다.
					updateForm.submit();
				});
			}   
		});
	</script>








			<c:if test="${not empty message}">
					
				<script>
					// EL/JSTL 구문이 먼저 해석
					// 문자열의 경우 따옴표가 없는 상태이니 옆에 붙여줘야함.
					alert('${message}') // ${message}
				</script>

			</c:if>

            <script src="/movieInsight/resources/js/manager/manager-event.js"></script>
        </main>	
	
	</body>
</html>