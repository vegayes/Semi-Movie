package semi.project.movieInsight.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.member.dto.Member;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 회원가입 DAO
	 * @param inputMember
	 * @return
	 */
	public int signUp(Member inputMember) {

		return sqlSession.insert("memberMapper.signUp", inputMember);
	}

}
