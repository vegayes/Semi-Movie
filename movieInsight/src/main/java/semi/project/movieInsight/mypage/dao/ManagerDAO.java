package semi.project.movieInsight.mypage.dao;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.dto.Promotion;

@Repository
public class ManagerDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 1-1)특별관 전체 조회
	 * @return
	 */
	public List<Promotion> selectPromotionList() {
		return sqlSession.selectList("promotionMapper.selectPromotionList");
	}

	/** 1-2)홍보 전체 조회
	 * @return
	 */
	public List<Promotion> selectEventPromotionList() {
		return sqlSession.selectList("promotionMapper.selectEventPromotionList");
	}

	/**2-1) 회원정보 조회 
	 * @return
	 */
	public List<Member> selectMemberList() {
		return sqlSession.selectList("memberMapper.selectMemberList");
	}
	
	/**2-2) 회원 수 조회 
	 * @return
	 */
	public int selectMemberCount() {
		return sqlSession.selectOne("memberMapper.selectMemberCount");
	}

	
	

	/** 관리자 페이지에서 메뉴를 카테고리별로 조회해서 map 에 넣어서 반환
	 * @return
	 */
	public Map<String, List<Menu>> selectMenu() {
		
		 Map<String, List<Menu>> menuMap = new HashMap<String, List<Menu>>();
		
		List<Menu> popcorn = sqlSession.selectList("menuMapper.selectPopcorn");
		
		//System.out.println("popcorn : " + popcorn);
		
		List<Menu> drink = sqlSession.selectList("menuMapper.selectDrink");
		
		//System.out.println("drink : " + drink);
		
		
		List<Menu> snack = sqlSession.selectList("menuMapper.selectSnack");
		
		//System.out.println("snack : " + snack);
		
		menuMap.put("popcorn", popcorn);
		menuMap.put("drink", drink);
		menuMap.put("snack", snack);
		
		//System.out.println("menuMap : " + menuMap);
		
		return menuMap;
	}

	
	
	public int deleteCinema(int cinemaNo) {
		
		return sqlSession.update("cinemaMapper.deleteCinema", cinemaNo);
	}

	
	
	
	public int deleteMovie(int movieNo) {
		
		return sqlSession.update("movieMapper.deleteMovie", movieNo);
	}
	
	

	public int updateCinema(Cinema cinemaInfo) {
		
		return sqlSession.update("cinemaMapper.updateCinema", cinemaInfo);
	}
	

	
}
