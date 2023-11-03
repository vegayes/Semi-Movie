<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Admin-Menu</title>
		
		<link rel="stylesheet" href = "/movieInsight/resources/css/manager/manager-menu.css">
		<!--  
		<link rel="stylesheet" href = "/movieInsight/resources/css/manager/menu_update_popup.css">
        -->
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
                    메뉴 관리
                    <a class="fa-regular fa-square-plus" id = "event-add"></a> 
                </div>
            </section>

            <section class = "menu-list-container">
                <div class = "menu-container">
                    <div class = "menu-list">
                        <div class = "menu-container-title" >
                            팝콘
                        </div>
                        <div class = "menu-container-search">
                            
                               
                            <input type="text" id = "popcorn-search-input" class="search-input"
                            autocomplete="off" placeholder="메뉴 검색">
                            <button id="popcorn-search-btn" class="fa-solid fa-magnifying-glass"></button>
                               
                            
                        </div>

                        <div class = "menu-info-container">

                            <table class = "popcorn-info-table">

                                <c:forEach items = "${popcorn}" var="popcorn" >

                                    <tr class="popcorn-info-list">
                                        <td class = "menu-title">
                                           ${popcorn.menuName}
                                        </td>

                                        <td class = "menu-price">
                                            ${popcorn.menuPrice}
                                        </td>

                                        <td class = "menu-del">
                                            <div>
                                                <a href="/movieInsight/managerDetail/deleteMenu/${popcorn.menuNo}" class="delete-button">삭제</a>
                                            </div>
                                        </td>
                                    </tr>

                                </c:forEach>

                            </table>

                        </div>


                    </div>
                </div>

                <div class = "menu-container">
                    <div class = menu-list>
                        <div class = "menu-container-title" >
                            음료
                        </div>

                        <div class = "menu-container-search">
                           
                                <input type="text" id = "drink-search-input" class="search-input"
                                autocomplete="off" placeholder="메뉴 검색">
                                <button id="drink-search-btn" class="fa-solid fa-magnifying-glass"></button>
                               
                        </div>

                        <div class = "menu-info-container">
                            
                            <table class = "dirnk-info-table">

                                <c:forEach items = "${drink}" var="drink" >

                                    <tr class="drink-info-list">

                                        <td class = "menu-title">
                                            ${drink.menuName}
                                        </td>

                                        <td class = "menu-price">
                                            ${drink.menuPrice}
                                        </td>

                                        <td class = "menu-del">
                                            <div>
                                                <a href="/movieInsight/managerDetail/deleteMenu/${drink.menuNo}" class="delete-button">삭제</a>
                                            </div>
                                        </td>
                                    </tr>

                                </c:forEach>

                             


                            </table>

                        </div>
                    </div>
                </div>


                <div class = "menu-container">
                    <div class = menu-list>
                        <div class = "menu-container-title" >
                            스낵
                        </div>

                        <div class = "menu-container-search">
                            
                                <input type="type" id = "snack-search-input" class="search-input"
                                autocomplete="off" placeholder="메뉴 검색">
                                <button id="snack-search-btn" class="fa-solid fa-magnifying-glass"></button>
                              
                        </div>

                        <div class = "menu-info-container">
                            <table class = "snack-info-table">



                                <c:forEach items = "${snack}" var="snack" >
                                    <tr class="snack-info-list">
                                        <td class = "menu-title">
                                            ${snack.menuName}
                                        </td>

                                        <td class = "menu-price">
                                            ${snack.menuPrice}
                                        </td>

                                        <td class = "menu-del">
                                            <div>
                                                <a href="/movieInsight/managerDetail/deleteMenu/${snack.menuNo}" class="delete-button">삭제</a>
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
                    								 메뉴 수정  팝업    --%> 

                     <div id = "manager-menu-update-box" style="display : none;">
                         <div id = "manager-menu-update-content">
                             <div id = "menu-modal-close" >&times;</div>
                                <form  action="/movieInsight/managerDetail/insertMenu" method="POST" encType="multipart/form-data" id="updateform">   
                                    <section class = "update-container">
                                        <div class = "update-info-container">
                                            <table>
                                                <tr>
                                                    <th> 메뉴 카테고리</th>
                                                    <td>
                                                        
                                                        <select name="menuCategory" >
                                                            <option value="none">=== 카테고리 선택 ===</option>
                                                            <option value="팝콘">팝콘</option>
                                                            <option value="음료">음료</option>
                                                            <option value="스낵">스낵</option>
                                                        </select>
                                                        
                                                    </td>
                                                </tr>
                        
                                                <tr>
                                                    <th> 메뉴명</th>
                                                    <td>
                                                        <input type="text"  autocomplete="off" name="menuName">
                                                    </td>
                                                </tr>
                        
                                                <tr>
                                                    <th> 가격</th>
                                                    <td>
                                                        <input type="number" name="menuPrice">
                                                    </td>
                                                </tr>
                        
                                                <tr>
                                                    <th> 메뉴 이미지 </th>
                                                    <td>
                                                        <input type="file" accept="image/*" name="img">
                                                    </td>
                                                </tr>

                                               
                                                <tr class = "cinema-content">
                                                    <th>판매 영화관</th>
                                                    
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
                        
                                            </table>
                        
                        
                                        </div>
                                    </section>
                        
                                    <section class = "update-btn-container">
                                        <div class ="btn" id="updateButton">
                                            <button>추가하기</button>
                                        </div>
                                        <div class ="btn" id="update-del-button">
                                            취소하기
                                        </div>
                                    </section>                          
                                </form>
                            </div>
                         <label  id = "event-modal-back"></label>
                     </div>    

    
    
<%-- ============================================================  메뉴 수정 (팝업) 끝 ============================================================ --%>



        </main>

    <script>

        // menu-search.js
        document.getElementById("popcorn-search-btn").addEventListener("click", function() {
            var searchInput = document.getElementById("popcorn-search-input").value;
            console.log("searchInput :" , searchInput);
            searchMenu(searchInput, "popcorn");
        });

        document.getElementById("popcorn-search-input").addEventListener("keyup", function(event) {

            if (event.key === "Enter") { 
                var searchInput = document.getElementById("popcorn-search-input").value;
                searchMenu(searchInput, "popcorn");
            }
        });

        // =============================//

        document.getElementById("drink-search-btn").addEventListener("click", function() {

            var searchInput = document.getElementById("drink-search-input").value;
            searchMenu(searchInput, "drink");
        });
        
        document.getElementById("drink-search-input").addEventListener("keyup", function(event) {

            if (event.key === "Enter") { 
                var searchInput = document.getElementById("drink-search-input").value;
                searchMenu(searchInput, "drink");
            }
        });

        // =============================//


        document.getElementById("snack-search-btn").addEventListener("click", function() {
            var searchInput = document.getElementById("snack-search-input").value;
            searchMenu(searchInput, "snack");
        });

        document.getElementById("snack-search-input").addEventListener("keyup", function(event) {

            if (event.key === "Enter") { 
                var searchInput = document.getElementById("snack-search-input").value;
                searchMenu(searchInput, "snack");
            }
        });
        
        // =============================//

        function searchMenu(searchValue, menuCategory) {
            
            
            console.log("menuCategory : ",menuCategory);

            let menuItems = [];
            switch(menuCategory) {
                case "popcorn":
                    menuItems = document.querySelectorAll(".popcorn-info-list");
                    console.log("menuItems(팝콘) : ", menuItems);
                    break;
                case "drink":
                    menuItems = document.querySelectorAll(".drink-info-list");
                    console.log("menuItems(음료) : ", menuItems);
                    break;
                case "snack":
                    menuItems = document.querySelectorAll(".snack-info-list");
                    console.log("menuItems(간식) : ", menuItems);
                    break;
            }
        
            
                for (var i = 0; i < menuItems.length; i++) {
                // 각 영화 항목의 제목 가져오기
                    var menuTitle = menuItems[i].querySelector(".menu-title").textContent;
            
                    // 영화 제목에 검색어가 포함되어 있으면 항목을 보이게 하고, 포함되어 있지 않으면 항목을 숨김
                    if (menuTitle.toLowerCase().includes(searchValue.toLowerCase())) {
                        menuItems[i].style.display = "flex";
                    } else {
                        menuItems[i].style.display = "none";                      
                    }
                }


        }



	</script>


        
        <c:if test="${not empty message}">
                
            <script>
                // EL/JSTL 구문이 먼저 해석
                // 문자열의 경우 따옴표가 없는 상태이니 옆에 붙여줘야함.
                alert('${message}') // ${message}
            </script>

        </c:if>


	
        <script src ="/movieInsight/resources/js/manager/manager-menu.js"></script>
    </body>	
	</body>
</html>