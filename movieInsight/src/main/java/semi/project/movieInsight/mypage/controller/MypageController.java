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
	
	
	@GetMapping("/manager/movie")
	public String managerPageMovie(Model model) {
		
		
		// 관리자 페이지에서 영화목록 전체 조회시 사용
		List<Movie> movieList = movieService.selectManagerMovieList();
		
		model.addAttribute("movieList", movieList);
		
		
		
		return "manager/manager-movie";
		
	}
	
	
	
	/** 관리자 페이지의 메뉴 관리 페이지에서 전체 메뉴 조회 
	 * @return
	 */
	@GetMapping("/manager/menu") 
	public String managerPageMenu(Model model) {
		Map<String,List<Menu>> selectMenu = service.selectMenu();
		
		model.addAttribute("popcorn", selectMenu.get("popcorn"));
		model.addAttribute("drink", selectMenu.get("drink"));
		model.addAttribute("snack", selectMenu.get("snack"));
		
		return "manager/manager-menu";
	}
		
	

}
