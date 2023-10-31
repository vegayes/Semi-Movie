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
	
	
	@ResponseBody
	@GetMapping("/dupCheck/email")
	public int checkEmail(String email) {
		
		System.out.println(email);
		
		return service.checkEmail(email);
	}
}

