package semi.project.movieInsight.movie.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import semi.project.movieInsight.movie.service.MovieDetailService;

@Controller
public class MovieDetailController {
	
	@Autowired
	private MovieDetailService service;
	

	/** 영화 검색 후 이동 ( movieNo를 가지고 주소 설정 ) 
	 * @param movieNo
	 * @return
	 */
	@GetMapping("/movie/{movieNo}")
	public String searchMovieMove(@PathVariable("movieNo") int movieNo) {
		
		System.out.println("검색 후 이동");
		System.out.println(movieNo);
		
		// 1) movieNo를 가지고 movie에 대한 정보 가져오기
		Map<String, Object> movie = service.selectMovie(movieNo);
		
		
		
		
		return "movie/movie-detail-page";
	}
	
	
}
