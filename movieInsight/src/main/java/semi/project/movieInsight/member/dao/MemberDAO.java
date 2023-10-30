package semi.project.movieInsight.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.member.dto.Member;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int signUp(Member inputMember) {
	
		return sqlSession.insert("memberMapper.signUp", inputMember);
	}

	public int idCheck(String id_check) {
	
		return sqlSession.selectOne("memberMapper.checkId",id_check);
	}


	/** 로그인
	 * @param inputMember
	 * @return
	 */
	public Member login(Member inputMember) {
		return sqlSession.selectOne("memberMapper.login",inputMember);
	}

	

	// 이메일 중복검사
	public int CheckEmail(String email) {
	
		return sqlSession.selectOne("memberMapper.checkEmail", email);

	}

	

}
