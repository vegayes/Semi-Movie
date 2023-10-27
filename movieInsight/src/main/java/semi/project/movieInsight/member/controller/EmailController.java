package semi.project.movieInsight.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import semi.project.movieInsight.member.service.EmailService;
import semi.project.movieInsight.member.service.MemberService;

@Controller
public class EmailController {
   
	@Autowired
	private MemberService service;
	
//	@Autowired
//	private EmailService Eservice;
   
	
	@ResponseBody
	@GetMapping("/dupCheck/email")
	public int checkEmail(String email) {
		
		System.out.println(email);
		
		return service.checkEmail(email);
	}
}
//	
//	@GetMapping("/superEmail")
//	@ResponseBody
//	public int signUp(String superEmail) {
//		System.out.println("회원가입 Controller");
//		
//		int check = Eservice.signUp(superEmail, "회원가입");
//		
//		System.out.println("check:" + check);
//		
//		return 0; 
//	}
//
//	
//   
//}
