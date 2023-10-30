package semi.project.movieInsight.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import semi.project.movieInsight.member.dto.Member;

@SessionAttributes("{loginMember}")
public class CookieUrlClass {
	
	private static Cookie cookie;
	
//    public static void setCookieUrl(HttpServletRequest request,
//    								HttpServletResponse response, 
//    								String UserId, String pageNo) throws IOException {
//    	
//    	Date currentDate = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
//        String formattedDate = dateFormat.format(currentDate);
//    	
//    	String saveString = UserId + "_" + pageNo + "_" + formattedDate;
//        // 쿠키 생성
//        cookie = new Cookie("uniqueCookie_" + UserId + "_" + pageNo, saveString);
//        cookie.setPath("/movieInsight/mypage/member");
//        
//        
//        // 쿠키를 응답 헤더에 추가
//        response.addCookie(cookie);
//
//        // 응답을 생성
//        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().println("쿠키가 설정되었습니다.");
//    }
    
	
    public static void setCookieUrl(HttpServletRequest request, 
    								 HttpServletResponse response, 
    								 String UserId, String pageNo) throws IOException {
    	
    	
        cookie = findCookieUrl(request, UserId);
        cookie.setPath("/movieInsight");
       
        String newValue = String.join("_", arrayNewValue(pageNo));
        cookie.setValue(newValue);
        
        response.addCookie(cookie);
        
        // 응답을 생성
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("쿠키가 설정되었습니다.");
    }
    
    private static List<String> getCookieUrlValue() {
    	List<String> list = new ArrayList<>();
    	
    	String[] items = cookie.getValue().split("_");
    	
    	for(String item : items) {
    		if(item.equals("")) {
    			continue;
    		}
    		list.add(item);
    	}
    	
    	return list;
    }
    
    private static Cookie findCookieUrl(HttpServletRequest request, String UserId) {
    	Cookie[] cookies = request.getCookies();
    	
    	if(cookies != null) {
    		for (Cookie cookie : cookies) {
    			if(cookie.getName().equals("urlCookie_" + UserId)) return cookie;
    		}
    	}
    	
    	
    	Cookie cookie = new Cookie("urlCookie_" + UserId, "");
    	cookie.setPath("/movieInsight");
    	return cookie; 
    }
    
    private static List<String> arrayNewValue(String pageNo) {
    	List<String> list = new ArrayList<>();
    	
    	list = getCookieUrlValue();
    	
    	if(!list.isEmpty()) {
    		list.remove(pageNo);
    	}
    	list.add(pageNo);
    	
    	return list;
    }
 }

