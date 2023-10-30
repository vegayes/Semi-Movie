package semi.project.movieInsight.cinema.service;

import java.util.List;
import java.util.Map;

import semi.project.movieInsight.cinema.dto.Cinema;

public interface CinemaService {

	/** 영화관 검색 리스트
	 * @param cinemaQuery
	 * @return
	 */
	List<Cinema> searchCinemaList(String cinemaQuery);

	/** 즐겨찾기 영화관 
	 * @return
	 */
	List<Cinema> selectLikeCinema();

	
	
	/**관리자 페이지에서 영화관 정보 가져오기
	 * @return
	 */
	List<Cinema> selectManagerCinemaList();


}
