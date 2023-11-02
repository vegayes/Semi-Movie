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
	public int idCheck(String memberId) {
		System.out.println("memberSevice"+memberId);
		return dao.idCheck(memberId);
	}
	
	// 이메일 중복검사
	@Override
	public int checkEmail(String email) {
	
		return dao.CheckEmail(email);
	}

	/**
	 * 임시 로그인 
	 */
	@Override
	public Member login(Member inputMember) {
		logger.info("MemberServiceImpl.login 실행");
		logger.debug("inputMember : " + inputMember.getMemberId());
		//logger.warn("경고");
		//logger.error("오류");
		return dao.login(inputMember);
	}

	@Override
	public int nickCheck(String nick_check) {
		
		return dao.nickCheck(nick_check);
	}



}
