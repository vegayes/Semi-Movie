package semi.project.movieInsight.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PolicyController {
	
	
	@GetMapping("/policy")
	public String policy() {
		
		return "policy/policy";
	}
	

	

}
