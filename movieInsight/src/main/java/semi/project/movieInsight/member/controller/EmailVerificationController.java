package semi.project.movieInsight.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.member.service.EmailService;

@Controller
@RequestMapping("/sendEmail")
public class EmailVerificationController {

	@Autowired
	private EmailService emailService;
	
	

	@GetMapping("/superEmail")
	@ResponseBody
	public int sendVerificationEmail(@RequestParam("memberEmail") String memberEmail) {
	    // 인증번호 생성
	    String verificationCode = generateVerificationCode();

	    // 이메일 전송
	    boolean emailSent = emailService.sendVerificationEmail(memberEmail, verificationCode);

	    if (emailSent) {
	        return 1; // 인증번호 발송 성공
	    } else {
	        return 0; // 이메일 전송 실패
	    }
	}

	/** 인증번호 생성 메소드
	 * @return
	 */
	private String generateVerificationCode() {
	
		return String.valueOf((int) (Math.random() * 10000));
	}
}
