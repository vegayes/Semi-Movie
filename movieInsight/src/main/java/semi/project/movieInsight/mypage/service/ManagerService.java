package semi.project.movieInsight.mypage.service;

import java.util.List;
import java.util.Map;

import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.movie.dto.Movie;

public interface ManagerService {
	
	/** 1) 모든 홍보정보 조회
	 * @return
	 */
	Map<String, Object> selectPromotion();

	/**2) 모든 회원정보 조회 
	 * @return
	 */
	Map<String, Object> selectMember();

	
	/** 모든 메뉴 조회
	 * @return
	 */
	Map<String, List<Menu>> selectMenu();

	int deleteCinema(int cinemaNo);
	
	
	
}
