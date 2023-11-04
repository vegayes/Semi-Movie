package semi.project.movieInsight.movie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.common.utility.Util;
import semi.project.movieInsight.movie.dao.MovieDetailDAO;
import semi.project.movieInsight.movie.dto.Movie;

@Service
public class MovieDetailServiceImpl implements MovieDetailService{

	@Autowired
	private MovieDetailDAO dao;

	/**
	 * 1) 클릭으로 가져온 영화정보
		<%--${boardCode} : @Pathvariable로 request scope에 추가된 값--%>
        <a href="/board/${boardCode}/${board.boardNo}?cp=${pagination.currentPage}">${board.boardTitle}</a>   
        [${board.commentCount}]               
	 */
	@Override
	public Movie selectMovie(int movieNo) {
		
		// 1) 일단 영화에 대한 정보 가져오기
		Movie movieInfo = dao.selectMovie(movieNo);
		
		// 2) 평점 가져오기 
		float sumMovieGrade = dao.sumMovieGrade(movieNo);
		
		System.out.println("총 평점 : " + sumMovieGrade);
		
		movieInfo.setSumMovieGrade(sumMovieGrade);
		
		return movieInfo;
	}

	/**
	 * 2) 해당 영화의 배우 정보 가져오기
	 */
	@Override
	public List<Map<String, Object>> actorInfoList(int movieNo) {
		return dao.actorInfoList(movieNo);
	}

	/**
	 * 3) 해당 영화의 감독 정보 가져오기
	 */
	@Override
	public List<Map<String, Object>> directorInfoList(int movieNo) {
		return dao.directorInfoList(movieNo);
	}

	/**
	 * 4) 해당 영화가 상영하는 정보 리스르토 가져오기
	 */
	@Override
	public List<Cinema> selectCinemaList(int movieNo) {
		return dao.selectCinemaList(movieNo);
	}



	
	
	/**
	 * 5) 해당 영화와 비슷한 (장르기준) 추천
	 */
//	@Override
//	public List<Movie> recommendMovie(List<String> genreList) {
//		return dao.recommendMovie(genreList);
//	}
	@Override
	public List<Movie> recommendMovie(Map<String, Object> genreMap) {
		return dao.recommendMovie(genreMap);
	}

	
	
	
	/**
	 * 영화에 대한 댓글 조회 
	 */
	@Override
	public List<Movie> commentMovieList(int movieNo) {
		return dao.commentMovieList(movieNo);
	}
	
//	================================================================= 댓글 ========================================

	/**
	 * 댓글 삽입 
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insert(Movie movie) {
		
		// 0. XSS 방지 처리 
		movie.setMovieCommentContent(Util.XSSHandling(movie.getMovieCommentContent()));
		
		return dao.commentInsert(movie);
	}

	/**
	 * 댓글 삭제
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int delete(int movieCommentNo) {
		return dao.commentDelete(movieCommentNo);
	}

	/**
	 * 즐겨찾기 체크
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatefavorite(Map<String, Integer> paramMap) {
		
		int check = (int)paramMap.get("check"); 
		
		System.out.println("check :" + check);
		
		int result = 0;
		
		if(check == 0) {
			result = dao.addFavorite(paramMap);

		}else {
			result = dao.delFavorite(paramMap);
		}
		
		if(result == 0) return -1;
		
		
		return result;
	}

	/**
	 * 즐겨찾기 조회 
 	 */
	@Override
	public int favoriteCheck(Map<String, Object> favoriteCheck) {
		return dao.favoriteCheck(favoriteCheck);
	}

	/**
	 * 방문기록 남기기 
	 */
	@Override
	public int visitMovie(int memberNo, int movieNo) {
		
		Map<String,Object> visitInfo = new HashMap<String, Object>();
		
		visitInfo.put("memberNo", memberNo);
		visitInfo.put("movieNo", movieNo);
		
		return dao.visitMovie(visitInfo);
	}
	
	
	
	
	
}
