package semi.project.movieInsight.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import semi.project.movieInsight.mypage.service.ManagerService;

@Controller
@RequestMapping("/managerDetail")
public class ManagerDetailController {

	// **** 관리자 페이지에서 추가,수정,삭제를 담당하는 컨트롤러 **********//
	
	@Autowired
	private ManagerService service;

	
	/** 영화관 삭제 메서드
	 * @param cinemaNo
	 * @param model
	 * @return
	 */
	@GetMapping("delete/{cinemaNo:^[^0]\\d*}") // 정수 숫자만
	public String deleteCinema(@PathVariable("cinemaNo") int cinemaNo, Model model,
			RedirectAttributes ra
	) {
		
		int result = service.deleteCinema(cinemaNo);
		
		if(result == 1) {
			
			ra.addFlashAttribute("message","삭제에 성공했습니다");
		}
		
		
		return "redirect:/manager/cinema";
	}
	
	
	
}
