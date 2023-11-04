package semi.project.movieInsight.movie.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.service.CinemaService;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.movie.service.MovieService;

@Controller
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@Autowired
	private CinemaService service2;
	
	/** 1) 영화 관련 페이지에서 검색한 내용 가져오기 
	 * @param movieQuery
	 * @return movie/search-movie
	 */
	@GetMapping("/search")
    public String searchMovie(String movieQuery, Model model,HttpServletRequest request) {
		
		System.out.println("search 페이지 들어옴");
		
		System.out.println("검색 내용 : " + movieQuery);
		
		// 검색한 영화 목록 조회 서비스 호출
		List<Movie> movieList = service.searchMovieList(movieQuery);

		//System.out.println("movieList : " + movieList);
		
		model.addAttribute("movieQuery", movieQuery);
		model.addAttribute("movieList", movieList);
		
		// 해당 위치 넘기기
//		List<Object> currentUrl =  Arrays.asList(request.getRequestURI().toString().split("/"));
//		model.addAttribute("currentUrl" + currentUrl.get(2));
//		System.out.println("currentUrl" + currentUrl);
		
		// 해당 페이지 넘기기
		 model.addAttribute("pageType", "movie");
		
        return "movie/search-movie";
    }

/*
  @RequestMapping("/Reservation")
    public String showMovieReservation(Model model) {
        List<MovieReservation> reservations = movieService.getAllReservations(); // 데이터베이스에서 정보 가져오기
        model.addAttribute("reservations", reservations); // 모델에 정보 추가
        return "reservationPage";
    }
	*/
    
    
	
	/** header의 정렬 클릭 시 
	 * @param movieQuery
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/search/orderBy")
    public String orderByMovie(String query, Model model,HttpServletRequest request) {
		
		System.out.println("orderBy 페이지 들어옴");
		
		System.out.println("query : " + query);
		
		
		// 검색한 영화 목록 조회 서비스 호출
		List<Movie> movieList = service.orderByMovieList(query);

		
		model.addAttribute("movieQuery", query);
		model.addAttribute("movieList", movieList);

		model.addAttribute("pageType", "movie");
		
        return "movie/search-movie";
    }
	
	
	
	


}
