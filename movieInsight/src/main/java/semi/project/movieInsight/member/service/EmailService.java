package semi.project.movieInsight.member.service;

import java.util.Map;

public interface EmailService {
	

	boolean sendVerificationEmail(String toEmail, String verificationCode);


	int signUp(String memberEmail);

	int checkAuthKey(Map<String, Object> paramMap);




  
}