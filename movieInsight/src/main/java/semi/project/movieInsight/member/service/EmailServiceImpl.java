package semi.project.movieInsight.member.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import semi.project.movieInsight.member.dao.EmailDAO;

@Service
public class EmailServiceImpl  implements EmailService{
	
	@Autowired
    private JavaMailSender mailSender;
	
	

	 @Override
	    public boolean sendVerificationEmail(String toEmail, String verificationCode) {
	        try {
	            MimeMessage mimeMessage = mailSender.createMimeMessage();
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

	            helper.setTo(toEmail);
	            helper.setSubject("이메일 인증"); // 이메일 제목
	            helper.setText("인증 코드: " + verificationCode, true); // 이메일 본문

	            mailSender.send(mimeMessage);
	            
	            System.out.println("이메일 전송 성공: To: " + toEmail);
	            return true;
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            System.out.println("이메일 전송 실패: To: " + toEmail);
	            return false;
	        }



}

}
	