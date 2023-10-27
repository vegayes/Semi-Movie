package semi.project.movieInsight.cinema.service;

import java.util.List;

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


}
