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


	
	/**관리자 페이지에서 영화관 정보 가져오기
	 * @return
	 */
	List<Cinema> selectManagerCinemaList();



	/** 평점 가져오기
	 * @param cinemaName
	 * @return
	 */
	float cinemaGrade(String cinemaName);


	/** 영화관 정렬 검색
	 * @param query
	 * @return
	 */
	List<Cinema> orderByCinemaList(String query);


}
