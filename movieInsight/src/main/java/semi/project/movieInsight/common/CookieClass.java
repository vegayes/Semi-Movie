package semi.project.movieInsight.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import semi.project.movieInsight.member.dto.Member;

@WebServlet("/SetCookieServlet")
@SessionAttributes("{loginMember}")
public class CookieClass extends HttpServlet {
	
    public static void setCookieUrl(HttpServletRequest request, HttpServletResponse response, String UserId, String pageNo) throws ServletException, IOException {
        // 사용자마다 다르게 저장할 값을 얻어온다 (예: 사용자 아이디 등)
    	Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String formattedDate = dateFormat.format(currentDate);
        String saveString = UserId + "_" + pageNo + "_" + formattedDate;
        // 쿠키 생성
        
        Cookie cookie = new Cookie("uniqueCookie_" + UserId + "_" + pageNo, saveString);
        
        cookie.setPath("/movieInsight/mypage/member");
        // 쿠키를 응답 헤더에 추가
        response.addCookie(cookie);

        // 응답을 생성
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("쿠키가 설정되었습니다.");
    }
}

