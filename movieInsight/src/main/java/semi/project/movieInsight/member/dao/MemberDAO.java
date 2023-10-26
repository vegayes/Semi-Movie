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

	

}
