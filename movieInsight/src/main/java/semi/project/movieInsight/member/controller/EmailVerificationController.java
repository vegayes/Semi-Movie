package semi.project.movieInsight.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import semi.project.movieInsight.member.service.EmailService;

@Controller
@RequestMapping("/movieInsignht")
public class EmailVerificationController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/superEmail")
	@ResponseBody
	public String sendVerificationEmail(@RequestParam("memberEmail") String memberEmail) {
		
		// 인증번호 생성
		String verificationCode = generateVerificationCode();
		
		// 이메일 전송
		boolean emailSent = emailService.sendVerificationEmail(memberEmail, verificationCode);
	
		if (emailSent) {
			return "인증번호가 이메일로 전송되었습니다.";
		} else {
			return "이메일 전송에 실패했습니다.";
		}
		
		
		

	}

	/** 인증번호 생성 메소드
	 * @return
	 */
	private String generateVerificationCode() {
	
		return String.valueOf((int) (Math.random() * 10000));
	}
}
