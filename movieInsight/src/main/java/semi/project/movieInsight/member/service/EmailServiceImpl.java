//package semi.project.movieInsight.member.service;
//
//import java.util.HashMap;
//
//import java.util.Map;
//
//import javax.mail.Message;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import semi.project.movieInsight.member.dao.EmailDAO;
//
//@Service
//public class EmailServiceImpl  implements EmailService{
//	
//	@Autowired
//	private EmailDAO dao;



//	private String fromEmail = "movieinsightsemiproject@gmail.com";
//	private String fromUsername = "MovieInsight";
//
//	public String createAuthKey() {
//        String key = "";
//        for(int i=0 ; i< 6 ; i++) {
//            
//            int sel1 = (int)(Math.random() * 3); // 0:숫자 / 1,2:영어
//            
//            if(sel1 == 0) {
//                
//                int num = (int)(Math.random() * 10); // 0~9
//                key += num;
//                
//            }else {
//                
//                char ch = (char)(Math.random() * 26 + 65); // A~Z
//                
//                int sel2 = (int)(Math.random() * 2); // 0:소문자 / 1:대문자
//                
//                if(sel2 == 0) {
//                    ch = (char)(ch + ('a' - 'A')); // 대문자로 변경
//                }
//                
//                key += ch;
//            }
//            
//        }
//        return key;
//	
//	}
//	
//	@Transactional
//	@Override
//	public int signUp(String superEmail, String title) {
//		
//		System.out.println("회원가입 service");
//		
//		String authKey = createAuthKey();
//		
//		try {
//			
//			//인증메일 보내기
//            MimeMessage mail = mailSender.createMimeMessage();
//            
//            // 제목
//            String subject = "[MovieInsight]"+title+" 인증코드";
//            
//            // 문자 인코딩
//            String charset = "UTF-8";
//            
//            // 메일 내용
//            String mailContent 
//                = "<p>Board Project "+title+" 인증코드입니다.</p>"
//                + "<h3 style='color:blue'>" + authKey + "</h3>";
//            
//            
//            
//            // 송신자(보내는 사람) 지정
//            mail.setFrom(new InternetAddress(fromEmail, fromUsername));
//            // 수신자(받는사람) 지정
//            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(superEmail));
//            
//            
//            // 이메일 제목 세팅
//            mail.setSubject(subject, charset);
//            
//            // 내용 세팅
//            mail.setText(mailContent, charset, "html"); //"html" 추가 시 HTML 태그가 해석됨
//            
//            mailSender.send(mail);
//			
//		 } catch (Exception e) {
//	            e.printStackTrace();
//	            return 0;
//		 }
//		
//	            Map<String, String> map = new HashMap<String, String>();
//	            map.put("authKey", authKey);
//	            map.put("email", superEmail);
//	            
//	            System.out.println(map);
//	            
//	            int result = dao.updateAuthKey(map);
//	            
//	            if(result == 0) {
//	            	result = dao.insertAuthKey(map);
//	            	
//	            	System.out.println("service result  = " + result );
//	            }
//		
//
//	    return result;
//		
//	 }






//}


	