package semi.project.movieInsight.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.project.movieInsight.cinema.dao.CinemaDAO;
import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.service.CinemaService;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.movie.service.MovieService;
import semi.project.movieInsight.mypage.service.ManagerService;
import semi.project.movieInsight.mypage.service.MypageService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	// **** 관리자 페이지에서 조회를 담당하는 컨트롤러 **********//
	
	@Autowired
	private ManagerService service;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CinemaDAO cinemaDAO;
	
	/** 1) 모든 홍보정보 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/promotion")
	public String movePromotion(Model model) {
		// 1) Map으로 가져오기
		Map<String,Object> promotionMap = service.selectPromotion();
		
		List<Cinema> cinemaList = cinemaDAO.selectManagerCinemaList();
		
		//System.out.println("promotionMap : " + promotionMap);
		//System.out.println("cinemaList : " + cinemaList);
		model.addAttribute("cinemaList", cinemaList);
		model.addAttribute("promotionMap", promotionMap);
		
		return "manager/manager-event";
	}
	
	/** 2) 모든 회원정보 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/member")
	public String moveMember(Model model) {
		// 1) Map으로 가져오기
		Map<String,Object> memberMap = service.selectMember();
		
		model.addAttribute("memberMap", memberMap);
	
		return "/manager/manager-member";

	}
	
	
	/** 관리자 페이지의 메뉴 관리 페이지에서 전체 메뉴 조회 
	 * @return
	 */
	@GetMapping("/menu") 
	public String managerPageMenu(Model model) {
		Map<String,List<Menu>> selectMenu = service.selectMenu();
		
		model.addAttribute("popcorn", selectMenu.get("popcorn"));
		model.addAttribute("drink", selectMenu.get("drink"));
		model.addAttribute("snack", selectMenu.get("snack"));
		
		return "manager/manager-menu";
	}
	
	
	/** 관리자 페이지에서 영화목록 전체 조회시 사용
	 * @param model
	 * @return
	 */
	@GetMapping("/movie")
	public String managerPageMovie(Model model) {
		
		List<Movie> movieList = movieService.selectManagerMovieList();
		
		model.addAttribute("movieList", movieList);
		
		//System.out.println("movieList : " + movieList);
		
		return "manager/manager-movie";
		
	}
	
	
	
	/**관리자 페이지에서 영화관 전체조회
	 * @param model
	 * @return
	 */
	@GetMapping("/cinema")
	public String moveCinema(Model model) {
		
		
		// 1) Map으로 가져오기 : 영화관 정보 가져오기
		List<Cinema> cinemaList = cinemaService.selectManagerCinemaList();
		
		//System.out.println("cinemaList : " + cinemaList);
		model.addAttribute("cinemaList",cinemaList);
		
		return "manager/manager-cinema";
	}
	
}
