package semi.project.movieInsight.main;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import semi.project.movieInsight.cinema.dto.Cinema;
import semi.project.movieInsight.main.service.StaffKindService;

//@RequestMapping("/")
@Controller
public class StaffKindController {
	

	private StaffKindService service;
	
	//@GetMapping("/movie")
	public String cinemaStaff(Model model) {
		
		List<Cinema> cinemaStaff = service.cinemaStaff();
		
	    model.addAttribute("cinemaStaff", cinemaStaff);
		
	    System.out.println("ccccccccccccccccccccccccc : "+cinemaStaff);
	    
		  return "movie/home-page";
		
	}

}
