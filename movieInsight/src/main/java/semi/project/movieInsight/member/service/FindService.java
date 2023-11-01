package semi.project.movieInsight.member.service;

import java.util.Map;

public interface FindService {

	String findId(String memberEmail);

	String sendEmail(String result, String memberEmail);

	// 비밀번호 찾기 
	int findPW(Map<String, Object> map);

	// 비밀번호 발송
	void sendEmail(int result, Map<String, Object> map);

}
