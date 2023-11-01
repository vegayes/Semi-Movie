package semi.project.movieInsight.cinema.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.movie.dto.Movie;

@Repository
public class CinemaDetailDAO {

	
	
	@Autowired
	private SqlSessionTemplate sqlSession;

//	public Cinema selectCinema(int cinemaCode) {
//		// TODO Auto-generated method stub
//		return sqlSession.selectList("Mapper.");
//	}
	
	
	
	public Cinema selectCinemaInfo(String cinemaName) {
		
		return sqlSession.selectOne("cinemaMapper.selectCinemaInfo",cinemaName);
	}

	
	public List<Movie> selectMovieList(int cinemaNo) {
		
		return sqlSession.selectList("cinemaMapper.selectMovieList", cinemaNo);
	}


	/** 영화관 댓글 조회 
	 * @param cinemaName
	 * @return
	 */
	public List<Cinema> commentCinemaList(String cinemaName) {
		return sqlSession.selectList("cinemaMapper.commentCinemaList", cinemaName);
	}


	/** 영화관 댓글 삽입
	 * @param cinema
	 * @return
	 */
	public int commentInsert(Cinema cinema) {
		return sqlSession.insert("cinemaMapper.commentInsert", cinema);
	}
	

}
