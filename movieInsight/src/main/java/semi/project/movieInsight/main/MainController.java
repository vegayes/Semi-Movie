package semi.project.movieInsight.main;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	    public String getMovies(Model model) {
	        // 다양한 장르의 영화를 조회합니다.
	        List<Movie> popularMovies = movieService.findMoviesByCategory("인기순");
	        List<Movie> actionMovies = movieService.findMoviesByCategory("액션");
	        List<Movie> crimeMovies = movieService.findMoviesByCategory("범죄");
	        List<Movie> animationMovies = movieService.findMoviesByCategory("애니메이션");
	        List<Movie> sfMovies = movieService.findMoviesByCategory("SF");
	        List<Movie> comedyMovies = movieService.findMoviesByCategory("코미디");
	        List<Movie> romanceMovies = movieService.findMoviesByCategory("로맨스");
            
	        
	        
	        model.addAttribute("popularMovies", popularMovies);
	        model.addAttribute("actionMovies", actionMovies);
	        model.addAttribute("crimeMovies", crimeMovies);
	        model.addAttribute("animationMovies", animationMovies);
	        model.addAttribute("sfMovies", sfMovies);
	        model.addAttribute("comedyMovies", comedyMovies);
	        model.addAttribute("romanceMovies", romanceMovies);
	        
	        
	        System.out.println("범죄 : " + crimeMovies);
	
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


