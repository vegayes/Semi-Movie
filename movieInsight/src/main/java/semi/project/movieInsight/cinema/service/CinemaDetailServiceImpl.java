package semi.project.movieInsight.cinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import semi.project.movieInsight.cinema.dao.CinemaDetailDAO;
import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.common.utility.Util;
import semi.project.movieInsight.movie.dto.Movie;

@Service
public class CinemaDetailServiceImpl implements CinemaDetailService {

	@Autowired
	private CinemaDetailDAO dao;
	
	

	@Override
	public Cinema selectCinemaInfo(String cinemaName) {
		
		return dao.selectCinemaInfo(cinemaName);
	}



	@Override
	public List<Movie> selectMovieList(int cinemaNo) {
		
		return dao.selectMovieList(cinemaNo);
	}



	/**
	 * 영화관 댓글 조회 ㄴ
	 */
	@Override
	public List<Cinema> commentCinemaList(String cinemaName) {
		return dao.commentCinemaList(cinemaName);
	}



	/**
	 * 영화관 댓글 삽입
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insert(Cinema cinema) {
		
		// 0. XSS 방지 처리 
		cinema.setCinemaCommentContent(Util.XSSHandling(cinema.getCinemaCommentContent()));
		
		return dao.commentInsert(cinema);
	}



	/**
	 * 영화관 메뉴 가져오기 ( 카테고리에 넣기 위해서 ) 
	 */
	@Override
	public List<Menu> getMenuList(String cinemaName) {
		return dao.getMenuList(cinemaName);
	}



	/**
	 * 영화관 댓글 삭제 
	 */
	@Override
	public int delete(int cinemaCommentNo) {
		return dao.delete(cinemaCommentNo);
	}




}
