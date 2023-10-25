package semi.project.movieInsight.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import semi.project.movieInsight.member.dao.MemberDAO;
import semi.project.movieInsight.member.domain.MemberRepository;
import semi.project.movieInsight.member.dto.Member;

@Service
public class MemberServiceimpl implements MemberService{

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	public void MemberServiceImpl(MemberRepository memberRepo) {
		this.memberRepo = memberRepo;
	}
	
	@Transactional
	@Override
	public int signUp(Member inputMember) {
		
		String encPw = bcrypt.encode(inputMember.getMemberPw());
		inputMember.setMemberPw(encPw);
		
		return dao.signUp(inputMember);
	}

	@Override
	public int idCheck(String id_check) {

		return 0;
	}

}
