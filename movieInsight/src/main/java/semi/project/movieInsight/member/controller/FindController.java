package semi.project.movieInsight.member.controller;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import semi.project.movieInsight.member.service.FindService;



@RequestMapping("/find")
@Controller
public class FindController {
	
	@Autowired
	private FindService service;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	private String fromEmail = "movieinsightsemiproject@gmail.com";
	private String fromUsername = "아이디또는 비밀번호 찾기";
	
	
	// 아이디 찾기 페이지
	@GetMapping("/findID")
	public String Id() {
		
		return "member/find_id";
	}
	

	// 비밀번호 찾기 페이지
	@GetMapping("/pw")
	public String Pw() {
		
		return "member/find_pw";
	}
	
	
	// 아이디 찾기 이메일
	@ResponseBody
	@PostMapping("/findID")
	public String findId(@RequestParam("email") String email, Model model) {

	  String result= service.findId(email);
	  
	  if(result!=null) {
		  
		   sendEmail(result, email);
		  
		   model.addAttribute("message", "아이디가 이메일로 발송되었습니다.");
		   
		   
	  }else {
		  
		    model.addAttribute("message", "입력한 이메일 주소에 해당하는 사용자를 찾을 수 없습니다.");
		    sendEmail(result, email);
		    return "member/find_id";
	  }
	  
		
	  return "member/login_signUp";
	}

	// 이메일 발송
	private void sendEmail(String result, String email) {
		// TODO Auto-generated method stub
		try {
			

		  MimeMessage mail = mailSender.createMimeMessage();
		   
	       String subject = "[MovieInsight]"+"아이디찾기";
	       
	         // 문자 인코딩
           String charset = "UTF-8";
           
           // 메일 내용
           String mailContent 
               = "<p>MovieInsight "+" 아이디 입니다.</p>"
               + "<h3 style='color:blue'>" +result  + "</h3>";
    
           
           // 송신자(보내는 사람) 지정
           mail.setFrom(new InternetAddress(fromEmail, fromUsername));
           // 수신자(받는사람) 지정
           mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
           
           
           // 이메일 제목 세팅
           mail.setSubject(subject, charset);
           
           // 내용 세팅
           mail.setText(mailContent, charset, "html"); //"html" 추가 시 HTML 태그가 해석됨
           
           mailSender.send(mail);
           
		} catch (Exception e) {
            e.printStackTrace();
       
        }

	}
	
	//비밀번호 찾기 
	
	
	

	
}
