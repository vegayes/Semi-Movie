package semi.project.movieInsight.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.member.dto.Member;

@Repository
public class FindDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// 아이디 찾기
	public Member selectFindId(String memberEmail) {
		return sqlSession.selectOne("findMapper.findId",memberEmail);
	}

	// 비밀번호 찾기 
	public Member findPW(Map<String, Object> map) {
		return sqlSession.selectOne("findMapper.findPw",map);
	}

	// 임시 비밀번호로 변경
	public int updatePw(Map<String, Object> map) {
		return sqlSession.update("findMapper.updatePw",map);
	}
	


}
