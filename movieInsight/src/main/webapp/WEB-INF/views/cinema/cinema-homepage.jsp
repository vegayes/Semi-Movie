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
        <a href="${promotion.promotionURL}" class="cinema_special" id="cinema_special" target="_blank">
            <img src="/movieInsight/resources/images/cinema/${promotion.promotionImg}" id="promotionImg">
        </a>
        <a href="" class="cinema_special" target="_blank">
            <li>${promotion.promotionType}</li> <br>
            <li>${promotion.promotionContent}</li> 
        </a>
    </div>

    

    <div id="cinema_promotion">
        <div class="event_promotion">

            <a href="${event.eventURL}"  class="preview_img"  target="_blank">
                <img src="/movieInsight/resources/images/cinema/${event.eventImg}" id="eventImg">
            </a>
            <a href="${event.eventURL}"  class="preview_text" target="_blank">

                &lt;${event.eventTitle}&gt; <br>
                <li>${event.eventContent}</li>

            </a>
            
        </div>
    </div>

    <br><br><br><br><br>
    


<jsp:include page="/WEB-INF/views/common/footer.jsp" />

</body>


<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bd0095e2bd371f5a6ae43c67546f3c62&libraries=services"></script>
<script src="/movieInsight/resources/js/cinema/cinema-homepage.js"></script>



</html>