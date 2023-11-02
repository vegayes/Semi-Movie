package semi.project.movieInsight.cinema.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
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


	/** 영화관 메뉴 카테고리 조회 
	 * @param cinemaName
	 * @return
	 */
	public List<Menu> getMenuList(String cinemaName) {
		return sqlSession.selectList("menuMapper.getMenuList", cinemaName);
	}


	/** 영화 댓글 삭제 
	 * @param cinemaCommentNo
	 * @return
	 */
	public int delete(int cinemaCommentNo) {
		return sqlSession.update("cinemaMapper.commentDelete", cinemaCommentNo);
	}
	

}
