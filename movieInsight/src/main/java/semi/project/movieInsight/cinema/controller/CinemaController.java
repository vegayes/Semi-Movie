package semi.project.movieInsight.cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.cinema.service.CinemaService;

@Controller
@RequestMapping("/cinema")
public class CinemaController {
    
	@Autowired
	private CinemaService service;
	
	public String cinemaMain() {
		
		System.out.println("메인페이지 이동");
		
		return "cinema/cinema-homepage";
	}
	

	@GetMapping("/search")
    public String searchCinema(String cinemaQuery, Model model) {
		
		System.out.println("search 페이지 들어옴");
		
		System.out.println("검색 내용 : " + cinemaQuery);
		
		// 검색한 영화 목록 조회 서비스 호출
		List<Cinema> cinemaList = service.searchCinemaList(cinemaQuery);

		System.out.println("cinemaList : " + cinemaList);
		
		model.addAttribute("cinemaQuery", cinemaQuery);
		model.addAttribute("cinemaList", cinemaList);
		
        return "cinema/search-cinema";
    }
	

}

