package semi.project.movieInsight.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDAO {
      
	@Autowired
	private SqlSessionTemplate sqlSession ;
	
	public int updateAuthKey1(Map<String, String> map) {
		return sqlSession.update("emaillMapper.updateAuthKey",map);
		
	}
	
	public int updateAuthKey2(Map<String, String> map) {
		return sqlSession.insert("emaillMapper.updateAuthKey",map);
		
	}
	
	public int updateAuthKey3(Map<String, String> map) {
		return sqlSession.selectOne("emaillMapper.updateAuthKey",map);
		
	}
	
	
}
