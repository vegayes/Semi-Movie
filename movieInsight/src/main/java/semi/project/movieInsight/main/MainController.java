package semi.project.movieInsight.main;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.movie.service.MovieService;


@Controller
public class MainController {
	
	  @Autowired
	    private MovieService movieService; // 영화 정보를 가져오는 서비스를 주입	
	
	@RequestMapping("/")
	public String test1() {


		return "redirect:movie";
		//return "/member/login_signUp";
	}

	
	@GetMapping("/movie")
	public String movieMain(Model model) {
		
		System.out.println("영화 메인페이지 이동");
		
        List<Movie> movies = movieService.getMovies(); // 영화 정보를 가져오는 로직을 호출
        model.addAttribute("movieList", movies); // 모델에 영화 리스트를 추가
		

		model.addAttribute("pageType","movie");
		
		return "movie/home-page";
	}




	@GetMapping("/cinema")
	public String cinemaMain(Model model) {
		
		System.out.println("영화관 메인페이지 이동");
		
		 model.addAttribute("pageType", "cinema");
		
		return "cinema/cinema-homepage";
	}
	



	
	
}


