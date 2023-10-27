package semi.project.movieInsight.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import semi.project.movieInsight.member.dao.MemberDAO;
import semi.project.movieInsight.member.dto.Member;

@Service
public class MemberServiceimpl implements MemberService{

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

		return dao.idCheck(id_check);
	}
	
	// 이메일 중복검사
	@Override
	public int checkEmail(String email) {
	
		return dao.CheckEmail(email);
	}

}
