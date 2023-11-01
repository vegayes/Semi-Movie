package semi.project.movieInsight.movie.service;

import java.util.List;
import java.util.Map;

import semi.project.movieInsight.movie.dto.Movie;

public interface MovieService {

	/** 1) 영화 검색 결과 조회
	 * @param movieQuery
	 * @return
	 */
	List<Movie> searchMovieList(String movieQuery);

	List<Movie> selectManagerMovieList();

	List<Movie> getMovies();



}
