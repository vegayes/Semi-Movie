package semi.project.movieInsight.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/checkloginstatus")
public class CheckLoginStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("memberId");
        
        if (memberId != null) {
            // 사용자가 로그인한 경우
            response.getWriter().write("loggedIn"); // 또는 원하는 응답을 반환
        } else {
            // 사용자가 로그인하지 않은 경우
            response.getWriter().write("notLoggedIn"); // 또는 원하는 응답을 반환
        }
    }
}