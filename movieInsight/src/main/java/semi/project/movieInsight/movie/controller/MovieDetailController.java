package semi.project.movieInsight.movie.controller;

import java.io.IOException;

import java.nio.file.spi.FileSystemProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.service.CinemaService;
import semi.project.movieInsight.common.CookieUrlClass;
import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.movie.service.MovieDetailService;


@Controller
@RequestMapping("/movie")
@SessionAttributes("{loginMember}")
public class MovieDetailController {
	
	@Autowired
	private MovieDetailService service;
	
	@Autowired
	private CinemaService cinemaService;
	/** 영화 검색 후 이동 ( movieNo를 가지고 주소 설정 ) 
	 * @param movieNo
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */


//	@GetMapping("/movie/{movieNo}")

	@GetMapping("{movieNo:\\d+}")
	public String selectMovie(
			@SessionAttribute(value = "loginMember", required =false) Member loginMember,
			@PathVariable("movieNo") int movieNo,
								Model model,
								HttpServletRequest request, 
								HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("검색 후 이동");
		System.out.println(movieNo);
		if(movieNo == 0) {
			List<Cinema> cinemaList = cinemaService.selectManagerCinemaList();
			model.addAttribute("cinemaList", cinemaList);
			return "movie/movie-detail-page";
		}
		
		
		// 1) movieNo를 가지고 movie에 대한 정보 가져오기
		System.out.println("movieNo : " + movieNo);
		Movie movieInfo = service.selectMovie(movieNo);
		
		List<Map<String,Object>> directorInfoList = service.directorInfoList(movieNo);
		List<Map<String,Object>> actorInfoList = service.actorInfoList(movieNo);
		
		model.addAttribute("loginMember", loginMember);
		model.addAttribute("movieInfo", movieInfo);
		model.addAttribute("directorInfoList", directorInfoList);
		model.addAttribute("actorInfoList", actorInfoList);
		
		System.out.println("movieInfo : " + movieInfo);
		
		// 1-2) 즐겨찾기 여부 확인
		if(loginMember != null) {
			Map<String, Object> favoriteCheck = new HashMap<String , Object>();
			favoriteCheck.put("memberNo", loginMember.getMemberNo());
			favoriteCheck.put("movieNo", movieNo);
			
			
			// 좋아요 여부 확인 서비스 호출
			int result = service.favoriteCheck(favoriteCheck);
			
			System.out.println("즐겨찾기 Controller result : " + result);

			if(result > 0) model.addAttribute("favorite", "on");
			
		}
		
		
		// 2) 영화를 상영중인 영화관 찾기
		List<Cinema> selectCinemaList = service.selectCinemaList(movieNo);
		model.addAttribute("selectCinemaList", selectCinemaList);
		
		//System.out.println("장르 : " + movieInfo.getMovieGenre());
		
		// 3) 영화에 댓글 조회
		List<Movie> commentMovieList = service.commentMovieList(movieNo);
		System.out.println(commentMovieList);
		model.addAttribute("commentMovieList", commentMovieList);
		
		
		
		// 4) 해당 영화와 비슷한 장르 추천
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
			
			//System.out.println(genreMap);
			
			List<Movie> recommendMovie = service.recommendMovie(genreMap);
			model.addAttribute("recommendMovie", recommendMovie);
		
			
			// 해당 위치 넘기기
//			List<Object> currentUrl =  Arrays.asList(request.getRequestURI().toString().split("/"));
//			model.addAttribute("currentUrl" + currentUrl.get(2));
//			System.out.println("url : " + currentUrl);
			 model.addAttribute("pageType", "movie");
			 

//			 CookieUrlClass.setCookieUrl(request, response, loginMember.getMemberId(), Integer.toString(movieNo));


			 
			 
			 
			 
		return "movie/movie-detail-page";
	}
	
	/** 댓글 삽입
	 * @param commentContent
	 * @param movieNo
	 * @param movieGrade
	 * @param movie
	 * @param loginMember
	 * @return
	 */
	@GetMapping(value = "/comment/insert", produces = "application/json; charset=UTF-8" )	
	@ResponseBody
	public int insert(String commentContent, int movieNo, float movieGrade,
					 Movie movie,
					@SessionAttribute(value = "loginMember", required =false) Member loginMember) {
		
		
		System.out.println("commentContent" + commentContent);
		System.out.println("movieNo" + movieNo);
		
		System.out.println("댓글 삽입 로그인된 회원 번호 :" + loginMember.getMemberNo());
		
		movie.setMovieCommentContent(commentContent);
		movie.setMovieGrade(movieGrade);
		movie.setMovieNo(movieNo);
		movie.setMemberNo(loginMember.getMemberNo());
		
		
		return service.insert(movie);
	} 
	
	
	/** 댓글 삭제
	 * @param commentNo
	 * @return
	 */
	@GetMapping(value = "/comment/delete", produces = "application/json; charset=UTF-8" )
	@ResponseBody
	public int delete(int movieCommentNo) {
		return service.delete(movieCommentNo);
	}
	
	
	// 즐겨찾기 
	@PostMapping("/favorite")
	@ResponseBody
	public int updatefavorite(@RequestBody Map<String, Integer> paramMap) {
	
		System.out.println(" 확인 : " + paramMap);
		
		return service.updatefavorite(paramMap);
	}
	
	/** 댓글 조회 (ajax) 
	 * @param cinemaName
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/comment/select", produces = "application/json; charset=UTF-8")
	public List<Movie> commentMovieList( int movieNo) {
		
		List<Movie> result = service.commentMovieList(movieNo);
		
		System.out.println("비동기 조회 " + result);
		
		return result;
	}	
	
	// 평점 수정되는 것 조회 
	@ResponseBody
	@GetMapping(value = "/update/grade", produces = "application/json; charset=UTF-8")
	public Movie updateGrade(int movieNo) {
		return service.selectMovie(movieNo);
	}
	
	
}
