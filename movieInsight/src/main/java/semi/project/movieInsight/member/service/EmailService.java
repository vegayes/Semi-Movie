package semi.project.movieInsight.member.service;

import java.util.Map;

public interface EmailService {
	

	int signUp(String memberEmail);

	int checkAuthKey(Map<String, Object> paramMap);



  
}