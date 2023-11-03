<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cinema-homepage</title>
 <link rel="stylesheet" href="/movieInsight/resources/css/cinema/cinema-homepage.css">
   <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.css"
    />
     <script src="https://cdn.jsdelivr.net/npm/swiper@10/swiper-bundle.min.js"></script>
</head>
<body>

	<jsp:include page="/WEB-INF/views/common/header.jsp" />
       
    <!-- <div id="sortButton">

        <button onclick="sortKoreanLinks()" style="margin: 50px 0 0 100px;  
            border-radius: 10px;">&nbsp;한글순&nbsp;</button>

        <button onclick="sortDistance()" style="margin: 10px 0 0 10px;  
            border-radius: 10px;">&nbsp;거리순&nbsp;</button>

        <button  style="margin: 10px 0 0 10px;  
            border-radius: 10px;"> &nbsp;시설 만족도&nbsp; </button>

        <button  style="margin: 10px 0 0 10px;  
            border-radius: 10px;"> &nbsp;직원 친절도&nbsp; </button>

    </div>
   -->

    <div class="cinema_list_container">

       

        <section class="cinema_list swiper main-1">

                
            <ul class="swiper-wrapper">
              
            </ul>

            <div class="swiper-button-prev main-1-p"></div>
            <div class="swiper-button-next main-1-n"></div>
        </section>

    </div>
   
    <!--영화 이동 링크-->
    <a href="/movieInsight/movie">
        <img  class="movie_link"><br>
    </a>

    <!--지도-->
    <div style="position:relative; width:70%; height: 500px; display:flex; margin-left:25%; margin-top:1%;">
	    <div class="map_wrap">
	        <div id="map" style="position:absolute; width:100%; height:100% ; overflow:hidden;"></div>
	
	        <div id="menu_wrap" class="bg_white">
	            <div class="option">
	                <div>
	                    <form onsubmit="searchPlaces(); return false;">
	                        키워드 : <input type="text" value="cgv" id="keyword" size="10"> 
	                        <button type="submit" style="color:black";>검색하기</button> 
	                    </form>
	                </div>
	            </div>
	            <hr>
	            <ul id="placesList"></ul>
	            <div id="pagination"></div>
	        </div>
	    </div>
    </div>           
    <div class="cinema_promotion">
        <a href="" class="cinema_special" id="cinema_special"></a>
        <a href="" class="cinema_special">
            <li>대형 스크린에 맞는 맞춤형 설계로 모든 좌석에서 최고의 몰입 가능 !!!</li> <br><br>
            <li>Customized Projector로 비교할 수 없는 밝음과 선명한 화질 제공 !</li>  <br><br>
            <li>완벽하게 조율된 소리와 핀 포인트 정확도로 최적의 사운드를 제공 !</li>  <br><br>
            <li>추석 연휴 간 특별 할인 진행중 !!</li>
        </a>
    </div>


    <div id="cinema_promotion">
        <div class="event_promotion">
           
            <a href=""  class="preview_img"></a>
            <a href=""  class="preview_text">

                    &lt;씰벤져스 시사회&gt; <br>
                   <li>응모기간</li> <br> 
                    9월 10일 8시~9월15일 16시   <br>
                    <li>시사회 일시</li>  <br>
                    09월20일 오후 05시 <br>
                    <li>시사회 장소</li> <br>
                    메가박스 코엑스 38383E

            </a>
            
            <a href="" class="discount_img"></a>
            <a href="" class="preview_text">
                
                &lt;cgv용산아이파크몰&gt; <br><br> 
                <li>티켓 할인 이벤트</li> <br>
                10월 1일까지 최대 20% 할인 <br>
                <li>팝콘 추가 이벤트</li> <br>
                10월 7일까지 팝콘 M사이즈 주문 시 <br>
                L사이즈로 무료 업그레이드
                 
            </a>
        </div>
    </div>

    <br><br><br><br><br>
    


<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>


<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bd0095e2bd371f5a6ae43c67546f3c62&libraries=services"></script>
<script src="/movieInsight/resources/js/cinema/cinema-homepage.js"></script>



</html>