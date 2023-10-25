package semi.project.movieInsight.movie.service;

import java.util.List;
import java.util.Map;

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




	
	
}
