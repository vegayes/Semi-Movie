package semi.project.movieInsight.main;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {
	
	
	@RequestMapping("/")
	public String test1() {


		return "redirect:/movie";

	}

	
	@GetMapping("/movie")
	public String movieMain(Model model) {
		
		System.out.println("영화 메인페이지 이동");

		model.addAttribute("pageType","movie");
		
		return "movie/home-page";
	}




	@GetMapping("/cinema")
	public String cinemaMain(Model model) {
		
		System.out.println("영화관 메인페이지 이동");
		
		 model.addAttribute("pageType", "cinema");
		
		return "cinema/cinema-homepage";
	}
	
	
	
}


