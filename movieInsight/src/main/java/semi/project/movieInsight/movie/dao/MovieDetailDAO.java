package semi.project.movieInsight.movie.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.movie.dto.Movie;

@Repository
public class MovieDetailDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;


	/** 1) 영화 정보 가져오기
	 * @param movieNo
	 * @return
	 */
	public Movie selectMovie(int movieNo) {
		return sqlSession.selectOne("movieMapper.selectMovie", movieNo);
	}


	/** 2) 영화 배우 정보 가져오기
	 * @param movieNo
	 * @return
	 */
	public List<Map<String, Object>> actorInfoList(int movieNo) {
		return sqlSession.selectList("movieMapper.actorInfoList", movieNo);
	}


	/** 3) 영화 감독 정보 가져오기
	 * @param movieNo
	 * @return
	 */
	public List<Map<String, Object>> directorInfoList(int movieNo) {
		return sqlSession.selectList("movieMapper.directorInfoList", movieNo);
	}


	/** 4) 해당 영화가 상영하는 상영관 리스트 가져오기
	 * @param movieNo
	 * @return
	 */
	public List<Cinema> selectCinemaList(int movieNo) {
		return sqlSession.selectList("movieMapper.selectCinemaList", movieNo);
	}


	/** 5) 해당 영화와 비슷한 (장르기준) 추천
	 * @param movieGenre
	 * @return
	 */
	public List<Movie> recommendMovie(String movieGenre) {
		return sqlSession.selectList("movieMapper.recommendMovie", movieGenre);
	}

	
	
	
	
	
	
	
	
	
	
}
