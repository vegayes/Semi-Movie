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
	
	
	/** 회원 비밀번호 수정
	 * @param loginMember
	 * @return
	 */
	public int updatePw(Member loginMember) {
		// TODO Auto-generated method stub
		return 0;
	}



	

	
	
	
}
