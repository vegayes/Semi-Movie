package semi.project.movieInsight.cinema.service;

import java.util.List;
import java.util.Map;

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
		
		// 1) 영화관에 대한 정보 가져오기
		Cinema cinemaInfo =  dao.selectCinemaInfo(cinemaName);
		
		float sumCinemaGrade = dao.sumCinemaGrade(cinemaName);
		
		System.out.println("총 평점 : " + sumCinemaGrade);
		
		cinemaInfo.setSumCinemaGrade(sumCinemaGrade);
		
		return cinemaInfo;
	}



	@Override
	public List<Movie> selectMovieList(int cinemaNo) {
		
		return dao.selectMovieList(cinemaNo);
	}



	/**
	 * 영화관 댓글 조회 
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



	/**
	 * 즐겨찾기 여부 확인
	 */
	@Override
	public int favoriteCheck(Map<String, Object> favoriteCheck) {
		return dao.favoriteCheck(favoriteCheck);
	}


	/**
	 * 즐겨찾기 추가 및 삭제
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatefavorite(Map<String, Object> paramMap) {
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
	 * 베스트 메뉴 가져오기
	 */
	@Override
	public Map<String, Menu> getBestMenu(String cinemaName) {
		return dao.getBestMenu(cinemaName);
	}



	/**
	 * 메뉴 평점 삽입
	 */
	@Override
	public int insertMenuGrade(Menu menu) {
		return dao.insertMenuGrade(menu);
	}







}
