package semi.project.movieInsight.member.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import semi.project.movieInsight.member.dao.EmailDAO;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
    private JavaMailSender mailSender;
	
	private String fromEmail = "movieinsightsemiproject@gmail.com";
	private String fromUsername = "세미프로젝트";
	
	
	

	 @Override
    public boolean sendVerificationEmail(String toEmail, String verificationCode) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(toEmail);
            helper.setSubject("이메일 인증"); // 이메일 제목
            helper.setText("인증 코드: " + verificationCode, true); // 이메일 본문
            
            mimeMessage.setFrom(new InternetAddress(fromEmail, fromUsername));
            
            
            

            mailSender.send(mimeMessage);
            
            System.out.println("이메일 전송 성공: To: " + toEmail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("이메일 전송 실패: To: " + toEmail);
            return false;
        }



}

}
	