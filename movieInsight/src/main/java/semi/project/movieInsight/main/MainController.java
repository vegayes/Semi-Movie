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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Event;
import semi.project.movieInsight.cinema.dto.Promotion;
import semi.project.movieInsight.cinema.service.CinemaDetailService;
import semi.project.movieInsight.main.service.StaffKindService;
import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.movie.service.MovieService;


@Controller
@SessionAttributes("{loginMember}")
public class MainController {
	
	  @Autowired
	    private MovieService movieService; // 영화 정보를 가져오는 서비스를 주입	
	  
	  @Autowired
	  private StaffKindService service; // 직원친절도
	
	  @Autowired
	  private CinemaDetailService cinemaService;
	  
	  
	  
	@RequestMapping("/")
	public String test1() {



		return "redirect:movie";
		//return "/member/login_signUp";
	}

	
	 @GetMapping("/movie")
	    public String getMovies(Model model , @SessionAttribute(value = "loginMember", required =false) Member loginMember) {
	        // 다양한 장르의 영화를 조회합니다.
		     
	        List<Movie> popularMovies = movieService.findMoviesByCategory("인기순");
	        List<Movie> actionMovies = movieService.findMoviesByCategory("액션");
	        List<Movie> crimeMovies = movieService.findMoviesByCategory("범죄");
	        List<Movie> animationMovies = movieService.findMoviesByCategory("애니메이션");
	        List<Movie> sfMovies = movieService.findMoviesByCategory("SF");
	        List<Movie> comedyMovies = movieService.findMoviesByCategory("코미디");
	        List<Movie> romanceMovies = movieService.findMoviesByCategory("로맨스");
	        List<Movie> latestMovies = movieService.findMoviesByCategory("최신순");
	        if(loginMember!= null) {
	        	List<Movie> userPrefMovies = movieService.userPreMovies(loginMember.getMemberNo());	
	        	model.addAttribute("userPrefMovies", userPrefMovies);
	        }
	        List<Movie> horrorMovies = movieService.findMoviesByCategory("호러");
	        

	        
	        
	        model.addAttribute("horrorMovies", horrorMovies);
	        model.addAttribute("popularMovies", popularMovies);
	        model.addAttribute("actionMovies", actionMovies);
	        model.addAttribute("crimeMovies", crimeMovies);
	        model.addAttribute("animationMovies", animationMovies);
	        model.addAttribute("sfMovies", sfMovies);
	        model.addAttribute("comedyMovies", comedyMovies);
	        model.addAttribute("romanceMovies", romanceMovies);
	        model.addAttribute("latestMovies", latestMovies);
	        	

	
          
           
           // 직원 친절도 상위 점수 조회
           List<Cinema> cinemaGrade = service.selectcinemaGrade();
           
           model.addAttribute("cinemaGrade", cinemaGrade);
      
           // cinemaGrade 리스트를 평점 역순으로 정렬
           cinemaGrade.sort((c1, c2) -> Double.compare(c2.getCinemaGrade(), c1.getCinemaGrade()));

           // 6위부터 9위까지의 영화관을 추출
           List<Cinema> cinemasFrom6thTo9th = cinemaGrade.subList(5, Math.min(9, cinemaGrade.size()));
          
           
           System.out.println(cinemasFrom6thTo9th);
           
           model.addAttribute("cinemasFrom6thTo9th",cinemasFrom6thTo9th);
           
           // 영화관 평점 보내기
           model.addAttribute("pageType","movie");

	        return "movie/home-page";
   

	     
 
	    }
	

	@GetMapping("/cinema")
	public String cinemaMain(Model model) {
		
		System.out.println("영화관 메인페이지 이동");
		
		Promotion promotion = cinemaService.getPromotionInfo();
		Event event = cinemaService.getEventInfo();
		
		 model.addAttribute("pageType", "cinema");
		 model.addAttribute("promotion", promotion);
		 model.addAttribute("event", event);
		
		return "cinema/cinema-homepage";
	}

      
	
	  // 영화관 페이지로 이동
	  @RequestMapping(value = "/movieInsight/cinema", method = RequestMethod.GET)
	    public String redirectToCinemaHomePage() {
	        return "cinema-homepage";
	    }
	   //  영화관 상세 페이지 이동
	  @RequestMapping(value = "/movieInsight/cinemaDetail/{cinemaName}", method = RequestMethod.GET)
	    public String cinemaDetail(@PathVariable String cinemaName, Model model) {
	        
	        model.addAttribute("cinemaName", cinemaName);
	        
	        return "cinemaDetailPage"; 
	    }


	}




	
	



