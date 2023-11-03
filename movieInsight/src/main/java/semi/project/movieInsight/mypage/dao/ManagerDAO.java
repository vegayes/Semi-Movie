package semi.project.movieInsight.mypage.dao;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Event;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.dto.Promotion;
import semi.project.movieInsight.member.service.MemberServiceimpl;
import semi.project.movieInsight.movie.dto.Movie;

@Repository
public class ManagerDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	private Logger logger = LoggerFactory.getLogger(ManagerDAO.class);
	
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
	
	
	

	/** 이미지를 넣었는지 유무에 따라 다르게 설정
	 * @param cinemaInfo
	 * @return
	 */
	public int updateCinema(Cinema cinemaInfo) {
		System.out.println("DAO에서 cinemaInfo : " + cinemaInfo);

		if(cinemaInfo.getCinemaImg().equals("")) {
			System.out.println("updateCinema 실행");
			return sqlSession.update("cinemaMapper.updateCinema", cinemaInfo);
		}else {
			System.out.println("updateCinemaImg 실행");
			return sqlSession.update("cinemaMapper.updateCinemaImg", cinemaInfo);
		}
		
	}


	
	/** 영화관 새로 등록 DAO
	 * @param cinemaInfo
	 * @return
	 */
	public int insertCinema(Cinema cinemaInfo) {
		
		return sqlSession.insert("cinemaMapper.insertCinema", cinemaInfo);
	}

	
	
	
	/** 영화 새로 등록 DAO :
	 * 1. 감독을 insert  2. 배우를 insert 3.영화no 가져움 4. 배우와 감독의 castingNo 가져움 5. MOVIE_CASTING 에 INSERT
	 * @param movieInfo
	 * @param directorNamesList 
	 * @param actorNamesList 
	 * @return
	 */
	public int insertMovie(Movie movieInfo, List<String> actorNamesList, List<String> directorNamesList) {
		
		int insertMovie = sqlSession.insert("movieMapper.insertMovie",movieInfo);
		List<String> castingList = new ArrayList<>(actorNamesList);
		castingList.addAll(directorNamesList);
		
		Map<String,Object> castingMap = new HashMap<String,Object>();
		castingMap.put("actorNamesList", actorNamesList);
		castingMap.put("directorNamesList", directorNamesList);
		
		logger.debug("결합된 리스트 : " + castingList);
		
		if(insertMovie > 0) {
			
			
			int insertDirectors = sqlSession.insert("movieMapper.insertDirectors", directorNamesList);
			
			logger.debug("insertDirectors insert :: " + insertDirectors);
			
			if(insertDirectors > 0) {
				
				logger.info("insertDirectors 성공");
				int insertActors = sqlSession.insert("movieMapper.insertActors", actorNamesList);
				
				if(insertActors > 0) {
					
					 logger.info("insertActors 성공");
					 int movieNo = selectMovieNo(movieInfo.getMovieTitle());
					 List<Integer> castingNo= selectCastingNo(castingList);
					 System.out.println("castingNo : " + castingNo);
					 
					 Map<String, Object> movieCastingMap = new HashMap<String, Object>();
					 movieCastingMap.put("movieNo", movieNo);
					 movieCastingMap.put("castingNo",castingNo);
					 int movieCasting  = sqlSession.insert("movieMapper.insertMovieCasting", movieCastingMap);
					 
					 if(movieCasting > 0) {
						 
						 logger.info("movieCasting 성공");
						 return movieCasting;
						 
					 }else {
						 return 0;
					 }
				}else{
					return 0; }
			}else 
				return 0;
		}else {
			return 0;
		}
		
	}
	
	
	public int updateMovie(Movie movieInfo) {
		
		return sqlSession.update("movieMapper.updateMovie",movieInfo);
	}
	
// 영화, 출연진 정보 입력
//  영화에 대한 입력값을 dB에 저장, 제대로 들어가면(result >0)
//	출연진 정보 : jsp : 가/나/다/라/마.. -> 컨트롤러 :  구분자로 리스트로 변환 [가,나,다]... 이걸 dB에 넣음
// movie_casting 테이블에 insert : movieNo, castingNo 얻어와야됨
	
	
   /** 영화관 새로 만들고 영화관 번호 얻어옴
	 * @param movieTitle
	 * @return
	 */
	public int selectMovieNo(String movieTitle) {
		
		return sqlSession.selectOne("movieMapper.selectMovieNo", movieTitle);
	}

	
	/** 출연castingNo
	 * @param castingList
	 * @return
	 */
	public List<Integer> selectCastingNo(List<String> castingList) {
		
		return sqlSession.selectList("movieMapper.selectCastingNo", castingList);
	}

	
	
	
	/** 특별관 추가
	 * @param promotionMap
	 * @return
	 */
	public int insertPromotion(Map<String, Object> promotionMap) {
		//System.out.println("insertPromotionDAO 에서 가져온 promotionMap : " + promotionMap);
		return sqlSession.insert("promotionMapper.insertPromotion", promotionMap);
	}

	
	
	
	
	/** 이벤트 추가 
	 * @param eventMap
	 * @return
	 */
	public int insertEvent(Map<String, Object> eventMap) {

		return sqlSession.insert("promotionMapper.insertEvent", eventMap);
	}

	
	
	public int deleteEvent(String eventTitle) {
		
		return sqlSession.delete("promotionMapper.deleteEvent", eventTitle);
	}

	
	
	public int deletePromotion(String promotionType) {
		
		return sqlSession.delete("promotionMapper.deletePromotion", promotionType);
	}

	
	
	/** 메뉴 추가
	 * @param menuMap
	 * @return
	 */
	public int insertMenu(Map<String, Object> menuMap) {
		int result = sqlSession.insert("menuMapper.insertMenu", menuMap);
		
		if(result > 0) {
			int menuNo = sqlSession.selectOne("menuMapper.selectMenuNo", menuMap);
			menuMap.put("menuNo", menuNo);
			int menuStatus = sqlSession.insert("menuMapper.insertMenuStatus", menuMap);
			if(menuStatus > 0) {
				return menuStatus;
			}else {
				System.out.println("menuStatus 필드에 입력 중 오류");
				return 0;
			}
		}
		System.out.println("insertMenu 중 오류");
		return 0;
	}

	public int deleteMenu(int menuNo) {
		
		int result = sqlSession.delete("menuMapper.deleteMenu", menuNo);
//		if(result > 0) {
//			return sqlSession.delete("menuMapper.")
//		}
		return result;
	}

	
	
	// 관리자 : 회원 페이지에서 댓글목록 조회
	public List<Movie> selectMovieComment(int memberNo) {
		
		return sqlSession.selectList("memberMapper.selectMovieComment", memberNo);
	}

	public List<Cinema> selectCinemaComment(int memberNo) {
		
		return sqlSession.selectList("memberMapper.selectCinemaComment", memberNo);
	}

	
	
	
	

	
}
