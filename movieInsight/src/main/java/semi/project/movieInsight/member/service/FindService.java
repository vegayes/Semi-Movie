package semi.project.movieInsight.member.service;

import java.util.Map;

public interface FindService {

	String findId(String email);

	void sendEmail(String result, String email);

	// 비밀번호 찾기 
	int findPW(Map<String, Object> map);

}
