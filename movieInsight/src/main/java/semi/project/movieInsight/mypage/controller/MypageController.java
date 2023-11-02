package semi.project.movieInsight.mypage.controller;



import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.cinema.service.CinemaService;
import semi.project.movieInsight.common.CookieUrlClass;
import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.mypage.service.MypageService;
import semi.project.movieInsight.movie.service.MovieService;


@Controller
@RequestMapping("/mypage")
@SessionAttributes("{loginMember}")
public class MypageController {
	
	@Autowired
	MypageService service;
	
	@Autowired // bean으로 등록된 객체 중 타입이 일치하는 객체를 DI
	private BCryptPasswordEncoder bcrypt;

	
	
	
	/** 즐겨찾기 페이지 조회 
	 * @return
	 */
	@GetMapping("/member")
	public String mypageMove(Model model ,
							@SessionAttribute(value = "loginMember", required =false) Member loginMember,
							HttpServletRequest request
							) {
		
		System.out.println("마이페이지 로그인 : " + loginMember );
		
		// 1) 영화 즐겨찾기 목록 조회 
		List<Movie> selectLikeMovie = service.selectLikeMovie(loginMember.getMemberNo());
		model.addAttribute("movieList", selectLikeMovie);
		
		// 2) 영화관 즐겨찾기 목록 조회 
		List<Cinema> selectLikeCinema = service.selectLikeCinema(loginMember.getMemberNo());
		model.addAttribute("cinemaList", selectLikeCinema);
		
		// 3)방문기록 목록 조회
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (cookie.getName().startsWith("urlCookie_")) {
		            String value = cookie.getValue();
		            
		            System.out.println("쿠키 이름 : " + cookie.getName());
		            
		            System.out.println("cookies value : " + value );
		            
		            List<String> visitInfoList = Arrays.asList(value.split("_"));
		            
		            if(cookie.getName().split("_")[1].equals(loginMember.getMemberId())) {
		            	
		            	System.out.println("===== 로그인한 번호와 쿠키의 번호가 일치한 경우 =====");
		            	
			            System.out.println("리스트 저장 : " + visitInfoList);
	            		
		            	
		            }
		            
//		            if(visitInfo[0].equals(loginMember.getMemberId())) {
		            	
//		            	
//		            	int visitMovie = Integer.parseInt(visitInfo[1]);
//		            	
//		            	System.out.println("visitMovie : " + visitMovie);
		            	//======================================= [ 방법 ] =========================================
		            	/* 
		            	 * 쿠키 이름 -> urlCookie_(사용자id) 
		            	 * 쿠키 값 -> 최신 순서대로 저장 가능 ex) 2_3_4 경우 (2가 가장 예전에 방문한 페이지, 4가 가장 최근에 방문한 페이지)
		            	 */
		            	//==========================================================================================
		            	
		            	
		            	
		            
		            
		        }
		    }
		}
		
		// 4) 영화 댓글 목록 조회 
		List<Movie> selectCommentMovie = service.selectCommentMovie(loginMember.getMemberNo());
		System.out.println("영화 댓글 찾기" + selectCommentMovie);
		model.addAttribute("commentMovie", selectCommentMovie);
		
		List<Cinema> selectCommentCinema = service.selectCommentCinema(loginMember.getMemberNo());
		System.out.println("영화관 댓글 찾기" + selectCommentCinema);
		model.addAttribute("commentCinema", selectCommentCinema);
		
		
		model.addAttribute("loginMember", loginMember);
		
		
		return "mypage/mypage";
	}	
	
	
	/** 닉네임 변경
	 * @param nickname
	 * @return
	 */
	@GetMapping("/nickname")
	@ResponseBody
	public int checkNickname(String nickname) {
	
		return service.checkNickname(nickname);
	}
	
	
	
	
	/** 프로필
	 * @param profileImage
	 * @param session
	 * @param loginMember
	 * @param ra
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/profile")
	public String updateProfile(
			@RequestParam("profileImage") MultipartFile profileImage // 업로드 파일
			, @SessionAttribute("loginMember") Member loginMember
			, HttpSession session // 세션 객체
			, RedirectAttributes ra // 리다이렉 시 메세지 전달
			) throws Exception{
		
		// 로그인한 회원의 번호를 updateMember에 세팅

		System.out.println("수정 버튼 누른경우");
		System.out.println(profileImage);
		//MultipartFile[field="profileImage", filename=물괴.jpg, contentType=image/jpeg, size=1215764]
	
		String webPath = "/resources/images/member/";
		
		String filePath = session.getServletContext().getRealPath(webPath);
			
		
		System.out.println("filePath" + filePath);
		
		// 프로필 이미지 수정 서비스 호출
		int result = service.updateProfile(profileImage, webPath, filePath, loginMember);
		
		
		String message = null;
		if(result > 0) message = "프로필 이미지가 변경되었습니다";
		else			message = "프로필 변경 실패";
		
		ra.addFlashAttribute("message", message);

		return "redirect:/mypage/member";
	}
	
	
	 
	/** 정보 수정
	 * @param loginMember
	 * @param updateMember
	 * @param memberAddress
	 * @param ra
	 * @return
	 */
	@PostMapping("/update")
	public String updateInfo(@SessionAttribute("loginMember") Member loginMember,
							Member updateMember,
							RedirectAttributes ra) {

		// 로그인한 회원의 번호를 updateMember에 세팅
		updateMember.setMemberNo( loginMember.getMemberNo() );
		
		int result = 0;	
		String message = null;		
		// 비밀번호 입력하지 않은 경우
		if(updateMember.getMemberPw().equals(null)) {
			
			System.out.println("입력 안함 ");
			
			System.out.println("입력한 닉네임 " + updateMember.getMemberNickname());
			System.out.println("입력한 성별 " + updateMember.getMemberGender());
			
			System.out.println("일치 확인" + updateMember.getMemberNickname().equals(loginMember.getMemberNickname()));
			
			if(updateMember.getMemberNickname().equals(loginMember.getMemberNickname()) || updateMember.getMemberGender().equals(loginMember.getMemberGender())) {
				
				result = 0;
				
				message = "기존과 일치하는 정보 입력 불가";
				System.out.println("기존과 일치하는 정보 입력 불가");
				
			}
			
			
			result = service.updateInfo(updateMember);
			
			if(result > 0) {
				loginMember.setMemberNickname( updateMember.getMemberNickname() );
				loginMember.setMemberGender( updateMember.getMemberGender() );
				
				message = "회원 정보 수정 성공";
				
			} else {
				// 실패에 따른 처리 

				message = "회원 정보 수정 실패";
				
			}
			
			
		}else { // 비밀번호를 입력한 경우
			
			System.out.println("입력한 비밀번호 " + updateMember.getMemberPw());
			System.out.println("입력한 닉네임 " + updateMember.getMemberNickname());
			System.out.println("입력한 성별 " + updateMember.getMemberGender());			
			
			
			String newPw = bcrypt.encode(updateMember.getMemberPw());	
			
			System.out.println("newPw : " + newPw);
			
			updateMember.setMemberPw(newPw);
			
			result = service.updateInfo(updateMember);
			
			if(result > 0) {
				// -> 성공 시 Session에 로그인된 회원 정보도 수정(동기화)
				loginMember.setMemberPw(newPw);
				loginMember.setMemberNickname( updateMember.getMemberNickname() );
				loginMember.setMemberGender( updateMember.getMemberGender() );
				message = "회원 정보 수정 성공";
				
			} else {
				// 실패에 따른 처리 

				message = "회원 정보 수정 실패";
				
			}
			
		}

		// 결과값으로 성공

		System.out.println("변경된 닉네임 : " +loginMember.getMemberNickname());

		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/mypage/member"; 
	}
	
	
	// 탈퇴 
	
	
	
	
	
	
	// 댓글 수정후 마이페이지에서 조회 
	@ResponseBody
	@GetMapping(value = "/comment/select", produces = "application/json; charset=UTF-8")
	public List<Movie> selectMyMovieComment(int memberNo) {
		System.out.println("댓글 조회 비동기 :" +  memberNo);
		
		return service.selectCommentMovie(memberNo);
	}	
	
	
	// 댓글 수정 팝업 내용 조회하기 (영화)
	@ResponseBody
	@GetMapping(value = "/comment", produces = "application/json; charset=UTF-8")
	public Movie selectMovieComment(int commentNo) {
		System.out.println("댓글 조회 비동기 :" +  commentNo);
		
		return service.selectMovieComment(commentNo);
	}
	
	
	// 댓글 수정 팝업 내용 조회하기 (영화관)
	@ResponseBody
	@GetMapping(value = "/comment2", produces = "application/json; charset=UTF-8")
	public Cinema selectCinemaComment(int cinemaCommentNo) {
		System.out.println("댓글 조회 비동기 :" +  cinemaCommentNo);
		
		return service.selectCinemaComment(cinemaCommentNo);
	}
	
	
	// 댓글 수정 팝업에서 진행하기
	@ResponseBody
	@GetMapping(value ="/comment/update", produces = "application/json; charset=UTF-8")
	public int updateMovieComment(String movieCommentContent, float movieGrade , int movieCommentNo , Movie movie) {
		
		System.out.println("머야");
		 
		System.out.println("댓글 NO 수정 조회 비동기 :" +  movieCommentNo);
		System.out.println("댓글 내용 수정 조회 비동기 :" +  movieCommentContent);
		System.out.println("댓글 평점 수정 조회 비동기 :" +  movieGrade);
		
		movie.setMovieCommentNo(movieCommentNo);
		movie.setMovieGrade(movieGrade);
		movie.setMovieCommentContent(movieCommentContent);
		
		return service.updateMovieComment(movie);
	}
	
	// 댓글 수정 팝업에서 진행하기
	@ResponseBody
	@GetMapping(value ="/comment2/update", produces = "application/json; charset=UTF-8")
	public int updateCinemaComment(String cinemaCommentContent, float cinemaGrade , int cinemaCommentNo , Cinema cinema) {
		
		System.out.println("머야");
		 
		System.out.println("댓글 NO 수정 조회 비동기 :" +  cinemaCommentNo);
		System.out.println("댓글 내용 수정 조회 비동기 :" +  cinemaCommentContent);
		System.out.println("댓글 평점 수정 조회 비동기 :" +  cinemaGrade);
		
		cinema.setCinemaCommentNo(cinemaCommentNo);
		cinema.setCinemaGrade(cinemaGrade);
		cinema.setCinemaCommentContent(cinemaCommentContent);
		
		return service.updateCinemaComment(cinema);
	}
	
	// 즐겨찾기 팝업에서 조회 하기 ( ajax 영화 ) 
	@ResponseBody
	@GetMapping(value ="/like/select", produces = "application/json; charset=UTF-8")
	public List<Movie> selectFvMovie(int memberNo){
		
		System.out.println("비동기 조회 : " + memberNo);
		
		return service.selectLikeMovie(memberNo);
	}
		
	
	// 즐겨찾기 팝업에서 삭제하기 (영화) 	
	@ResponseBody
	@PostMapping(value = "/like/del", produces = "application/json; charset=UTF-8" )
	public int delMovieComment(@RequestBody Map<String, List<Integer>> requestBody, // "delMovie" 의 key로 들어가 있음. 
								@SessionAttribute("loginMember") Member loginMember) {
				
	
		Map<String, Object> favoriteDelMovie = new HashMap<String , Object>();
		favoriteDelMovie.put("delMovieNo", requestBody.get("delMovie"));
		favoriteDelMovie.put("memberNo", loginMember.getMemberNo());
		
	    return service.delFavoriteMovie(favoriteDelMovie);
	}
	
	// 즐겨찾기 삭제 후 마이페이지에서 다시 조회 
	@ResponseBody
	@GetMapping(value = "/favorite/select", produces = "application/json; charset=UTF-8")
	public List<Movie> selectMyMovieFavorite(int memberNo) {
		System.out.println("즐겨찾기 조회 비동기 :" +  memberNo);
		
		return service.selectLikeMovie(memberNo);
	}	
	
	

}
