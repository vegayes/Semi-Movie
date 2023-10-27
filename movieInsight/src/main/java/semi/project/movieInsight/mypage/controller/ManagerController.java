package semi.project.movieInsight.mypage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.project.movieInsight.mypage.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService service;
	
	/** 1) 모든 홍보정보 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/promotion")
	public String movePromotion(Model model) {
		// 1) Map으로 가져오기
		Map<String,Object> promotionMap = service.selectPromotion();
		
		System.out.println("promotionMap : " + promotionMap);
		model.addAttribute("promotionMap", promotionMap);
		
		return "/manager/manager-event";
	}
	
	/** 2) 모든 회원정보 조회
	 * @param model
	 * @return
	 */
	@GetMapping("/member")
	public String moveMember(Model model) {
		// 1) Map으로 가져오기
		Map<String,Object> memberMap = service.selectMember();
		
		model.addAttribute("memberMap", memberMap);
		
		
		
		
		
		return "/manager/manager-member";
	}
	
	
	
}
