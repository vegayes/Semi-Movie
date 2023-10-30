package semi.project.movieInsight.movie.service;

import java.util.List;
import java.util.Map;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.movie.dto.Movie;

public interface MovieDetailService {

	/** 1) 영화 정보 가져오기
	 * @param movieNo
	 * @return
	 */
	Movie selectMovie(int movieNo);
	
	/** 2) 배우 정보 가져오기
	 * @param movieNo
	 * @return
	 */
	List<Map<String, Object>> actorInfoList(int movieNo);

	/** 3) 감독 정보 가져오기
	 * @param movieNo
	 * @return
	 */
	List<Map<String, Object>> directorInfoList(int movieNo);

	/** 4) 해당 영화를 상영하는 영화관 및 매체 List
	 * @param movieNo
	 * @return
	 */
	List<Cinema> selectCinemaList(int movieNo);

	/** 5) 해당 영화 장르와 비슷한 영화 
	 * @param genreList
	 * @return
	 */
//	List<Movie> recommendMovie(List<String> genreList);
	List<Movie> recommendMovie(Map<String, Object> genreMap);

	/** 6) 영화에 대한 댓글 조회 
	 * @param movieNo
	 * @return
	 */
	List<Movie> commentMovieList(int movieNo);

	
	
	
//	================================ 댓글 ===============================================
	

	/** 1) 댓글 삽입 
	 * @param movie
	 * @return
	 */
	int insert(Movie movie);

	/** 2) 댓글 삭제
	 * @param movieCommentNo
	 * @return
	 */
	int delete(int movieCommentNo);

	/** 즐겨찾기
	 * @param paramMap
	 * @return
	 */
	int updatefavorite(Map<String, Integer> paramMap);

	/** 즐겨찾기 조회 
	 * @param favoriteCheck
	 * @return
	 */
	int favoriteCheck(Map<String, Object> favoriteCheck);


	

	
	
}
