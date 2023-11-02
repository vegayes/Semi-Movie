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
	    public String getMovies(Model model){
		  // 모든 영화 정보 가져오기
	        List<Movie> movieList = movieService.findAllMovies();
	    // 가져온 영화 정보  model에 추가  
	    //    model.addAttribute("movieList", movieList);
//            List<Movie> categoryMovieList = movieService.findMoviesByCategory(genre);
//	         //  movieNo에 해당하는 영화의 상세 정보를 가져오기
//	        Movie movie = movieService.findMovieById(movieNo);
	        //  movieNo에 해당하는 영화의 상세 정보를 가져오기
//	        model.addAttribute("movie", movie);
	        //  movieNo에 해당하는 영화의 상세 정보 가져오기
//	          model.addAttribute("movieList", movieList);      
        
        
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


