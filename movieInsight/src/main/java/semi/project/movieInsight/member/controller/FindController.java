//package semi.project.movieInsight.member.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import semi.project.movieInsight.member.service.FindService;
//
//
//
//@RequestMapping("/find")
//@Controller
//public class FindController {
//	
//	@Autowired
//	private FindService service;
//	
//	// 아이디 찾기 페이지
//	@GetMapping("/id")
//	public String Id() {
//		
//		return "member/find_id";
//	}
//	
//
//	// 비밀번호 찾기 페이지
//	@GetMapping("/pw")
//	public String Pw() {
//		
//		return "member/find_pw";
//	}
//	
//	
//	// 아이디 찾기 이메일
//	@ResponseBody
//	@PostMapping("/findId")
//	public int findId(String email) {
//		
//		
//		return service.findId(email,"아이디찾기");
//		
//	}
//	
//	
//	
//	
//
//	
//}
