package semi.project.movieInsight.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.project.movieInsight.cinema.dto.Menu;
import semi.project.movieInsight.mypage.service.MypageService;


@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	@Autowired
	MypageService service;
	
	
	@GetMapping("/member")
	public String mypageMove() {
		
		return "mypage/mypage";
	}
	
	
	
	/** 관리자 페이지의 메뉴 관리 페이지에서 전체 메뉴 조회 
	 * @return
	 */
	@GetMapping("/manager/menu") 
	public String managerPageMenu(Model model) {
		
		Map<String,List<Menu>> selectMenu = service.selectMenu();
		
		System.out.println("selectmenu : " + selectMenu);
		
		model.addAttribute("popcorn", selectMenu.get("popcorn"));
		model.addAttribute("drink", selectMenu.get("drink"));
		model.addAttribute("snack", selectMenu.get("snack"));
		
		return "manager/manager-menu";
		
	}
		
	

}
