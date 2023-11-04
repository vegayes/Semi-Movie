package semi.project.movieInsight.member.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import semi.project.movieInsight.member.dao.MemberDAO;
import semi.project.movieInsight.member.dto.Member;

@Service
public class MemberServiceimpl implements MemberService{

	private Logger logger = LoggerFactory.getLogger(MemberServiceimpl.class);
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	

	
	@Transactional
	@Override
	public int signUp(Member inputMember) {
		

		String encPw = bcrypt.encode(inputMember.getMemberPw());
		inputMember.setMemberPw(encPw);
	
		return dao.signUp(inputMember);
	}

	// 아이디 중복검사
	@Override
	public int idCheck(String id_check) {
		
		System.out.println(id_check);
		
		return dao.idCheck(id_check);
	}
	
	// 이메일 중복검사
	@Override
	public int checkEmail(String email) {
	
		return dao.CheckEmail(email);
	}
	/**
	 *로그인 
	 */
	@Override
	public Member login(Member inputMember) {
		logger.info("MemberServiceImpl.login 실행");
		logger.debug("inputMember : " + inputMember.getMemberId());
		//logger.warn("경고");
		//logger.error("오류");
		Member loginMember = dao.login(inputMember);
		
//		if(loginMember != null) { // 아이디가 일치하는 회원이 조회된 경우
//			
//			// 입력한 pw,  암호화된 pw 같은지 확인
//			
//			// 같을 경우
//			if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
//				
//				// 비밀번호를 유지하지 않기 위해서 로그인 정보에서 제거
//				loginMember.setMemberPw(null);
//			
//			} else { // 다를경우
//				loginMember = null;
//			}
//			
//			
//		} 
//		System.out.println("loginMember : "+loginMember.getMemberPw());
//		
//		System.out.println(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw()));
	
		
		

		return loginMember;
		
		
	}
	
	@Override
	public int nickCheck(String nick_check) {
		
		return dao.nickCheck(nick_check);
	}


}
