package semi.project.movieInsight.member.service;

public interface EmailService {
	
	boolean sendVerificationEmail(String toEmail, String verificationCode);



  
}