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
		
//		return "member/login_signUp";
		return "redirect:/member/login";
	}
	
	/** 임시 로그인 페이지
	 * @param req
	 * @return
	 */
//	@PostMapping("/login")
	@GetMapping("/login")
	public String login( Model model,
						@RequestHeader("referer") String referer,
						RedirectAttributes ra,
						@RequestParam(value="saveId", required = false) String saveId,
						HttpServletResponse resp
						) {
		
		System.out.println("로그인 진행 시작");
		
		Member inputMember = new Member();
		// ------- 임시 로그인 설정 ------------
		inputMember.setMemberId("id2");
		inputMember.setMemberPw("pw2");
		// ------------------------------------------
		
		
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);

		
		// 로그인 결과에 따라 리타이렉트 경로를 다르게 지정
		String path = "redirect:";
		
		if(loginMember != null) { 	// 로그인 성공시
			path += "/movie";  // 메인페이지로 리다이렉트
			
			
			// 1) model에 로그인한 회원 정보 추가
			model.addAttribute("loginMember", loginMember);
			
			System.out.println("로그인 진행 : " +  loginMember);

			
			// 쿠키 생성(해당 쿠키에 담을 데이터를 k:v 로 지정)
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) { 
				cookie.setMaxAge(60*60*24*30); // 초단위 지정
				
			} else { 
				cookie.setMaxAge(0);
			}
			
			
			cookie.setPath("/movieInsight"); // localhost:/ 이하 모든 주소????
			
			System.out.println("loginMember" + loginMember.getMemberId());

			resp.addCookie(cookie);
			
			ra.addFlashAttribute("message", "로그인 진행 완료");
			
			
		} else { // 로그인 실패
			path += referer;

			ra.addFlashAttribute("message", "아이디 또는 비밀번호 불일치");
		}
		
		return path;
	}
	
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {

		status.setComplete();
		
		return "redirect:/movie";
	}
	
	@PostMapping("/login_signUp")
	public String signUp(Member inputMember,
						RedirectAttributes ra) {
		
		
		
		int result = service.signUp(inputMember);
		
		String path = "redirect:";
		
		String message = null;
		
		if(result > 0) {
			path += "/";
			
			message = inputMember.getMemberNickname() + "님 가입성공!";
		} else {
			path += "signUp";
			message = "회원가입 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return path;
		
		
	}
	
	public String exceptionHandler(Exception e, Model model) {

		e.printStackTrace();
		
		model.addAttribute("e", e);
		
		return "common/error";
	
}
	
	@ResponseBody
	@PostMapping("/idCheck")
	public int IdCheck(String id_check) {
		int result = service.idCheck(id_check);
		return result;
	}
	


	
}
