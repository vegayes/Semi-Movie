package semi.project.movieInsight.member.service;

import java.util.Map;

import semi.project.movieInsight.member.dto.Member;

public interface FindService {

	// 아이디 찾기
	String findId(String memberEmail);

	// 아이디 발송
	String sendEmail(String result, String memberEmail);

	// 비밀번호 찾기 
	Member findPW(Map<String, Object> map);

	// 비밀번호 발송
	void sendEmail(Map<String, Object> map);

	// 비밀번호 변경
	int updatePw(Map<String, Object> map);

	// 무작위 비밀번호 생성
	String createPw(String memberId);

}
