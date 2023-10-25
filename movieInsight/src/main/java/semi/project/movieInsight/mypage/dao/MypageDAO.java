package semi.project.movieInsight.mypage.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Menu;

@Repository
public class MypageDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	public Map<String, List<Menu>> selectMenu() {
		
		List<Menu> popcorn = sqlSession.selectList("menuMapper.selectPopcorn");
		
		System.out.println("popcorn : " + popcorn);
		
		
		return null;
	}

	
	
	
}
