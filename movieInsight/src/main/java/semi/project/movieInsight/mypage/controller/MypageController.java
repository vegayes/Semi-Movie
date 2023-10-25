package semi.project.movieInsight.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	
	@GetMapping("/manager/menu") 
	public String managerPageMenu() {
		
		Map<String,List<Menu>> selectMenu = service.selectMenu();
		
		System.out.println("selectmenu : " + selectMenu);
		
		return null;
		
	}
		
	

}
