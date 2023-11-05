package semi.project.movieInsight.member.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.member.service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"}) // Model의 이름(key)를 적으면 session으로 추가
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	/** 로그인 페이지 이동
	 * @return
	 */
	@GetMapping("/loginPage")
	public String moveLogin() {
		
		return "member/login_signUp";

	}

	
	// 로그인
	@PostMapping("login")
	public String login(Member inputMember,Model model,
			@RequestParam(value="saveId", required = false) String saveId,
			@RequestHeader("referer") String referer, RedirectAttributes ra,
			HttpServletResponse resp) {
		
		
			Member loginMember = service.login(inputMember);
			
			System.out.println(loginMember);
			
			String path = "redirect:";
			if(loginMember != null) {
				
				path += "/";
				
				model.addAttribute("loginMember", loginMember);
				
				Cookie cookie = new Cookie("saveId", loginMember.getMemberId());
			
				if(saveId != null) { // 체크가 되었을 때
					
					// 한 달(30일) 동안 유지되는 쿠키 생성
					cookie.setMaxAge(60*60*24*30); // 초단위 지정
					
					
					
					
				} else { // 체크가 안되었을 때 
					
					// 0초 동안 유지되는 쿠키 생성
					// -> 기존에 쿠기가 지정되어있었다면 해당 쿠키를 삭제
					cookie.setMaxAge(0);
					
				}
				
				// 클라이언트가 어떤 요청을 할 때 쿠키가 첨부될지 경로(주소)를 지정
				cookie.setPath("/member/loginPage");
				resp.addCookie(cookie);
		
				
				
			}else {
				path += referer;
				ra.addFlashAttribute("message", "아이디 또는 비밀번호 불일치");
				return "redirect:/member/loginPage";
				
			}
		
		return path;
	}
	
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {

		status.setComplete();
		
		return "redirect:/movie";
	}
	
	@PostMapping("/signUp")
	public String signUp(Member inputMember,
						RedirectAttributes ra) {
		
		
		System.out.println(inputMember);
		
		int result = service.signUp(inputMember);
		
		
		System.out.println(result);
		
		
		String path = "redirect:";
		
		String message = null;
		
		if(result > 0) {
			path += "movieInsight/home-page";
			
			message = inputMember.getMemberNickname() + "님 가입성공!";
		} else {
			path += "login_signUp";
			message = "회원가입 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/member/loginPage";
		
		
	}
	
	public String exceptionHandler(Exception e, Model model) {

		e.printStackTrace();
		
		model.addAttribute("e", e);
		
		return "common/error";
	
}
	
	@ResponseBody
	@GetMapping("/idCheck")
	public int IdCheck(String id_check) {
		return service.idCheck(id_check);
	}
	
	
	@ResponseBody
	@GetMapping("/nickCheck")
	public int nickCheck(String nick_check) {
		return service.nickCheck(nick_check);
	}
	


	
}
