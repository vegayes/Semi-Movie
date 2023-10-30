package semi.project.movieInsight.member.controller;


import java.util.Map;

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
// movieInsight => /senEmail로 바꿔주세요! 
@RequestMapping("/sendEmail")
public class EmailVerificationController {

	@Autowired
	private EmailService emailService;
	
//	@Autowired
//	private EmailService service;


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
		
		
    
  @GetMapping("/superEmail")
	@ResponseBody
	public int sendVerificationEmail(@RequestParam("memberEmail") String memberEmail) {
		return service.signUp(memberEmail);
		}

  @GetMapping("/checkAuthKey")
  @ResponseBody
  public int checkAuthKey(@RequestParam Map<String, Object> paramMap){

    System.out.println(paramMap); // {inputKey=wc3rxG, email=knbdh@nate.com}

      return service.checkAuthKey(paramMap);
    }
    
    
		

	

	/** 인증번호 생성 메소드
	 * @return
	 */
	private String generateVerificationCode() {
	
		return String.valueOf((int) (Math.random() * 10000));
	}
  
  
  
  

}
