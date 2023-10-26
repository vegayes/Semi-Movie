package semi.project.movieInsight.movie.controller;

import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.movie.service.MovieDetailService;


@Controller
@RequestMapping("/movie")
public class MovieDetailController {
	
	@Autowired
	private MovieDetailService service;
	

	/** 영화 검색 후 이동 ( movieNo를 가지고 주소 설정 ) 
	 * @param movieNo
	 * @return
	 */

//	@GetMapping("/movie/{movieNo}")
	@GetMapping("/{movieNo:^[^0]\\d*}") // 정수 숫자만
	public String selectMovie(@PathVariable("movieNo") int movieNo,
								Model model) {
		
		System.out.println("검색 후 이동");
		System.out.println(movieNo);
		
		// 1) movieNo를 가지고 movie에 대한 정보 가져오기
		Movie movieInfo = service.selectMovie(movieNo);
		
		List<Map<String,Object>> directorInfoList = service.directorInfoList(movieNo);
		List<Map<String,Object>> actorInfoList = service.actorInfoList(movieNo);
		
		model.addAttribute("movieInfo", movieInfo);
		model.addAttribute("directorInfoList", directorInfoList);
		model.addAttribute("actorInfoList", actorInfoList);
		
		
		// 2) 영화를 상영중인 영화관 찾기
		List<Cinema> selectCinemaList = service.selectCinemaList(movieNo);
		
		model.addAttribute("selectCinemaList", selectCinemaList);
		
		
		
		// 3) 해당 영화와 비슷한 장르 추천
		List<String>  genreList = Arrays.asList(movieInfo.getMovieGenre().split("/"));
		/*
			List<Movie> recommendMovie = service.recommendMovie(genreList);
			
			for (Movie movie : recommendMovie) {
		    String movieTitle = movie.getMovieTitle();
		    System.out.println("추천영화 Movie Title: " + movieTitle);
		    // 내가 들어온 장르도 포함할 수 있음.
		    }
		*/
		// 출력
			Map<String,Object> genreMap = new HashMap<String, Object>();
		
			genreMap.put("genreList", genreList);
			genreMap.put("movieNo", movieNo);
			
			System.out.println(genreMap);
			
			List<Movie> recommendMovie = service.recommendMovie(genreMap);
			
			System.out.println("recommendMovie Map전달 결과 : " + recommendMovie );
		
			model.addAttribute("recommendMovie", recommendMovie);
		
			
		return "movie/movie-detail-page";
	}
	
	
}
