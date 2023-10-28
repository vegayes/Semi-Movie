package semi.project.movieInsight.mypage.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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

	
	@PostMapping("updateCinema/{cinemaNo}")
	public String updateCinema(Cinema cinemaInfo,RedirectAttributes ra,
			@PathVariable("cinemaNo") int cinemaNo, Model model,
			@RequestParam(value = "cinemaImage", required = false) MultipartFile cinemaImage, HttpSession session
			) throws Exception {
		
		System.out.println("수정된 cinemaInfo : " + cinemaInfo);
		System.out.println("cinemaImage : " + cinemaImage);
		String cinemaName = cinemaInfo.getCinemaName();
		// 웹 접근경로(webapp 기준, 경로는 webapp의 하위 폴더부터 시작) 
		String webPath = "/resources/images/cinema/";
		//실제로 이미지 파일이 저장되어야 하는 서버컴퓨터 경로
		String filePath = session.getServletContext().getRealPath(webPath);
		
		int result = service.updateCinema(cinemaImage,webPath,filePath,cinemaInfo);
		
		if(result > 0) {
			ra.addFlashAttribute("message","업데이트 성공");
		}else {
			ra.addFlashAttribute("message","업데이트 실패");
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
	
	
	
}
