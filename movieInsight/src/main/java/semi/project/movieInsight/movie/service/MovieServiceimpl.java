package semi.project.movieInsight.movie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import semi.project.movieInsight.movie.dao.MovieDAO;
import semi.project.movieInsight.movie.dto.Movie;

@Service
public class MovieServiceimpl implements MovieService{

	@Autowired
	private MovieDAO dao;
	
	/**
	 * 1) 영화 검색 목록 조회 ( 영화제목, 장르?,  출연진 부분 일치하면 가져오기 ) 
	 */
	@Override
	public List<Movie> searchMovieList(String movieQuery) {
	
		return dao.searchMovieList(movieQuery);
	}

	
	
	/**
	 * 관리자 페이지에서 영화목록 전체 조회시 사용
	 */
	@Override
	public List<Movie> selectManagerMovieList() {
		
		
		return dao.selectManagerMovieList();
	}



	@Override
	public List<Movie> getMovies() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Movie> findMoviesByCategory(String category) {
		return dao.findMoviesByCategory(category);
	}



	@Override
	public Movie findMovieById(Long movieNo) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Movie> findAllMovies() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Movie selectMovie(int movieNo) {
		// TODO Auto-generated method stub
		return null;
	}
 
	

}
