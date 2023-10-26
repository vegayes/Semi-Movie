<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Admin-Menu</title>
		
		<link rel="stylesheet" href = "/movieInsight/resources/css/manager/manager-menu.css">

        <script src="https://kit.fontawesome.com/ac58eafae7.js" crossorigin="anonymous"></script>
		
	</head>
	<body>
        <nav>
            <div class="admin-menu">
                <ul>
                    <li class="admin-list"><a href="#">회원 관리</a></li> 
                    <li class="admin-list"><a href="#">영화</a></li>               
                    <li class="admin-list"><a href="#">영화관</a></li>
                    <li class="admin-list"><a href="/movieInsight/mypage/manager/menu">메뉴</a></li>
                    <li class="admin-list"><a href="#">이벤트</a></li>
                </ul>
            </div>
        </nav>

        <main>
            <section class = "title-container">
                <div>
                    메뉴 관리 
                </div>
            </section>

            <section class = "menu-list-container">
                <div class = "menu-container">
                    <div class = "menu-list">
                        <div class = "menu-container-title" >
                            팝콘
                        </div>
                        <div class = "menu-container-search">
                            <form action="#" name="search-form">
                                <fieldset>
                                    <input type="search" id = "search-input" name="search-input"
                                    autocomplete="off" placeholder="메뉴 검색">
                                    <button id="search-btn" class="fa-solid fa-magnifying-glass"></button>
                                </fieldset>
                            </form>
                        </div>

                        <div class = "menu-info-container">

                            <table class = "menu-info-table">

                                <c:forEach items = "${popcorn}" var="popcorn" >
                                    <tr>
                                        <td class = "menu-title">
                                           ${popcorn.menuName}
                                        </td>

                                        <td class = "menu-price">
                                            ${popcorn.menuPrice}
                                        </td>

                                        <td class = "menu-update">
                                            <div>
                                                수정
                                            </div>
                                        </td>
                                        <td class = "menu-del">
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

                <div class = "menu-container">
                    <div class = menu-list>
                        <div class = "menu-container-title" >
                            음료
                        </div>

                        <div class = "menu-container-search">
                            <form action="#" name="search-form">
                                <fieldset>
                                    <input type="search" id = "search-input" name="search-input"
                                    autocomplete="off" placeholder="메뉴 검색">
                                    <button id="search-btn" class="fa-solid fa-magnifying-glass"></button>
                                </fieldset>
                            </form>
                        </div>

                        <div class = "menu-info-container">
                            
                            <table class = "menu-info-table">

                                <tr>
                                    <td class = "menu-title">
                                         메뉴1
                                    </td>

                                    <td class = "menu-price">
                                        가격
                                    </td>

                                    <td class = "menu-update">
                                        <div>
                                            수정
                                        </div>
                                    </td>
                                    <td class = "menu-del">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>


                             


                            </table>

                        </div>
                    </div>
                </div>


                <div class = "menu-container">
                    <div class = menu-list>
                        <div class = "menu-container-title" >
                            사이드
                        </div>

                        <div class = "menu-container-search">
                            <form action="#" name="search-form">
                                <fieldset>
                                    <input type="search" id = "search-input" name="search-input"
                                    autocomplete="off" placeholder="메뉴 검색">
                                    <button id="search-btn" class="fa-solid fa-magnifying-glass"></button>
                                </fieldset>
                            </form>
                        </div>

                        <div class = "menu-info-container">
                            <table class = "menu-info-table">
                                <tr>
                                    <td class = "menu-title">
                                         메뉴1
                                    </td>

                                    <td class = "menu-price">
                                        가격
                                    </td>

                                    <td class = "menu-update">
                                        <div>
                                            수정
                                        </div>
                                    </td>
                                    <td class = "menu-del">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>


                                <tr>
                                    <td class = "menu-title">
                                            메뉴1
                                    </td>

                                    <td class = "menu-price">
                                        가격
                                    </td>

                                    <td class = "menu-update">
                                        <div>
                                            수정
                                        </div>
                                    </td>
                                    <td class = "menu-del">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td class = "menu-title">
                                         메뉴1
                                    </td>

                                    <td class = "menu-price">
                                        가격
                                    </td>

                                    <td class = "menu-update">
                                        <div>
                                            수정
                                        </div>
                                    </td>
                                    <td class = "menu-del">
                                        <div>
                                            삭제
                                        </div>
                                    </td>
                                </tr>

                                <tr>
                                    <td class = "menu-title">
                                         메뉴1
                                    </td>

                                    <td class = "menu-price">
                                        가격
                                    </td>

                                    <td class = "menu-update">
                                        <div>
                                            수정
                                        </div>
                                    </td>
                                    <td class = "menu-del">
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




        </main>

        <script src ="/movieInsight/resources/js/manager/manager-menu.js"></script>
    </body>	
	</body>
</html>