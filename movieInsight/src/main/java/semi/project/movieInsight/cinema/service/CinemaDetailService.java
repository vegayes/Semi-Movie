package semi.project.movieInsight.cinema.service;

import java.util.List;

import semi.project.movieInsight.cinema.dto.Cinema;
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

}
