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

	List<Movie> findMoviesByCategory(String category);

	Movie findMovieById(Long movieNo);

	List<Movie> findAllMovies();

	Movie selectMovie(int movieNo);

	/** header의 인기순 누르면 검색 창 페이지로 넘어감.
	 * @param query
	 * @return
	 */
	List<Movie> orderByMovieList(String query);

	


}
