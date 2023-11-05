package semi.project.movieInsight.mypage.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.movie.dto.Movie;

public interface MypageService {

	
//	 1) 즐겨찾기
	
	/** 1-1) 영화 즐겨찾기 조회
	 * @param memberNo
	 * @return
	 */
	List<Movie> selectLikeMovie(int memberNo);

	/** 1-2) 영화관 즐겨찾기 조회
	 * @param memberNo
	 * @return
	 */
	List<Cinema> selectLikeCinema(int memberNo);

	
// 3) 댓글 조회 
	/** 3-1) 영화 댓글 조회 
	 * @param memberNo
	 * @return
	 */
	List<Movie> selectCommentMovie(int memberNo);

	/** 3-2) 영화관 댓글 조회 
	 * @param memberNo
	 * @return
	 */
	List<Cinema> selectCommentCinema(int memberNo);	
	
	/** 3-3) 팝업에 내용 조회 하기 ( 영화 )
	 * @param commentNo
	 * @return
	 */
	Movie selectMovieComment(int commentNo);
	
// 4) 정보 수정
	
	/** 4-1) 닉네임 확인
	 * @param nickname
	 * @return
	 */
	int checkNickname(String nickname);
	
	
	/** 4-2) 프로필 이미지 수정
	 * @param profileImage
	 * @param webPath
	 * @param filePath
	 * @param loginMember
	 * @return
	 */
	int updateProfile(MultipartFile profileImage, String webPath, String filePath, Member loginMember) throws IllegalStateException, IOException;



	/** 4-3) 회원정보 수정
	 * @param updateMember
	 * @return
	 */
	int updateInfo(Member updateMember);

	/** 3-4) 팝업에 내용 수정하기 ( 영화 ) 
	 * @param movie
	 * @return
	 */
	int updateMovieComment(Movie movie);

	/** 3-5) 팝업에 내용 조회하기 ( 영화관) 
	 * @param cinemaCommentNo
	 * @return
	 */
	Cinema selectCinemaComment(int cinemaCommentNo);

	/** 3-6) 팝업에 내용 수정하기 ( 영화관 ) 
	 * @param cinema
	 * @return
	 */
	int updateCinemaComment(Cinema cinema);

	/** 즐겨찾기 선택 된 값 삭제하기 ( 영화 ) 
	 * @param favoriteDelMovie
	 * @return
	 */
	int delFavoriteMovie(Map<String, Object> favoriteDelMovie);

	/** 탈퇴 
	 * @param memberNo
	 * @return
	 */
	int secession(int memberNo);

	/** 방문기록
	 * @param memberNo
	 * @return
	 */
	List<Movie> visitMovie(int memberNo);

	/** 방문기록 삭제
	 * @param visitNo
	 * @return
	 */
	int delVisit(int visitNo);

	/** 댓글 삭제 
	 * @param commentDelMovie
	 * @return
	 */
	int delCommentMovie(Map<String, Object> commentDelMovie);


	/** 즐겨찾기 삭제 ( 영화관) 
	 * @param favoriteDelCinema
	 * @return
	 */
	int delFavoriteCinema(Map<String, Object> favoriteDelCinema);

	/**댓글 삭제 (영화관)
	 * @param commentDelCinema
	 * @return
	 */
	int delCommentCinema(Map<String, Object> commentDelCinema);



	
	// 댓글 삭제
	
	// 방묹기록 조회
	
	// 방문기록 삭제 
	
	

	

}
