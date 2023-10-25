package semi.project.movieInsight.mypage.dao;

import java.util.HashMap;
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
	
	
	// 관리자 페이지에서 메뉴를 카테고리별로 조회 
	public Map<String, List<Menu>> selectMenu() {
		
		 Map<String, List<Menu>> menuMap = new HashMap<String, List<Menu>>();
		
		List<Menu> popcorn = sqlSession.selectList("menuMapper.selectPopcorn");
		
		System.out.println("popcorn : " + popcorn);
		
		List<Menu> drink = sqlSession.selectList("menuMapper.selectDrink");
		
		System.out.println("drink : " + drink);
		
		return null;
	}

	
	
	
}
