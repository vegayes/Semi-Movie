package semi.project.movieInsight.movie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.service.CinemaService;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.movie.service.MovieService;

@Controller
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
    public String searchMovie(String movieQuery, Model model) {
		
		System.out.println("search 페이지 들어옴");
		
		System.out.println("검색 내용 : " + movieQuery);
		
		// 검색한 영화 목록 조회 서비스 호출
		List<Movie> movieList = service.searchMovieList(movieQuery);

		System.out.println("movieList : " + movieList);
		
		model.addAttribute("movieQuery", movieQuery);
		model.addAttribute("movieList", movieList);
		
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
    
    
	
	/** 좋아할 만한 영화 클릭
	 * @return
	 */
	@GetMapping("/favorite1")
	public String favorite1() {
		return "movie/movieT";
	}
	
	/** 좋아할 만한 영화 클릭
	 * @return
	 */
	@GetMapping("/favorite2")
	public String favorite2() {
		return "movie/movieT";
	}
	
	/** 좋아할 만한 영화 클릭
	 * @return
	 */
	@GetMapping("/favorite3")
	public String favorite3() {
		return "movie/movieT";
	}
	
	/** 좋아할 만한 영화 클릭
	 * @return
	 */
	@GetMapping("/favorite4")
	public String favorite4() {
		return "movie/movieT";
	}
	
	/** 좋아할 만한 영화 클릭
	 * @return
	 */
	@GetMapping("/favorite5")
	public String favorite5() {
		return "movie/movieT";
	}
	
	
	
	//@GetMapping("/movie/이동할 상세페이지의 movieNo")
	@GetMapping("/movie/move")
	public String movieMove() {
		
//		int movieNo = 1;
//		String path = "redirect:";
		
//		path += "/movie/" + movieNo;
		
		return "movie/movieT";
	}
	
	


}
