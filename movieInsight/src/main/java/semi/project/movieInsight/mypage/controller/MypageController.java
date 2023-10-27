package semi.project.movieInsight.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.service.CinemaService;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.mypage.service.MypageService;
import semi.project.movieInsight.movie.service.MovieService;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Autowired
	MypageService service;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	CinemaService cinemaService;
	
	// 예시
	
	
	/** 즐겨찾기 페이지 조회 
	 * @return
	 */
	@GetMapping("/member")
	public String mypageMove(Model model) {
		
		// 1) 영화 즐겨찾기 목록 조회 
		List<Movie> selectLikeMovie = movieService.selectLikeMovie();
		
		model.addAttribute("movieList", selectLikeMovie);
		
		// 2) 영화관 즐겨찾기 목록 조회 
		List<Cinema> selectLikeCinema = cinemaService.selectLikeCinema();
		
		return "mypage/mypage";
	}
	
	
	
	
	
	
	
		
	

}
