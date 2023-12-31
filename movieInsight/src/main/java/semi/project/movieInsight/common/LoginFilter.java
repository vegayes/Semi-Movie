package semi.project.movieInsight.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName = "loginFilter", urlPatterns = {"/movieInsight/*"})
public class LoginFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("--- 마이페이지 로그인 필터 생성 ---");
	}
  
	public void destroy() {
		System.out.println("--- 마이페이지 로그인 필터 파괴 ---");
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("로그인 필터 실행할 내용");

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		 
		
		/* 
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember.getAuthority() != 2 ) { // 관리자가 아니면 메인페이지로
			resp.sendRedirect("/");
		} else {
			resp.sendRedirect("/관리자페이지로..");
		}
		*/
		
			System.out.println("뭐야");
			System.out.println(session.getAttribute("loginMember"));
		
		if( session.getAttribute("loginMember") == null ) {
			resp.sendRedirect("/");
			System.out.println("마이페이지");
		} else {// 4) 로그인 상태인 경우 다음 필터 또는 DispatcherServlet으로 전달
			
			System.out.println("마이페이지 실행할거야");
			chain.doFilter(request, response);	
		}	
	}

	
	

}
