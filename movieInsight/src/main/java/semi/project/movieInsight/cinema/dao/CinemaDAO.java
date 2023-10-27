package semi.project.movieInsight.cinema.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;

@Repository
public class CinemaDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	
	
	/** 영화관 검색결과
	 * @param cinemaQuery
	 * @return
	 */
	public List<Cinema> searchCinemaList(String cinemaQuery) {	
		return sqlSession.selectList("cinemaMapper.searchCinemaList", cinemaQuery);
	}

	
	
	/** 관리자 페이지에서 영화관 조회
	 * @return
	 */
	public List<Cinema> selectManagerCinemaList() {
		
		return sqlSession.selectList("cinemaMapper.selectManagerCinemaList");
	}

}
