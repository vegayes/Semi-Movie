package semi.project.movieInsight.cinema.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Event;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.dto.Promotion;
import semi.project.movieInsight.movie.dto.Movie;

@Repository
public class CinemaDetailDAO {

	
	
	@Autowired
	private SqlSessionTemplate sqlSession;

//	public Cinema selectCinema(int cinemaCode) {
//		// TODO Auto-generated method stub
//		return sqlSession.selectList("Mapper.");
//	}
	
	
	
	public Cinema selectCinemaInfo(String cinemaName) {
		
		return sqlSession.selectOne("cinemaMapper.selectCinemaInfo",cinemaName);
	}

	
	public List<Movie> selectMovieList(int cinemaNo) {
		
		return sqlSession.selectList("cinemaMapper.selectMovieList", cinemaNo);
	}


	/** 영화관 댓글 조회 
	 * @param cinemaName
	 * @return
	 */
	public List<Cinema> commentCinemaList(String cinemaName) {
		return sqlSession.selectList("cinemaMapper.commentCinemaList", cinemaName);
	}


	/** 영화관 댓글 삽입
	 * @param cinema
	 * @return
	 */
	public int commentInsert(Cinema cinema) {
		return sqlSession.insert("cinemaMapper.commentInsert", cinema);
	}


	/** 영화관 메뉴 카테고리 조회 
	 * @param cinemaName
	 * @return
	 */
	public List<Menu> getMenuList(String cinemaName) {
		return sqlSession.selectList("menuMapper.getMenuList", cinemaName);
	}


	/** 영화 댓글 삭제 
	 * @param cinemaCommentNo
	 * @return
	 */
	public int delete(int cinemaCommentNo) {
		return sqlSession.update("cinemaMapper.commentDelete", cinemaCommentNo);
	}


	/** 즐겨찾기 조회 
	 * @param favoriteCheck
	 * @return
	 */
	public int favoriteCheck(Map<String, Object> favoriteCheck) {
		return sqlSession.selectOne("cinemaMapper.favoriteCheck", favoriteCheck);
	}


	/** 즐겨찾기 삽입
	 * @param paramMap
	 * @return
	 */
	public int addFavorite(Map<String, Object> paramMap) {
		return sqlSession.insert("cinemaMapper.addFavorite", paramMap);
	}


	/** 즐겨찾기 삭제
	 * @param paramMap
	 * @return
	 */
	public int delFavorite(Map<String, Object> paramMap) {
		return sqlSession.delete("cinemaMapper.delFavorite", paramMap);
	}


	/** 베스트 메뉴 뽑기
	 * @param cinemaName
	 * @return
	 */
	public Map<String, Menu> getBestMenu(String cinemaName) {
		
		Map<String,Menu> bestMenuMap = new HashMap<String,Menu>();
		
		Menu bestPopCorn = sqlSession.selectOne("menuMapper.getBestPopCornMenu", cinemaName);
		Menu bestDrink = sqlSession.selectOne("menuMapper.getBestDrinkMenu", cinemaName);
		Menu bestSnack = sqlSession.selectOne("menuMapper.getBestSnackMenu", cinemaName);
		
		System.out.println(bestPopCorn);
		System.out.println(bestDrink);
		System.out.println(bestSnack);
		
		bestMenuMap.put("bestPopCorn", bestPopCorn);
		bestMenuMap.put("bestDrink", bestDrink);
		bestMenuMap.put("bestSnack", bestSnack);
		
		
		return bestMenuMap;
	}


	/** 메뉴 평점 삽입
	 * @param menu
	 * @return
	 */
	public int insertMenuGrade(Menu menu) {
		
		System.out.println(menu.getMenuName());
		System.out.println(menu.getCinemaName());
		System.out.println(menu.getMemberNo());
		System.out.println(menu.getMenuGrade());
		
		return sqlSession.insert("menuMapper.insertMenuGrade", menu);
	}


	/** 영화관 평점
	 * @param cinemaName
	 * @return
	 */
	public float sumCinemaGrade(String cinemaName) {
		

//		Cinema sumcinema = sqlSession.selectOne("cinemaMapper.sumCinemaGrade",cinemaName);
		
		Float sumCinemaGrade = sqlSession.selectOne("cinemaMapper.sumCinemaGrade", cinemaName);

		float sum = (sumCinemaGrade != null) ? sumCinemaGrade : 0.0f;

	   System.out.println(sum);
	

		return sum;
	}


	/** 영화관 정렬 
	 * @param query
	 * @return
	 */
	public List<Cinema> orderByCinemaList(String query) {
		
		List<Cinema> cinemaList = new ArrayList<Cinema>();
		
		if(query.equals("극장 평점순")) {
			cinemaList = sqlSession.selectList("cinemaMapper.orderByCinema");
			System.out.println("극장");
		}else if (query.equals("직원 친절도순")) {
			cinemaList = sqlSession.selectList("cinemaMapper.orderByGood");
			System.out.println("직원");
		}else if(query.equals("메뉴 만족도순")) {
			cinemaList = sqlSession.selectList("cinemaMapper.orderByMenu");
		}
		
		
		return cinemaList;
	}


	/**  영화관 댓글 수정
	 * @param cinema
	 * @return
	 */
	public int updateCommentCinema(Cinema cinema) {
		return sqlSession.update("mypageMapper.updateCinemaComment", cinema);
	}


	
	
	public Promotion getPromotionInfo() {
		
		return sqlSession.selectOne("promotionMapper.getPromotionInfo");
	}


	public Event getEventInfo() {
		
		return sqlSession.selectOne("promotionMapper.getEventInfo");
	}
	

}
