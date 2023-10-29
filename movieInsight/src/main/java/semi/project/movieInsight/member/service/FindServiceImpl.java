//package semi.project.movieInsight.member.service;
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
//import semi.project.movieInsight.member.dao.FindDAO;
//
//@Service
//public class FindServiceImpl implements FindService {
//
//	@Autowired
//	private FindDAO dao;
//	
//	@Autowired
//	private JavaMailSender mailSender;
//	
//	private String fromEmail = "eee595398@gmail.com";
//	private String fromUsername = "아이디또는 비밀번호 찾기";
//	
//	
//	@Transactional
//	@Override
//	public int findId(String email, String string) {
//		
//	 	int result = dao.selectFindId(email);
//		
//		
//		try {
//			
//		   
//			
//			   MimeMessage mail = mailSender.createMimeMessage();
//			   
//		       String subject = "[MovieInsight]"+"아이디찾기";
//		       
//		         // 문자 인코딩
//	            String charset = "UTF-8";
//	            
//	            // 메일 내용
//	            String mailContent 
//	                = "<p>MovieInsight "+" 아이디 입니다.</p>"
//	                + "<h3 style='color:blue'>"  + "</h3>";
//         
//	            
//	            // 송신자(보내는 사람) 지정
//	            mail.setFrom(new InternetAddress(fromEmail, fromUsername));
//	            // 수신자(받는사람) 지정
//	            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
//	            
//	            
//	            // 이메일 제목 세팅
//	            mail.setSubject(subject, charset);
//	            
//	            // 내용 세팅
//	            mail.setText(mailContent, charset, "html"); //"html" 추가 시 HTML 태그가 해석됨
//	            
//	            mailSender.send(mail);
//
//		       
//		}catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//		}
//		
//	  
//		
//		
//		return result;
//	}
//


//@Override
//	public List<MemberVO> findId(String memberEmail)throws Exception{
//		return memberDAO.findId(memberEmail);
//	}
//	
//	@Override
//	public int findIdCheck(String memberEmail)throws Exception{
//		return memberDAO.findIdCheck(memberEmail);
//	}
//


//}
