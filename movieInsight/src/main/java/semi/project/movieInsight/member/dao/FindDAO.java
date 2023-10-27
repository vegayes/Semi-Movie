package semi.project.movieInsight.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FindDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public int selectFindId(String email) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("findMapper.findId", email);
	}
	


}
