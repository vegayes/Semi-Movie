//package semi.project.movieInsight.member.service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.mail.Message;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import semi.project.movieInsight.member.dao.FindDAO;
//
//@Service
//public class FindServiceImpl implements FindService{
//	
//	@Autowired
//	private FindDAO dao;
//
//	@Autowired
//	private JavaMailSender mailSender;
//	
//	private String fromEmail = "movieinsightsemiproject@gmail.com";
//	private String fromUsername = "movieInsight";
//	
//	
//	@Override
//	public int findId(String email) {
//		// TODO Auto-generated method stub
//		
//		 try {
//
//		        //인증메일 보내기
//		        MimeMessage mail = mailSender.createMimeMessage();
//		        
//		        // 제목
//		        String subject = "[movieInsight]"+" 인증코드";
//		        
//		        // 문자 인코딩
//		        String charset = "UTF-8";
//		        
//		        // 메일 내용
//		        String mailContent 
//		            = "<p>movieInsight "+" 인증코드입니다.</p>"
//		            + "<h3 style='color:blue'>"  + "</h3>";
//		        
//		        
//		        
//		        // 송신자(보내는 사람) 지정
//		        mail.setFrom(new InternetAddress(fromEmail, fromUsername));
//		        // 수신자(받는사람) 지정
//		        mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//		        
//		        
//		        // 이메일 제목 세팅
//		        mail.setSubject(subject, charset);
//		        
//		        // 내용 세팅
//		        mail.setText(mailContent, charset, "html"); //"html" 추가 시 HTML 태그가 해석됨
//		        
//		        mailSender.send(mail);
//		        
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		        return 0;
//		    }
//		    
//		    Map<String, String> map = new HashMap<String, String>();
//		    map.put("Id", Id);
//		    map.put("email", email);
//		    
//		    System.out.println(map);
//		    
//
//
//
//
//
//	
//	}
//
//}
