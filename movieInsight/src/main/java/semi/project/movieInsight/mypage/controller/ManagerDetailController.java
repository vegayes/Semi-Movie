package semi.project.movieInsight.mypage.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.movie.dto.Movie;
import semi.project.movieInsight.mypage.service.ManagerService;

@Controller
@RequestMapping("/managerDetail")
public class ManagerDetailController {

// **** 관리자 페이지에서 추가,수정,삭제를 담당하는 컨트롤러 **********//
	
	@Autowired
	private ManagerService service;

	
	/* ********************************영화관****************************************** */
	/** 영화관 삭제 메서드
	 * @param cinemaNo
	 * @param model
	 * @return
	 */
	@GetMapping("deleteCinema/{cinemaNo:^[^0]\\d*}") // 정수 숫자만
	public String deleteCinema(@PathVariable("cinemaNo") int cinemaNo, Model model,
			RedirectAttributes ra
	) {
		
		int result = service.deleteCinema(cinemaNo);
		
		if(result == 1) {
			ra.addFlashAttribute("message","삭제에 성공했습니다");
		}else {
			ra.addFlashAttribute("message","삭제 실패");
		}
		
		return "redirect:/manager/cinema";
	}

	
	
	
	/** 관리자 페이지에서 영화관 정보를 업데이트하거나 새로 등록할 때 사용
	 * 
	 * @param cinemaInfo
	 * @param ra
	 * @param cinemaNo
	 * @param model
	 * @param cinemaImage
	 * @param session
	 * @param updateButton  버튼이 update인지, new 인지에 따라 다르게 작동
	 * @param newButton
	 * @return
	 * @throws Exception
	 */
	@PostMapping("updateCinema/{cinemaNo}")
	public String updateCinema(Cinema cinemaInfo,RedirectAttributes ra,
			@PathVariable("cinemaNo") int cinemaNo, Model model,
			@RequestParam(value = "cinemaImage", required = false) MultipartFile cinemaImage, HttpSession session,
			@RequestParam(name = "update", required = false) String updateButton,
            @RequestParam(name = "new", required = false) String  newButton
			) throws Exception {
		
		
		
		String cinemaName = cinemaInfo.getCinemaName();
		
		
		String webPath = "/resources/images/cinema/";
		String filePath = session.getServletContext().getRealPath(webPath);
		
		if(updateButton != null) {
			
			int result = service.updateCinema(cinemaImage,filePath,cinemaInfo);
			
			if(result > 0) {
				ra.addFlashAttribute("message","업데이트 성공");
			}else {
				ra.addFlashAttribute("message","업데이트 실패");
			}
			
		}else if(newButton != null){
			System.out.println("영화관등록 실행");
			int result = service.insertCinema(cinemaImage,filePath,cinemaInfo);
			
			if(result > 0) {
				ra.addFlashAttribute("message","등록 성공");
			}else {
				ra.addFlashAttribute("message","등록 실패");
			}
		}
		
		
		String encodedCinemaName = URLEncoder.encode(cinemaName, StandardCharsets.UTF_8.toString()).replace("+", " ");
		
		System.out.println("encodedCinemaName : " + encodedCinemaName);

		
		return "redirect:/cinemaDetail/" + encodedCinemaName;
	}	
	
	
	
	
	
	
/* ********************************영화****************************************** */
	
	/** 영화 삭제 메서드
	 * @param cinemaNo
	 * @param model
	 * @return
	 */
	@GetMapping("deleteMovie/{movieNo:^[^0]\\d*}") // 정수 숫자만
	public String deleteMovie(@PathVariable("movieNo") int movieNo, Model model,
			RedirectAttributes ra
	) {
		int result = service.deleteMovie(movieNo);
		if(result == 1) {
			
			ra.addFlashAttribute("message","삭제에 성공했습니다");
		}
		
		return "redirect:/manager/movie";
	}
	
	
	
	/** 영화 새로 등록하는 매서드
	 * @param movieInfo
	 * @param ra
	 * @param cinemaImage
	 * @param session
	 * @return
	 */
	@PostMapping("insertMovie")
	public String insertMovie(Movie movieInfo,RedirectAttributes ra,
			@RequestParam(value = "movieImage", required = false) MultipartFile movieImage, HttpSession session
			) throws Exception{
		
		System.out.println("insert 컨트롤러로 전달된 movieInfo : " + movieInfo);
		String[] actorNamesArray = movieInfo.getActorNames().split("/");
		String[] directorNamesArray = movieInfo.getDirectorNames().split("/");
		List<String> actorNamesList = new ArrayList<>(Arrays.asList(actorNamesArray));
		List<String> directorNamesList = new ArrayList<>(Arrays.asList(directorNamesArray));
		
		int movieNo = 0;
		String webPath = "/resources/images/movie/";
		String filePath = session.getServletContext().getRealPath(webPath);
		
		int result = service.insertMovie(movieInfo,movieImage,filePath,actorNamesList,directorNamesList);
		
		if(result > 0) {
			ra.addFlashAttribute("message","등록 성공");
			movieNo = service.selectMovieNo(movieInfo.getMovieTitle());
			return "redirect:/movie/"+movieNo;
		}else {
			ra.addFlashAttribute("message","등록 실패");
			return "redirect:/movie/0";
		}
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
