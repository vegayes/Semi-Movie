package semi.project.movieInsight.member.service;

import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import semi.project.movieInsight.member.dao.FindDAO;
import semi.project.movieInsight.member.dto.Member;

@Service
public class FindServiceImpl implements FindService {

	@Autowired
	private FindDAO dao;
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	private String fromEmail = "movieinsightsemiproject@gmail.com";
	private String fromUsername = "아이디또는 비밀번호 찾기";
	
	
	@Transactional
	@Override
	public String findId(String memberEmail) {
		
	 
		 Member member = dao.selectFindId(memberEmail);
		    if(member != null) {
		        return member.getMemberId();
		    }
		    return null;
	
	}


	@Override
	public String sendEmail(String result, String memberEmail) {
		
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
       mail.addRecipient(Message.RecipientType.TO, new InternetAddress(memberEmail));
       
       
       // 이메일 제목 세팅
       mail.setSubject(subject, charset);
       
       // 내용 세팅
       mail.setText(mailContent, charset, "html"); //"html" 추가 시 HTML 태그가 해석됨
       
       mailSender.send(mail);
       
	} catch (Exception e) {
        e.printStackTrace();
   
    }
		
		
		return result;
		
		
		
	}

	// 비밀번호 찾기 
	@Override
	public int findPW(Map<String, Object> map) {
		
		return dao.findPW(map);
	}


	// 비밀번호 보내기
	@Override
	public void sendEmail(int result, Map<String, Object> map) {
		try {
			
			for (String key : map.keySet()) {
			
			}
				
				String memberEmail =(String)map.get("memberEmail");
			
			  MimeMessage mail = mailSender.createMimeMessage();
			   
		       String subject = "[MovieInsight]"+"비밀번호찾기";
		       
		         // 문자 인코딩
		       String charset = "UTF-8";
		       
		       // 메일 내용
		       String mailContent 
		           = "<p>MovieInsight "+" 비밀번호 입니다.</p>"
		           + "<h3 style='color:blue'>" +result  + "</h3>";

		       
		       // 송신자(보내는 사람) 지정
		       mail.setFrom(new InternetAddress(fromEmail, fromUsername));
		       // 수신자(받는사람) 지정
		       mail.addRecipient(Message.RecipientType.TO, new InternetAddress(memberEmail));
		       
		       
		       // 이메일 제목 세팅
		       mail.setSubject(subject, charset);
		       
		       // 내용 세팅
		       mail.setText(mailContent, charset, "html"); //"html" 추가 시 HTML 태그가 해석됨
		       
		       mailSender.send(mail);
		       
			} catch (Exception e) {
		        e.printStackTrace();
		   
		    }
		
	}



}
