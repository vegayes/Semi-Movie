package semi.project.movieInsight.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import semi.project.movieInsight.member.service.FindService;



@RequestMapping("/find")
@Controller
public class FindController {
	
	@Autowired
	private FindService service;
	
	
	// 아이디 찾기 페이지
	@GetMapping("/findID")
	public String Id() {
		
		return "member/find_id";
	}
	

	// 비밀번호 찾기 페이지
	@GetMapping("/findPW")
	public String Pw() {
		
		return "member/find_pw";
	}
	
	
	// 아이디 찾기 이메일
	@PostMapping("/findID")
	public String findId(String email, Model model) {
		
		//@RequestParam(value="memberEmail", required=true) 
		
	  String result= service.findId(email);
	  
	  if(result!=null) {
		  	
		  	// 아이디 전송
		   service.sendEmail(result, email);
		  
		   model.addAttribute("message", "아이디가 이메일로 발송되었습니다.");
		   
		   
	  }else {
		  
		    model.addAttribute("message", "입력한 이메일 주소에 해당하는 사용자를 찾을 수 없습니다.");
		    service.sendEmail(result, email);
		 
	  }

	   return "member/find_id";
	}

	
	
	//비밀번호 찾기 
	
//	@PostMapping("/findPW")
//	public int findPW(String email, String pass, Model model) {
//		
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("email", email);
//		map.put("Id", pass);
//		
//			int result=service.findPW(map);
//		
//			  if(result!=0) {
//				  
//				   service.sendEmail(result, email);
//				  
//				   model.addAttribute("message", "새로운 비밀번호가 이메일로 발송되었습니다.");
//				   
//				   
//			  }else {
//				  
//				    model.addAttribute("message", "해당하는 사용자를 찾을 수 없습니다.");
//				    service.sendEmail(result, email);
//				 
//			  }
//			
//			
//		 return "member/find_pw";
//	}
//	
	
	

	
}
