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
	 * @param genreList
	 * @return
	 */
//	public List<Movie> recommendMovie(List<String> list) {
//		
//		System.out.println("DAO에서의 list" + list);
//		
//		return sqlSession.selectList("movieMapper.recommendMovie", list);
//	}


	public List<Movie> recommendMovie(Map<String, Object> genreMap) {
		
		//System.out.println("DAO에서의 Map  : " + genreMap);
		
		return sqlSession.selectList("movieMapper.recommendMovie", genreMap);
	}


	public List<Movie> commentMovieList(int movieNo) {
		return sqlSession.selectList("movieMapper.commentMovieList", movieNo);
	}


	/** 댓글 삽입
	 * @param movie
	 * @return
	 */
	public int commentInsert(Movie movie) {
		return sqlSession.insert("movieMapper.commentInsert", movie	);
	}


	/** 댓글 삭제
	 * @param movieCommentNo
	 * @return
	 */
	public int commentDelete(int movieCommentNo) {

		return sqlSession.update("movieMapper.commentDelete", movieCommentNo);
	}





	/** 즐겨찾기 추가
	 * @param paramMap
	 * @return
	 */
	public int addFavorite(Map<String, Integer> paramMap) {
		return sqlSession.insert("movieMapper.addFavorite", paramMap);
	}


	/** 즐겨찾기 제거 
	 * @param paramMap
	 * @return
	 */
	public int delFavorite(Map<String, Integer> paramMap) {
		return sqlSession.delete("movieMapper.delFavorite", paramMap);
	}


	/** 즐겨찾기 조회 
	 * @param favoriteCheck
	 * @return
	 */
	public int favoriteCheck(Map<String, Object> favoriteCheck) {
		return sqlSession.selectOne("movieMapper.favoriteCheck", favoriteCheck);
	}


	/*영화에 대한 총 평점*/
	public float sumMovieGrade(int movieNo) {
		
		Float sum = sqlSession.selectOne("movieMapper.sumMovieGrade", movieNo);
		System.out.println("평점 : " + sum);
		if (sum == null) {
		    sum = 0.0f;
		}
		return sum;
	}



	
	
	
	
	
	
	
	
	
	
}
