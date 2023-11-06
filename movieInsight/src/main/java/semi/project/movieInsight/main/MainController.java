package semi.project.movieInsight.main;

import java.text.DateFormat;
import java.util.ArrayList;
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
import semi.project.movieInsight.movie.service.MovieServiceimpl;

/*
 * CSS 보안.. 
 * 트러블 슈팅 보안
 * 
 * */

@Controller
@SessionAttributes("{loginMember}")
public class MainController {
	
	  @Autowired
	   private MovieService movieService; // 영화 정보를 가져오는 서비스를 주입	
	  
	  @Autowired
	  private MovieServiceimpl userService; // 유저 추천순 
	  
	  @Autowired
	  private StaffKindService service; // 직원친절도
	
	  @Autowired
	  private CinemaDetailService cinemaService; // 직원친절도
	  
	@RequestMapping("/")
	public String test1() {



		return "redirect:movie";
		//return "/member/login_signUp";
	}

	
	 @GetMapping("/movie")
	    public String getMovies(Model model, @SessionAttribute(value = "loginMember", required =false) Member loginMember ) {
	        // 다양한 장르의 영화를 조회합니다.

		 	List<Movie> userPrefMovies = new ArrayList<Movie>();
		 	
		 
	        List<Movie> popularMovies = movieService.findMoviesByCategory("인기순");
	        List<Movie> actionMovies = movieService.findMoviesByCategory("액션");
	        List<Movie> crimeMovies = movieService.findMoviesByCategory("범죄");
	        List<Movie> animationMovies = movieService.findMoviesByCategory("애니메이션");
	        List<Movie> sfMovies = movieService.findMoviesByCategory("SF");
	        List<Movie> comedyMovies = movieService.findMoviesByCategory("코미디");
	        List<Movie> romanceMovies = movieService.findMoviesByCategory("로맨스");
	        List<Movie> latestMovies = movieService.findMoviesByCategory("최신순");
//	        List<Movie> userPrefMovies = movieService.findMoviesByCategory("user 맞춤 영상");
	        List<Movie> horrorMovies = movieService.findMoviesByCategory("호러");
	        
	        if(loginMember == null) {
	        	int memberNo = 0;
	        	 userPrefMovies = userService.userPreMovies(memberNo);
	        	
	        }else {
	        	 userPrefMovies = userService.userPreMovies(loginMember.getMemberNo());
	        }
	        
	        model.addAttribute("horrorMovies", horrorMovies);
	        model.addAttribute("popularMovies", popularMovies);
	        model.addAttribute("actionMovies", actionMovies);
	        model.addAttribute("crimeMovies", crimeMovies);
	        model.addAttribute("animationMovies", animationMovies);
	        model.addAttribute("sfMovies", sfMovies);
	        model.addAttribute("comedyMovies", comedyMovies);
	        model.addAttribute("romanceMovies", romanceMovies);
	        model.addAttribute("latestMovies", latestMovies);
	        model.addAttribute("userPrefMovies", userPrefMovies);
	        
	        

	        
	        System.out.println("범죄 : " + crimeMovies);
	
           model.addAttribute("pageType","movie");
           
            //직원친절도를 위한 영화관 이름 번호 찾기
//         List<Cinema> cinemaStaff = service.cinemaStaff();
//	
//          model.addAttribute("cinemaStaff", cinemaStaff);
           

          List<Cinema> cinemaGrade = service.selectcinemaGrade();
           model.addAttribute("cinemaGrade", cinemaGrade);
      
           // cinemaGrade 리스트를 평점 역순으로 정렬
           cinemaGrade.sort((c1, c2) -> Double.compare(c2.getCinemaGrade(), c1.getCinemaGrade()));

           // 6위부터 9위까지의 영화관을 추출
           List<Cinema> cinemasFrom6thTo9th = cinemaGrade.subList(5, Math.min(9, cinemaGrade.size()));
          
           
           model.addAttribute("cinemasFrom6thTo9th",cinemasFrom6thTo9th);



          // 영화관 정보 번호를 가져와서 다른 테이블에 있는 각각의 영화관 친절도 평점 조회 
           
//          System.out.println(cinemaStaff);
           
            //영화관 평점 더하고 평균내기 
           
          //영화관 평점 보내기

	        return "movie/home-page";
   

	     
 
	    }
	

	@GetMapping("/cinema")
	public String cinemaMain(Model model) {
		
		System.out.println("영화관 메인페이지 이동");
		
		Promotion promotion = cinemaService.getPromotionInfo();
		Event event = cinemaService.getEventInfo();
		
	   model.addAttribute("promotion", promotion);
	   model.addAttribute("event", event);
	   model.addAttribute("pageType", "cinema");
		
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




	
	



