package semi.project.movieInsight.movie.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
