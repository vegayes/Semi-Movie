package semi.project.movieInsight.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/policy")
@Controller
public class PolicyController {
	
	// 개인정보 페이지
	@GetMapping
	public String policy() {
		
		return "policy/policy";
	}
	
	// 공지사항 페이지

	

}
