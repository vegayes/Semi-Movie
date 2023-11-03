package semi.project.movieInsight.cinema.service;

import java.util.List;
import java.util.Map;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.movie.dto.Movie;


public interface CinemaDetailService {

	
	Cinema selectCinemaInfo(String cinemaName);

	List<Movie> selectMovieList(int cinemaNo);

	/** 영화관 댓글 조회 
	 * @param cinemaName
	 * @return
	 */
	List<Cinema> commentCinemaList(String cinemaName);

	/** 영화관 댓글 삽입
	 * @param cinema
	 * @return
	 */
	int insert(Cinema cinema);

	/** 영화관에서 파는 메뉴 가져오기 (카테고리 별
	 * @param cinemaName
	 * @return
	 */
	List<Menu> getMenuList(String cinemaName);

	/** 영화관 댓글 삭제 
	 * @param cinemaCommentNo
	 * @return
	 */
	int delete(int cinemaCommentNo);

	/** 즐겨찾기 체크 
	 * @param favoriteCheck
	 * @return
	 */
	int favoriteCheck(Map<String, Object> favoriteCheck);

	/** 즐겨찾기 수정
	 * @param paramMap
	 * @return
	 */
	int updatefavorite(Map<String, Object> paramMap);

	/** 베스트 메뉴 가져오기
	 * @return
	 */
	Map<String, Menu> getBestMenu(String cinemaName);

	/** 메뉴 평점 삽입 
	 * @param menu
	 * @return
	 */
	int insertMenuGrade(Menu menu);


}
