package semi.project.movieInsight.cinema.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.service.CinemaDetailService;
import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.movie.dto.Movie;


@SessionAttributes({"loginMember"})
@Controller
@RequestMapping("/cinemaDetail")
public class CinemaDetailController {

	
	@Autowired
	private CinemaDetailService service;
	
	

	
	
	//영화관 정보 조회
	@GetMapping("/{cinemaName}")
	public String selectCinema(
			@PathVariable("cinemaName") String cinemaName,
			@SessionAttribute(value="loginMember",required=false) Member loginMember,
			HttpServletRequest req,
			HttpServletResponse resp,
			Model model
			){
		

			System.out.println("loginMember : " + loginMember);

			if(cinemaName.equals("insert")) {
				
				return "cinema/cinema-detail-page";
			}
		
			// 1) 영화관 정보 조회
			Cinema  cinemaInfo =  service.selectCinemaInfo(cinemaName);
		 	
			model.addAttribute("loginMember", loginMember);
		 	model.addAttribute("cinemaInfo", cinemaInfo);
		 	
			// 1-2) 즐겨찾기 여부 확인
			if(loginMember != null) {
				Map<String, Object> favoriteCheck = new HashMap<String , Object>();
				favoriteCheck.put("memberNo", loginMember.getMemberNo());
				favoriteCheck.put("cinemaName", cinemaName);
				
				
				// 좋아요 여부 확인 서비스 호출
				int result = service.favoriteCheck(favoriteCheck);
				
				System.out.println("즐겨찾기 Controller result : " + result);

				if(result > 0) model.addAttribute("favorite", "on");
				
			}
		 	
		 	
		 	
		 	// 2) 영화관에서 상영중인 영화 목록 조회
		 	List<Movie> movieList = service.selectMovieList(cinemaInfo.getCinemaNo());
		 	//System.out.println("movieList : " + movieList);
		
		 	model.addAttribute("movieList", movieList);
		 	

			
		 	
		 	
		 	
			// 3) 영화관에서 댓글 조회 (시설 / 직원 / 메뉴 ) 
			List<Cinema> commentCinemaList = service.commentCinemaList(cinemaName);
			System.out.println(commentCinemaList);
			model.addAttribute("commentCinemaList", commentCinemaList);
		 	
			
			// 4) 메뉴 목록 가져오기 
			List<Menu> menuList = service.getMenuList(cinemaName);
			System.out.println(menuList);
			model.addAttribute("menuList", menuList);
		 	
			// 4-1) 베스트 메뉴 가져오기
			Map<String,Menu> bestMenu = service.getBestMenu(cinemaName);
			model.addAttribute("bestMenu", bestMenu);
		 	
			
			
			
			model.addAttribute("pageType","cinema");
			
			return "cinema/cinema-detail-page";
	}
	
	
	
	
	
	
	// 시설만족도,직원친절도,메뉴 추천 조회 O
	
	// 로그인 회원만 댓글 입력 가능하게 O
	
	// 댓글 평점 입력 O
	
	// 메뉴 추천 점수순으로 업데이트 O
	
	
	
	
	
	
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
	public int insert(String commentValue, String cinemaName, float cinemaGrade,
					 String cinemaCommentType, Cinema cinema,
					@SessionAttribute(value = "loginMember", required =false) Member loginMember) {
		
		
//		System.out.println("commentValue" + commentValue);
//		System.out.println("cinemaName" + cinemaName);
//		System.out.println("cinemaCommentType" + cinemaCommentType);
//		System.out.println("댓글 삽입 로그인된 회원 번호 :" + loginMember.getMemberNo());
		
		cinema.setCinemaCommentContent(commentValue);
		cinema.setCinemaGrade(cinemaGrade);
		cinema.setCinemaName(cinemaName);
		cinema.setCinemaCommentType(cinemaCommentType);
		cinema.setMemberNo(loginMember.getMemberNo());
		
		return service.insert(cinema);
	} 
	
	
	/** 댓글 삭제
	 * @param cinemaCommentNo
	 * @return
	 */
	@GetMapping(value = "/comment/delete", produces = "application/json; charset=UTF-8" )
	@ResponseBody
	public int delete(int cinemaCommentNo) {
		return service.delete(cinemaCommentNo);
	}
	
	
	
	// 즐겨찾기 
	@PostMapping("/favorite")
	@ResponseBody
	public int updatefavorite(@RequestBody Map<String, Object> paramMap) {
		
		return service.updatefavorite(paramMap);
	}
	
	
	/** 댓글 조회 (ajax) 
	 * @param cinemaName
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/comment/select", produces = "application/json; charset=UTF-8")
	public List<Cinema> selectCinemaCommentList( String cinemaName) {
		
		List<Cinema> result = service.commentCinemaList(cinemaName);
		
		System.out.println("비동기 조회 " + result);
		
		return result;
	}	
	
	
	
	/*메뉴 평점 삽입*/
	@ResponseBody
	@GetMapping(value = "/menu/insert", produces = "application/json; charset=UTF-8" )	
	public int insertMenuGrade(@SessionAttribute(value = "loginMember", required =false) Member loginMember,
						 int menuGrade, String cinemaName, String menuSelect , 
						 Menu menu) {
		
		
//		System.out.println("menuGrade" + menuGrade);
//		System.out.println("cinemaName" + cinemaName);
//		System.out.println("menuSelect" + menuSelect);
		
		menu.setMenuName(menuSelect);
		menu.setMenuGrade(menuGrade);
		menu.setCinemaName(cinemaName);
		menu.setMemberNo(loginMember.getMemberNo());
	
		return service.insertMenuGrade(menu);
	} 	
	
	
	
	@ResponseBody
	@GetMapping(value = "/menu/select", produces = "application/json; charset=UTF-8")
	public Map<String,Menu>  selectBestMenuList( String cinemaName) {
		
		Map<String,Menu> result = service.getBestMenu(cinemaName);
		
		System.out.println("비동기 조회 " + result);
		
		return result;
	}	
	
	
	// 평점 수정되는 것 조회 
	@ResponseBody
	@GetMapping(value = "/update/grade", produces = "application/json; charset=UTF-8")
	public Cinema updateGrade(String cinemaName) {
		return service.selectCinemaInfo(cinemaName);
	}
	
	
	
	
}
