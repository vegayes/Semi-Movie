package semi.project.movieInsight.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
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
	public int emailCheck(String id_check) {
		int result = service.idCheck(id_check);
		return result;
	}
	
}
