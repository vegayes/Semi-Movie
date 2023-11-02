package semi.project.movieInsight.member.service;

import java.util.Map;

import semi.project.movieInsight.member.dto.Member;

public interface MemberService {

	int signUp(Member inputMember);
	
	int idCheck(String memberId);


	Member login(Member inputMember);

	int checkEmail(String email);

	int nickCheck(String nick_check);




}
