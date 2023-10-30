package semi.project.movieInsight.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import semi.project.movieInsight.member.dto.Member;
import semi.project.movieInsight.member.service.EmailService;

@Controller
@RequestMapping("/sendEmail")
public class EmailVerificationController {

	@Autowired
	private EmailService service;

	@GetMapping("/superEmail")
	@ResponseBody
	public int sendVerificationEmail(@RequestParam("memberEmail") String memberEmail) {
		return service.signUp(memberEmail);
		}

    @GetMapping("/checkAuthKey")
    @ResponseBody
    public int checkAuthKey(@RequestParam Map<String, Object> paramMap){

    	System.out.println(paramMap); // {inputKey=wc3rxG, email=knbdh@nate.com}
        
        return service.checkAuthKey(paramMap);
    }
    
	
	
}
