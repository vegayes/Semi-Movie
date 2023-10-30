package semi.project.movieInsight.cinema.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.service.CinemaDetailService;
import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.movie.dto.Movie;


//@SessionAttributes({"loginMember"})

@Controller
@RequestMapping("/cinemaDetail")
public class CinemaDetailController {

	
	@Autowired
	private CinemaDetailService service;
	
	

	
	
	//영화관 정보 조회
	@GetMapping("/{cinemaName}")
	public String selectCinema(
			@PathVariable("cinemaName") String cinemaName,
		//	@SessionAttribute(value="loginMember",required=false) Member loginMember,
			HttpServletRequest req,
			HttpServletResponse resp,
			Model model
			){
		
			if(cinemaName.equals("insert")) {
				
				return "cinema/cinema-detail-page";
			}
		
			Cinema  cinemaInfo =  service.selectCinemaInfo(cinemaName);
		 	
		 	
		 	model.addAttribute("cinemaInfo", cinemaInfo);
		 	
		 	
		 	// 영화관에서 상영중인 영화 목록 조회
		 	List<Movie> movieList = service.selectMovieList(cinemaInfo.getCinemaNo());
		 	//System.out.println("movieList : " + movieList);
		
		 	model.addAttribute("movieList", movieList);
		 	
		 	
			// 해당 위치 넘기기
//			List<Object> currentUrl =  Arrays.asList(req.getRequestURI().toString().split("/"));
//			model.addAttribute("cureentUrl" + currentUrl.get(2));
		 	
			model.addAttribute("pageType","cinema");
			
			return "cinema/cinema-detail-page";
	}
	
	
	
	
	

	
	
	// 상영중인 영화 조회
	
	
	
	
	// 시설만족도,직원친절도,메뉴 추천 조회
	
	// 로그인 회원만 댓글 입력 가능하게
	
	// 댓글 평점 입력
	
	// 메뉴 추천 점수순으로 업데이트
	
	
	 
	
	
	
}
