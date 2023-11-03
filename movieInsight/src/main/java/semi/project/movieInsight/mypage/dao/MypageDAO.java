package semi.project.movieInsight.mypage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.movie.dto.Movie;

@Repository
public class MypageDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 영화 즐겨찾기 
	 * @param memberNo
	 * @return
	 */
	public List<Movie> selectLikeMovie(int memberNo) {
		return sqlSession.selectList("mypageMapper.selectLikeMovie",memberNo);
	}

	/** 영화관 즐겨찾기
	 * @param memberNo
	 * @return
	 */
	public List<Cinema> selectLikeCinema(int memberNo) {
		return sqlSession.selectList("mypageMapper.selectLikeCinema",memberNo);
	}

	/** 영화 댓글 조회 
	 * @param memberNo
	 * @return
	 */
	public List<Movie> selectCommentMovie(int memberNo) {
		return sqlSession.selectList("mypageMapper.selectCommentMovie",memberNo);
	}

	
	/** 영화관 댓글 조회 
	 * @param memberNo
	 * @return
	 */
	public List<Cinema> selectCommentCinema(int memberNo) {
		return sqlSession.selectList("mypageMapper.selectCommentCinema",memberNo);
	}	
	
	
	// 회원정보 수정

	/** 닉네임 체크
	 * @param nickname
	 * @return
	 */
	public int checkNickname(String nickname) {
		return sqlSession.selectOne("mypageMapper.checkNickname",nickname);
	}

	/** 이미지 변경
	 * @param loginMember
	 * @return
	 */
	public int updateProfileImage(Member loginMember) {
		return sqlSession.update("mypageMapper.updateProfileImage", loginMember);
	}
	
	


	/**회원 정보 수정
	 * @param updateMember
	 * @return
	 */
	public int updateInfo(Member updateMember) {
		return sqlSession.update("mypageMapper.updateInfo", updateMember);
	}

	/** 영화 댓글 팝업 조회 
	 * @param commentNo
	 * @return
	 */
	public Movie selectMovieComment(int commentNo) {
		return sqlSession.selectOne("mypageMapper.selectMovieComment", commentNo);
	}

	/** 영화 댓글 수정 
	 * @param movie
	 * @return
	 */
	public int updateMovieComment(Movie movie) {
		return sqlSession.update("mypageMapper.updateMovieComment", movie);
	}

	/** 영화관 댓글 팝업 조회 
	 * @param cinemaCommentNo
	 * @return
	 */
	public Cinema selectCinemaComment(int cinemaCommentNo) {
		return sqlSession.selectOne("mypageMapper.selectCinemaComment", cinemaCommentNo);
	}

	/** 영화관 댓글 팝업 수정 
	 * @param cinema
	 * @return
	 */
	public int updateCinemaComment(Cinema cinema) {
		return sqlSession.update("mypageMapper.updateCinemaComment", cinema);
	}

	/** 즐겨찾기 영화 삭제 
	 * @param favoriteDelMovie
	 * @return
	 */
	public int delFavoriteMovie(Map<String, Object> favoriteDelMovie) {
		return sqlSession.delete("mypageMapper.delFavoriteMovie", favoriteDelMovie);
	}



	

	
	
	
}
