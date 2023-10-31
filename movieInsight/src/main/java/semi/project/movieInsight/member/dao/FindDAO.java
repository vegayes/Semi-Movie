package semi.project.movieInsight.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FindDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public String selectFindId(String email) {
		return sqlSession.selectOne("findMapper.findId", email);
	}

	// 비밀번호 찾기 
	public int findPW(Map<String, Object> map) {
		return sqlSession.selectOne("findMapper.findPw",map);
	}
	


}
