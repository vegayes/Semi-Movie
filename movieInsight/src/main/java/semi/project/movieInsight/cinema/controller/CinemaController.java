package semi.project.movieInsight.cinema.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
	


	@GetMapping("/search")
    public String searchCinema(String cinemaQuery, Model model ,HttpServletRequest request , Cinema cinema) {
		
		System.out.println("search 페이지 들어옴");
		
		//System.out.println("검색 내용 : " + cinemaQuery);
		List<Cinema> cinemaList  = new ArrayList<Cinema>();
		// 검색한 영화 목록 조회 서비스 호출
		List<Cinema> selectCinema = service.searchCinemaList(cinemaQuery);

		//System.out.println("cinemaList : " + cinemaList);
		
		for (Cinema getCinema : selectCinema) {
		    String cinemaName = getCinema.getCinemaName();

		    float cinemaGrade = service.cinemaGrade(cinemaName);

		    // 가져온 평점을 Cinema 객체에 설정
		    getCinema.setSumCinemaGrade(cinemaGrade);
		    cinemaList.add(getCinema);
		}
		
		model.addAttribute("cinemaQuery", cinemaQuery);
		model.addAttribute("cinemaList", cinemaList);
		
		
		
        return "cinema/search-cinema";
    }
	
	

	}
	



