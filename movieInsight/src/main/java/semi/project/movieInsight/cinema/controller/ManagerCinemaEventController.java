package semi.project.movieInsight.cinema.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import semi.project.movieInsight.cinema.service.ManagerCinemaEventService;
import semi.project.movieInsight.movie.dto.Movie;

@Controller
public class ManagerCinemaEventController {
	
	@Autowired
	private ManagerCinemaEventService service;
	
	
	@GetMapping("/manager/promotion")
	public String movePromotion(Model model) {
		
		// 1) Map으로 가져오기
		Map<String,Object> promotionMap = service.selectPromotion();
		
		System.out.println("promotionMap : " + promotionMap);
		
		model.addAttribute("promotionMap", promotionMap);
		
		
		
		return "/manager/manager-event";
	}
	
	
	
	
	
	
	
	
	

}
